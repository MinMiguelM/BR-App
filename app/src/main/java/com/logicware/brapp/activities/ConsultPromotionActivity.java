package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.asus.br.R;
import com.logicware.brapp.adapters.PromocionForList;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;

import java.util.ArrayList;

public class ConsultPromotionActivity extends AppCompatActivity {
    private ArrayList<PromocionForList> promociones = new ArrayList<PromocionForList>();
    private Establecimiento establishment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_promotion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");


    }

    private void llenarPromociones() {
        int hasta = 0;
        hasta = establishment.getEventos().size();
        for (int i = 0; i < hasta; i++) {
            String titulo = ((ArrayList<Evento>) establishment.getEventos()).get(i).getTitulo();
            String fechaI = ((ArrayList<Evento>) establishment.getEventos()).get(i).getFecha_inicio();
            String fechaF = ((ArrayList<Evento>) establishment.getEventos()).get(i).getFecha_fin();
            String descripcion = ((ArrayList<Evento>) establishment.getEventos()).get(i).getDescripcion();
            PromocionForList comen = new PromocionForList(titulo, "Fecha de inicio: " + fechaI, "Fecha de Fin: " + fechaF, "Descripcion: " + descripcion);
            promociones.add(comen);
        }
    }
}
