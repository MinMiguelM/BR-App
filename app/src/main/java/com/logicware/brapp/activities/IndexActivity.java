package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.meta.User;

/**
 * Es la interfaz del menu principal despues de que el usuario
 * se logguea correctamente
 */
public class IndexActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textName; // Name to display
    private User user = null;

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
        user = (User)getIntent().getExtras().getSerializable("user");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadBasisInfo();
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
            this.finish();
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * nombre: getTextName
     * Entradas: -
     * Salidas: el componente que despliega el nombre del usuario
     * Descripcion: trae el componente con id: textName que se encuentra en el
     *              layout
     */
    public TextView getTextName(){
        return (TextView)findViewById(R.id.textName);
    }

    /**
     * nombre: setTextName
     * Entradas: el valor a cambiar
     * Salidas: -
     * Descripcion: dado el valor a cambiar, se cambia el texto actual
     *              por el nuevo valor.
     */
    public void setTextName(CharSequence text){
        TextView t = (TextView)findViewById(R.id.textName);
        t.setText(text);
    }

    /**
     * nombre: loadBasisInfo
     * Entradas: -
     * Salidas: -
     * Descripcion: Carga la información basica del usuario en la pantalla
     *              temporalmente solo carga el nombre
     */
    public void loadBasisInfo(){
        try {
            System.out.println(user.getNombre());
            setTextName(user.getNombre());
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
