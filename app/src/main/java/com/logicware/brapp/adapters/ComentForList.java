package com.logicware.brapp.adapters;

/**
 * esta clase representa los datos que maneja la lista del adaptador
 * sobre los comentarios
 */
public class ComentForList {
    private String nombre;
    private String comentarios;
    private String Calificacion;

    /**
     * Nombre: comentForList
     * Entradas: nombre, comentarios y calificacion
     * Salidas: -
     * Descripcion: constructor
     */
    public ComentForList(String nombre, String comentarios, String calificacion) {
        this.nombre = nombre;
        this.comentarios = comentarios;
        Calificacion = calificacion;
    }
    /**
     * Nombre: getNombre
     * Entradas: -
     * Salidas: nombre
     * Descripcion: retorna el nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Nombre: setNombre
     * Entradas: nombre
     * Salidas: -
     * Descripcion: modifica el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Nombre: getComentarios
     * Entradas: -
     * Salidas: comentarios
     * Descripcion: retorna los comentarios
     */
    public String getComentarios() {
        return comentarios;
    }
    /**
     * Nombre: setComentario
     * Entradas: comentarios
     * Salidas: -
     * Descripcion: modifica el comentario
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    /**
     * Nombre: getCalificacion
     * Entradas: -
     * Salidas: calificacion
     * Descripcion: retorna la calificacino
     */
    public String getCalificacion() {
        return Calificacion;
    }
    /**
     * Nombre: setCalificacion
     * Entradas: calificacino
     * Salidas: -
     * Descripcion: modifica la calificacion
     */
    public void setCalificacion(String calificacion) {
        Calificacion = calificacion;
    }
}
