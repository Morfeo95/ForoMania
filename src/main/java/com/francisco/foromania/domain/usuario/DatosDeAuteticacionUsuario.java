package com.francisco.foromania.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosDeAuteticacionUsuario(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
