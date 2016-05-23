package com.logicware.brapp.adapters;

/**
 * esta clase representa los datos que maneja la lista del adaptador
 * sobre las promociones
 */
public class PromocionForList {

    private String titulo;
    private String fechaInicio;
    private String fechaFin;
    private String descripcion;
    /**
     * Nombre: PromocionForList
     * Entradas: titulo, fecha de inicioy calificacion
     * Salidas: -
     * Descripcion: constructor
     */
    public PromocionForList(String titulo, String fechaInicio, String fechaFin, String descripcion) {
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }
    /**
     * Nombre: getTitulo
     * Entradas: -
     * Salidas: titulo
     * Descripcion: retorna el titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Nombre: setTitulo
     * Entradas: titulo
     * Salidas: -
     * Descripcion: modifica el titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Nombre: getFechaInicio
     * Entradas: -
     * Salidas: fecha inicio
     * Descripcion: retorna la fecha de inicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Nombre: setFechaInicio
     * Entradas: fecha de inicio
     * Salidas: -
     * Descripcion: modifica la fecha de inicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Nombre: getFechaFin
     * Entradas: -
     * Salidas: fecha fin
     * Descripcion: retorna la fecha fin
     */
    public String getFechaFin() {
        return fechaFin;
    }
    /**
     * Nombre: setFechaFin
     * Entradas: fecha fin
     * Salidas: -
     * Descripcion: modifica la fecha fin
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Nombre: getDescripcion
     * Entradas: -
     * Salidas: descripcion
     * Descripcion: retorna la descripcion
     */    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Nombre: setDescripcion
     * Entradas: descripcion
     * Salidas: -
     * Descripcion: modifica la descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
