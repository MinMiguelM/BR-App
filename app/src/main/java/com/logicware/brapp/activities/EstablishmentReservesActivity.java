package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.logicware.brapp.R;
import com.logicware.brapp.adapters.ReserveClienteAdapter;
import com.logicware.brapp.adapters.ReservesForList;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Reserva;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
/*esta clase permite al cliente mirar las
*reservas por establecimiento
* */
public class EstablishmentReservesActivity extends AppCompatActivity {

    private ArrayList<ReservesForList> reservas = new ArrayList<ReservesForList>();
    private Establecimiento establishment;
    ArrayList<Reserva> resevas = new ArrayList<>();

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
        setContentView(R.layout.activity_establishment_reserves);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");
        llenarListaReservas();
        ReserveClienteAdapter adapter = new ReserveClienteAdapter(this, reservas);
        ListView listView = (ListView) findViewById(R.id.listViewReservasCliente);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ListView lista = (ListView) findViewById(R.id.listViewReservasCliente);
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EstablishmentReservesActivity.this);
                dialogo1.setTitle("Reserva");
                dialogo1.setMessage("Seleccione si desea rechazar o aceptar la reserva");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Aceptar ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        aceptar(position);
                    }
                });
                dialogo1.setNegativeButton("Rechazar ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar(position);
                    }
                });
                dialogo1.show();
            }
            /**
             * Nombre: aceptar
             * Entradas: posicion del item seleccionado en la lista
             * Salidas: -
             * Descripcion: modifica el estado de la reserva de pendiente a Aceptada
             */
            private void aceptar(int position) {
                resevas.get(position).setEstado("Aceptado");
                try{
                    new AdapterWebService().execute(Constantes.UPDATE_BOOKING,resevas.get(position)).get();
                }catch(Exception e){
                    e.printStackTrace();
                }
                llenarListaReservas();
            }

            /**
             * Nombre: cancelar
             * Entradas: posicion del item selecionado en la lista
             * Salidas: -
             * Descripcion: modifica el estado de la reserva de pendiente a Rechazada
             */
            private void cancelar(int position) {
                resevas.get(position).setEstado("Rechazado");
                try{
                    new AdapterWebService().execute(Constantes.UPDATE_BOOKING,resevas.get(position)).get();
                }catch(Exception e){
                    e.printStackTrace();
                }
                llenarListaReservas();
            }
        });
    }

    /**
     * Nombre: llenarListaReservas
     * Entradas: -
     * Salidas: -
     * Descripcion: este metodo llena la lista de reservas que se muestran en la
     *              pantalla, con datos provenientes del servidor
     */
    private void llenarListaReservas() {
        int hasta = 0;
        try{
            resevas =(ArrayList<Reserva>) new AdapterWebService().execute(Constantes.GET_BOOKING_BY_IDESTABLECIMIENTO,establishment.getIdEstablecimiento()).get();
        }catch(Exception e){
            e.printStackTrace();
        }
        reservas.clear();
        hasta = resevas.size();
        for (int i = 0; i < hasta; i++) {
            String fecha = resevas.get(i).getFecha_reserva();
            String estado = resevas.get(i).getEstado();
            Long cantidadPersonas = resevas.get(i).getCantidad_personas();
            ReservesForList comen = new ReservesForList("Fecha: " + fecha, "Estado: " + estado, "Cantidad de personas: " + cantidadPersonas.toString());
            reservas.add(comen);
        }
    }

}
