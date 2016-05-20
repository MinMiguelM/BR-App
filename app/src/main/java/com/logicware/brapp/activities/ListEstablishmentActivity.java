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
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_expandable_list_item_1;

public class ListEstablishmentActivity extends AppCompatActivity {

    private ListView lista;
    private Usuario user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        List<String> establecimientos= new ArrayList<String>();
        int hasta=user.getEstablecimientos().size();

        for(int i=0; i<hasta;i++){
            establecimientos.add(((ArrayList<Establecimiento>)user.getEstablecimientos()).get(i).getNombre());

        }

        ArrayAdapter array = new ArrayAdapter(ListEstablishmentActivity.this,simple_expandable_list_item_1,establecimientos);

        lista= (ListView) findViewById(R.id.listViewCliente);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListEstablishmentActivity.this, OneEstablishmentActivity.class);
                intent.putExtra("establecimiento",((ArrayList<Establecimiento>)user.getEstablecimientos()).get(position));
                startActivity(intent);
            }
        });


    }



}
