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
 * Created by Camilo Andres on 16/05/2016.
 */
public class ComentAdapter extends ArrayAdapter<ComentForList> {

    public ComentAdapter(Context context,ArrayList<ComentForList> listaDatos){
       super(context,0,listaDatos);

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ComentForList list = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_content, parent, false);
        }
        // Lookup view for data population
        TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
        TextView calificacion = (TextView) convertView.findViewById(R.id.calificacion);
        TextView comentarios = (TextView) convertView.findViewById(R.id.comentario);

        // Populate the data into the template view using the data object
        nombre.setText(list.getNombre());
        calificacion.setText(list.getCalificacion());
        comentarios.setText(list.getComentarios());
        // Return the completed view to render on screen
        return convertView;
    }

}
