package com.logicware.brapp.persistence;

import android.os.AsyncTask;
import android.util.Log;

import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.meta.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


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
                return getUserByToken(params[1]); // get by token method params[1]
            if(params[0].equals(Constantes.ADD_USER))
                return addUser(params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
            if(params[0].equals(Constantes.UPDATE_TOKEN_USER))
                return updateTokenUser(params[1],params[2]);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getUserByToken
     * Entradas: token por el cual se traera el usuario
     * Salidas: Usuario si existe, si no null
     * Descripcion: trae un usuario de la base de datos segun el token.
     */
    public User getUserByToken(String token){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            User u = restTemplate.getForObject(Constantes.GET_USER_BY_TOKEN + token, User.class, "Android");
            return u;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Trae el correo y la contrasena de un usuario según el email.
     * @param correo Parametro que trae la consulta
     */
    public User getUserByCorreo(String correo){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            User u = restTemplate.getForObject(Constantes.GET_USER_BY_CORREO + "\"" + correo + "\"", User.class, "Android");
            return u;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
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
        try{
            User response = restTemplate.postForObject(Constantes.ADD_USER, newUser,User.class);
            return response;
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: updateTokenUser
     * Entradas: El correo del usuario a quien se le actualizara el token y ademas
     *           el new token generado para dicho usuario
     * Salidas: El usuario con sus datos acualizados.
     * Descripcion: Actualiza el token debido a que este ya expiró.
     */
    public User updateTokenUser(String newToken, String correo){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            User u = restTemplate.getForObject(Constantes.UPDATE_TOKEN_USER + newToken + "&" + "\"" + correo + "\"", User.class, "Android");
            return u;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
