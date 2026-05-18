package com.app.priorix.config;

import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.Service.CustomOAuth2UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    
    // 1. Inyectamos nuestro servicio de OAuth2
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, 
                          UserDetailsService userDetailsService,
                          CustomOAuth2UserService customOAuth2UserService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/error", "/activar").permitAll() // Liberamos /activar
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/enfermero/**").hasRole("ENFERMERO")
                .requestMatchers("/medico/**").hasRole("MEDICO")
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            
            // 2. REEMPLAZAMOS formLogin por oauth2Login
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login") // Tu página de login personalizada
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService) // Vincula tu lógica de MongoDB
                )
                .defaultSuccessUrl("/redirectByRole", true) // Mantiene tu redirección por roles
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
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
