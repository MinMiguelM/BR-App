package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Establecimiento;
import com.logicware.brapp.entities.Usuario;
import com.logicware.brapp.handlerWS.Constantes;
import com.logicware.brapp.persistence.AdapterWebService;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_expandable_list_item_1;
/*Esta clase muestra al cliente todos sus establecimientos asociados
* en forma de una lista
*   */
public class ListEstablishmentActivity extends AppCompatActivity {

    private ListView lista;
    private Usuario user;
    private ArrayList<Establecimiento> establecimientosUser = new ArrayList<>();
    /**
     * Nombre: onCreate
     * Entradas: Instancia del estado salvada
     * Salidas: -
     * Descripcion: Este metodo se encarga de cargar todo lo necesario para
     *              que la aplicacion pueda mostrar sus componentes graficos
     *              y funcionales
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");
        try{
            establecimientosUser = (ArrayList<Establecimiento>) new AdapterWebService().execute(Constantes.GET_ESTABLISHMENT_BY_USUARIO,user.getIdUsuario()).get();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        List<String> establecimientos= new ArrayList<String>();
        int hasta=establecimientosUser.size();

        for(int i=0; i<hasta;i++){
            establecimientos.add(establecimientosUser.get(i).getNombre());

        }

        ArrayAdapter array = new ArrayAdapter(ListEstablishmentActivity.this,simple_expandable_list_item_1,establecimientos);

        lista= (ListView) findViewById(R.id.listViewCliente);
        lista.setAdapter(array);
        /**
         * Nombre: setOnItemClickListener
         * Entradas: la vista actual del componente
         * Salidas: -
         * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
         *              sobre el algun elemento de la lista
         */
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListEstablishmentActivity.this, OneEstablishmentActivity.class);
                establecimientosUser.get(position).setUsuario(user);
                intent.putExtra("establecimiento", (establecimientosUser.get(position)));
                startActivity(intent);
            }
        });


    }



}
