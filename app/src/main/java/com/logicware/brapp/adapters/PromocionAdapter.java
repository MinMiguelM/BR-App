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
public class PromocionAdapter extends ArrayAdapter<PromocionForList> {

    public PromocionAdapter(Context context,ArrayList<PromocionForList> listaDatos){
        super(context,0,listaDatos);

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PromocionForList list = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_promocion, parent, false);
        }
        // Lookup view for data population
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloPromo);
        TextView fechaIni = (TextView) convertView.findViewById(R.id.fechI);
        TextView fechaFin = (TextView) convertView.findViewById(R.id.fechF);
        TextView descripcion=(TextView)convertView.findViewById(R.id.descripcionPromo);

        // Populate the data into the template view using the data object
        titulo.setText(list.getTitulo());
        fechaIni.setText(list.getFechaInicio());
        fechaFin.setText(list.getFechaFin());
        descripcion.setText(list.getDescripcion());
        // Return the completed view to render on screen
        return convertView;
    }
}
