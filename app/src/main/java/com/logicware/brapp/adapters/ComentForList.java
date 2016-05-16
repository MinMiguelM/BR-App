package com.logicware.brapp.adapters;

/**
 * Created by Camilo Andres on 16/05/2016.
 */
public class ComentForList {
    private String nombre;
    private String comentarios;
    private String Calificacion;


    public ComentForList(String nombre, String comentarios, String calificacion) {
        this.nombre = nombre;
        this.comentarios = comentarios;
        Calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String calificacion) {
        Calificacion = calificacion;
    }
}
