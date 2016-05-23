package com.logicware.brapp.adapters;

/**
 * Created by Camilo Andres on 22/05/2016.
 */
public class ReservesForList {

    private String fecha_reserva;
    private String estado;
    private String cantidad_personas;

    public ReservesForList(String fecha_reserva, String estado, String cantidad_personas) {
        this.fecha_reserva = fecha_reserva;
        this.estado = estado;
        this.cantidad_personas = cantidad_personas;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCantidad_personas() {
        return cantidad_personas;
    }

    public void setCantidad_personas(String cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }
}
