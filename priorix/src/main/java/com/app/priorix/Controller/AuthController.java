package com.app.priorix.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.priorix.Dto.AuthResponse;
import com.app.priorix.Dto.LoginRequest;
import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.Service.JwtService;



@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
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


}
