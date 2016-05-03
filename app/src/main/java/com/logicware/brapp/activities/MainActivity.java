package com.logicware.brapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.br.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.meta.User;
import com.logicware.brapp.persistence.AdapterWebService;

import org.json.JSONObject;

/**
 * Esta clase se encarga de mostrarle al usuario
 * la primera pantalla para que pueda iniciar sesion
 * o registrarse
 */
public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Button createAccount;
    private Button loggin;
    private User user = null;
    public static String currentToken;

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
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        createAccount = (Button)findViewById(R.id.buttonRegistrarse);
        createAccount.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                //AdapterWebService.getUsersByCorreo(getApplicationContext(),"kb");
                //User user = new User(null, "montanez-m", "prueba", "3165498", "USUARIO", false, null);
                //AdapterWebService.insertUser(getApplicationContext(),user); Falta solucionar errores
            }
        });

        loggin = (Button)findViewById(R.id.buttonIniciarSesion);
        loggin.setOnClickListener(new View.OnClickListener() {

            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
                if (email.equalsIgnoreCase("") &&
                        password.equalsIgnoreCase("")) {
                    mostrarError("Campos vacíos", "Para iniciar sesión debe llenar los datos requeridos");
                } else if (email.equalsIgnoreCase(""))
                    mostrarError("Email vacío", "Para iniciar sesión hace falta señalar un email");
                else if (password.equalsIgnoreCase(""))
                    mostrarError("Password vacío", "Para iniciar sesión hace falta ingresar la contraseña");
                else if (!email.matches("[a-zA-Z][a-zA-Z_\\-\\.0-9]*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+"))
                    mostrarError("Email inválido", "Por favor ingrese un email válido para iniciar sesión");
                else
                    login(email, password);
            }
        });
        loginFacebook();
    }

    @Override
    public void onStart(){
        super.onStart();
        SharedPreferences settings;
        settings = getSharedPreferences("PreferencesUser", Context.MODE_PRIVATE);
        currentToken = settings.getString("key_token", null);
        if(currentToken != null) {
            try {
                user = (User)new AdapterWebService().execute(Constantes.GET_USER_BY_TOKEN,currentToken).get();
                System.out.println(user + "   " + currentToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(user != null && user.getRol().equals("USUARIO")){
                Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }else if(user != null && user.getRol().equals("CLIENTE")) {
                //Mandar a activity client
                /*Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);*/
            }
        }
    }

    /**
     * Nombre: login
     * Entradas: email del usuario, y password
     * Salidas: -
     * Descripción: valida si el usuario está registrado para que pueda ingresar
     *              a la aplicación
     */
    public void login(String email, String password){
        // If the token's date expired, renove after logIn
        if(email.equals(user.getCorreo()) && password.equals(user.getPassword())){
            Intent intent = new Intent(MainActivity.this, IndexActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }else
            mostrarError("No se pudo ingresar","Email o contraseña incorrectas");

    }

    /**
     * Nombre de Método: mostrar Error
     * Entradas: nombre del error y su descripcion
     * Salidas: void
     * Descripcion:  imprime una alerta para el usuario que verifica si hay errores
     */
    private void mostrarError(String nombreError, String descripcion) {
        AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();
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
     * Nombre de Método: loginFacebook
     * Entradas:
     * Salidas: void
     * Descripcion: Cuando el usuario le da clic al boton de ingresar con facebook
     *              se debe mirar tres posibles estados de acuerdo a la transaccion
     *              Exito: se le muestra la actividad de inicio
     *              Cancel: simplemente no se hace nada
     *              Error: se muestra mensaje de error
     */
    public void loginFacebook(){
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        // loginButton.setReadPermissions(); Cambiar permisos de lectura
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    /*user = new User(new Long(1238), object.getString("first_name"), null, null, null, "USUARIO", "true", AccessToken.getCurrentAccessToken().toString(),new ArrayList<Establishment>());
                                    String jsonUser = user.serializeUser();
                                    SharedPreferences preferencesUser = getSharedPreferences("PreferencesUser", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferencesUser.edit();
                                    editor.putString("key_userObject", jsonUser);
                                    editor.commit();*/
                                    System.out.println("hello");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name");
                request.setParameters(parameters);
                request.executeAsync();
                intent.putExtra("user", user);
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

    /**
     * Nombre de Método: onActivityResult
     * Entradas: codigo de solicitud, codigo de resultado, data
     * Salidas: void
     * Descripcion: Maneja la respuesta de facebook.
     *              De acuerdo a esto, se mira si la transaccion se pudo llevar a cualquiera
     *              de los tres estados posibles.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
