package com.backend.portfolio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudio")
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;
    private String institucion;
    private String descripcion;
    private String imagen;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Estudio() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return this.institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estudio id(long id) {
        setId(id);
        return this;
    }

    public Estudio titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public Estudio institucion(String institucion) {
        setInstitucion(institucion);
        return this;
    }

    public Estudio descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    public Estudio imagen(String imagen) {
        setImagen(imagen);
        return this;
    }

    public Estudio fechaInicio(Date fechaInicio) {
        setFechaInicio(fechaInicio);
        return this;
    }

    public Estudio fechaFin(Date fechaFin) {
        setFechaFin(fechaFin);
        return this;
    }

    public Estudio usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", titulo='" + getTitulo() + "'" +
                ", institucion='" + getInstitucion() + "'" +
                ", descripcion='" + getDescripcion() + "'" +
                ", imagen='" + getImagen() + "'" +
                ", fechaInicio='" + getFechaInicio() + "'" +
                ", fechaFin='" + getFechaFin() + "'" +
                "}";
    }

}
