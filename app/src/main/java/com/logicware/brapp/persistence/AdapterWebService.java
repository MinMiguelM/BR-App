package com.logicware.brapp.persistence;

import android.os.AsyncTask;

import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Producto;
import com.logicware.brapp.entities.Reserva;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 4/16/2016.
 * Permite la conexion entre el cliente, es decir, el dispositivo
 * y el servidor de LogicWare.
 */
public class AdapterWebService extends AsyncTask<Object, Void, Object> {
    /**
     * Nombre: doInBackGround
     * Entradas: una lista de parametros, segun sea se hace una
     *           operacion hacia el servidor
     * Salidas: Un objeto, puede ser cualquiera de los que esta en el paquete META
     * Descripcion: Segun el tipo de operacion a realizar este llama a un metodo
     *              para realizar dicha operacion del cliente al servidor
     */
    @Override
    protected Object doInBackground(Object... params){
        try {
            if(params[0].equals(Constantes.GET_USER_BY_CORREO))
                return getUserByCorreo((String) params[1]);
            if(params[0].equals(Constantes.GET_USER_BY_TOKEN))
                return getUserByToken((String) params[1]); // get by token method params[1]
            if(params[0].equals(Constantes.ADD_USER))
                return addUser((String) params[1], (String) params[2], (String) params[3], (String) params[4],
                        (String) params[5], (String) params[6], (String) params[7]);
            if(params[0].equals(Constantes.UPDATE_USER))
                return updateUser((Usuario) params[1]);
            if(params[0].equals(Constantes.ADD_ESTABLISHMENT))
                return addEstablishment((Usuario) params[1], (String) params[2], (String) params[3], (String) params[4],
                        (String) params[5], (String) params[6], (String) params[7]);
            if(params[0].equals(Constantes.GET_ESTABLISHMENT_BY_NOMBRE))
                return getEstablishmentByNombre((String) params[1]);
            if(params[0].equals(Constantes.GET_ESTABLISHMENT_BY_USUARIO))
                return getEstablishmentByUser((Long) (params[1]));
            if(params[0].equals(Constantes.UPDATE_ESTABLISHMENT))
                return updateEstablishment((Establecimiento) params[1]);
            if(params[0].equals(Constantes.GET_ESTABLISHMENT_BY_TIPO))
                return getEstablishmentsByTipo((String) params[1]);
            if(params[0].equals(Constantes.ADD_EVENT) && params[1] instanceof Establecimiento)
                return addEventByEstablishment((Establecimiento) params[1], (String) params[2], (String) params[3],
                        (String) params[4], (String) params[5]);
            if(params[0].equals(Constantes.ADD_EVENT) && params[1] instanceof Usuario)
                return addEventByUser((Usuario) params[1], (String) params[2], (String) params[3],
                        (String) params[4], (String) params[5]);
            if(params[0].equals(Constantes.UPDATE_EVENT))
                return updateEvent((Evento) params[1]);
            if(params[0].equals(Constantes.GET_EVENT_BY_IDESTABLECIMIENTO))
                return getEventByIdEstablecimiento((Long) params[1]);
            if(params[0].equals(Constantes.GET_EVENT_BY_IDUSUARIO))
                return getEventByIdUsuario((Long) params[1]);
            if(params[0].equals(Constantes.ADD_COMMENTS))
                return addComments((Establecimiento) params[1], (Usuario) params[2], (String) params[3], (String) params[4]);
            if(params[0].equals(Constantes.UPDATE_COMMENTS))
                return updateComments((ComentarioYCalificacion) params[1]);
            if(params[0].equals(Constantes.GET_COMMENTS_BY_IDESTABLECIMIENTO))
                return getCommentsByIdEstablecimiento((Long)params[1]);
            if(params[0].equals(Constantes.ADD_ITEM))
                return addItem((Establecimiento) params[1], (String) params[2], (String) params[3], (String) params[4]);
            if(params[0].equals(Constantes.UPDATE_ITEM))
                return updateItem((Producto) params[1]);
            if(params[0].equals(Constantes.ADD_BOOKING))
                return addBooking((Establecimiento) params[1], (Usuario) params[2], (String) params[3], (String) params[4],
                        (String) params[5]);
            if(params[0].equals(Constantes.UPDATE_BOOKING))
                return updateBooking((Reserva) params[1]);
            if(params[0].equals(Constantes.GET_BOOKING_BY_IDESTABLECIMIENTO))
                return getBookingByIdEstablecimiento((Long)params[1]);
            if(params[0].equals(Constantes.GET_BOOKING_BY_IDUSUARIO))
                return getBookingByIdUsuario((Long)params[1]);
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
    public Usuario getUserByToken(String token){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.getForObject(Constantes.GET_USER_BY_TOKEN + token, Usuario.class, "Android");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getUserByCorreo
     * Entradas: correo por el cual se traera el usuario
     * Salidas: Usuario si existe, si no null
     * Descripcion: trae un usuario de la base de datos segun el correo.
     */
    public Usuario getUserByCorreo(String correo){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.getForObject(Constantes.GET_USER_BY_CORREO + "\"" + correo + "\"", Usuario.class, "Android");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Nombre: addUser
     * Entradas: Params0 -> Nombre
     *           params1 -> Telefono
     *           params2 -> Correo
     *           params3 -> Contrasena
     *           params4 -> link_facebook
     *           params5 -> token
     *           params6 -> Tipo, USUARIO o CLIENTE
     * Salidas: Un usuario nuevo
     * Descripcion: Agrega un nuevo usuario a la base de datos.
     */
    public Usuario addUser(String... params){
        Usuario newUser = new Usuario();
        newUser.setNombre(params[0]);
        newUser.setTelefono(params[1]);
        newUser.setCorreo(params[2]);
        newUser.setContrasena(params[3]);
        newUser.setLink_facebook(Boolean.parseBoolean(params[4]));
        newUser.setToken(params[5]);
        newUser.setTipo(params[6]);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_USER, newUser,Usuario.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }
    /**
     * Nombre: updateUser
     * Entradas: usuario con datos nuevos
     * Salidas: El usuario con sus datos acualizados.
     * Descripcion: Actualiza el usuario.
     */
    public Usuario updateUser(Usuario user){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.postForObject(Constantes.UPDATE_USER,user,Usuario.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Nombre: addEstablishment
     * Entradas: user -> El usuario que está agregando el establecimiento
     *           params0 -> Nombre
     *           params1 -> Direccion
     *           params2 -> Horario
     *           params3 -> Tematica
     *           params4 -> Tipo
     *           params5 -> Telefono
     * Salida: Un establecimiento nuevo
     * Descripcion: Agrega un nuevo establecimiento a la base de datos
     */
    public Establecimiento addEstablishment(Usuario user, String... params){
        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre(params[0]);
        establecimiento.setDireccion(params[1]);
        establecimiento.setHorario(params[2]);
        establecimiento.setTematica(params[3]);
        establecimiento.setTipo(params[4]);
        establecimiento.setTelefono(params[5]);
        establecimiento.setCalificacion_promedio(0.0);
        establecimiento.setUsuario(user);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_ESTABLISHMENT, establecimiento,Establecimiento.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getEstablishmentByNombre
     * Entradas: nombre del establecimiento a buscar
     * Salidas: lista de establecimientos
     * Descripcion: trae una lista de establecimientos segun el nombre del establecimiento
     */
    public Collection<Establecimiento> getEstablishmentByNombre(String nombre){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Establecimiento> retorno = new ArrayList<>();
            Establecimiento[] response = restTemplate.getForObject(Constantes.GET_ESTABLISHMENT_BY_NOMBRE + nombre , Establecimiento[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getEstablishmentByUser
     * Entradas: id del usuario a consultar sus establecimientos
     * Salidas: lista de establecimientos
     * Descripcion: trae una lista de establecimientos segun el id de usuario
     */
    public Collection<Establecimiento> getEstablishmentByUser(Long idUsuario){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Establecimiento> retorno = new ArrayList<>();
            Establecimiento[] response = restTemplate.getForObject(Constantes.GET_ESTABLISHMENT_BY_USUARIO + idUsuario , Establecimiento[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: updateEstablishment
     * Entradas: establecimiento
     * Salidas: El establecimiento con sus datos acualizados.
     * Descripcion: Actualiza el establecimiento a peticion de su dueño.
     */
    public Establecimiento updateEstablishment(Establecimiento establecimiento){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Establecimiento e = restTemplate.postForObject(Constantes.UPDATE_ESTABLISHMENT, establecimiento, Establecimiento.class);
            System.out.println(e.getNombre()+ "  " + e.getDireccion());
            return e;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getEstablishmentByTipo
     * Entradas: Tipo de establecimiento a buscar
     * Salidas: lista de establecimientos
     * Descripcion: trae una lista de establecimientos segun el tipo dado
     */
    public Collection<Establecimiento> getEstablishmentsByTipo(String tipo){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Establecimiento> retorno = new ArrayList<>();
            Establecimiento[] response = restTemplate.getForObject(Constantes.GET_ESTABLISHMENT_BY_TIPO + tipo , Establecimiento[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Nombre: addEventByUser
     * Entradas: user -> El usuario que está agregando el evento
     *           params0 -> fecha_inicio
     *           params1 -> fecha_fin
     *           params2 -> Descripcion
     *           params3 -> Titulo
     * Salida: Un evento nuevo
     * Descripcion: Agrega un nuevo evento a la base de datos hecho por un usuario
     */
    public Evento addEventByUser(Usuario user,String... params){
        Evento newEvento = new Evento();
        newEvento.setFecha_inicio(params[0]);
        newEvento.setFecha_fin(params[1]);
        newEvento.setDescripcion(params[2]);
        newEvento.setTitulo(params[3]);
        newEvento.setUsuario(user);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_EVENT, newEvento,Evento.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }
    /**
     * Nombre: addEventByEstablishment
     * Entradas: establecimiento -> El establecimiento que está agregando el evento
     *           params0 -> fecha_inicio
     *           params1 -> fecha_fin
     *           params2 -> Descripcion
     *           params3 -> Titulo
     * Salida: Un evento nuevo
     * Descripcion: Agrega un evento nuevo a la base de datos hecho por un establecimiento
     */
    public Evento addEventByEstablishment(Establecimiento establecimiento,String... params){
        Evento newEvento = new Evento();
        newEvento.setFecha_inicio(params[0]);
        newEvento.setFecha_fin(params[1]);
        newEvento.setDescripcion(params[2]);
        newEvento.setTitulo(params[3]);
        newEvento.setEstablecimiento(establecimiento);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_EVENT, newEvento,Evento.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: updateEvent
     * Entradas: evento con sus datos modificados
     * Salidas: El evento con sus datos acualizados.
     * Descripcion: Actualiza el evento a peticion de un usuario o establecimiento
     */
    public Evento updateEvent(Evento evento){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.postForObject(Constantes.UPDATE_EVENT, evento, Evento.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getEventByIdEstablecimiento
     * Entradas: id del establecimiento
     * Salidas: lista de eventos
     * Descripcion: trae una lista de eventos del establecimiento según id
     */
    public Collection<Evento> getEventByIdEstablecimiento(Long id){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Evento> retorno = new ArrayList<>();
            Evento[] response = restTemplate.getForObject(Constantes.GET_EVENT_BY_IDESTABLECIMIENTO + id , Evento[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getEventByIdUsuario
     * Entradas: id del usuario
     * Salidas: lista de eventos
     * Descripcion: trae una lista de eventos que ha hecho el usuario con id id
     */
    public Collection<Evento> getEventByIdUsuario(Long id){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Evento> retorno = new ArrayList<>();
            Evento[] response = restTemplate.getForObject(Constantes.GET_EVENT_BY_IDUSUARIO + id , Evento[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: addComments
     * Entradas: establecimiento -> El establecimiento el cual esta siendo calificado y comentado
     *           usuario -> El usuario que esta haciendo el comentario y la calificacion
     *           params0 -> Descripcion
     *           params1 -> Calificacion
     * Salida: Un comentario y calificacion nuevo
     * Descripcion: Agrega a la base de datos un comentario y calificacion
     */
    public ComentarioYCalificacion addComments(Establecimiento establecimiento, Usuario usuario, String... params){
        ComentarioYCalificacion comentario = new ComentarioYCalificacion();
        comentario.setDescripcion(params[0]);
        comentario.setCalificacion(Integer.parseInt(params[1]));
        comentario.setUsuario(usuario);
        comentario.setEstablecimiento(establecimiento);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_COMMENTS, comentario,ComentarioYCalificacion.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: updateComments
     * Entradas: comentario y calificacion con sus datos nuevos
     * Salidas: El comentario y calificacion con sus datos acualizados.
     * Descripcion: Actualiza el comentario y calificacion a peticion de un usuario.
     */
    public ComentarioYCalificacion updateComments(ComentarioYCalificacion comentarioYCalificacion){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.postForObject(Constantes.UPDATE_COMMENTS, comentarioYCalificacion, ComentarioYCalificacion.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getCommentsByIdEstablecimiento
     * Entradas: id del establecimiento
     * Salidas: lista de comentarios y calificaciones
     * Descripcion: trae una lista de comentarios y calificaciones que le han hecho al establecimiento
     */
    public Collection<ComentarioYCalificacion> getCommentsByIdEstablecimiento(Long id){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<ComentarioYCalificacion> retorno = new ArrayList<>();
            ComentarioYCalificacion[] response = restTemplate.getForObject(Constantes.GET_COMMENTS_BY_IDESTABLECIMIENTO + id , ComentarioYCalificacion[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: addItem
     * Entradas: establecimiento -> El establecimiento el cual esta agregando el producto
     *           params0 -> Nombre
     *           params1 -> Precio en String
     *           params2 -> Descripcion
     * Salida: Un producto nuevo
     * Descripcion: Agrega a la base de datos un nuevo producto
     */
    public Producto addItem(Establecimiento establecimiento, String... params){
        Producto producto = new Producto();
        producto.setNombre(params[0]);
        producto.setPrecio(Double.parseDouble(params[1]));
        producto.setDescripcion(params[2]);
        producto.setEstablecimiento(establecimiento);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_ITEM,producto,Producto.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: updateItem
     * Entradas: producto con sus nuevos datos
     * Salidas: El producto con sus datos acualizados.
     * Descripcion: Actualiza el producto a peticion de un estalecimiento
     */
    public Producto updateItem(Producto producto){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.postForObject(Constantes.UPDATE_ITEM, producto, Producto.class);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Nombre : addBooking
     * Entradas: establecimiento -> El establecimiento al cual se le hace la reserva
     *           usuario -> El usuario que hace la reserva
     *           params0 -> Fecha_reserva
     *           params1 -> Estado
     *           params2 -> cantidad personas
     * Salida: Una nueva reserva
     * Descripcion: Agrega a la base de datos una nueva reserva
     *
     */
    public Reserva addBooking(Establecimiento establecimiento,Usuario usuario,String... params){
        Reserva reserva = new Reserva();
        reserva.setFecha_reserva(params[0]);
        reserva.setEstado(params[1]);
        reserva.setCantidad_personas(Long.parseLong(params[2]));
        reserva.setUsuario(usuario);
        reserva.setEstablecimiento(establecimiento);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        try{
            return restTemplate.postForObject(Constantes.ADD_BOOKING,reserva,Reserva.class);
        }catch(HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                return null;
            else
                e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: updateBooking
     * Entradas: reserva con sus nuevos datos
     * Salidas: La reserva con sus datos acualizados.
     * Descripcion: Actualiza la reserva a peticion de un usuario o un establecimiento segun
     * corresponda
     */
    public Reserva updateBooking(Reserva reserva){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.postForObject(Constantes.UPDATE_BOOKING,reserva,Reserva.class);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getBookingByIdEstablecimiento
     * Entradas: id del establecimiento
     * Salidas: lista de reservas
     * Descripcion: trae una lista de reservas que tiene un establecimiento
     */
    public Collection<Reserva> getBookingByIdEstablecimiento(Long id){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Reserva> retorno = new ArrayList<>();
            Reserva[] response = restTemplate.getForObject(Constantes.GET_BOOKING_BY_IDESTABLECIMIENTO + id , Reserva[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Nombre: getBookingByIdUsuario
     * Entradas: id del usuario
     * Salidas: lista de reservas
     * Descripcion: trae una lista de reservas que ha hecho el usuario
     */
    public Collection<Reserva> getBookingByIdUsuario(Long id){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            Collection<Reserva> retorno = new ArrayList<>();
            Reserva[] response = restTemplate.getForObject(Constantes.GET_BOOKING_BY_IDUSUARIO + id , Reserva[].class, "Android");
            for ( int i = 0; i<response.length;i++)
                retorno.add(response[i]);
            return retorno;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}