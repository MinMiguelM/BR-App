package com.logicware.brapp.adapters;

/**
 * Created by Camilo Andres on 22/05/2016.
 */
public class PromocionForList {

    private String titulo;
    private String fechaInicio;
    private String fechaFin;
    private String descripcion;

    public PromocionForList(String titulo, String fechaInicio, String fechaFin, String descripcion) {
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
