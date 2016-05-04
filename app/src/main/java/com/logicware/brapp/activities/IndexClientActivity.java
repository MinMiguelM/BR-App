package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.asus.br.R;
import com.logicware.brapp.meta.User;

public class IndexClientActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (User)getIntent().getExtras().getSerializable("user");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected  void onResume(){
        super.onResume();
        loadBasisInfo();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.index_client, menu);
        return true;
    }

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

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
        return (TextView)getNavigation().findViewById(R.id.textName);
    }

    /**
     * nombre: setTextName
     * Entradas: el valor a cambiar
     * Salidas: -
     * Descripcion: dado el valor a cambiar, se cambia el texto actual
     *              por el nuevo valor.
     */
    public void setTextName(CharSequence text){
        TextView t = getTextName();
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
            /*
                Cargar imagen y el nombre
             */
            setTextName(user.getNombre());
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Nombre: getNavigation
     * Entradas: -
     * Salidas: retorna el Navigation de esta actividad
     * Descripcion: Retornar esta Navigation con sus componentes cargados.
     */
    public NavigationView getNavigation(){
        return (NavigationView)findViewById(R.id.nav_view);
    }
}
