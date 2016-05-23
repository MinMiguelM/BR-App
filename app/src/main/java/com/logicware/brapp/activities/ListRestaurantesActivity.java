package com.logicware.brapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.R.layout.simple_expandable_list_item_1;

public class ListRestaurantesActivity extends AppCompatActivity {

    private ListView lista;
    private Usuario user;
    private Collection<Establecimiento> esta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurantes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<String> Establecimientos= new ArrayList<String>();
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        try {
            esta = (Collection<Establecimiento>)new AdapterWebService().execute(Constantes.GET_ESTABLISHMENT_BY_TIPO,"Restaurante").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       for(int i = 0; i < esta.size() ;i++)
        {
            Establecimientos.add(((ArrayList<Establecimiento>)esta).get(i).getNombre());
        }

        ArrayAdapter array = new ArrayAdapter(ListRestaurantesActivity.this,simple_expandable_list_item_1,Establecimientos);

        lista= (ListView) findViewById(R.id.listViewUsuarioRes);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListRestaurantesActivity.this, OneEstablishmentUsuarioActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("establecimiento",((ArrayList<Establecimiento>)esta).get(position));
                startActivity(intent);
            }
        });

    }
}
