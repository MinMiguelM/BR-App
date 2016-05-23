package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Reserva;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

public class EventoUsuarioActivity extends AppCompatActivity {
    private Usuario user = null;
    private Button evento;
    private Evento ev = new Evento();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        final EditText RecibirFechaInicio = (EditText) findViewById(R.id.editTextFechaInicio);
        final EditText RecibirFechaFin = (EditText) findViewById(R.id.editTextFechaFin);
        final EditText RecibirTitulo = (EditText) findViewById(R.id.editTextTitulo);
        final EditText RecibirDescripcion = (EditText) findViewById(R.id.editTextDescripcion);

        evento = (Button)findViewById(R.id.buttonE);
        evento.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {

                String fechaInicio = RecibirFechaInicio.getText().toString();
                String fechaFin = RecibirFechaFin.getText().toString();
                String titulo = RecibirTitulo.getText().toString();
                String descripcion = RecibirDescripcion.getText().toString();

                if (fechaInicio.equalsIgnoreCase("")) {
                    mostrarError("Campos vacíos", "Para realizar un evento debe llenar los datos requeridos");
                } else if (fechaFin.equalsIgnoreCase("")) {
                    mostrarError("Campos vacíos", "Para realizar un evento debe llenar los datos requeridos");
                }else if (titulo.equalsIgnoreCase("")) {
                    mostrarError("Campos vacíos", "Para realizar un evento debe llenar los datos requeridos");
                }else if (descripcion.equalsIgnoreCase("")){
                    mostrarError("Campos vacíos", "Para realizar un evento debe llenar los datos requeridos");
                }else{
                    RealizarEvento(fechaInicio,fechaFin,titulo,descripcion);
                }
            }

            /**
             * Nombre: modificarUsuario
             * Entradas: nombre, email y telefono del usuario
             * Salidas: -
             * Descripcion: modifica el usuario en la base de datos
             */
            private void RealizarEvento(String fechaIni, String fechaFin, String Titulo,String des) {
                try {
                    ev.setFecha_inicio(fechaIni);
                    ev.setFecha_fin(fechaFin);
                    ev.setTitulo(Titulo);
                    ev.setDescripcion(des);
                    ev = (Evento) new AdapterWebService().execute(Constantes.ADD_EVENT, user, fechaIni, fechaFin,des,Titulo).get();
                    if (ev != null) {
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
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(EventoUsuarioActivity.this).create();
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
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(EventoUsuarioActivity.this).create();
        alerta.setTitle("Confirmación");
        alerta.setMessage("Se ha programado el evento");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
    }
}
