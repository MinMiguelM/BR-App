package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.logicware.brapp.R;
import com.logicware.brapp.adapters.ComentAdapter;
import com.logicware.brapp.adapters.ComentForList;
import com.logicware.brapp.entities.ComentarioYCalificacion;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;

/**
 * Esta clase se encarga de mostrarle al cliente
 * la pantalla de los comentarios recibidos para un
 * establecimiento
 */
public class CalificacionEstablishmentActivity extends AppCompatActivity {

    private ArrayList<ComentForList> calificaciones = new ArrayList<ComentForList>();
    private Establecimiento establishment;
    private ArrayList<ComentarioYCalificacion> comments = new ArrayList<>();

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
        setContentView(R.layout.activity_calificacion_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        establishment = (Establecimiento) getIntent().getExtras().getSerializable("establecimiento");

        try{
            comments = (ArrayList<ComentarioYCalificacion>) new AdapterWebService().execute(Constantes.GET_COMMENTS_BY_IDESTABLECIMIENTO,establishment.getIdEstablecimiento()).get();
        }catch(Exception e){
            e.printStackTrace();
        }

        llenarListaComentarios();
        ComentAdapter adapter = new ComentAdapter(this, calificaciones);
        ListView listView = (ListView) findViewById(R.id.calificacionlistView);
        listView.setAdapter(adapter);
        float calificacion = calificacionPromedio();
        TextView caliProm = (TextView) findViewById(R.id.calificacionPromedio);
        caliProm.setText("La calificacion promedio su establecimiento es: " + calificacion);
        establishment.setCalificacion_promedio(calificacion);

        RatingBar estrellas = (RatingBar) findViewById(R.id.ratingBarComentarios);
        estrellas.setNumStars((int) calificacion);

        actualizarEstablecimiento();

    }
    /**
     * Nombre: llenarListaComentarios
     * Entradas: -
     * Salidas: -
     * Descripcion: este metodo se encarga de llenar la lista
     *              de comentarios para el establecimiento, con datos
     *              provinientes del servidor
     */
    private void llenarListaComentarios() {

        int hasta = 0;
        hasta = comments.size();
        for (int i = 0; i < hasta; i++) {
            String nombre = comments.get(i).getUsuario().getNombre();
            String descripcion = comments.get(i).getDescripcion();
            Integer calificacion = comments.get(i).getCalificacion();
            ComentForList comen = new ComentForList(nombre, descripcion, "calificacion: " + calificacion.toString());
            calificaciones.add(comen);
        }


    }
    /**
     * Nombre: calificacionPromedio
     * Entradas: -
     * Salidas: un entero con la calificacion promedio
     * Descripcion: este metodo halla la calificacion promedio
     *              para el establecimiento
     */
    private int calificacionPromedio() {
        int hasta = 0;
        int contador = 0;
        hasta = comments.size();
        for (int i = 0; i < hasta; i++) {
            contador = contador + comments.get(i).getCalificacion();

        }
        return contador / hasta;

    }
    /**
     * Nombre: actualizarEstablecimiento
     * Entradas: -
     * Salidas: -
     * Descripcion: este metodo atualiza el establecimiento
     *              en el servidor
     */
    private void actualizarEstablecimiento() {
        try {
           establishment = (Establecimiento) new AdapterWebService().execute(Constantes.UPDATE_ESTABLISHMENT, establishment).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
