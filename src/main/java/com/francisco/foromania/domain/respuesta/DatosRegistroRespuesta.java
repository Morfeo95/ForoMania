package com.francisco.foromania.domain.respuesta;


import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotNull
        String mensaje,
        @NotNull
        Long topicoRespodidoId,
        @NotNull
        Long autorId
) {
}
