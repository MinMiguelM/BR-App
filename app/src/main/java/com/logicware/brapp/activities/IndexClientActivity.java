package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.meta.Usuario;

/**
 * Es la interfaz del menu principal despues de que el cliente
 * se logguea correctamente
 */
public class IndexClientActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario user;
    private Button establecimiento;

    private Button promocion;
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

        setContentView(R.layout.activity_index_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Usuario)getIntent().getExtras().getSerializable("user");
        establecimiento = (Button)findViewById(R.id.buttonEstablecimiento);
       establecimiento.setOnClickListener(new View.OnClickListener() {
           /**
            * Nombre: onClick
            * Entradas: la vista actual del componente
            * Salidas: -
            * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
            *              sobre el boton.
            */
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(IndexClientActivity.this, ListEstablishmentActivity.class);
               startActivity(intent);
           }
       });


        promocion = (Button)findViewById(R.id.buttonPromocion);
        promocion.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexClientActivity.this, PromoteEstablishmentActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexClientActivity.this, createEstablishmentActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View v = navigationView.getHeaderView(0);
        TextView name = (TextView)v.findViewById(R.id.textName);
        try {
            name.setText(user.getNombre());
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Nombre: onBackPressed
     * Entradas: -
     * Salidas: -
     * Descripcion: Si el usuario de la aplicacion le da al boton
     *              de retroceso cerrara la aplicacion si la barra del menu
     *              no esta desplegada
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    /**
     * Nombre: onCreateOptionsMenu
     * Entradas: Menu
     * Salidas: retorna verdadero si pudo crear el menu
     * Descripcion: Crea un menu a desplegar en la interfaz
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.index_client, menu);
        return true;
    }

    /**
     * Nombre: onOptionsItemSelected
     * Entradas: Un item del munu desplegado
     * Salidas: Verdadero despues de haber llevado a cabo la tarea del boton
     * Descripcion: Se programa la funcionalidad despues de darle al boton de
     *              Configuracion que es deplegado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Nombre: onNavigationItemSelected
     * Entradas: item que representa un boton del menu desplegable
     * salidas: siempre retorna verdadero
     * Descripcion: Segun al item que se le haya dado clic se deberia implementar
     *              una funcionalidad para mostrar
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent intent = new Intent(IndexClientActivity.this, ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_establecimiento) {
            Intent intent = new Intent(IndexClientActivity.this, ListEstablishmentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_promocion) {
            Intent intent = new Intent(IndexClientActivity.this, PromoteEstablishmentActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
