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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_establishment_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // user = this.user;

        TextView mostrarNombre = (TextView)findViewById(R.id.textViewSmallNombre) ;
        mostrarNombre.setText("NOMBRE POR EL MOMENTO");
        TextView mostrarID = (TextView)findViewById(R.id.textViewSmallID) ;
        //mostrarID.setText(String.valueOf(user.getIdUsuario()));
        mostrarID.setText("ID 3 POR EL MOMENTO");

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
                Intent intent = new Intent(OneEstablishmentUsuarioActivity.this, CalificacionEstablishmentActivity.class);// cambiar
                startActivity(intent);
            }
        });
    }
}
