package com.francisco.foromania.domain.topico;

import java.time.LocalDateTime;

public record DatosDeActualizacionTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaDeActualizacion,
        Long curso
) {
}
