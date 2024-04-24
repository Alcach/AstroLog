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
    EditText TextoMin;
    EditText TextoHora;
    String titulo;
    int categoria =0;
    int year =0;
    int month =0;
    int day =0;
    int hour =0;
    int minute=0;
    ImageButton botonCat1;
    ImageButton botonCat2;
    ImageButton botonCat3;
    ImageButton botonCat4;
    ImageButton botonCat5;
    ImageButton botonCat6;
    ImageButton botonCat7;
    ImageButton botonCat8;
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
        botonCat6 = findViewById(R.id.Categoria6);
        botonCat7 = findViewById(R.id.Categoria7);
        botonCat8 = findViewById(R.id.Categoria8);
        botonCrearObs = findViewById(R.id.VolverALaLista);
        CalendarioVista = findViewById(R.id.Calendario);
        botonVerLista = findViewById(R.id.VerLaLista);
        TextoMin = findViewById(R.id.minuto);
        TextoHora = findViewById(R.id.hora);
        Calendario = Calendar.getInstance();
        //al pulsar los botones, pone una categoria(el boton 1 la categoria 1, etc...)
        botonCat1.setOnClickListener(view -> Cat1());
        botonCat2.setOnClickListener(view -> Cat2());
        botonCat3.setOnClickListener(view -> Cat3());
        botonCat4.setOnClickListener(view -> Cat4());
        botonCat5.setOnClickListener(view -> Cat5());
        botonCat6.setOnClickListener(view -> Cat6());
        botonCat7.setOnClickListener(view -> Cat7());
        botonCat8.setOnClickListener(view -> Cat8());
        //LLeva los datos a la lista y los añade al final
        botonCrearObs.setOnClickListener(view -> CreameLaObservacion());
        //Si solo se quiere ver los datos de la tabla, te lleva a la escena sin añadir nada
        botonVerLista.setOnClickListener(view -> verLista());
        preferencias = getSharedPreferences("DatosObs", Context.MODE_PRIVATE);
        CalendarioVista.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int anio, int mes, int dia)
            {
                //un toast que te dice que fecha has puesto con el calendario
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
        categoria =1;
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
    public void Cat6()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 6", Toast.LENGTH_SHORT).show();
        categoria =6;
    }
    public void Cat7()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 7", Toast.LENGTH_SHORT).show();
        categoria =7;
    }
    public void Cat8()
    {
        Toast.makeText(MeterObservacionNueva.this, "Has puesto categoría 8", Toast.LENGTH_SHORT).show();
        categoria =8;
    }
    //guarda los datos y los lleva a la escena de la lista
    public void CreameLaObservacion()
    {
        //Creamos nuevas variables para revisar que no se haya puesto datos null(por ejemplo que no hay nombre)
        titulo = TituloCategoria.getText().toString();
        String Titulo = titulo;
        int Categoria =categoria;

        int Day = day;
        int Month = month;
        int Year = year;
        int Minute;
        int Hour;
        String MinutoStr = TextoMin.getText().toString();
        String HoraStr = TextoHora.getText().toString();
        //Si no ha metido los minutos
        if(MinutoStr.isEmpty() ) {
           Minute=0;

        }
        else {
            minute = Integer.parseInt(MinutoStr);
            Minute = minute;
        }
        //Si no ha metido la hora
        if(HoraStr.isEmpty())
        {
            Hour=0;
        }
        else {
            hour = Integer.parseInt(HoraStr);
            Hour = hour;
        }
        //Si ha puesto nombre, categoria, fecha, hora y minutos, crea la nueva observacion y te lleva a la otra pantalla
        if(!Titulo.isEmpty()&& Categoria != 0 && Minute !=0 && Hour !=0 && Day !=0 && Month !=0 && Year !=0)
        {

            Intent intent = new Intent(this, NumeroDescubrimientos.class);
            intent.putExtra("minuto",Minute);
            intent.putExtra("hora", Hour);
            intent.putExtra("nombre", Titulo);
            intent.putExtra("Categoria", Categoria);
            intent.putExtra("Año", Year);
            intent.putExtra("Mes", Month);
            intent.putExtra("Dia", Day);
            guardarDat(Titulo, Categoria, Year, Month, Day, Minute, Hour);
            Toast.makeText(MeterObservacionNueva.this, Titulo + ", " + Categoria + ", " + Day +"/"+(Month +1)+"/"+ Year, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        //Ponemos un toast dependiendo de que falte
        else if (Titulo.isEmpty())
        {
            Toast.makeText(MeterObservacionNueva.this, "Falta Titulo", Toast.LENGTH_SHORT).show();
        }
        else if(Categoria == 0)
        {
            Toast.makeText(MeterObservacionNueva.this, "Falta Categoria", Toast.LENGTH_SHORT).show();
        }
        else if(Minute == 0)
        {
            Toast.makeText(MeterObservacionNueva.this, "Faltan Minutos", Toast.LENGTH_SHORT).show();
        }
        else if(Hour == 0)
        {
            Toast.makeText(MeterObservacionNueva.this, "Falta hora", Toast.LENGTH_SHORT).show();
        }
        else if(Day == 0 || Month ==0 || Year ==0)
        {
            Toast.makeText(MeterObservacionNueva.this, "Falta fecha", Toast.LENGTH_SHORT).show();
        }
    }
    void guardarDat(String Titulo, int categoria,int year, int month, int day, int minute, int hour)
    {
        //declaramos las variables y valores a guardar
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("nombreAGuardar", Titulo);
        editor.putInt("CategoriaAGuardar",categoria);
        editor.putInt("AñoAGuardar",year);
        editor.putInt("MesAGuardar",month);
        editor.putInt("DiaAGuardar",day);
        editor.putInt("MinutoAGuardar",minute);
        editor.putInt("HoraAGuardar",hour);
        editor.commit();
    }
    void verLista()
    {
        Intent intent = new Intent(this, NumeroDescubrimientos.class);
        startActivity(intent);
    }
}