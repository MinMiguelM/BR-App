package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;
/**
 * Esta clase permite al usuario realizar un eventos en el establecimiento
 * */
public class EventoUsuarioActivity extends AppCompatActivity {
    private Usuario user = null;
    private Button evento;
    private Evento ev = new Evento();
    private Establecimiento establishment;
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
        setContentView(R.layout.activity_evento_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");
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
             * Nombre: RealizarEvento
             * Entradas: fecha de inicio, fecha de fin, titulo del evento, descripción del evento
             * Salidas: -
             * Descripcion: crea un evento y se conecta con la base de datos
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
        android.app.AlertDialog alerta2 = new android.app.AlertDialog.Builder(EventoUsuarioActivity.this).create();
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
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(EventoUsuarioActivity.this).create();
        alerta.setTitle("Confirmación");
        alerta.setMessage("Se ha programado el evento");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(EventoUsuarioActivity.this, OneEstablishmentUsuarioActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("establecimiento",establishment);
                startActivity(intent);
            }

        });
        alerta.show();
    }
}
