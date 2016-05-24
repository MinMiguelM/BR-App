package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Reserva;

import java.util.ArrayList;

/**
 *esta clase es un adaptador de lista para las reservas
 * de un usuario
 */
public class CustomAdapterReservas extends BaseAdapter {


    private ArrayList<Reserva> listaDatos;
    private LayoutInflater layoutInflater;
    public CustomAdapterReservas(Context aContext, ArrayList<Reserva> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_row_eventos, null);
            holder = new ViewHolder();
            holder.fechaView = (TextView) convertView.findViewById(R.id.FechaFecha);
            holder.nombreView = (TextView) convertView.findViewById(R.id.Titulo);
            holder.descripcionView = (TextView) convertView.findViewById(R.id.Descripcion);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombreView.setText("FECHA DE LA RESERVA:  " + listaDatos.get(position).getFecha_reserva());
        holder.fechaView.setText("ESTADO DE LA RESERVA:  " + listaDatos.get(position).getEstado());
        holder.descripcionView.setText("CANTIDAD DE PERSONAS:  " + listaDatos.get(position).getCantidad_personas());
        return convertView;


    }

    static class ViewHolder {
        TextView fechaView;
        TextView nombreView;
        TextView descripcionView;
    }
}
