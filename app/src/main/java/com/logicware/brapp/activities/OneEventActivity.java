package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.CustomAdapterEventos;
import com.logicware.brapp.adapters.ItemListEventos;

import java.util.ArrayList;

/**
 * Created by Pipe on 15/05/2016.
 */
public class OneEventActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.listViewEventos);
        lv1.setAdapter(new CustomAdapterEventos(this, image_details));
    }

    private ArrayList getListData() {
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
    }


}
