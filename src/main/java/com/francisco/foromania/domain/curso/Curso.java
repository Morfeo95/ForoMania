package com.francisco.foromania.domain.curso;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Entity(name = "Curso")
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(@Valid DatosDeRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

    public Curso(){}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
