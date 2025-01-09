package com.francisco.foromania.domain.topico;

import com.francisco.foromania.domain.respuesta.Respuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record DatosDeRegistroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
                @PastOrPresent
        LocalDateTime fechaDePublicacion,
        @NotNull
        Long autor,
        @NotNull
        Long curso,
        Respuesta respuesta
) {
}
