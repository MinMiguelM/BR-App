package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.ComentAdapter;
import com.logicware.brapp.adapters.ComentForList;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;

import java.util.ArrayList;

public class CalificacionEstablishmentActivity extends AppCompatActivity {
    private ArrayList<ComentForList> calificaciones = new ArrayList<ComentForList>();
    private Establecimiento establishment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

        llenarListaComentarios();
        ComentAdapter adapter = new ComentAdapter(this, calificaciones);
        ListView listView = (ListView) findViewById(R.id.calificacionlistView);
        listView.setAdapter(adapter);
    }

    private void llenarListaComentarios() {

        int hasta = 0;
        hasta = establishment.getComentariosYCalificaciones().size();
        for (int i = 0; i < hasta; i++) {
            String nombre = ((ArrayList<ComentarioYCalificacion>) establishment.getComentariosYCalificaciones()).get(i).getUsuario().getNombre();
            String descripcion = ((ArrayList<ComentarioYCalificacion>) establishment.getComentariosYCalificaciones()).get(i).getDescripcion();
            Integer calificacion = ((ArrayList<ComentarioYCalificacion>) establishment.getComentariosYCalificaciones()).get(i).getCalificacion();
            ComentForList comen = new ComentForList(nombre, descripcion, "calificacion: " + calificacion.toString());
            calificaciones.add(comen);
        }


    }

}
