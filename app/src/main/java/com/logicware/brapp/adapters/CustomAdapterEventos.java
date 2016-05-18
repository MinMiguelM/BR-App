package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.br.R;

import java.util.ArrayList;

/**
 * Created by Pipe on 15/05/2016.
 */
public class CustomAdapterEventos extends BaseAdapter {


    private ArrayList<ItemListEventos> listaDatos;
    private LayoutInflater layoutInflater;
    public CustomAdapterEventos(Context aContext, ArrayList<ItemListEventos> listData) {
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
            holder.horaView = (TextView) convertView.findViewById(R.id.Hora);
            holder.diaView = (TextView) convertView.findViewById(R.id.Dia);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.establecimientoView.setText(listaDatos.get(position).getEstablecimiento());
        holder.diaView.setText("Dia: " + listaDatos.get(position).getDia());
        holder.horaView.setText("Hora: " + listaDatos.get(position).getHora());
        return convertView;


    }

    static class ViewHolder {
        TextView establecimientoView;
        TextView horaView;
        TextView diaView;
    }
}
