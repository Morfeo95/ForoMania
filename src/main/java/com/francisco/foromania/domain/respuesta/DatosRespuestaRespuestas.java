package com.francisco.foromania.domain.respuesta;


import java.time.LocalDateTime;

public record DatosRespuestaRespuestas(
        String mensaje,
        String topicoRespodido,
        String autorRespuesta,
        LocalDateTime fecha,
        boolean solucion
) {
    public DatosRespuestaRespuestas(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopicoRespodido().getTitulo(), respuesta.getAutorRespuesta().getNombre(), respuesta.getFecha(), respuesta.isSolucion());
    }
}
