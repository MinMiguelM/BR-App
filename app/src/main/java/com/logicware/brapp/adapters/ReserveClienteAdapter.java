package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.logicware.brapp.R;

import java.util.ArrayList;

/*
*esta clase es un adaptador de lista para las reservas que son aceptadas y rechazas  por el cliente
*sobre sus establecimientos
*/
public class ReserveClienteAdapter extends ArrayAdapter<ReservesForList> {

    /**
     * Nombre: ReserveClienteAdapter
     * Entradas: clase sobre la que se trabaja la vista, lista de datos con los que trabaja el adaptador
     * Salidas:
     * Descripcion: constructor
     */
    public ReserveClienteAdapter(Context context,ArrayList<ReservesForList> listaDatos){
        super(context,0,listaDatos);

    }
    /**
     * Nombre: getView
     * Entradas: posicion, vista a convertir, grupo de vistas
     * Salidas: Vista
     * Descripcion: obtiene la vista sobre la que se va a trabajar y a partir de ella
     *              modifica el formato de la lista para que esta tenga
     *              sub items
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ReservesForList list = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reserves_cliente, parent, false);
        }
        // Lookup view for data population
        TextView fechaReserva = (TextView) convertView.findViewById(R.id.fechaReserva);
        TextView estado = (TextView) convertView.findViewById(R.id.estado);
        TextView personas = (TextView) convertView.findViewById(R.id.personas);

        // Populate the data into the template view using the data object
        fechaReserva.setText(list.getFecha_reserva());
        estado.setText(list.getEstado());
        personas.setText(list.getCantidad_personas());
        // Return the completed view to render on screen
        return convertView;
    }


}
