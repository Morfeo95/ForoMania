package com.francisco.foromania.domain.usuario;

public record DatosRespuestaUsuario(
        String nombre,
        String rango
) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getRango().toString());
    }
}
