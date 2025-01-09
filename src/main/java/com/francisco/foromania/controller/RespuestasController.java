package com.francisco.foromania.controller;


import com.francisco.foromania.domain.respuesta.*;
import com.francisco.foromania.domain.topico.TopicoRepository;
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
@RequestMapping("/respuestas")
public class RespuestasController {
    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaRespuestas> registrarNuevaRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        var respuesta = new Respuesta(datosRegistroRespuesta);
        var topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topicoRespodidoId());
        var autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.autorId());
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        topico.setRespueta(respuesta);
        repository.save(respuesta);
        DatosRespuestaRespuestas datos = new DatosRespuestaRespuestas(respuesta);
        URI uri =uriComponentsBuilder.path("/curso/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(datos);
    }

    @GetMapping
    public ResponseEntity <Page<DatosListadoRespuestas>> listarRespuestas(@PageableDefault(size = 5)Pageable paginacion){
        return ResponseEntity.ok(repository.findByVisibleTrue(paginacion).map(DatosListadoRespuestas::new));
    }

    @GetMapping("/{id}")
    public  ResponseEntity detallarRespuesta(@PathVariable Long id){
        Optional<Respuesta> respuestaOptional = repository.findById(id);
        if (!respuestaOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var respuesta = respuestaOptional.get();
        var datos = new DatosRespuestaRespuestas(respuesta);
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<DatosRespuestaRespuestas> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosDeActualizacionRespuesta datosActualizacion){
        Optional<Respuesta> respuestaOptional = repository.findById(id);
        if (!respuestaOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var respuesta = respuestaOptional.get();
        respuesta.actualizar(datosActualizacion);
        var datos = new DatosRespuestaRespuestas(respuesta);
        return  ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity <DatosRespuestaRespuestas> ocultarTopico(@PathVariable Long id){
        Optional<Respuesta> respuestaOptional = repository.findById(id);
        if (!respuestaOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var respuesta = respuestaOptional.get();
        respuesta.ocultar();
        var datos = new DatosRespuestaRespuestas(respuesta);
        return ResponseEntity.ok(datos);
    }
}
