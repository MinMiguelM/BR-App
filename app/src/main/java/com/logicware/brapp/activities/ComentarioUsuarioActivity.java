package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.asus.br.R;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Reserva;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

public class ComentarioUsuarioActivity extends AppCompatActivity {

    private Button comentario;
    private Usuario user = null;
    private Establecimiento establishment;
    private ComentarioYCalificacion com = new ComentarioYCalificacion();
    private boolean repetido = false;
    private RatingBar ratingBar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario) getIntent().getExtras().getSerializable("user");
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

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
                }if (!cali.matches("[0-9]+")) {
                    mostrarError("Número inválido", "Por favor ingrese un número ");
                }
                else {
                    for(ComentarioYCalificacion cc :establishment.getComentariosYCalificaciones())
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
             * Nombre: modificarUsuario
             * Entradas: nombre, email y telefono del usuario
             * Salidas: -
             * Descripcion: modifica el usuario en la base de datos
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
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(ComentarioUsuarioActivity.this).create();
        alerta.setTitle(nombreError);
        alerta.setMessage(descripcion);
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
    }

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
