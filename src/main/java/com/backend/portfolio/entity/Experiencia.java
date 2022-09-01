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
@Table(name = "experiencia")
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String puesto;
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

    public Experiencia() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getnombre() {
        return this.nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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

    public void setFechaInicio(java.sql.Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(java.sql.Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Experiencia id(long id) {
        setId(id);
        return this;
    }

    public Experiencia nombre(String nombre) {
        setnombre(nombre);
        return this;
    }

    public Experiencia puesto(String puesto) {
        setPuesto(puesto);
        return this;
    }

    public Experiencia descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    public Experiencia imagen(String imagen) {
        setImagen(imagen);
        return this;
    }

    public Experiencia fechaInicio(java.sql.Date fechaInicio) {
        setFechaInicio(fechaInicio);
        return this;
    }

    public Experiencia fechaFin(java.sql.Date fechaFin) {
        setFechaFin(fechaFin);
        return this;
    }

    public Experiencia usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nombre='" + getnombre() + "'" +
                ", puesto='" + getPuesto() + "'" +
                ", descripcion='" + getDescripcion() + "'" +
                ", imagen='" + getImagen() + "'" +
                ", fechaInicio='" + getFechaInicio() + "'" +
                ", fechaFin='" + getFechaFin() + "'" +
                "}";
    }

}
