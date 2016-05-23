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
*esta clase es un adaptador de lista para las promciones realizadas por el cliente
*sobre sus establecimientos
*/
public class PromocionAdapter extends ArrayAdapter<PromocionForList> {
    /**
     * Nombre: PromocionAdapter
     * Entradas: clase sobre la que se trabaja la vista, lista de datos con los que trabaja el adaptador
     * Salidas:
     * Descripcion: constructor
     */
    public PromocionAdapter(Context context,ArrayList<PromocionForList> listaDatos){
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
