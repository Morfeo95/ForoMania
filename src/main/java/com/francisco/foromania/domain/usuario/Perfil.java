package com.francisco.foromania.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "perfil")
@Table(name = "perfiles")
public class Perfil {

    public Perfil(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Perfil(Usuario usuario) {
        this.nombre = usuario.getNombre();
    }
}
