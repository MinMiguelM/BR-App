package com.logicware.brapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Usuario;

import static android.R.layout.simple_expandable_list_item_1;

public class ListBaresActivity extends AppCompatActivity {
    private ListView lista;
    private Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bares);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Usuario)getIntent().getExtras().getSerializable("user");
        String[] Establecimientos = new String[] {"Bar 1","Bar 2","Bar 3","Bar 4","Bar 5"};
        ArrayAdapter array = new ArrayAdapter(ListBaresActivity.this,simple_expandable_list_item_1,Establecimientos);

        lista= (ListView) findViewById(R.id.listViewUsuarioBar);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListBaresActivity.this, OneEstablishmentUsuarioActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }
}
