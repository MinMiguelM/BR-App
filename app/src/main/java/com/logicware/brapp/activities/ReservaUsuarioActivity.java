package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.entities.Reserva;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;
/**
* Esta clase permite al usuario realizar una reservación
* */
public class ReservaUsuarioActivity extends AppCompatActivity {

    private Usuario user = null;
    private Button reserva;
    private Establecimiento establishment;
    private Reserva res = new Reserva();
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
        setContentView(R.layout.activity_reserva_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario) getIntent().getExtras().getSerializable("user");
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");


        final EditText RecibirFecha = (EditText) findViewById(R.id.editTextFecha);
        final EditText RecibirCPersonas = (EditText) findViewById(R.id.editTextCPer);

        reserva = (Button) findViewById(R.id.buttonReservar);
        reserva.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             * sobre el boton.
             */
            @Override
            public void onClick(View v) {
                String fecha = RecibirFecha.getText().toString();
                String cantidadPersonas = RecibirCPersonas.getText().toString();
                String estado = "En espera";
                if (fecha.equalsIgnoreCase("")) {
                    mostrarError("Campos vacíos", "Para solicitar una reserva debe llenar los datos requeridos");
                } else if (!cantidadPersonas.matches("[0-9]+")) {
                    mostrarError("Número inválido", "Por favor ingrese un número ");
                } else {
                    Reservar(fecha, cantidadPersonas, estado);
                }

            }

            /**
             * Nombre: reservar
             * Entradas: fecha, cantidad de personas, estado de reservación
             * Salidas: -
             * Descripcion: hace la reservación y se conecta con la base de datos
             */
            private void Reservar(String fecha, String per, String estado) {
                try {
                    res.setFecha_reserva(fecha);
                    res.setCantidad_personas(Long.parseLong(per));
                    res.setEstado(estado);
                    //System.out.println(establishment.getNombre());
                    //System.out.println(user.getNombre());
                    res = (Reserva) new AdapterWebService().execute(Constantes.ADD_BOOKING, establishment, user, fecha, estado, per).get();
                    if (res != null) {
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
        android.app.AlertDialog alerta2 = new android.app.AlertDialog.Builder(ReservaUsuarioActivity.this).create();
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
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(ReservaUsuarioActivity.this).create();
        alerta.setTitle("Confirmación");
        alerta.setMessage("Se ha solicitado una reservación");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(ReservaUsuarioActivity .this,  OneEstablishmentUsuarioActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("establecimiento",establishment);
                startActivity(intent);
            }

        });
        alerta.show();
    }
}
