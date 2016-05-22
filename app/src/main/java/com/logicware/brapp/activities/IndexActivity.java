package com.logicware.brapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.entities.Usuario;

/**
 * Es la interfaz del menu principal despues de que el usuario
 * se logguea correctamente
 */
public class IndexActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario user = null;
    private Button resta;
    private Button bares;
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
        setContentView(R.layout.activity_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (Usuario)getIntent().getExtras().getSerializable("user");

        resta = (Button)findViewById(R.id.buttonResta);
        resta.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ListRestaurantesActivity.class);
                intent.putExtra("user", user);

                startActivity(intent);
            }
        });

       bares = (Button)findViewById(R.id.buttonBares);
        bares.setOnClickListener(new View.OnClickListener() {
            /**
             * Nombre: onClick
             * Entradas: la vista actual del componente
             * Salidas: -
             * Descripcion: es la encargada de darle funcionalidad al evento de dar clic
             *              sobre el boton.
             */
          @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ListBaresActivity.class);
                intent.putExtra("user", user);
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
            //this.finish();
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
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
            Intent intent = new Intent(IndexActivity.this, ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);

        } else if (id == R.id.nav_eventos) {
            Intent intent = new Intent(IndexActivity.this,ListEventosUsuarioActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }else if (id == R.id.nav_reservas) {
            Intent intent = new Intent(IndexActivity.this, ListReservasUsuarioActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
