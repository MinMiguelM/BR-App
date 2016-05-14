package com.logicware.brapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.br.R;

import java.util.ArrayList;

/**
 * Created by Camilo Andres on 13/05/2016.
 */
public class CustomAdapterComentarios extends BaseAdapter{


    private ArrayList<ItemListComentarios> listaDatos;
    private LayoutInflater layoutInflater;

    public CustomAdapterComentarios(Context aContext, ArrayList<ItemListComentarios> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_row_comentarios, null);
            holder = new ViewHolder();
            holder.nombreUsuarioView = (TextView) convertView.findViewById(R.id.nombre);
            holder.comentariosView = (TextView) convertView.findViewById(R.id.comentario);
            holder.calificacionView = (TextView) convertView.findViewById(R.id.calification);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombreUsuarioView.setText(listaDatos.get(position).getNombreUsuario());
        holder.calificacionView.setText("Calificacion:, " + listaDatos.get(position).getCalificacion());
        holder.comentariosView.setText("Comentarios: "+listaDatos.get(position).getComentarios());
        return convertView;



    }

    static class ViewHolder {
        TextView nombreUsuarioView;
        TextView comentariosView;
        TextView calificacionView;
    }
}
