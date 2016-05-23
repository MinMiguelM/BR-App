package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;

/*esta clase permite al cliente
* crear un nuevo establecimiento
*
* */
public class CreateEstablishmentActivity extends AppCompatActivity {
    private Establecimiento establishment;
    private Usuario user;
    private Button aceptar;
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
        setContentView(R.layout.activity_create_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");
        System.out.println(user.getNombre());

        aceptar = (Button) findViewById(R.id.aceptarCrearEsta);
        aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView nombreEstablecimiento = (TextView) findViewById(R.id.nombreEsta);
                TextView telefono = (TextView) findViewById(R.id.telefonoEsta);
                TextView tematica = (TextView) findViewById(R.id.tematica);
                TextView direccion = (TextView) findViewById(R.id.ubicacionEsta);
                TextView horario = (TextView) findViewById(R.id.horaio);
                RadioGroup botones = (RadioGroup) findViewById(R.id.grupoBoton);

                String nom = nombreEstablecimiento.getText().toString();
                String dir = direccion.getText().toString();
                String hor = horario.getText().toString();
                String tem = tematica.getText().toString();
                String tipo = ((RadioButton) findViewById(botones.getCheckedRadioButtonId())).getText().toString();
                String tel = telefono.getText().toString();

                if (camposVacios(nom, tel, dir, hor, tem)) {
                    mostrarError("Campos vacios", "Complete los campos vacios");
                } else {
                    insertarEstablecimiento(nom, tel, dir, hor, tem, tipo);
                }
                System.out.println("asldhasldjasldjasljdlsajdl");
                if (establishment == null) {
                    mostrarError("Establecimiento no creado", "No se ha podido crear el establecimiento.");
                } else {
                    Intent intent = new Intent(CreateEstablishmentActivity.this, IndexClientActivity.class);
                    intent.putExtra("user",user);
//                    intent.putExtra("establecimiento",establishment);
                    startActivity(intent);
                }
            }

            /**
             * Nombre de Método: Campos Vacios
             * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
             * Salidas: boolean
             * Descripcion:  verifica que no existan campos vacios en los datos registrados por el usuario retornando Verdadero o Falso
             */

            private boolean camposVacios(String nombre, String telefono, String email, String pass1, String pass2) {
                return nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty();
            }

            /**
             * Nombre de Método: mostrar Error
             * Entradas: nombre del error y su descripcion
             * Salidas: void
             * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
             */
            private void mostrarError(String nombreError, String descripcion) {
                AlertDialog alerta = new AlertDialog.Builder(CreateEstablishmentActivity.this).create();
                alerta.setTitle(nombreError);
                alerta.setMessage(descripcion);
                alerta.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                alerta.show();
            }
            /**
             * Nombre: insertarEstablecimiento
             * Entradas: nombre, telefono, direccion, horario, tematica y tipo de establecimiento
             * Salidas: -
             * Descripcion: este metodo permite al usuario insertar un nuevo establecimiento en el servidor
             *
             */
            public void insertarEstablecimiento(String nom, String tel, String dir, String hor, String tem, String tipo) {
                try {
                    establishment = (Establecimiento) new AdapterWebService().execute(Constantes.ADD_ESTABLISHMENT, user, nom, dir, hor, tem, tipo, tel).get();
                    user = (Usuario) new AdapterWebService().execute(Constantes.GET_USER_BY_CORREO,user.getCorreo()).get();
                    try{
                        ArrayList<Establecimiento> establecimientos = (ArrayList<Establecimiento>) new AdapterWebService().execute(Constantes.GET_ESTABLISHMENT_BY_USUARIO,user.getIdUsuario()).get();
                        user.setEstablecimientos(establecimientos);
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
    }


}
