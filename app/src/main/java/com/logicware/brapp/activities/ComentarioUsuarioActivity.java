package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
/**
* esta clase permite al usuario realizar
 * un comentario sobre el establecimiento
* */
public class ComentarioUsuarioActivity extends AppCompatActivity {

    private Button comentario;
    private Usuario user = null;
    private Establecimiento establishment;
    private ComentarioYCalificacion com = new ComentarioYCalificacion();
    private boolean repetido = false;
    private RatingBar ratingBar = null;
    private ArrayList<ComentarioYCalificacion> comments = new ArrayList<>();

    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     *              que la aplicacion pueda mostrar sus componentes graficos
     *              y funcionales
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario) getIntent().getExtras().getSerializable("user");
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

        try{
            comments = (ArrayList<ComentarioYCalificacion>) new AdapterWebService().execute(Constantes.GET_COMMENTS_BY_IDESTABLECIMIENTO,establishment.getIdEstablecimiento()).get();
        }catch(Exception e){
            e.printStackTrace();
        }

        final EditText RecibirDes= (EditText) findViewById(R.id.editTextDes);
        final EditText RecibirCla= (EditText) findViewById(R.id.editTextCali);

        comentario = (Button)findViewById(R.id.buttonModifiEstable);
        comentario.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             * sobre el boton.
             */
            @Override
            public void onClick(View v) {

                String des = RecibirDes.getText().toString();
                String cali = RecibirCla.getText().toString();

                if (des.equalsIgnoreCase("")) {
                    mostrarError("Campos vacíos", "Para comentar debe llenar los datos requeridos");
                }if (!cali.matches("[0-5]")) {
                    mostrarError("Número inválido", "Por favor ingrese un número del 1 al 5 ");
                }
                else {
                    for(ComentarioYCalificacion cc :comments)
                    {
                        //System.out.println("ID DE USUARIO EN COMENTARIOS "+ cc.getUsuario().getIdUsuario());
                        //System.out.println("ID ACTUAL "+ user.getIdUsuario());
                        if(cc.getUsuario().getIdUsuario().equals(user.getIdUsuario()))
                        {
                            //System.out.println("ENTRO AL ID!! ");
                            repetido = true;
                        }
                    }
                    if(!repetido)
                    Comentar(des, cali);
                    else
                        mostrarError("Error solicitud", "El usuario ya ha comentado este establecimiento");
                }
            }

            /**
             * Nombre: comentar
             * Entradas: descripción, calificación
             * Salidas: -
             * Descripcion: hace el comentario y se conecta con la base de datos
             */
            private void Comentar(String des, String cali) {
                try {
                    com.setDescripcion(des);
                    com.setCalificacion(Integer.parseInt(cali));
                    com.setUsuario(user);
                    com.setEstablecimiento(establishment);
                    com = (ComentarioYCalificacion) new AdapterWebService().execute(Constantes.ADD_COMMENTS, establishment, user, des, cali).get();
                    if (com != null) {
                        mostrarConfirmacion();

                    } else
                        mostrarError("Error solicitud", "No se ha enviado la solicitud");
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    /**
     * Nombre de Método: mostrar Error
     * Entradas: nombre del error y su descripcion
     * Salidas: void
     * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
     */
    private void mostrarError(String nombreError, String descripcion) {
        android.app.AlertDialog alerta2 = new android.app.AlertDialog.Builder(ComentarioUsuarioActivity.this).create();
        alerta2.setTitle(nombreError);
        alerta2.setMessage(descripcion);
        alerta2.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta2.show();
    }
    /**
     * Nombre de Método: mostrar Confirmación
     * Entradas: -
     * Salidas: void
     * Descripcion:  imprime confirmación
     */
    private void mostrarConfirmacion() {
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(ComentarioUsuarioActivity.this).create();
        alerta.setTitle("Confirmación");
        alerta.setMessage("Se a hecho el comentario");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
    }
}
