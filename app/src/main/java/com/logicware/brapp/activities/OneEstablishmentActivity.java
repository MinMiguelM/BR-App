package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.asus.br.R;
import com.logicware.brapp.Adapters.CustomAdapterComentarios;
import com.logicware.brapp.Adapters.ItemListComentarios;

import java.util.ArrayList;
import java.util.HashMap;

public class OneEstablishmentActivity extends AppCompatActivity {
    private TabHost tab;
    private ArrayList<HashMap<String, String>> comentarios;
    private String[] titleArray, subItemArray;

    private ListView lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tab = (TabHost) findViewById(R.id.tabHostCliente);
        tab.setup();

        TabHost.TabSpec tab1 = tab.newTabSpec("tab1");  //aspectos de cada Tab (pestaña)
        TabHost.TabSpec tab2 = tab.newTabSpec("tab2");
        TabHost.TabSpec tab3 = tab.newTabSpec("tab3");

        tab1.setIndicator("Modificar ");    //qué queremos que aparezca en las pestañas
        tab1.setContent(R.id.Modificar); //definimos el id de cada Tab (pestaña)

        tab2.setIndicator("Opiniones ");
        tab2.setContent(R.id.Calificación);

        tab3.setIndicator("Reservas");
        tab3.setContent(R.id.Reservas);

        tab.addTab(tab1);
        tab.addTab(tab2);
        tab.addTab(tab3);


        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.listViewCalificacion);
        lv1.setAdapter(new CustomAdapterComentarios(this, image_details));

    }

    private ArrayList getListData() {
        ArrayList<ItemListComentarios> results = new ArrayList<ItemListComentarios>();
        ItemListComentarios newsData = new ItemListComentarios();
        newsData.setNombreUsuario("Juan David ");
        newsData.setCalificacion("5");
        newsData.setComentarios("Horrible comida");


        ItemListComentarios newsData2 = new ItemListComentarios();
        newsData2.setNombreUsuario("locas of Democracy");
        newsData2.setCalificacion("5");
        newsData2.setComentarios("Horrible comida");


        results.add(newsData);
        results.add(newsData2);
        return results;
    }


}
