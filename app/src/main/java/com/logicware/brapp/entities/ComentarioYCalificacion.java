package com.logicware.brapp.entities;

import java.io.Serializable;

/**
 * Created by ASUS on 5/14/2016.
 */
public class ComentarioYCalificacion implements Serializable {

    private Long idComentarioYCalificacion;

    private String descripcion;
    private Integer calificacion;

    private Usuario usuario;

    private Establecimiento establecimiento;

    /**
     * @return the idComentarioYCalificacion
     */
    public Long getIdComentarioYCalificacion() {
        return idComentarioYCalificacion;
    }

    /**
     * @param idComentarioYCalificacion the idComentarioYCalificacion to set
     */
    public void setIdComentarioYCalificacion(Long idComentarioYCalificacion) {
        this.idComentarioYCalificacion = idComentarioYCalificacion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the usuario
     */
	public Usuario getUsuario() {
		return usuario;
	}

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the establecimiento
     */
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
