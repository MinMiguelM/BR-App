package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.br.R;

import java.util.ArrayList;

/**
 * Created by Camilo Andres on 22/05/2016.
 */
public class ReserveClienteAdapter extends ArrayAdapter<ReservesForList> {

    public ReserveClienteAdapter(Context context,ArrayList<ReservesForList> listaDatos){
        super(context,0,listaDatos);

    }
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
