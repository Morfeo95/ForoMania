package com.francisco.foromania.domain.usuario;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String email,
        RANGO rango
) {
    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRango());
    }

}
