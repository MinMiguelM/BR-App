package com.logicware.brapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.adapters.CustomAdapterComentariosStablishment;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Esta clase permite al usuario ver
 * los comentarios y la calificación de un establecimiento
 * */
public class CalificacionEstablishmentUsuarioActivity extends AppCompatActivity {

    private  ListView lv1;
    private Button editar ;
    private Establecimiento establishment = null;
    private Usuario user = null;
    private Collection<ComentarioYCalificacion> comentarios = null;
    private float promedio = 0;
    private RatingBar ratingBar = null;
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
        setContentView(R.layout.activity_calificacion_establishment_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");
        user = (Usuario) getIntent().getExtras().getSerializable("user");

        try{
            comentarios = (ArrayList<ComentarioYCalificacion>) new AdapterWebService().execute(Constantes.GET_COMMENTS_BY_IDESTABLECIMIENTO,establishment.getIdEstablecimiento()).get();
        }catch(Exception e){
            e.printStackTrace();
        }
        promedio = (float) establishment.getCalificacion_promedio();

        if(comentarios.isEmpty())
        {
            Mensaje();
        }else
        {
            ArrayList image_details = (ArrayList) comentarios;
            lv1 = (ListView) findViewById(R.id.calificacionlistUsuarioView);
            lv1.setAdapter(new CustomAdapterComentariosStablishment(this, image_details));
            ratingBar = (RatingBar)findViewById(R.id.ratingBarOne);
            //ratingBar.setNumStars((int)promedio);
            ratingBar.setRating(promedio);
            ratingBar.setIsIndicator(true);
            TextView ra = (TextView)findViewById(R.id.textView19) ;
            ra.setText(ra.getText()+String.valueOf(promedio));

        }

        editar = (Button)findViewById(R.id.button3);
        editar.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalificacionEstablishmentUsuarioActivity.this, ComentarioUsuarioActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("establecimiento",establishment);
                startActivity(intent);
            }
        });
    }
    /**
     * Nombre de Método: Mensaje
     * Entradas: -
     * Salidas: void
     * Descripcion:  imprime si no tiene eventos asociados
     */
    private void Mensaje() {
        android.app.AlertDialog alerta = new android.app.AlertDialog.Builder(CalificacionEstablishmentUsuarioActivity.this).create();
        alerta.setTitle("No comentario");
        alerta.setMessage("No tiene comentarios este establecimiento");
        alerta.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alerta.show();
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

        Intent intent = new Intent(CalificacionEstablishmentUsuarioActivity.this, OneEstablishmentUsuarioActivity.class);
        intent.putExtra("user",user);
        intent.putExtra("establecimiento",establishment);
        startActivity(intent);
    }
}
