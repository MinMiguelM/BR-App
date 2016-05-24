package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.logicware.brapp.R;
import com.logicware.brapp.adapters.CustomAdapterEventos;
import com.logicware.brapp.adapters.CustomAdapterReservas;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Reserva;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Esta clase permite al usuario consultar sus reservas
 * */
public class ListReservasUsuarioActivity extends AppCompatActivity {

    private Usuario user = null;
    private Collection<Reserva> reservas = null;
    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     *              que la aplicacion pueda mostrar sus componentes graficos
     *              y funcionales
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reservas_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        try {
            reservas = (ArrayList<Reserva>) (new AdapterWebService().execute(Constantes.GET_BOOKING_BY_IDUSUARIO, user.getIdUsuario()).get());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(reservas == null || reservas.isEmpty())
        {
            AlertDialog ms = new AlertDialog.Builder(this).create();
            ms.setTitle("No tiene reservas asociadas");
            ms.show();
        }else
        {
            ArrayList image_details = (ArrayList) reservas;
            final ListView lv1 = (ListView) findViewById(R.id.listViewReservas);
            lv1.setAdapter(new CustomAdapterReservas(this, image_details));
        }

    }



}
