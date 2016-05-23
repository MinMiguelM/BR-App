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
 * Created by Pipe on 22/05/2016.
 */
public class CustomAdapterEventosStablishment extends BaseAdapter {


    private ArrayList<Evento> listaDatos;
    private LayoutInflater layoutInflater;

    public CustomAdapterEventosStablishment(Context aContext, ArrayList<Evento> listData) {
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
