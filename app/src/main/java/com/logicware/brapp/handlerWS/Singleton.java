package com.logicware.brapp.handlerWS;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Se encarga de manejar la solicitudes que desde el dispositivo se hacen
 * hacia el servidor.
 * Created by ASUS on 4/16/2016.
 */
public class Singleton {

    private static Singleton singleton;
    private RequestQueue requestQueue;
    private static Context context;

    /**
     * Constructor de la clase Singleton
     * @param context
     */
    private Singleton(Context context) {
        Singleton.context = context;
        requestQueue = getRequestQueue();
    }

    /**
     *  Nombre de Método: getInstace
     *  Entradas: contexto donde se ejecutaran las peticiones
     *  Salidas: instancia unica del singleton
     *  Descripcion: retona la instancia unica del singleton
     */

    public static synchronized Singleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new Singleton(context.getApplicationContext());
        }
        return singleton;
    }

    /**
     *  Nombre de Método: getRequestQueue
     *  Entradas:
     *  Salidas: cola de peticiones
     *  Descripcion: obtiene la instancia de la cola de peticiones
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Añade la petición a la cola
     *
     * @param req petición
     * @param <T> Resultado final de tipo T
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}