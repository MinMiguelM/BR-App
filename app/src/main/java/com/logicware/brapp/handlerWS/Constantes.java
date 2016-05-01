package com.logicware.brapp.handlerWS;

/**
 * Clase que contiene la información necesaria para realizar las peticiones
 * al servidor, a continuación se detallan cada una.
 *
 */
public class Constantes {

    /**
     * Dirección IP del Host.
     */
    private static final String IP = "45.55.193.13";

    /**
     * URLs del Web Service
     */
    public static final String GET_ALL_USERS = "http://" + IP + "/webservice/requests/getUsers.php";
    public static final String GET_USER_BY_CORREO = "http://" + IP + "/webservice/requests/getUsersByCorreo.php";
    public static final String INSERT_USER = "http://" + IP + "/webservice/requests/insertUser.php";
}
