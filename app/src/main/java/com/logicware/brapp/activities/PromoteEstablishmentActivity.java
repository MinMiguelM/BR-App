package com.logicware.brapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.asus.br.R;

import java.util.ArrayList;
import java.util.List;

public class PromoteEstablishmentActivity extends AppCompatActivity {
    private Spinner estabecimientos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_establishment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inicializarSpinner();
    }

    private void inicializarSpinner() {
        estabecimientos=(Spinner)findViewById(R.id.spinnerEstablecimientos);
        List<String> lista=new ArrayList<String>();

        lista.add("Establecimiento 1");
        lista.add("Establecimiento 2");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estabecimientos.setAdapter(dataAdapter);


    }

}
