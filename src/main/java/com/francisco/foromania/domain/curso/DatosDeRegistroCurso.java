package com.francisco.foromania.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDeRegistroCurso(

        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria
) {
}
