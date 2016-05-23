package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logicware.brapp.R;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;
/*
* esta clase permite al usuario y cliente ver y modificar los
* datos personales del perfil
*
* */
public class ProfileActivity extends AppCompatActivity {

    private Button modificarPass;
    private Button modificarPerfil;
    private Usuario user;
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
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");


       final  EditText nombre=(EditText) findViewById(R.id.nombre_perfil);
        nombre.setText(user.getNombre());
        final EditText email=(EditText) findViewById(R.id.email_perfil);
        email.setText(user.getCorreo());

        final EditText telefono=(EditText) findViewById(R.id.telefono_perfil);
        telefono.setText(user.getTelefono());

        modificarPerfil=(Button) findViewById(R.id.buttonPerfil);
        modificarPerfil.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                String nomb=nombre.getText().toString();
                String ema=email.getText().toString();
                String tel=telefono.getText().toString();
                modificarUsuario(nomb, ema, tel);
            }


            /**
             * Nombre: modificarUsuario
             * Entradas: nombre, email y telefono del usuario
             * Salidas: -
             * Descripcion: modifica el usuario en la base de datos
             */
            private void modificarUsuario(String nomb, String ema, String tele) {
                try {
                    user.setCorreo(ema);
                    user.setNombre(nomb);
                    user.setTelefono(tele);
                    user=(Usuario)new AdapterWebService().execute(Constantes.UPDATE_USER, user).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });



        modificarPass = (Button)findViewById(R.id.buttonContraseña);
        modificarPass.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }

}
