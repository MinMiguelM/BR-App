package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.br.R;

public class CreateAccountActivity extends AppCompatActivity {

    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confirm = (Button) findViewById(R.id.confirmar);
        confirm.setOnClickListener(new View.OnClickListener() {
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
                } //else

                //insertarUsuario(nom, tel, ema, pas1);

                /*if(emailErroneo(ema)){
                    mostrarError("Email incorrecto","El formato del email es incorrecto.");
                } FALTA IMPLEMENTACION*/

                /*if(emailRegistrado(ema)){
                    mostrarError("Email registrado","El email a registrar ya se encontro en la base de datos");
                } */


            }

            /*
            * Nombre de Método: Campos Vacios
            * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
            * Salidas: boolean
            * Descripcion:  verifica que no existan campos vacios en los datos registrados por el usuario retornando Verdadero o Falso
            */

            private boolean camposVacios(String nombre, String telefono, String email, String pass1, String pass2) {
                return nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty();
            }

            /*
            * Nombre de Método: Email Erroneo
            * Entradas: email
            * Salidas: boolean
            * Descripcion: verifica el formato del email escrito por el usuario
            */

            private boolean emailErroneo(String email) {
                // metodo de miguel
                return false;
            }

            /*
            * Nombre de Método: Email Registrado
            * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
            * Salidas: boolean
            * Descripcion: verifica que el email que registrara el usuario no exista en el sistema
            */

            private boolean emailRegistrado(String email) {
                //implementar
                return false;
            }

            /*
            * Nombre de Método: contrasenas Distintas
            * Entradas: contraseña y confirmacion de contraseña
            * Salidas: boolean
            * Descripcion: Verifica que la contraseña y su confirmacion sean iguales
            */
            private boolean contrasenasDistintas(String pass1, String pass2) {
                return !pass1.equals(pass2);

            }

            /*
            * Nombre de Método: Insertar Usuario
            * Entradas: nombre del usuario, telefono, email, contraseña y confirmacion de contraseña
            * Salidas: void
            * Descripcion:  inserta el usuario a la base de datos del sistema
            */

            /*public void insertarUsuario(String nom, String telefono, String email, String pass) {
                String nombre = nom;
                String correo = email;
                String contrasena = pass;
                String numCelular = telefono;
                boolean linkFacebook = false;
                String tokenFacebook = "";
                //meter a la base de Datos
            } */

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