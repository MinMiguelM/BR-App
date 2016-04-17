package com.logicware.brapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.asus.br.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.logicware.brapp.meta.User;
import com.logicware.brapp.persistence.AdapterWebService;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        final String PREFS_NAME = "MyPrefsFile";

        createAccount = (Button)findViewById(R.id.buttonRegistrarse);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                //AdapterWebService.getUsersByCorreo(getApplicationContext(),"kb");
                User user = new User(null, "montanez-m", "prueba", "3165498", "USUARIO", false, null);
                AdapterWebService.insertUser(getApplicationContext(),user);
            }
        });

        //login();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        if(settings.getBoolean("my_first_time",true) || AccessToken.getCurrentAccessToken()==null) {
            login();
            settings.edit().putBoolean("my_first_time", false).commit();
        }else{
            Intent intent = new Intent(MainActivity.this, IndexActivity.class);
            startActivity(intent);
        }
    }

    /*
    *   Cuando el usuario le da clic al boton de ingresar con facebook
    *   se debe mirar tres posibles estados de acuerdo a la transaccion
    *   Exito: se le muestra la actividad de inicio
    *   Cancel: simplemente no se hace nada
    *   Error: se muestra mensaje de error
     */
    public void login(){
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        // loginButton.setReadPermissions(); Cambiar permisos de lectura
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(intent);
                // save token = loginResult.getAccessToken().getToken() a bases de datos
            }

            @Override
            public void onCancel() {
                // retornar a la misma ventana
            }

            @Override
            public void onError(FacebookException error) {
                // message of error
            }
        });
    }

    /*
     *  Maneja la respuesta de facebook.
     *  De acuerdo a esto, se mira si la transaccion se pudo llevar a cualquiera
     *  de los tres estados posibles.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
