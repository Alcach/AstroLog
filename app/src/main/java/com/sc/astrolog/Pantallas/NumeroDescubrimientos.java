package com.sc.astrolog.Pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sc.astrolog.Clases.Observacion;
import com.sc.astrolog.Clases.ObservacionesLista;
import com.sc.astrolog.ObservacionAdapter;
import com.sc.astrolog.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NumeroDescubrimientos extends AppCompatActivity {
    ObservacionesLista observacionesLista;
    Button botonNuevaObs;
    ListView ObservacionesListView;
    ObservacionAdapter adapter;
    SharedPreferences preferencias;
    int anio;
    int mes;
    int dia;
    int minuto;
    int hora;
    int categoria;
    String Nombre;
    int fotoDescubr;
    Calendar calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numero_descubrimientos);
        botonNuevaObs = findViewById(R.id.BotonAIrMeterObs);
        ObservacionesListView = findViewById(R.id.ListaObserva);
        CargaTabla();
        meterNuevaObs();
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        // Vincular la vista de cada fila a los datos
        adapter = new ObservacionAdapter(this, R.layout.activity_plantilla_lista_observacion, observacionesLista.observaciones);
        ObservacionesListView.setAdapter(adapter);
        // Vista del listado
        botonNuevaObs.setOnClickListener(view -> IntroducirNuevaObs());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Guardar conversaciones
        guardarDat();
    }
    private void IntroducirNuevaObs()
    {
        guardarDat();
        finish();
    }
    void CargaTabla()
    {
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String json = preferences.getString("Observaciones", null);
        if (json == null) {
            observacionesLista = new ObservacionesLista();
        } else {
            observacionesLista = ObservacionesLista.convertirAJava(json);
        }
    }
    void guardarDat()
    {
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String json = observacionesLista.convertirAJson();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Observaciones", json);
        editor.commit();
    }
    void meterNuevaObs()
    {
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        Nombre = intent.getStringExtra("nombre");
        categoria = intent.getIntExtra("Categoria", 1);
        anio = intent.getIntExtra("Año", 2000);
        mes = intent.getIntExtra("Mes", 1);
        dia = intent.getIntExtra("Dia", 1);
        minuto=intent.getIntExtra("minuto",1);
        hora=intent.getIntExtra("hora",1);
        calendario = new GregorianCalendar();
        calendario.set(anio,mes,dia,hora,minuto);
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
        if(Nombre != null)
        {
            observacionesLista.observaciones.add(new Observacion(Nombre, fotoDescubr, categoria, calendario.getTime()));
        }
    }
}