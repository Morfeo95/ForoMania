package com.francisco.foromania.controller;

import com.francisco.foromania.domain.usuario.DatosDeAuteticacionUsuario;
import com.francisco.foromania.domain.usuario.Usuario;
import com.francisco.foromania.infra.security.DatosJWT;
import com.francisco.foromania.infra.security.TokeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokeService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid DatosDeAuteticacionUsuario datosAutenticacion){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(),datosAutenticacion.password());
        var usuarioAuth = authenticationManager.authenticate(authToken);
        var token = tokenService.generarToken((Usuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJWT(token));
    }
}
