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

public class ListRestaurantesActivity extends AppCompatActivity {

    private ListView lista;
    private Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurantes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Usuario)getIntent().getExtras().getSerializable("user");
        String[] Establecimientos = new String[] {"Restaruante 1","Restaruante 2","Restaruante 3","Restaruante 4","Restaruante 5"};
        ArrayAdapter array = new ArrayAdapter(ListRestaurantesActivity.this,simple_expandable_list_item_1,Establecimientos);

        lista= (ListView) findViewById(R.id.listViewUsuarioRes);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListRestaurantesActivity.this, OneEstablishmentUsuarioActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }
}
