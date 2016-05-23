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
*esta clase es un adaptador de lista para los comentarios presentados al cliente
*aceca de su establecimiento
*/
public class ComentAdapter extends ArrayAdapter<ComentForList> {
    /**
     * Nombre: ComentAdapter
     * Entradas: clase sobre la que se trabaja la vista , lista con los datos que se manejaran en el adaptador
     * Salidas:
     * Descripcion: constructor
     */
    public ComentAdapter(Context context,ArrayList<ComentForList> listaDatos){
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
