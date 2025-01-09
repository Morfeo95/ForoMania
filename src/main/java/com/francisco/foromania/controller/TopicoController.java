package com.francisco.foromania.controller;


import com.francisco.foromania.domain.curso.Curso;
import com.francisco.foromania.domain.curso.CursoRepository;
import com.francisco.foromania.domain.topico.*;
import com.francisco.foromania.domain.usuario.Usuario;
import com.francisco.foromania.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosDeRegistroTopico datosDeRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = new Topico(datosDeRegistroTopico);
        Usuario usuario = usuarioRepository.getReferenceById(datosDeRegistroTopico.autor());
        Curso curso = cursoRepository.getReferenceById(datosDeRegistroTopico.curso());
        topico.setUsuaro(usuario);
        topico.setCurso(curso);
        repository.save(topico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI uri =uriComponentsBuilder.path("/curso/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity <Page<DatosListadoTopico>> listarTopicos(@PageableDefault(sort = "curso")Pageable paginacion){
        return ResponseEntity.ok(repository.findByVisibleTrue(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<DatosRespuestaTopico> detallaTopico(@PathVariable Long id){
        Optional <Topico> topicoOptional = repository.findById(id);
        if (!topicoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Topico topico = topicoOptional.get();
        var datos = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosDeActualizacionTopico datosDeActualizacionTopico){
        Optional <Topico> topicoOptional = repository.findById(id);
        if (!topicoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var topico = topicoOptional.get();
        var curso = cursoRepository.getReferenceById(datosDeActualizacionTopico.curso());
        topico.actualizar(datosDeActualizacionTopico);
        if (topico.getCurso()!= null){
            topico.setCurso(curso);
        }
        var datos = new  DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id){
        Optional<Topico> topicoOptional = repository.findById(id);
        if (!topicoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var topico = topicoOptional.get();
        topico.ocultar();
        var datos = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datos);
    }
}
