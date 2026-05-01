package com.app.priorix.config;

import com.app.priorix.Repository.UsuarioRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga el usuario desde la base de datos y construye el objeto UserDetails para Spring Security.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository.findByNombreUsuario(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getNombreUsuario())
                        .password(user.getPassword())
                        .roles(user.getRol().name().replace("ROLE_", "")) // Elimina el prefijo ROLE_
                        .build())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                
                .requestMatchers("/login", "/css/**", "/error").permitAll()

                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/enfermero/**").hasRole("ENFERMERO")
                .requestMatchers("/medico/**").hasRole("MEDICO")
                .requestMatchers("auth/login").permitAll()

                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            

            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/redirectByRole", true) 
                .permitAll()
            )

            // Configuración del logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // Deshabilita CSRF para formularios simples (opcional si usas Thymeleaf correctamente)
            .csrf(csrf -> csrf.disable())

            .sessionManagement((session) -> session
                .invalidSessionUrl("/login?invalid-session")
                .maximumSessions(1)
                .expiredUrl("/login?session-expired")
            );

            return http.build();

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
}
