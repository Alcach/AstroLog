package com.sc.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NumeroDescubrimientos extends AppCompatActivity {
    Button botonNuevaObs;
    ListView ObservacionesListView;
    List<Observacion> Observaciones= new ArrayList<>();
    ObservacionAdapter adapter;
    SharedPreferences preferencias;
    SharedPreferences ListaAnterior;
    int anio;
    int mes;
    int dia;

    String fechaPuesta;
    int categoria;
    String Nombre;
    int fotoDescubr;
    int indice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numero_descubrimientos);
        botonNuevaObs = findViewById(R.id.BotonAIrMeterObs);
        botonNuevaObs.setOnClickListener(view -> IntroducirNuevaObs());
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        ListaAnterior = getSharedPreferences("ListaGuard", Context.MODE_PRIVATE);
        // Vincular la vista de cada fila a los datos
        adapter = new ObservacionAdapter(this, R.layout.activity_plantilla_lista_observacion, Observaciones);
        ObservacionesListView = findViewById(R.id.ListaObserva);
        // Vincular el adapta a la vista del listado
        ObservacionesListView.setAdapter(adapter);
        cargarDat();
        //nuevo();

        // Vista del listado

        ObservacionesListView.setOnItemClickListener((adapterView, view, i, l) ->RevisionData() );

    }
    private void IntroducirNuevaObs()
    {
        /*
        Intent intent = new Intent(this, MeterObservacionNueva.class);
        startActivity(intent);
        */

        //guardarDat(Observaciones);
        finish();
    }
    public void nuevo()
    {
        Observaciones.add((Observaciones.size()),new Observacion(Nombre, fotoDescubr, categoria, new Date(anio,mes,dia)));
    }
    void guardarDat(String nombre, int categoria, int anio, int mes, int dia)
    {
        for (int i = 0; i < Observaciones.size(); i++) {
            
        }
        //declaramos las variables y valores a guardar
        SharedPreferences.Editor editor = ListaAnterior.edit();
        //editor.putStringSet("ListaGuardada",Lista);
        Toast.makeText(NumeroDescubrimientos.this, ""+indice , Toast.LENGTH_SHORT).show();
        editor.commit();

    }
    void RevisionData()
    {
        Toast.makeText(NumeroDescubrimientos.this, ""+ Nombre + ", "+ categoria + ", " +"("+dia+"/"+(mes + 1) +"/"+anio+") " + fechaPuesta , Toast.LENGTH_SHORT).show();
    }
    void cargarDat()
    {
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        Nombre = intent.getStringExtra("nombre");
        categoria = intent.getIntExtra("Categoria", 1);
        anio = intent.getIntExtra("Año", 2000);
        mes = intent.getIntExtra("Mes", 1);
        dia = intent.getIntExtra("Dia", 1);
        fechaPuesta = intent.getStringExtra("Fecha");
        if(categoria==1)
        {
            fotoDescubr = R.drawable.abrazado_a;
        }
        if(categoria==2)
        {
            fotoDescubr = R.drawable.actor_fav;
        }
        if(categoria==3)
        {
            fotoDescubr = R.drawable.arbol;
        }
        if(categoria==4)
        {
            fotoDescubr = R.drawable.bailando_con;
        }
        if(categoria==5)
        {
            fotoDescubr = R.drawable.bebiendo_junto_a;
        }


        Observaciones.add((Observaciones.size()),new Observacion(Nombre, fotoDescubr, categoria, new Date(fechaPuesta)));
    }
}