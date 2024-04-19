package com.sc.astrolog.Pantallas;

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

import com.sc.astrolog.R;

import java.util.Calendar;

public class MeterObservacionNueva extends AppCompatActivity {

    SharedPreferences preferencias;
    EditText TituloCategoria;
    String Titulo;
    int categoria;
    int year;
    int month;
    int day;
    ImageButton botonCat1;
    ImageButton botonCat2;
    ImageButton botonCat3;
    ImageButton botonCat4;
    ImageButton botonCat5;
    CalendarView CalendarioVista;
    Calendar Calendario;
    Button botonCrearObs;
    Button botonVerLista;

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
        botonVerLista = findViewById(R.id.VerLaLista);
        Calendario = Calendar.getInstance();

        botonCat1.setOnClickListener(view -> Cat1());
        botonCat2.setOnClickListener(view -> Cat2());
        botonCat3.setOnClickListener(view -> Cat3());
        botonCat4.setOnClickListener(view -> Cat4());
        botonCat5.setOnClickListener(view -> Cat5());
        botonCrearObs.setOnClickListener(view -> CreameLaObservacion());
        botonVerLista.setOnClickListener(view -> verLista());
        preferencias = getSharedPreferences("DatosObs", Context.MODE_PRIVATE);
        CalendarioVista.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int anio, int mes, int dia)
            {
                Toast.makeText(MeterObservacionNueva.this, "Se ha cambiado la fecha a " + dia + "/" + (mes + 1) +"/" +anio, Toast.LENGTH_SHORT).show();
                year = anio;
                month = mes;
                day = dia;
            }
        });
    }
    public void Cat1()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 1", Toast.LENGTH_SHORT).show();
        categoria=1;
    }
    public void Cat2()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 2", Toast.LENGTH_SHORT).show();
        categoria =2;
    }
    public void Cat3()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 3", Toast.LENGTH_SHORT).show();
        categoria =3;
    }
    public void Cat4()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 4", Toast.LENGTH_SHORT).show();
        categoria =4;
    }
    public void Cat5()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 5", Toast.LENGTH_SHORT).show();
        categoria =5;
    }

    public void CreameLaObservacion()
    {
        Titulo = TituloCategoria.getText().toString();
        Intent intent = new Intent(this, NumeroDescubrimientos.class);
        intent.putExtra("nombre", Titulo);
        intent.putExtra("Categoria", categoria);
        intent.putExtra("Año", year);
        intent.putExtra("Mes", month);
        intent.putExtra("Dia", day);
        guardarDat(Titulo,categoria,year,month,day);
        Toast.makeText(MeterObservacionNueva.this, Titulo + ", " + categoria + ", " + day+"/"+(month+1)+"/"+year, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    void guardarDat(String Titulo, int categoria,int year, int month, int day)
    {
        //declaramos las variables y valores a guardar
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("nombreAGuardar", Titulo);
        editor.putInt("CategoriaAGuardar",categoria);
        editor.putInt("AñoAGuardar",year);
        editor.putInt("MesAGuardar",month);
        editor.putInt("DiaAGuardar",day);
        editor.commit();
    }
    void verLista()
    {
        Intent intent = new Intent(this, NumeroDescubrimientos.class);
        startActivity(intent);
    }
}