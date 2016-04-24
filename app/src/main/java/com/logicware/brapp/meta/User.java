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
    /*
           * Nombre de Método: User
           * Entradas: id, correo, password, num_celular, rol, link_facebook, token_Facebook
           * Salidas: void
           * Descripcion: constructor de la clase usuario
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
    /*
               * Nombre de Método: getID
               * Entradas:
               * Salidas:  id
               * Descripcion: metodo que retona el id del usuario
               */
    public Integer getId() {
        return id;
    }

    /*
               * Nombre de Método: setID
               * Entradas: id
               * Salidas:
               * Descripcion: modifica el valor del id
               */

    public void setId(Integer id) {
        this.id = id;
    }
    /*
                   * Nombre de Método: getCorreo
                   * Entradas:
                   * Salidas:  correo
                   * Descripcion: retona el correo del usuario
                   */
    public String getCorreo() {
        return correo;
    }
    /*
                       * Nombre de Método: setCorreo
                       * Entradas: correo
                       * Salidas:
                       * Descripcion: modifica el correo del usuario
                       */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /*
                       * Nombre de Método: getPassword
                       * Entradas: password
                       * Salidas:
                       * Descripcion: modifica el password del usuario
                       */
    public String getPassword() {
        return password;
    }
    /*
                           * Nombre de Método: setPassword
                           * Entradas:
                           * Salidas: password
                           * Descripcion: retorna el password del usuario
                           */
    public void setPassword(String password) {
        this.password = password;
    }
    /*
                           * Nombre de Método: getNum_celular
                           * Entradas: numero de celular
                           * Salidas:
                           * Descripcion: retorna el numero de celular del usuario
                           */
    public String getNum_celular() {
        return num_celular;
    }

    /*
                       * Nombre de Método: setNum_celular
                       * Entradas:
                       * Salidas: numero de celular
                       * Descripcion: modifica el numero de celular del usuario
                       */
    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }
    /*
                           * Nombre de Método: getRol
                           * Entradas:
                           * Salidas: rol
                           * Descripcion: retorna el rol del usuario
                           */
    public String getRol() {
        return rol;
    }
    /*
                           * Nombre de Método: setRol
                           * Entradas: rol
                           * Salidas:
                           * Descripcion: modifica el rol del usuario
                           */
    public void setRol(String rol) {
        this.rol = rol;
    }
    /*
                           * Nombre de Método: isLink_facebook
                           * Entradas:
                           * Salidas: boleano
                           * Descripcion: verifica si es un link de facebook
                           */
    public boolean isLink_facebook() {
        return link_facebook;
    }
    /*
                              * Nombre de Método: setLink_facebook
                              * Entradas: boleano
                              * Salidas:
                              * Descripcion: comprueba que sea un link de facebook y modifica el del usuario
                              */
    public void setLink_facebook(boolean link_facebook) {
        this.link_facebook = link_facebook;
    }
    /*
                                  * Nombre de Método: getToken_facebook
                                  * Entradas:
                                  * Salidas: token de facebook
                                  * Descripcion: retorna el token de facebook
                                  */
    public String getToken_facebook() {
        return token_facebook;
    }
    /*
                              * Nombre de Método: setToken_facebook
                              * Entradas:
                              * Salidas: token de facebook
                              * Descripcion: modifica el token de facebook
                              */
    public void setToken_facebook(String token_facebook) {
        this.token_facebook = token_facebook;
    }
}
