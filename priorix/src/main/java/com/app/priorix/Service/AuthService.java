package com.app.priorix.Service;

import java.time.LocalDateTime;
import java.util.Random;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.priorix.Repository.OtpCodeRepository;
import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.model.entity.OtpCode;
import com.app.priorix.model.entity.Usuario;
import com.app.priorix.Service.UsuarioService;

@Service
public class AuthService {

    private final OtpCodeRepository otpCodeRepository;
    private final UsuarioRepository usuarioRepository;
    private final JavaMailSender mailSender;
    

    public AuthService(OtpCodeRepository otpCodeRepository, 
                       UsuarioRepository usuarioRepository, 
                       JavaMailSender mailSender) {
        this.otpCodeRepository = otpCodeRepository;
        this.usuarioRepository = usuarioRepository;
        this.mailSender = mailSender;
    }

    public void registrarYEnviarCodigo(String email) {
        // 1. Crear el usuario en estado "inactivo" o simplemente registrarlo
        if (usuarioRepository.findByEmail(email).isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setEmail(email);
            usuarioRepository.save(newUser);
        }

        // 2. Generar código de 6 dígitos
        String code = String.format("%06d", new Random().nextInt(999999));

        // 3. Guardar en MongoDB con expiración (ej. 10 minutos)
        OtpCode otp = new OtpCode();
        otp.setEmail(email);
        otp.setCode(code);
        otp.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        otpCodeRepository.save(otp);

        // 4. Enviar el correo
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Código de Autenticación");
        message.setText("Tu código de acceso es: " + code);
        mailSender.send(message);
    }

    
}
