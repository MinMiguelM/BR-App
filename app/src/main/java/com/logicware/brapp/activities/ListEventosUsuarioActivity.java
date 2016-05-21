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
     /*   Evento ev1 = new Evento();
        Evento ev2 = new Evento();
        Establecimiento es = new Establecimiento();
        es.setNombre("PRIMER ESTABLEMCIENTO");
        ev1.setDescripcion("EVENTO EN TAL RESTARUANTE BLA BLA BLA");
        ev1.setFecha_fin("Termina el 20 de algo");
        ev1.setFecha_inicio("comienza el 19 de algo");
        ev1.setTitulo("PARTYYYY");
        ev2.setDescripcion("EVENTO EN TAL RESTARUANTE BLA BLA BLA");
        ev2.setFecha_fin("Termina el 20 de algo");
        ev2.setFecha_inicio("comienza el 19 de algo");
        ev2.setTitulo("PARTYYYY");
        ev1.setEstablecimiento(es);
        ev2.setEstablecimiento(es);
        eventos.add(ev1);
        eventos.add(ev2);*/


        if(eventos.isEmpty())
        {
            AlertDialog ms = new AlertDialog.Builder(this).create();
            ms.setTitle("No tiene eventos asociados");
            ms.show();
           /* Intent intent = new Intent(ListEventosUsuarioActivity.this,IndexActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);*/
        }else
        {
            ArrayList image_details = (ArrayList) eventos;
            final ListView lv1 = (ListView) findViewById(R.id.listViewEventos);
            lv1.setAdapter(new CustomAdapterEventos(this, image_details));
        }

    }

  /*  private void getListData() {
        ArrayList<ItemListEventos> results = new ArrayList<ItemListEventos>();
        ItemListEventos newsData = new ItemListEventos();
        newsData.setDia("JUEVES 19 DE MAYO");
        newsData.setHora("3 AM");
        newsData.setEstablecimiento("BAR de la esquina");

        ItemListEventos newsData2 = new ItemListEventos();
        newsData2.setDia("VIERNES 20 DE MAYO");
        newsData2.setHora("9 PM");
        newsData2.setEstablecimiento("ZONA VIP BLACKSHEEP");


        results.add(newsData);
        results.add(newsData2);
        return results;
    }*/


}
