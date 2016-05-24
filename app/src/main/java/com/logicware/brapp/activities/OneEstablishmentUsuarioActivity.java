package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Evento;
import com.logicware.brapp.entities.Usuario;
/**
* esta clase permite al usuario acceder a una lista de
* opciones que se pueden realizar sobre un establecimiento en especifico
* */
public class OneEstablishmentUsuarioActivity extends AppCompatActivity {

    private Usuario user = null;
    private Establecimiento establishment;
    private Button comentario;
    private Button reserva;
    private Button evento;
    private Button conEventos;
    private Evento ev = new Evento();
    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     *              que la aplicacion pueda mostrar sus componentes graficos
     *              y funcionales
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_establishment_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");
        establishment = (Establecimiento)getIntent().getExtras().getSerializable("establecimiento");

        TextView mostrarNombre = (TextView)findViewById(R.id.textViewNomEsta) ;
        mostrarNombre.setText("Nombre: "+establishment.getNombre());

        TextView mostrarDireccion = (TextView)findViewById(R.id.textViewDirec) ;
        mostrarDireccion.setText("Dirección: "+establishment.getDireccion());

        TextView mostrarHorario = (TextView)findViewById(R.id.textViewHorario) ;
        mostrarHorario.setText("Horario: "+establishment.getHorario());

        TextView mostrarTelefono = (TextView)findViewById(R.id.textViewTelefono) ;
        mostrarTelefono.setText("Teléfono: "+establishment.getTelefono());

        TextView mostrarTematica = (TextView)findViewById(R.id.textViewTematica) ;
        mostrarTematica.setText("Temática: "+establishment.getTematica());

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
                intent.putExtra("establecimiento",establishment);
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
                intent.putExtra("user",user);
                intent.putExtra("establecimiento",establishment);
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
                intent.putExtra("user",user);
                intent.putExtra("establecimiento",establishment);
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
                intent.putExtra("establecimiento",establishment);
                startActivity(intent);
            }
        });
    }
    /**
     * Nombre: onBackPressed
     * Entradas: -
     * Salidas: -
     * Descripcion: Si el usuario de la aplicacion le da al boton
     *              de retroceso volvera al menu principal
     */
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(OneEstablishmentUsuarioActivity.this, IndexActivity.class);
        intent.putExtra("user",user);
        intent.putExtra("establecimiento",establishment);
        startActivity(intent);
    }

}
