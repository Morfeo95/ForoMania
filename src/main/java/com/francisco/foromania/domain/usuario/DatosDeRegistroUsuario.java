package com.francisco.foromania.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDeRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
                @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        RANGO rango
) {
}
