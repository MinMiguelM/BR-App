package com.logicware.brapp.persistence;

import android.os.AsyncTask;
import android.util.Log;

import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.meta.Establishment;
import com.logicware.brapp.meta.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ASUS on 4/16/2016.
 * Permite la conexion entre el cliente, es decir, el dispositivo
 * y el servidor de LogicWare.
 */
public class AdapterWebService extends AsyncTask<String, Void, Object> {

    /**
     * Nombre: doInBackGround
     * Entradas: una lista de parametros, segun sea se hace una
     *           operacion hacia el servidor
     * Salidas: Un objeto, puede ser cualquiera de los que esta en el paquete META
     * Descripcion: Segun el tipo de operacion a realizar este llama a un metodo
     *              para realizar dicha operacion del cliente al servidor
     */
    @Override
    protected Object doInBackground(String... params){
        try {
            if(params[0].equals(Constantes.GET_USER_BY_CORREO))
                return getUserByCorreo(params[1]);
            if(params[0].equals(Constantes.GET_USER_BY_TOKEN))
                return null; // get by token method params[1]
            if(params[0].equals(Constantes.ADD_USER))
                return addUser(params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
        }catch (Exception e) {
            Log.e("AdapterWebService", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Trae el correo y la contrasena de un usuario según el email.
     * @param correo Parametro que trae la consulta
     */
    public User getUserByCorreo(String correo){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        User u = restTemplate.getForObject(Constantes.GET_USER_BY_CORREO + correo, User.class, "Android");
        return u;
    }

    /**
     * Nombre: addUser
     * Entradas: la lista de atributos concerniente a un usuario
     * Salidas: Un usuario nuevo
     * Descripcion: Agrega un nuevo usuario a la base de datos.
     */
    public User addUser(String... params){
        User newUser = new User();
        newUser.setNombre(params[0]);
        newUser.setNum_cel(params[1]);
        newUser.setCorreo(params[2]);
        newUser.setPassword(params[3]);
        newUser.setLink_facebook(params[4]);
        newUser.setToken(params[5]);
        newUser.setRol(params[6]);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        User response = restTemplate.postForObject(Constantes.ADD_USER, newUser,User.class);
        return response;
    }
}
