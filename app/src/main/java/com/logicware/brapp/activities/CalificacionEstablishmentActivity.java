package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.ComentAdapter;
import com.logicware.brapp.adapters.ComentForList;

import java.util.ArrayList;

public class CalificacionEstablishmentActivity extends AppCompatActivity {
    private ArrayList<ComentForList> calificacion=new ArrayList<ComentForList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        llenarListaComentarios();
        ComentAdapter adapter = new ComentAdapter(this, calificacion);
        ListView listView = (ListView) findViewById(R.id.calificacionlistView );
        listView.setAdapter(adapter);
    }
    private void llenarListaComentarios() {
        calificacion.add(new ComentForList("Juan David", "me gusto la comida", "calificacion: 5"));
        calificacion.add(new ComentForList("Juan David", "muy malo el servicio", "calificacion: 3"));
        calificacion.add(new ComentForList("Juan David", "Pesima atencion", "calificacion: 1"));
    }

}
