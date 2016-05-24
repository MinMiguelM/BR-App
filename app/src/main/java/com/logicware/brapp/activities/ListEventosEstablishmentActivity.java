package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.logicware.brapp.R;
import com.logicware.brapp.adapters.CustomAdapterEventosStablishment;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Esta clase permite al usuario consultar los eventos de un establecimiento
 * */
public class ListEventosEstablishmentActivity extends AppCompatActivity {

    private Establecimiento establishment;
    private Collection<Evento> eventos = null;
    private Usuario user = null;
    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     *              que la aplicacion pueda mostrar sus componentes graficos
     *              y funcionales
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_eventos_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");
        user = (Usuario)getIntent().getExtras().getSerializable("user");
        try {
            eventos = (ArrayList<Evento>) (new AdapterWebService().execute(Constantes.GET_EVENT_BY_IDESTABLECIMIENTO, establishment.getIdEstablecimiento()).get());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(eventos == null || eventos.isEmpty())
        {
            Mensaje();
        }else
        {
            ArrayList image_details = (ArrayList) eventos;
            final ListView lv1 = (ListView) findViewById(R.id.listViewEventosEstablecimiento);
            lv1.setAdapter(new CustomAdapterEventosStablishment(this, image_details));
        }


    }
    /**
     * Nombre de Método: Mensaje
     * Entradas: -
     * Salidas: void
     * Descripcion:  imprime si no tiene eventos asociados
     */
    private void Mensaje() {
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(ListEventosEstablishmentActivity.this).create();
        alerta.setTitle("No eventos");
        alerta.setMessage("No tiene eventos este establecimiento");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
    }

}
