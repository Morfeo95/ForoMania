package com.francisco.foromania.controller;

import com.francisco.foromania.domain.curso.Curso;
import com.francisco.foromania.domain.curso.CursoRepository;
import com.francisco.foromania.domain.curso.DatosDeRegistroCurso;
import com.francisco.foromania.domain.curso.DatosRespuestaCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    public ResponseEntity <DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosDeRegistroCurso datosDeRegistroCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = repository.save(new Curso(datosDeRegistroCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso);
        URI uri =uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaCurso);
    }
}
