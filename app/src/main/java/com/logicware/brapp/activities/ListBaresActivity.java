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
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.R.layout.simple_expandable_list_item_1;

public class ListBaresActivity extends AppCompatActivity {
    private ListView lista;
    private Usuario user;
    private Collection<Establecimiento> esta = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bares);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");
        List<String> Establecimientos= new ArrayList<String>();

        try {
            esta = (Collection<Establecimiento>)new AdapterWebService().execute(Constantes.GET_ESTABLISHMENT_BY_TIPO,"Bar").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < esta.size() ;i++)
        {
            Establecimientos.add(((ArrayList<Establecimiento>)esta).get(i).getNombre());
        }

        ArrayAdapter array = new ArrayAdapter(ListBaresActivity.this,simple_expandable_list_item_1,Establecimientos);

        lista= (ListView) findViewById(R.id.listViewUsuarioBar);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListBaresActivity.this, OneEstablishmentUsuarioActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("establecimiento",((ArrayList<Establecimiento>)esta).get(position));
                startActivity(intent);
            }
        });

    }
}
