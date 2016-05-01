package com.logicware.brapp.meta;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 4/16/2016.
 * Representa a un usuario en el sistema
 */
@SuppressWarnings("serial")
public class User implements Serializable {

    private Long id; // Id que se le asigna automáticamente en el sistema
    private String nombre; // Nombre del usuario
    private String correo; // correo que le permite ingresar a la aplicacion
    private String password; // hace parte de las credenciales del usuario
    private String num_celular; // numero de contacto
    private String rol; // Rol que desempeña para nuestro sistema: USUARIO, CLIENTE O ADMINISTRADOR
    private String link_facebook; // True si tiene la cuenta asociada con facebook
    private String token_facebook; // El token que facebook genera para este usuario.
    private Collection<Establishment> establishment = new ArrayList<>();

    /**
     * Nombre de Método: User
     * Entradas: id, nombre, correo, password, num_celular, rol, link_facebook, token_Facebook
     * Salidas: void
     * Descripcion: constructor de la clase usuario
     */
    public User(Long id,String nombre, String correo, String password, String num_celular, String rol, String link_facebook, String token_facebook) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.num_celular = num_celular;
        this.rol = rol;
        this.link_facebook = link_facebook;
        this.token_facebook = token_facebook;
    }

    /**
     * Nombre de Método: getID
     * Entradas:
     * Salidas:  id
     * Descripcion: metodo que retona el id del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Nombre de Método: setID
     * Entradas: id
     * Salidas:
     * Descripcion: modifica el valor del id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Nombre: getNombre
     * Entradas: -
     * Salidas: cadena del nombre
     * Descripción: metodo que retorna el nombre del usuario
     */
    public String getNombre(){ return this.nombre; }

    /**
     * nombre: setNombre
     * Entradas: nombre nuevo
     * Salidas: -
     * Descripción: cambia el nombre del usuario.
     */
    public void setNombre(String nombre){ this.nombre = nombre; }

    /**
     * Nombre de Método: getCorreo
     * Entradas:
     * Salidas:  correo
     * Descripcion: retona el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Nombre de Método: setCorreo
     * Entradas: correo
     * Salidas:
     * Descripcion: modifica el correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Nombre de Método: getPassword
     * Entradas: password
     * Salidas:
     * Descripcion: modifica el password del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Nombre de Método: setPassword
     * Entradas:
     * Salidas: password
     * Descripcion: retorna el password del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Nombre de Método: getNum_celular
     * Entradas: numero de celular
     * Salidas:
     * Descripcion: retorna el numero de celular del usuario
     */
    public String getNum_celular() {
        return num_celular;
    }

    /**
     * Nombre de Método: setNum_celular
     * Entradas:
     * Salidas: numero de celular
     * Descripcion: modifica el numero de celular del usuario
     */
    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }

    /**
     * Nombre de Método: getRol
     * Entradas:
     * Salidas: rol
     * Descripcion: retorna el rol del usuario
     */
    public String getRol() {
        return rol;
    }

    /**
     * Nombre de Método: setRol
     * Entradas: rol
     * Salidas:
     * Descripcion: modifica el rol del usuario
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Nombre de Método: isLink_facebook
     * Entradas:
     * Salidas: boleano
     * Descripcion: verifica si es un link de facebook
     */
    public String isLink_facebook() {
        return link_facebook;
    }

    /**
     * Nombre de Método: setLink_facebook
     * Entradas: boleano
     * Salidas:
     * Descripcion: comprueba que sea un link de facebook y modifica el del usuario
     */
    public void setLink_facebook(String link_facebook) {
        this.link_facebook = link_facebook;
    }

    /**
     * Nombre de Método: getToken_facebook
     * Entradas:
     * Salidas: token de facebook
     * Descripcion: retorna el token de facebook
     */
    public String getToken_facebook() {
        return token_facebook;
    }

    /**
     * Nombre de Método: setToken_facebook
     * Entradas:
     * Salidas: token de facebook
     * Descripcion: modifica el token de facebook
     */
    public void setToken_facebook(String token_facebook) {
        this.token_facebook = token_facebook;
    }

    /**
     * Nombre: SerializeUser
     * Entradas: -
     * Salidas: un string en formato json
     * Descripción: serializa una instancia en formato json
     */
    public String serializeUser(){
        Gson userJson = new Gson();
        return userJson.toJson(this);
    }

    /**
     * Nombre: createUserFromJson
     * Entradas: string en formato json
     * Salidas: la instancia de usuario
     * Descripción: crea un usuario a partir de un archivo json
     */
    public static User createUserFromJson(String userJson){
        Gson objectJson = new Gson();
        return objectJson.fromJson(userJson, User.class);
    }

    /**
     * Nombre: getEstablishment
     * Entradas: -
     * Salidas: una coleccion de establecimientos asociados al usuario
     * Descripcion: Retorna los establecimientos que tiene asociado el usuario
     * @return
     */
    public Collection<Establishment> getEstablishment() {
        return establishment;
    }

    /**
     * Nombre: setEstablishment
     * Entradas: una coleccion de establecimientos
     * Salidas: -
     * Descripcion: cambia la coleccion actual por una nueva o actualizada.
     * @param establishment
     */
    public void setEstablishment(Collection<Establishment> establishment) {
        this.establishment = establishment;
    }
}
