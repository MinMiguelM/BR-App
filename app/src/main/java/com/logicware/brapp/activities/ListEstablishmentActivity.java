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

public class ListEstablishmentActivity extends AppCompatActivity {

    private ListView lista;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] Establecimientos = new String[] { "Establecimiento 1","Establecimiento 2"};
        ArrayAdapter array = new ArrayAdapter(ListEstablishmentActivity.this,simple_expandable_list_item_1,Establecimientos);

        lista= (ListView) findViewById(R.id.listViewCliente);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListEstablishmentActivity.this, OneEstablishmentActivity.class);
                startActivity(intent);
            }
        });


    }



}
