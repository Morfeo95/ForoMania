package com.francisco.foromania.domain.topico;

import com.francisco.foromania.domain.curso.Curso;
import com.francisco.foromania.domain.respuesta.Respuesta;
import com.francisco.foromania.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;


@Entity(name = "topico")
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    private LocalDateTime fecha;
    private boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "respuestas")
    private Respuesta respuesta;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDePublicacion() {
        return fecha;
    }

    public boolean isVisible() {
        return visible;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public Topico(){}

    public Topico(@Valid DatosDeRegistroTopico datosDeRegistroTopico) {
        this.visible=true;
        this.titulo = datosDeRegistroTopico.titulo();
        this.mensaje = datosDeRegistroTopico.mensaje();
        this.fecha = datosDeRegistroTopico.fechaDePublicacion();
    }

    public void setUsuaro(Usuario usuario) {
        this.autor = usuario;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void actualizar(DatosDeActualizacionTopico datos) {
        if (datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
        this.fecha = LocalDateTime.now();
    }

    public void ocultar() {
        this.visible = false;
    }

    public void setRespueta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
}
