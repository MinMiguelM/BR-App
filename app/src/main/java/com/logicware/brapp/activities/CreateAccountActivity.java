package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.meta.User;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.concurrent.ExecutionException;

/**
 * Es la interfaz que permite a registrar cuentas nuevas
 * al sistema.
 */
public class CreateAccountActivity extends AppCompatActivity {

    private Button confirm;
    private User user;

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
        setContentView(R.layout.activity_crear_cuenta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confirm = (Button) findViewById(R.id.confirmar);
        confirm.setOnClickListener(new View.OnClickListener() {

            /**
             * Nombre: onClick
             * Entradas: vista actual del componente
             * Salidas: -
             * Descripción: valida que el usuario haya ingresado datos completos
             *              y valida la inserción de la información.
             */
            @Override
            public void onClick(View v) {

                TextView nombre = (TextView) findViewById(R.id.nombreUsuario);
                TextView telefono = (TextView) findViewById(R.id.telefonoUsuario);
                TextView email = (TextView) findViewById(R.id.emailUsuario);
                TextView pass1 = (TextView) findViewById(R.id.passUsuario1);
                TextView pass2 = (TextView) findViewById(R.id.passUsuario2);

                String nom = nombre.getText().toString();
                String tel = telefono.getText().toString();
                String ema = email.getText().toString();
                String pas1 = pass1.getText().toString();
                String pas2 = pass2.getText().toString();
                if (camposVacios(nom, tel, ema, pas1, pas2)) {
                    mostrarError("Campos vacios", "Complete los campos vacios");
                } else if (contrasenasDistintas(pas1, pas2)) {
                    mostrarError("Contraseñas Distintas", "La contraseña y su confirmacion son distintas");
                } else if (emailErroneo(ema)) {
                    mostrarError("Email incorrecto", "El email no es valido.");
                } else {
                    insertarUsuario(nom, tel, ema, pas1);
                    Intent intent = new Intent(CreateAccountActivity.this,IndexActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }

                /*else if(emailRegistrado(ema)){
                    mostrarError("Email registrado","El email a registrar ya se encontro en la base de datos");
                } else {
                    //insertarUsuario(nom, tel, ema, pas1);
                }*/
            }

            /**
             * Nombre de Método: Campos Vacios
             * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
             * Salidas: boolean
             * Descripcion:  verifica que no existan campos vacios en los datos registrados por el usuario retornando Verdadero o Falso
             */

            private boolean camposVacios(String nombre, String telefono, String email, String pass1, String pass2) {
                return nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty();
            }

            /**
             * Nombre de Método: Email Erroneo
             * Entradas: email
             * Salidas: boolean
             * Descripcion: verifica el formato del email escrito por el usuario
             */

            private boolean emailErroneo(String email) {
                return !email.matches("[a-zA-Z][a-zA-Z_\\-\\.0-9]*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+");
            }

            /**
             * Nombre de Método: Email Registrado
             * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
             * Salidas: boolean
             * Descripcion: verifica que el email que registrara el usuario no exista en el sistema
             */

            private boolean emailRegistrado(String email) {
                //implementar
                return false;
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
             * Nombre de Método: Insertar Usuario
             * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
             * Salidas: void
             * Descripcion:  inserta el usuario a la base de datos del sistema
             */

            public void insertarUsuario(String nom, String telefono, String email, String pass) {
                try {
                    user = (User)new AdapterWebService().execute(Constantes.ADD_USER,nom,telefono,email,pass,"false","1274021983","USUARIO").get();
                    if(user != null) {
                        SharedPreferences preferencesUser = getSharedPreferences("PreferencesUser", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencesUser.edit();
                        editor.putString("key_token", user.getToken());
                        editor.commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Nombre de Método: mostrar Error
             * Entradas: nombre del error y su descripcion
             * Salidas: void
             * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
             */
            private void mostrarError(String nombreError, String descripcion) {
                AlertDialog alerta = new AlertDialog.Builder(CreateAccountActivity.this).create();
                alerta.setTitle(nombreError);
                alerta.setMessage(descripcion);
                alerta.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                alerta.show();
            }
        });
    }
}