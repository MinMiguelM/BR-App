package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.asus.br.R;
import static android.R.layout.simple_expandable_list_item_1;

/**
 * Created by Pipe on 14/05/2016.
 */
public class ListaEventoActivity  extends AppCompatActivity {

    private ListView lista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String[] Establecimientos = new String[]{"Restaurante", "Bar","Restaurante", "Bar","Restaurante", "Bar","Restaurante", "Bar","Restaurante", "Bar","Restaurante", "Bar","Restaurante", "Bar","Restaurante", "Bar"};
        ArrayAdapter array = new ArrayAdapter(ListaEventoActivity.this, simple_expandable_list_item_1, Establecimientos);

        lista = (ListView) findViewById(R.id.listViewUsuario);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(Establecimientos[position].contains("Bar"))
                {
                    Intent intent = new Intent(ListaEventoActivity.this, OneEventActivity.class);
                    startActivity(intent);
                }
                else if(Establecimientos[position].contains("Restaurante"))
                {
                    Intent intent = new Intent(ListaEventoActivity.this, OneResEventActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
