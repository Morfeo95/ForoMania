package com.francisco.foromania.controller;

import com.francisco.foromania.domain.usuario.*;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private  PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosDeRegistroUsuario datosDeRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = new Usuario(datosDeRegistroUsuario);
        usuario.setPerfil(perfilRepository.save(new Perfil(usuario)));
        repository.save(usuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        URI uri =uriComponentsBuilder.path("/curso/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaUsuario);
    }

    @GetMapping
    public  ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 5)Pageable pagnacion){
        return ResponseEntity.ok(repository.findByActivoTrue(pagnacion).map(DatosListadoUsuario::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (!usuarioOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOptional.get();
        usuario.desactivarUsuario();
        var datos = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> detalleUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (!usuarioOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOptional.get();
        var datos = new  DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datos);
    }


}
