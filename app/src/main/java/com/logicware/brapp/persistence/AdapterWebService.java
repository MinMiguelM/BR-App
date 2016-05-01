package com.logicware.brapp.persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.handlerWS.Singleton;
import com.logicware.brapp.meta.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 4/16/2016.
 * Permite la conexion entre el cliente, es decir, el dispositivo
 * y el servidor de LogicWare.
 */
public class AdapterWebService {

    /**
     * Trae el correo y la contrasena de un usuario según el email.
     *
     * @param c Contexto de la actividad que llama al método
     * @param correo Parametro que trae la consulta
     */
    public static void getUsersByCorreo(Context c, String correo){
        String URL = Constantes.GET_USER_BY_CORREO + "?correo=" + correo;
        Singleton.getInstance(c).addToRequestQueue(new JsonObjectRequest(Request.Method.GET, URL, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                response_getUsersByCorreo(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Exception", error.toString());
            }
        }
        ));
    }

    /**
     * Revisa el archivo json que llega como repsuesta del servidor
     * y lo trata según sus llaves y valores.
     *
     * @param response
     * @return si retorna null es por dos razones
     *  1. Lo que se busca no se encuentra en la base de datos
     *  2. El parametro de la consulta estaba vacío.
     */
    public static User response_getUsersByCorreo(JSONObject response){
        User newUser = null;
        try {
            switch (Integer.parseInt(response.getString("estado"))) {
                case 1:
                    JSONObject obj = response.getJSONObject("meta");
                    break;
                default:
                    break;
            }
        }catch(Exception e){
            Log.e("Exception",e.toString());
        }
        return newUser;
    }

    /**
     * Inserta en la tabla un nuevo registro
     *
     * @param c Contexto de la actividad que llama al método
     * @param user El user que se acaba de crear.
     */
    public static void insertUser(Context c, User user){
        //boolean final ret = false;
        String URL = Constantes.INSERT_USER;
        Map<String,String> mapa = new HashMap<>();
        mapa.put("correo",user.getCorreo());
        mapa.put("password",user.getPassword());
        mapa.put("num_celular",user.getNum_cel());
        mapa.put("link_facebook", (user.isLink_facebook() + ""));
        mapa.put("token_facebook", user.getToken_facebook());
        JSONObject j = new JSONObject(mapa);
        Singleton.getInstance(c).addToRequestQueue(new JsonObjectRequest(Request.Method.POST, URL, j, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                response_insertUser(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Exception", error.toString());
            }
        }));
    }

    /**
     * La respuesta de un insert puede ser positiva o negativa, aquí se maneja esto
     *
     * @param response La respuesta del servidor
     * @return Retorna true si pudo insertar la tupla dentro de la tabla
     *         De lo contrario retorna false.
     */
    public static boolean response_insertUser(JSONObject response){
        boolean ret = false;
        try {
            switch(Integer.parseInt(response.getString("estado"))){
                case 1:
                    System.out.println("bien");
                    ret = true;
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            Log.e("Exception", e.toString());
        }
        return ret;
    }
}
