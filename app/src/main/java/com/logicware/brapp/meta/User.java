package com.logicware.brapp.meta;

/**
 * Created by ASUS on 4/16/2016.
 */
public class User {

    private Integer id;
    private String correo;
    private String password;
    private String num_celular;
    private String rol;
    private boolean link_facebook;
    private String token_facebook;

    /**
     * Constructor de la clase User
     * @param id
     * @param correo
     * @param password
     * @param num_celular
     * @param rol
     * @param link_facebook
     * @param token_facebook
     */
    public User(Integer id, String correo, String password, String num_celular, String rol, boolean link_facebook, String token_facebook) {
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.num_celular = num_celular;
        this.rol = rol;
        this.link_facebook = link_facebook;
        this.token_facebook = token_facebook;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNum_celular() {
        return num_celular;
    }

    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isLink_facebook() {
        return link_facebook;
    }

    public void setLink_facebook(boolean link_facebook) {
        this.link_facebook = link_facebook;
    }

    public String getToken_facebook() {
        return token_facebook;
    }

    public void setToken_facebook(String token_facebook) {
        this.token_facebook = token_facebook;
    }
}
