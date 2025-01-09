package com.francisco.foromania.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuestas(
        Long id,
        String mensaje,
        String topicoRespodido,
        String autorRespuesta,
        LocalDateTime fecha,
        boolean solucion
) {
    public DatosListadoRespuestas(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopicoRespodido().getTitulo(), respuesta.getAutorRespuesta().getNombre(), respuesta.getFecha(), respuesta.isSolucion());
    }
}
