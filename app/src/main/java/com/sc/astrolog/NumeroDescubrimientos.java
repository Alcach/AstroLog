package com.sc.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NumeroDescubrimientos extends AppCompatActivity {
    Button botonNuevaObs;
    ListView ObservacionesListView;
    List<Observacion> Observaciones;
    ObservacionAdapter adapter;
    int anio;
    int mes;
    int dia;
    int categoria;
    String Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numero_descubrimientos);
        botonNuevaObs = findViewById(R.id.BotonAIrMeterObs);
        botonNuevaObs.setOnClickListener(view -> IntroducirNuevaObs());
        Observaciones = new ArrayList<>();
        Observaciones.add(new Observacion("aaa1", R.drawable.ic_launcher_background, 1, new Date()));
        Observaciones.add(new Observacion("aaa2", R.drawable.ic_launcher_foreground, 2, new Date()));

        Intent intent = getIntent();
        Nombre = intent.getStringExtra("nombre");
        categoria = intent.getIntExtra("Categoria", 1);
        anio = intent.getIntExtra("Año", 2000);
        mes = intent.getIntExtra("Mes", 1);
        dia = intent.getIntExtra("Dia", 1);
        // Vista del listado
        ObservacionesListView = findViewById(R.id.ListaObserva);

        // Vincular la vista de cada fila a los datos
        adapter = new ObservacionAdapter(this, R.layout.activity_plantilla_lista_observacion, Observaciones);

        // Vincular el adapta a la vista del listado
        ObservacionesListView.setAdapter(adapter);
    }
    private void IntroducirNuevaObs()
    {
        Intent intent = new Intent(this, MeterObservacionNueva.class);
        startActivity(intent);
    }
}