package com.francisco.foromania.domain.respuesta;

import com.francisco.foromania.domain.topico.Topico;
import com.francisco.foromania.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;


@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topico topicoRespodido;

    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autorRespuesta;

    private boolean solucion;
    private boolean visible;

    public Respuesta(@Valid DatosRegistroRespuesta datos) {
        this.solucion = false;
        this.visible = true;
        this.mensaje = datos.mensaje();
        this.fecha = LocalDateTime.now();
    }
    public Respuesta(){}


    public void setAutor(Usuario autor) {
        this.autorRespuesta = autor;
    }

    public void setTopico(Topico topico) {
        this.topicoRespodido = topico;
    }

    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Topico getTopicoRespodido() {
        return topicoRespodido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Usuario getAutorRespuesta() {
        return autorRespuesta;
    }

    public boolean isSolucion() {
        return solucion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void actualizar(@Valid DatosDeActualizacionRespuesta datosActualizacion) {
        this.mensaje = datosActualizacion.mensaje();
        this.fecha=LocalDateTime.now();
    }

    public void ocultar() {
        this.visible=false;
    }
}
