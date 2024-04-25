package com.sc.astrolog.Pantallas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sc.astrolog.Clases.Observacion;
import com.sc.astrolog.Clases.ObservacionesLista;
import com.sc.astrolog.Adaptador.ObservacionAdapter;
import com.sc.astrolog.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        //Carga la tabla con todas las observaciones creadas
        CargaTabla();
        //inserta la nueva obsevacion que se haya metido
        meterNuevaObs();
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        // Vincular la vista de cada fila a los datos
        adapter = new ObservacionAdapter(this, R.layout.activity_plantilla_lista_observacion, observacionesLista.observaciones);
        // Vista del listado
        ObservacionesListView.setAdapter(adapter);
        //Al pulsar el boton nos lleva de vuelta a la pantalla para meter una nueva observacion
        botonNuevaObs.setOnClickListener(view -> IntroducirNuevaObs());
        ObservacionesListView.setOnItemClickListener((adapterView, view, i, l) -> VentanaEmergente(i));
    }
    //Si se cierra por algun bug/se apaga/similar
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Guarda la lista para no perder los datos
        guardarDat();
    }
    private void IntroducirNuevaObs()
    {
        //guarda la lista para que no se pierda
        guardarDat();
        //cierra la actividad(volviendo a la pantalla para crear)
        finish();
    }
    void CargaTabla()
    {
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        String json = preferencias.getString("Observaciones", null);
        if (json == null) {
            observacionesLista = new ObservacionesLista();
        } else {
            observacionesLista = ObservacionesLista.convertirAJava(json);
        }
    }
    void guardarDat()
    {
        preferencias = getSharedPreferences("datosObs", Context.MODE_PRIVATE);
        String json = observacionesLista.convertirAJson();
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Observaciones", json);
        editor.commit();
    }
    void meterNuevaObs()
    {
        //Recoger los datos de las obsevaciones
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
            fotoDescubr = R.drawable.sol;
        }
        if(categoria==2)
        {
            fotoDescubr = R.drawable.luna;
        }
        if(categoria==3)
        {
            fotoDescubr = R.drawable.planeta;
        }
        if(categoria==4)
        {
            fotoDescubr = R.drawable.satelite;
        }
        if(categoria==5)
        {
            fotoDescubr = R.drawable.galaxia;
        }
        if(categoria==6)
        {
            fotoDescubr = R.drawable.nube;
        }
        if(categoria==7)
        {
            fotoDescubr = R.drawable.estrella;
        }
        if(categoria==8)
        {
            fotoDescubr = R.drawable.estrellas;
        }
        //Mientras se haya puesto nombre te añade la observacion.
        if(Nombre != null)
        {
            observacionesLista.observaciones.add(new Observacion(Nombre, fotoDescubr, categoria, calendario.getTime()));
        }
    }
    void VentanaEmergente(int index)
    {
        //Creamos una ventana emergente
        AlertDialog.Builder ventana = new AlertDialog.Builder(NumeroDescubrimientos.this);
        //te pregunta:
        ventana.setMessage("¿Desea quitar esta observacion("+ observacionesLista.observaciones.get(index).nombre+")?").setCancelable(false)
                //si queremos quitarla le damos a si
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogo, int i)
                    {
                        //Un toast que dice que la observacion ha sido eliminada
                        Toast.makeText(NumeroDescubrimientos.this, "Observacion eliminada", Toast.LENGTH_SHORT).show();
                        //la elimina
                        observacionesLista.observaciones.remove(index);
                        //termina la actividad para que se actualice la lista
                        finish();
                    }
                })
                //si no queremos que se elimine
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    //cierra el dialogo
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.cancel();
                    }
                });
        AlertDialog titulo = ventana.create();
        titulo.setTitle("Aviso");
        titulo.show();
    }
}