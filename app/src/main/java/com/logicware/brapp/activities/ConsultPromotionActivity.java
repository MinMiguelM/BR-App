package com.logicware.brapp.activities;
/*
* permite al cliente consultar las promociones que han sido creadas
*
* */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.logicware.brapp.R;
import com.logicware.brapp.adapters.PromocionAdapter;
import com.logicware.brapp.adapters.PromocionForList;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.Collection;

public class ConsultPromotionActivity extends AppCompatActivity {
    private ArrayList<PromocionForList> promociones = new ArrayList<PromocionForList>();
    private Establecimiento establishment;
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
        setContentView(R.layout.activity_consult_promotion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

        llenarPromociones();
        if(promociones.isEmpty()){
            mostrarError("Sin promociones","Usted no ha creado promociones.");
        }else {
            PromocionAdapter adapter = new PromocionAdapter(this, promociones);
            ListView listView = (ListView) findViewById(R.id.listViewConsultPromo);
            listView.setAdapter(adapter);

        }
    }
    /**
     * Nombre: llenarPromociones
     * Entradas: -
     * Salidas: -
     * Descripcion: este metodo permite llenar la lista de promociones, con
     *              informacion proveniente del servidor
     */
    private void llenarPromociones() {
        int hasta = 0;
        Collection<Evento> events = new ArrayList<>();
        try {
            events = (ArrayList<Evento>) (new AdapterWebService().execute(Constantes.GET_EVENT_BY_IDESTABLECIMIENTO, establishment.getIdEstablecimiento()).get());
        }catch (Exception e){
            e.printStackTrace();
        }
        hasta = events.size();
        for (int i = 0; i < hasta; i++) {
            String titulo = ((ArrayList<Evento>) events).get(i).getTitulo();
            String fechaI = ((ArrayList<Evento>) events).get(i).getFecha_inicio();
            String fechaF = ((ArrayList<Evento>) events).get(i).getFecha_fin();
            String descripcion = ((ArrayList<Evento>) events).get(i).getDescripcion();
            PromocionForList comen = new PromocionForList(titulo,  fechaI,  fechaF, descripcion);
            promociones.add(comen);
        }
    }
    /**
     * Nombre de Método: mostrar Error
     * Entradas: nombre del error y su descripcion
     * Salidas: void
     * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
     */
    private void mostrarError(String nombreError, String descripcion) {
        AlertDialog alerta = new AlertDialog.Builder(ConsultPromotionActivity.this).create();
        alerta.setTitle(nombreError);
        alerta.setMessage(descripcion);
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
    }
}
