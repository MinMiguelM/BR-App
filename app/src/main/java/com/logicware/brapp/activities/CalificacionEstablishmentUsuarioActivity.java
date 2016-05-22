package com.logicware.brapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.ComentAdapter;
import com.logicware.brapp.adapters.ComentForList;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;

import java.util.ArrayList;

import static android.R.layout.simple_expandable_list_item_1;

public class CalificacionEstablishmentUsuarioActivity extends AppCompatActivity {

    private ListView lista;
    private Button editar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion_establishment_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

        String[] Establecimientos = new String[]{"Comentario","Comentario"};
        ArrayAdapter array = new ArrayAdapter(CalificacionEstablishmentUsuarioActivity.this, simple_expandable_list_item_1, Establecimientos);
        lista= (ListView) findViewById(R.id.calificacionlistUsuarioView);
        lista.setAdapter(array);

        editar = (Button)findViewById(R.id.button3);
        editar.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalificacionEstablishmentUsuarioActivity.this, ComentarioUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

}
