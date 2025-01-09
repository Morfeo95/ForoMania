package com.francisco.foromania.domain.usuario;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    public Usuario(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RANGO rango;

    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    public Usuario(@Valid DatosDeRegistroUsuario datosDeRegistroUsuario) {
        this.activo = true;
        this.nombre = datosDeRegistroUsuario.nombre();
        this.email = datosDeRegistroUsuario.email();
        this.password = datosDeRegistroUsuario.password();
        this.rango = datosDeRegistroUsuario.rango();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public RANGO getRango() {
        return rango;
    }

    public boolean isActivo() {
        return activo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void desactivarUsuario() {
        this.activo = false;
    }
}
