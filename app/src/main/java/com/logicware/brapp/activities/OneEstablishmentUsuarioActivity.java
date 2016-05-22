package com.logicware.brapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Usuario;

public class OneEstablishmentUsuarioActivity extends AppCompatActivity {

    private Usuario user = null;
    private Button comentario;
    private Button reserva;
    private Button evento;
    private Button conEventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_establishment_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        TextView mostrarNombre = (TextView)findViewById(R.id.textViewNomEsta) ;
        mostrarNombre.setText(user.getNombre());

        reserva = (Button)findViewById(R.id.buttonReservas);
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
                Intent intent = new Intent(OneEstablishmentUsuarioActivity.this, ReservaUsuarioActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        conEventos = (Button)findViewById(R.id.buttonConsultarEvento);
        conEventos.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneEstablishmentUsuarioActivity.this,  ListEventosEstablishmentActivity.class);
                //intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        comentario = (Button)findViewById(R.id.buttonComentarios);
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
                Intent intent = new Intent(OneEstablishmentUsuarioActivity.this,   CalificacionEstablishmentUsuarioActivity.class);
                //intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        evento = (Button)findViewById(R.id.buttonRealizarEvento);
        evento.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneEstablishmentUsuarioActivity.this, EventoUsuarioActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
}
