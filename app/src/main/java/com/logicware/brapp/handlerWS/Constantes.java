package com.logicware.brapp.handlerWS;

/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";
    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "45.55.193.13";
    /**
     * URLs del Web Service
     */

    public static final String GET = "http://" + IP + PUERTO_HOST + "/webservice/getUsers.php";
    public static final String GET_BY_ID = "http://" + IP + PUERTO_HOST + "/webservice/getUsersByID.php";
    /*public static final String UPDATE = "http://" + IP + PUERTO_HOST + "/webservice/actualizar_meta.php";
    public static final String DELETE = "http://" + IP + PUERTO_HOST + "/webservice/borrar_meta.php";
    public static final String INSERT = "http://" + IP + PUERTO_HOST + "/webservice/insertar_meta.php";*/

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";

}
