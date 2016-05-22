package com.logicware.brapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.br.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.layout.simple_expandable_list_item_1;

public class ListEventosEstablishmentActivity extends AppCompatActivity {

    private ListView lista;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_eventos_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] Establecimientos = new String[]{"Evento y su info....","Evento y su info...."};
        ArrayAdapter array = new ArrayAdapter(ListEventosEstablishmentActivity.this, simple_expandable_list_item_1, Establecimientos);
        lista= (ListView) findViewById(R.id.listViewEventosEstablecimiento);
        lista.setAdapter(array);

    }
}
