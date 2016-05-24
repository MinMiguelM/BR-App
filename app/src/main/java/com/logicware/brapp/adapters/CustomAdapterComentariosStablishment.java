package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.ComentarioYCalificacion;

import java.util.ArrayList;

/**
 *esta clase es un adaptador de lista para los comentarios presentados al usuario
 *aceca de un establecimiento
 */
public class CustomAdapterComentariosStablishment extends BaseAdapter {


    private ArrayList<ComentarioYCalificacion> listaDatos;
    private LayoutInflater layoutInflater;

    public CustomAdapterComentariosStablishment(Context aContext, ArrayList<ComentarioYCalificacion> listData) {
        this.listaDatos = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    /**
     * Nombre: getCount
     * Entradas: -
     * Salidas: tamaño de la lista de datos
     * Descripcion: get
     */
    @Override
    public int getCount() {
        return listaDatos.size();
    }
    /**
     * Nombre: getItem
     * Entradas: posición
     * Salidas: el objeto en la posicion de la lista de datos
     * Descripcion: get
     */
    @Override
    public Object getItem(int position) {
        return listaDatos.get(position);
    }
    /**
     * Nombre: getItemId
     * Entradas: posición
     * Salidas: posicion de la lista dada
     * Descripcion: get
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * Nombre: getView
     * Entradas: posicion, vista a convertir, grupo de vistas
     * Salidas: Vista
     * Descripcion: obtiene la vista sobre la que se va a trabajar y a partir de ella
     *              modifica el formato de la lista para que esta tenga
     *              sub items
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.list_row_comentarios_stablishment, null);
            holder = new ViewHolder();
            holder.DesViewSta = (TextView) convertView.findViewById(R.id.DesSta);
            holder.PromedioViewSta = (TextView) convertView.findViewById(R.id.PromedioSta);
            holder.NomViewSta = (TextView) convertView.findViewById(R.id.NomComen);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.DesViewSta.setText("        COMENTARIO:  " + listaDatos.get(position).getDescripcion());
        holder.PromedioViewSta.setText("        CALIFICACIÓN:  " + listaDatos.get(position).getCalificacion());
        holder.NomViewSta.setText("        USUARIO:  " + listaDatos.get(position).getUsuario().getNombre());
        //System.out.println("        calificacion  " +listaDatos.get(position).getCalificacion());
        return convertView;
    }

    static class ViewHolder {
        TextView DesViewSta;
        TextView PromedioViewSta;
        TextView NomViewSta;
    }
}
