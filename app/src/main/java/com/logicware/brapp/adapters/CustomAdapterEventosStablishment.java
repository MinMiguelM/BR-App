package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Evento;

import java.util.ArrayList;

/**
 *esta clase es un adaptador de lista para los eventos
 * dentro de un establecimiento
 */
public class CustomAdapterEventosStablishment extends BaseAdapter {


    private ArrayList<Evento> listaDatos;
    private LayoutInflater layoutInflater;

    public CustomAdapterEventosStablishment(Context aContext, ArrayList<Evento> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_row_eventos_stablishment, null);
            holder = new ViewHolder();
            holder.tituloViewSta = (TextView) convertView.findViewById(R.id.TituloSta);
            holder.fechaViewSta = (TextView) convertView.findViewById(R.id.FechaSta);
            holder.descripcionViewSta = (TextView) convertView.findViewById(R.id.DescripcionSta);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tituloViewSta.setText("        NOMBRE DEL EVENTO:  " + listaDatos.get(position).getTitulo());
        holder.fechaViewSta.setText("        FECHA:  " + listaDatos.get(position).getFecha_inicio()+" - "+listaDatos.get(position).getFecha_fin());
        holder.descripcionViewSta.setText("        DESCRIPCIÓN:  " + listaDatos.get(position).getDescripcion());
        //System.out.println("        DESCRIPCIÓN:  " + listaDatos.get(position).getDescripcion());
        return convertView;


    }

    static class ViewHolder {
        TextView tituloViewSta;
        TextView fechaViewSta;
        TextView descripcionViewSta;
    }
}
