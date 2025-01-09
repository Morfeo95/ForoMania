package com.francisco.foromania.domain.topico;

import com.francisco.foromania.domain.curso.Curso;
import com.francisco.foromania.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaDePublicacion,
        String autor,
        String curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaDePublicacion(),topico.getAutor().getNombre(),topico.getCurso().getNombre());
    }
}
