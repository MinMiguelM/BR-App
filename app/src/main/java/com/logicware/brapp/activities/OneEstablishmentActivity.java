package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Establecimiento;
/*
* esta clase permite al usuario acceder a una lista de
* opciones que se pueden realizar sobre un establecimiento en especifico
* */
public class OneEstablishmentActivity extends AppCompatActivity {
    private Establecimiento establishment;
    private Button modificar;
    private Button comentario;
    private Button reserva;
    private Button promocion;
    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     *              que la aplicacion pueda mostrar sus componentes graficos
     *              y funcionales
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

        modificar = (Button) findViewById(R.id.buttonModificar);
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

        comentario = (Button) findViewById(R.id.buttonComentario);
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

        reserva = (Button) findViewById(R.id.buttonReserva);
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
                intent.putExtra("establecimiento", establishment);


                startActivity(intent);
            }
        });

        promocion = (Button) findViewById(R.id.buttonEventos);
        promocion.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneEstablishmentActivity.this, ConsultPromotionActivity.class);
                intent.putExtra("establecimiento", establishment);
                startActivity(intent);
            }
        });
    }


}
