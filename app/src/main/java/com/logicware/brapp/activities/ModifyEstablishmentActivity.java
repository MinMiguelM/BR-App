package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

public class ModifyEstablishmentActivity extends AppCompatActivity {
    private Establecimiento establishment;
    private Button modificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento)getIntent().getExtras().getSerializable("establecimiento");


        final EditText nombre=(EditText) findViewById(R.id.modifiNombre);
        nombre.setText(establishment.getNombre());
        final EditText telefono=(EditText) findViewById(R.id.modifiTele);
        telefono.setText(establishment.getTelefono());
        final EditText direccion=(EditText) findViewById(R.id.modifiUbica);
        direccion.setText(establishment.getDireccion());
        final EditText tematica=(EditText) findViewById(R.id.modifiTema);
        tematica.setText(establishment.getTematica());


        modificar=(Button)findViewById(R.id.buttonModifiEstable);
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
                String nomb=nombre.getText().toString();
                String tele=telefono.getText().toString();
                String dir= direccion.getText().toString();
                String tema=tematica.getText().toString();
                modificarEstablecimiento(nomb,tele,dir,tema);
                Intent intent = new Intent(ModifyEstablishmentActivity.this, OneEstablishmentActivity.class);
                intent.putExtra("establecimiento", establishment);
                startActivity(intent);
            }


            private void modificarEstablecimiento(String nom,String tele, String dir,String tema){
                try {
                    establishment.setNombre(nom);
                    establishment.setTelefono(tele);
                    establishment.setTematica(tema);
                    establishment.setDireccion(dir);
                    establishment= (Establecimiento)new AdapterWebService().execute(Constantes.UPDATE_ESTABLISHMENT, establishment).get();

                } catch (Exception e) {
                    e.printStackTrace();
                }





            }
        });


    }

}
