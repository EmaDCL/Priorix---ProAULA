package com.app.priorix.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.priorix.Dto.AuthResponse;
import com.app.priorix.Dto.LoginRequest;
import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.Service.AuthService;
import com.app.priorix.Service.AuthVerificationService;
import com.app.priorix.Service.JwtService;



@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    
    private final AuthService authService;
    private final AuthVerificationService authVerificationService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioRepository usuarioRepository, AuthService authService, AuthVerificationService authVerificationService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.authService = authService;
        this.authVerificationService = authVerificationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request) {
        // 1. Delegar la autenticación al framework
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Si el paso anterior no lanza excepción, las credenciales son válidas. Recuperamos el documento.
        var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();

        // 3. Generamos el token usando nuestro motor criptográfico
        var jwtToken = jwtService.generateToken(user);

        // 4. Retornamos el DTO
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }

    @PostMapping("/enviar-codigo")
    public ResponseEntity<String> enviarCodigo(@RequestParam String email) {
        try {
            authService.registrarYEnviarCodigo(email);
            return ResponseEntity.ok("Código enviado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/verificar")
    public ResponseEntity<?> verificarCodigo(@RequestParam String email, @RequestParam String code) {
        try {
            // 1. Llamamos al método actualizado. 
            // Como es 'void', si la contraseña es incorrecta o expiró, saltará directamente al 'catch'.
            // Si pasa a la siguiente línea sin saltar, significa que fue un éxito.
            authVerificationService.activarCuenta(email, code);

            // 2. Si el PIN es correcto, recuperamos el usuario y le damos su JWT
            var user = usuarioRepository.findByEmail(email).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            
            return ResponseEntity.ok(new AuthResponse(jwtToken));

        } catch (RuntimeException e) {
            // Atrapa los errores como "Código inválido" o "El código ha expirado"
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
