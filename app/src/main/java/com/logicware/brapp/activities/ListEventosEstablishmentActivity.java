package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.CustomAdapterEventosStablishment;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;

import java.util.ArrayList;
import java.util.Collection;

public class ListEventosEstablishmentActivity extends AppCompatActivity {

    private Establecimiento establishment;
    private Collection<Evento> eventos = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_eventos_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");
        eventos = establishment.getEventos();

        if(eventos.isEmpty())
        {
            Mensaje();
        }else
        {
            ArrayList image_details = (ArrayList) eventos;
            final ListView lv1 = (ListView) findViewById(R.id.listViewEventosEstablecimiento);
            lv1.setAdapter(new CustomAdapterEventosStablishment(this, image_details));
        }


    }

    private void Mensaje() {
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(ListEventosEstablishmentActivity.this).create();
        alerta.setTitle("No eventos");
        alerta.setMessage("No tiene eventos este establecimiento");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
    }
}
