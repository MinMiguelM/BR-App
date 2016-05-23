package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.CustomAdapterEventos;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Usuario;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Pipe on 15/05/2016.
 */
public class ListEventosUsuarioActivity extends AppCompatActivity {

        private Usuario user = null;
        private Collection<Evento> eventos = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_eventos_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Usuario)getIntent().getExtras().getSerializable("user");
        eventos = user.getEventos();

        if(eventos.isEmpty())
        {
            AlertDialog ms = new AlertDialog.Builder(this).create();
            ms.setTitle("No tiene eventos asociados");
            ms.show();
        }else
        {
            ArrayList image_details = (ArrayList) eventos;
            final ListView lv1 = (ListView) findViewById(R.id.listViewEventos);
            lv1.setAdapter(new CustomAdapterEventos(this, image_details));
        }

    }
    

}
