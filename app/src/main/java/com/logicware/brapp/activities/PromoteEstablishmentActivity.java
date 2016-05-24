package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.List;

public class PromoteEstablishmentActivity extends AppCompatActivity {

    private Spinner estabecimientos;
    private Usuario user;
    private Button aceptar;
    private ArrayList<Establecimiento> establecimientosUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        try{
            establecimientosUser = (ArrayList<Establecimiento>) new AdapterWebService().execute(Constantes.GET_ESTABLISHMENT_BY_USUARIO,user.getIdUsuario()).get();
        } catch(Exception ex){
            ex.printStackTrace();
        }

        inicializarSpinner();

        aceptar = (Button)findViewById(R.id.buttonPromocion);
        aceptar.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {

                TextView titulo = (TextView) findViewById(R.id.tituloEvento);
                TextView fechaInicio = (TextView) findViewById(R.id.fechaInicio);
                TextView fechaFin = (TextView) findViewById(R.id.fechaFin);
                TextView descripcion = (TextView) findViewById(R.id.Descripcion);

                String esta=estabecimientos.getSelectedItem().toString();
                String tit = titulo.getText().toString();
                String fechI = fechaInicio.getText().toString();
                String fechF = fechaFin.getText().toString();
                String desc = descripcion.getText().toString();

                Establecimiento estable=obtenerEstablecimiento(esta);
                if (camposVacios(tit, fechI, fechF, desc)) {
                    mostrarError("Campos vacios", "Complete los campos vacios");
                } else {
                    crearPromocion(tit,fechI,fechF,desc,estable);
                    Intent intent = new Intent(PromoteEstablishmentActivity.this, IndexClientActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }

            private void modificarEstablecimiento(Establecimiento esta){
                int hasta=establecimientosUser.size();
                for(int i=0;i<hasta;i++){
                    if(esta.equals(establecimientosUser.get(i).getNombre())){
                        establecimientosUser.set(i,esta);
                    }
                }
            }

            private Establecimiento obtenerEstablecimiento(String esta){

                int hasta=establecimientosUser.size();
                for(int i=0;i<hasta;i++){
                    if(esta.equals(establecimientosUser.get(i).getNombre())){
                        return establecimientosUser.get(i);

                    }
                }
                return null;
            }
            /**
             * Nombre de Método: Campos Vacios
             * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
             * Salidas: boolean
             * Descripcion:  verifica que no existan campos vacios en los datos registrados por el usuario retornando Verdadero o Falso
             */

            private boolean camposVacios(String titulo, String fechaIni, String fechaFin, String Descripcion) {
                return titulo.isEmpty() || fechaIni.isEmpty() || fechaFin.isEmpty() || Descripcion.isEmpty() ;
            }

            /**
             * Nombre de Método: mostrar Error
             * Entradas: nombre del error y su descripcion
             * Salidas: void
             * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
             */
            private void mostrarError(String nombreError, String descripcion) {
                AlertDialog alerta = new AlertDialog.Builder(PromoteEstablishmentActivity.this).create();
                alerta.setTitle(nombreError);
                alerta.setMessage(descripcion);
                alerta.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                alerta.show();
            }

            private void crearPromocion(String titulo, String fechaIni, String fechaFin, String Descripcion , Establecimiento estable){
                Evento evento= new Evento();
                try {
                    Evento eve=(Evento) new AdapterWebService().execute(Constantes.ADD_EVENT, estable,fechaIni,fechaFin,Descripcion,titulo).get();
                    //estable.getEventos().add(eve);
                    modificarEstablecimiento(estable);
                    user.setEstablecimientos(establecimientosUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void inicializarSpinner() {
        estabecimientos=(Spinner)findViewById(R.id.spinnerEstablecimientos);
        List<String> listEstablecimientos= new ArrayList<String>();
        int hasta=establecimientosUser.size();

        for(int i=0; i<hasta;i++){
            listEstablecimientos.add(establecimientosUser.get(i).getNombre());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listEstablecimientos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estabecimientos.setAdapter(dataAdapter);


    }

}
