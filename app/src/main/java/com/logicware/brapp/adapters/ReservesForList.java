package com.logicware.brapp.adapters;

/**
 * esta clase representa los datos que maneja la lista del adaptador
 * sobre las reservas
 */
public class ReservesForList {

    private String fecha_reserva;
    private String estado;
    private String cantidad_personas;
    /**
     * Nombre: ReservesForList
     * Entradas: fecha de reserva, fecha de inicioy calificacion
     * Salidas: -
     * Descripcion: constructor
     */
    public ReservesForList(String fecha_reserva, String estado, String cantidad_personas) {
        this.fecha_reserva = fecha_reserva;
        this.estado = estado;
        this.cantidad_personas = cantidad_personas;
    }
    /**
     * Nombre: getFecha_reserva
     * Entradas: -
     * Salidas: fecha reserva
     * Descripcion: retorna el titulo
     */
    public String getFecha_reserva() {
        return fecha_reserva;
    }
    /**
     * Nombre: setFecha_reserva
     * Entradas: fecha_reserva
     * Salidas: -
     * Descripcion: modifica la fecha de reserva
     */
    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }
    /**
     * Nombre: getEstado
     * Entradas: -
     * Salidas: estado
     * Descripcion: retorna el estado
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Nombre: setEstado
     * Entradas: estado
     * Salidas: -
     * Descripcion: modifica el estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Nombre: getCantidad_Personas
     * Entradas: -
     * Salidas: cantidad de personas
     * Descripcion: la cantidad de personas
     */
    public String getCantidad_personas() {
        return cantidad_personas;
    }
    /**
     * Nombre: setCantidad_personas
     * Entradas: cantidad de personas
     * Salidas: -
     * Descripcion: modifica la cantidad de personas
     */
    public void setCantidad_personas(String cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }
}
