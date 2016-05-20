package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Establecimiento;

public class OneEstablishmentActivity extends AppCompatActivity {
    private Establecimiento establishment;
    private Button modificar;
    private Button comentario;
    private Button reserva;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        establishment = (Establecimiento)getIntent().getExtras().getSerializable("establecimiento");

        modificar = (Button)findViewById(R.id.buttonModificar);
        modificar.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneEstablishmentActivity.this, ModifyEstablishmentActivity.class);
                intent.putExtra("establecimiento", establishment);
                startActivity(intent);
            }
        });

        comentario = (Button)findViewById(R.id.buttonComentario);
        comentario.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneEstablishmentActivity.this, CalificacionEstablishmentActivity.class);
                intent.putExtra("establecimiento", establishment);
                startActivity(intent);
            }
        });

        reserva = (Button)findViewById(R.id.buttonReserva);
        reserva.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneEstablishmentActivity.this, EstablishmentReservesActivity.class);
                startActivity(intent);
            }
        });
    }








}
