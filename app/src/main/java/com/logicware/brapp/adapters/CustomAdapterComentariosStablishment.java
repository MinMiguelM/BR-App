package com.logicware.brapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Evento;

import java.util.ArrayList;

/**
 * Created by Pipe on 22/05/2016.
 */
public class CustomAdapterComentariosStablishment extends BaseAdapter {


    private ArrayList<ComentarioYCalificacion> listaDatos;
    private LayoutInflater layoutInflater;

    public CustomAdapterComentariosStablishment(Context aContext, ArrayList<ComentarioYCalificacion> listData) {
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
