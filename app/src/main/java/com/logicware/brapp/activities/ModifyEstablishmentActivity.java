package com.logicware.brapp.activities;

import android.app.AlertDialog;
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
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

/*esta clase permite al cliente modificar los datos de alguno de sus establecimientos
*
* */
public class ModifyEstablishmentActivity extends AppCompatActivity {

    private Establecimiento establishment;
    private Button modificar;

    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     * que la aplicacion pueda mostrar sus componentes graficos
     * y funcionales
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");


        final EditText nombre = (EditText) findViewById(R.id.modifiNombre);
        nombre.setText(establishment.getNombre());
        final EditText telefono = (EditText) findViewById(R.id.modifiTele);
        telefono.setText(establishment.getTelefono());
        final EditText direccion = (EditText) findViewById(R.id.modifiUbica);
        direccion.setText(establishment.getDireccion());
        final EditText tematica = (EditText) findViewById(R.id.modifiTema);
        tematica.setText(establishment.getTematica());


        modificar = (Button) findViewById(R.id.buttonModifiEstable);
        modificar.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                String nomb = nombre.getText().toString();
                String tele = telefono.getText().toString();
                String dir = direccion.getText().toString();
                String tema = tematica.getText().toString();
                if (modificarEstablecimiento(nomb, tele, dir, tema)) {
                    AlertDialog alerta = new AlertDialog.Builder(ModifyEstablishmentActivity.this).create();
                    alerta.setMessage("La modificación del establecimiento ha sido exitosa");
                    alerta.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ModifyEstablishmentActivity.this, OneEstablishmentActivity.class);
                            intent.putExtra("establecimiento", establishment);
                            startActivity(intent);
                        }

                    });
                    alerta.show();

                } else {
                    Intent intent = new Intent(ModifyEstablishmentActivity.this, OneEstablishmentActivity.class);
                    intent.putExtra("establecimiento", establishment);
                    startActivity(intent);
                    AlertDialog alerta = new AlertDialog.Builder(ModifyEstablishmentActivity.this).create();
                    alerta.setMessage("La modificación del establecimiento no ha sido exitosa");
                    alerta.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }

                    });
                }
            }

            /**
             * Nombre: modificarEstablecimiento
             * Entradas: nombre , telefono, direcion y tema dele stablecimiento
             * Salidas: boolean
             * Descripcion: este metodo se encarga de actualiza en el servidor los datos
             *              modificados del establecimiento
             */

            private boolean modificarEstablecimiento(String nom, String tele, String dir, String tema) {
                try {
                    establishment.setNombre(nom);
                    establishment.setTelefono(tele);
                    establishment.setTematica(tema);
                    establishment.setDireccion(dir);
                    establishment = (Establecimiento) new AdapterWebService().execute(Constantes.UPDATE_ESTABLISHMENT, establishment).get();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });


    }

}
