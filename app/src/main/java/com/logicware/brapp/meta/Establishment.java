package com.logicware.brapp.meta;

/**
 * Created by miguel on 30/04/16.
 */
public class Establishment {

    private Long id;
    private User user;
    private String nombre;
    private String ubicacion;
    private double calificacionPromedio;
    private String tematica;
    private String descripcion;
    private String horario;

    public Establishment(Long id, User user, String nombre, String ubicacion, double calificacionPromedio, String tematica, String descripcion, String horario) {
        this.id = id;
        this.user = user;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.calificacionPromedio = calificacionPromedio;
        this.tematica = tematica;
        this.descripcion = descripcion;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
