package com.backend.portfolio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String imagen;
    private Long porcentaje;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Skill() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public long getPorcentaje() {
        return this.porcentaje;
    }

    public void setPorcentaje(long porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Skill id(long id) {
        setId(id);
        return this;
    }

    public Skill nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Skill imagen(String imagen) {
        setImagen(imagen);
        return this;
    }

    public Skill porcentaje(long porcentaje) {
        setPorcentaje(porcentaje);
        return this;
    }

    public Skill usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nombre='" + getNombre() + "'" +
                ", imagen='" + getImagen() + "'" +
                ", porcentaje='" + getPorcentaje() + "'" +
                "}";
    }

}
