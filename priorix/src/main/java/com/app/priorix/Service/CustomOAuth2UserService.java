package com.app.priorix.Service;

import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.model.entity.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioRepository usuarioRepository;

    public CustomOAuth2UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. Obtener los datos del usuario desde Google
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        // 2. Buscar si el correo existe en MongoDB
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new OAuth2AuthenticationException(
                        new OAuth2Error("user_not_found"), "Este correo no está registrado en el sistema."));

        // 3. Validar si ya activó su cuenta con el código OTP
        if (!usuario.isActivo()) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("user_inactive"), "Debes activar tu cuenta primero con el código de verificación.");
        }

        // 4. Mapear el rol de MongoDB a la sesión de Spring Security
        String nombreRol = usuario.getRol().name();
        if (!nombreRol.startsWith("ROLE_")) {
            nombreRol = "ROLE_" + nombreRol;
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(nombreRol);

        // Retornamos el usuario autenticado con sus roles correspondientes de tu BD
        return new DefaultOAuth2User(
                List.of(authority),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}
