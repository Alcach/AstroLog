package com.sc.astrolog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MeterObservacionNueva extends AppCompatActivity {

    SharedPreferences preferencias;
    EditText TituloCategoria;
    String Titulo;
    ImageButton botonCat1;
    ImageButton botonCat2;
    ImageButton botonCat3;
    ImageButton botonCat4;
    ImageButton botonCat5;
    CalendarView CalendarioVista;
    Calendar Calendario;
    Button botonCrearObs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_observacion_nueva);
        TituloCategoria = findViewById(R.id.NombreAIntroducir);
        botonCat1 = findViewById(R.id.Categoria1);
        botonCat2 = findViewById(R.id.Categoria2);
        botonCat3 = findViewById(R.id.Categoria3);
        botonCat4 = findViewById(R.id.Categoria4);
        botonCat5 = findViewById(R.id.Categoria5);
        botonCrearObs = findViewById(R.id.VolverALaLista);
        CalendarioVista = findViewById(R.id.Calendario);
        Calendario = Calendar.getInstance();
        DameFecha();

        botonCat1.setOnClickListener(view -> Cat1());
        botonCat2.setOnClickListener(view -> Cat2());
        botonCat3.setOnClickListener(view -> Cat3());
        botonCat4.setOnClickListener(view -> Cat4());
        botonCat5.setOnClickListener(view -> Cat5());
        botonCrearObs.setOnClickListener(view -> CreameLaObservacion());
        preferencias = getSharedPreferences("datosPrestamos", Context.MODE_PRIVATE);
        CalendarioVista.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int anio, int mes, int dia)
            {
                Toast.makeText(MeterObservacionNueva.this, "Se ha cambiado la fecha a " + dia + "/" + (mes + 1) +"/" +anio, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Cat1()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 1", Toast.LENGTH_SHORT).show();
    }
    public void Cat2()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 2", Toast.LENGTH_SHORT).show();
    }
    public void Cat3()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 3", Toast.LENGTH_SHORT).show();
    }
    public void Cat4()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 4", Toast.LENGTH_SHORT).show();
    }
    public void Cat5()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 5", Toast.LENGTH_SHORT).show();
    }

    public void CreameLaObservacion()
    {
        Titulo = TituloCategoria.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nombre", Titulo);
        guardarDat(Titulo);
    }
    void guardarDat(String Titulo)
    {
        //declaramos las variables y valores a guardar
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("nombreAGuardar", Titulo);
        editor.commit();
    }
    //Te da la fecha actual, por si estas en 2030 sea 2030
    public void DameFecha()
    {
        long fecha = CalendarioVista.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        Calendario.setTimeInMillis(fecha);
        String fechaSeleccionada = sdf.format(Calendario.getTime());
        Toast.makeText(MeterObservacionNueva.this, "La fecha es: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();
    }
}