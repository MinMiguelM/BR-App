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
    //private static final String IP = "192.168.0.15";

    /**
     * Puerto donde funciona el servidor
     */
    private static  final String PORT = "8181";

    /**
     * Tiempo de espera de una respuesta por parte del servidor.
     * Tiempo en segundos.
     */
    public static final int TIMEOUT = 4;

    /**
     * URLs del Web Service
     */
    public static final String GET_ALL_USERS = "http://" + IP + ":" +PORT + "/user/getAll";
    public static final String GET_USER_BY_CORREO = "http://" + IP + ":" +PORT + "/user/getByCorreo/";
    public static final String GET_USER_BY_TOKEN = "http://" + IP + ":" +PORT + "/user/getByToken/";
    public static final String ADD_USER = "http://" + IP + ":" +PORT + "/user/add";
    public static final String UPDATE_USER = "http://" + IP + ":" +PORT + "/user/update";

    public static final String ADD_ESTABLISHMENT = "http://" + IP + ":" +PORT + "/establishment/add";
    public static final String GET_ESTABLISHMENT_BY_NOMBRE = "http://" + IP + ":" +PORT + "/establishment/getByNombre/";
    public static final String UPDATE_ESTABLISHMENT = "http://" + IP + ":" +PORT + "/establishment/update";
    public static final String GET_ESTABLISHMENT_BY_TIPO = "http://" + IP + ":" +PORT + "/establishment/getByTipo/";

    public static final String ADD_EVENT = "http://" + IP + ":" +PORT + "/event/add";
    public static final String UPDATE_EVENT = "http://" + IP + ":" +PORT + "/event/update";

    public static final String ADD_COMMENTS = "http://" + IP + ":" +PORT + "/comments/add";
    public static final String UPDATE_COMMENTS = "http://" + IP + ":" +PORT + "/comments/update";

    public static final String ADD_ITEM = "http://" + IP + ":" +PORT + "/item/add";
    public static final String UPDATE_ITEM = "http://" + IP + ":" +PORT + "/item/update";

    public static final String ADD_BOOKING = "http://" + IP + ":" +PORT + "/booking/add";
    public static final String UPDATE_BOOKING = "http://" + IP + ":" +PORT + "/booking/update";
}
