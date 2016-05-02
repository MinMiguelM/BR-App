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
    //private static final String IP = "45.55.193.13";
    private static final String IP = "192.168.0.15";

    /**
     * Puerto donde funciona el servidor
     */
    private static  final String PORT = "8181";

    /**
     * URLs del Web Service
     */
    public static final String GET_ALL_USERS = "http://" + IP + ":" +PORT + "/user/getAll";
    public static final String GET_USER_BY_CORREO = "http://" + IP + ":" +PORT + "/user/getByCorreo/";
    public static final String GET_USER_BY_TOKEN = "http://" + IP + ":" +PORT + "/user/getByToken/";
    public static final String ADD_USER = "http://" + IP + ":" +PORT + "/user/add";
}
