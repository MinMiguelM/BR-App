package com.logicware.brapp.Adapters;

/**
 * Created by Camilo Andres on 13/05/2016.
 */
public class ItemListComentarios {

    private String nombreUsuario;
    private String comentarios;
    private String calificacion;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
