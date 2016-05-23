package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;
/*
* esta clase permite al cliente y usuario cambiar la contraseña de su perfil
*
* */
public class ChangePasswordActivity extends AppCompatActivity {
    private Usuario user;
    private Button aceptar;
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
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Usuario) getIntent().getExtras().getSerializable("user");
        aceptar = (Button) findViewById(R.id.acepPass);
        aceptar.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {

                TextView passA = (TextView) findViewById(R.id.passAnterior);
                TextView pass1 = (TextView) findViewById(R.id.passNuevo);
                TextView pass2 = (TextView) findViewById(R.id.confirmNuevoPass);


                String passAnt = user.getContrasena();
                String passAnt2 = passA.getText().toString();
                String pas1 = pass1.getText().toString();
                String pas2 = pass2.getText().toString();


                if (camposVacios(passAnt2, pas1, pas2)) {
                    mostrarError("Campos vacios", "Complete los campos vacios");
                } else if (contrasenasDistintas(passAnt, passAnt2)) {
                    mostrarError("Contraseñas Anterior Distinta", "Su contraseña anterior no corresponde a la registrada en la aplicación.");
                } else if (contrasenasDistintas(pas1, pas2)) {
                    mostrarError("Contraseñas Distintas", "La nueva contraseña y su confirmacion no coinciden.");
                } else {
                    actualizarUsuario(pas1);
                    Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }

            }

            /**
             * Nombre de Método: Campos Vacios
             * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
             * Salidas: boolean
             * Descripcion:  verifica que no existan campos vacios en los datos registrados por el usuario retornando Verdadero o Falso
             */

            private boolean camposVacios(String passAnt, String pass1, String pass2) {
                return passAnt.isEmpty() || pass1.isEmpty() || pass2.isEmpty();
            }

            /**
             * Nombre de Método: contrasenas Distintas
             * Entradas: contraseña y confirmacion de contraseña
             * Salidas: boolean
             * Descripcion: Verifica que la contraseña y su confirmacion sean iguales
             */
            private boolean contrasenasDistintas(String pass1, String pass2) {
                return !pass1.equals(pass2);

            }

            /**
             * Nombre de Método: mostrar Error
             * Entradas: nombre del error y su descripcion
             * Salidas: void
             * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
             */
            private void mostrarError(String nombreError, String descripcion) {
                AlertDialog alerta = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                alerta.setTitle(nombreError);
                alerta.setMessage(descripcion);
                alerta.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                alerta.show();
            }
            /**
             * Nombre: actualizarUsuario
             * Entradas: contraseña nueva
             * Salidas: -
             * Descripcion: este metodo se encarga de actualizar la contraseña del usuario
             *              en el servidor
             */
            private void actualizarUsuario(String pas1) {
                try {
                    user.setContrasena(pas1);
                    user = (Usuario) new AdapterWebService().execute(Constantes.UPDATE_USER, user).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });


    }

}
