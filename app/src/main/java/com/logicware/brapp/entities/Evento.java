package com.logicware.brapp.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 5/14/2016.
 */
public class Evento implements Serializable {

    private Long idEvento;
    private String fecha_inicio;
    private String fecha_fin;
    private String descripcion;
    private String titulo;

    private Usuario usuario;

    private Establecimiento establecimiento;

    /**
     * @return the idEvento
     */
    public Long getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the fecha_inicio
     */
    public String getFecha_inicio() {
        return fecha_inicio;
    }
    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    /**
     * @return the fecha_fin
     */
    public String getFecha_fin() {
        return fecha_fin;
    }
    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the usuario
     */
	/*public Usuario getUsuario() {
		return usuario;
	}*/

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the establecimiento
     */
	/*public Establecimiento getEstablecimiento() {
		return establecimiento;
	}*/

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
