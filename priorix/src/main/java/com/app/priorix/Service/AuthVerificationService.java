package com.app.priorix.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.app.priorix.Repository.OtpCodeRepository;
import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.model.entity.OtpCode;
import com.app.priorix.model.entity.Usuario;

@Service
public class AuthVerificationService {

    private final OtpCodeRepository otpCodeRepository;
    private final UsuarioRepository usuarioRepository;

    public AuthVerificationService(OtpCodeRepository otpCodeRepository, UsuarioRepository usuarioRepository) {
        this.otpCodeRepository = otpCodeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void activarCuenta(String email, String code) {
        // 1. Buscar el código en MongoDB
        OtpCode otp = otpCodeRepository.findByEmailAndCode(email, code)
                .orElseThrow(() -> new RuntimeException("Código inválido o correo incorrecto"));

        // 2. Verificar si expiró
        if (otp.getExpirationTime().isBefore(LocalDateTime.now())) {
            otpCodeRepository.delete(otp); // Borramos el código inservible
            throw new RuntimeException("El código ha expirado");
        }

        // 3. Buscar al usuario y activarlo
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en el sistema"));
                
        usuario.setActivo(true); // ¡Aquí ocurre la magia de la activación!
        usuarioRepository.save(usuario);

        // 4. Eliminar el código usado
        otpCodeRepository.delete(otp);
    }
}
