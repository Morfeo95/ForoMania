package com.francisco.foromania.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDePublicacion,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDePublicacion(),topico.getAutor().getNombre(),topico.getCurso().getNombre());
    }
}
