package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Evento;

import java.util.ArrayList;

/**
 * Created by Pipe on 15/05/2016.
 */
public class CustomAdapterEventos extends BaseAdapter {


    private ArrayList<Evento> listaDatos;
    private LayoutInflater layoutInflater;
    public CustomAdapterEventos(Context aContext, ArrayList<Evento> listData) {
        this.listaDatos = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listaDatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.list_row_eventos, null);
            holder = new ViewHolder();
            holder.establecimientoView = (TextView) convertView.findViewById(R.id.Establecimiento);
            holder.fechaView = (TextView) convertView.findViewById(R.id.Fecha);
            holder.nombreView = (TextView) convertView.findViewById(R.id.NombreEvento);
            holder.descripcionView = (TextView) convertView.findViewById(R.id.Descripcion);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.establecimientoView.setText("ESTABLECIMIENTO:  "+listaDatos.get(position).getEstablecimiento().getNombre());
        holder.nombreView.setText("NOMBRE DEL EVENTO:  " + listaDatos.get(position).getTitulo());
        holder.fechaView.setText("FECHA:  " + listaDatos.get(position).getFecha_inicio()+" - "+listaDatos.get(position).getFecha_fin());
        holder.descripcionView.setText("DESCRIPCIÓN:  " + listaDatos.get(position).getDescripcion());
        return convertView;


    }

    static class ViewHolder {
        TextView establecimientoView;
        TextView fechaView;
        TextView nombreView;
        TextView descripcionView;
    }
}
