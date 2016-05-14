package com.logicware.brapp.meta;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by ASUS on 5/14/2016.
 */
public class Usuario implements Serializable {

    private Long idUsuario;

    private String nombre;
    private String correo;
    private String telefono;
    private String token;
    private boolean link_facebook;
    private String contrasena;
    private String tipo;
    private byte[] imagen;

    private Collection<Establecimiento> establecimientos;

    private Collection<Evento> eventos;

    private Collection<Reserva> reservas;

    private Collection<ComentarioYCalificacion> comentariosYCalificaciones;

    /**
     * @return the id
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the id to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the link_facebook
     */
    public boolean isLink_facebook() {
        return link_facebook;
    }

    /**
     * @param link_facebook the link_facebook to set
     */
    public void setLink_facebook(boolean link_facebook) {
        this.link_facebook = link_facebook;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return
     */
    public Collection<Establecimiento> getEstablecimientos(){
        return this.establecimientos;
    }

    /**
     * @param establecimientos the establecimientos to set
     */
    public void setEstablecimientos(Collection<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    /**
     * @return the eventos
     */
    public Collection<Evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(Collection<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the reservas
     */
    public Collection<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the comentariosYCalificaciones
     */
    public Collection<ComentarioYCalificacion> getComentariosYCalificaciones() {
        return comentariosYCalificaciones;
    }

    /**
     * @param comentariosYCalificaciones the comentariosYCalificaciones to set
     */
    public void setComentariosYCalificaciones(Collection<ComentarioYCalificacion> comentariosYCalificaciones) {
        this.comentariosYCalificaciones = comentariosYCalificaciones;
    }
}