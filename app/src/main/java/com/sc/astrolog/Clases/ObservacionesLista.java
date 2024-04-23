package com.sc.astrolog.Clases;

import android.util.Log;

import com.google.gson.Gson;
import com.sc.astrolog.R;

import java.util.ArrayList;
import java.util.Date;

public class ObservacionesLista {
    public ArrayList<Observacion> observaciones;

    public ObservacionesLista() {
        observaciones = new ArrayList<>();
        //Para revisar que funciona y los datos sean los correctos
        Log.i("prueba: ",observaciones.toString());
    }
//convierte la tabla y sus datos en un json
    public String convertirAJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
//usa el json de la tabla y actiualiza los datos de la tabla
    public static ObservacionesLista convertirAJava(String json) {
        Gson gson = new Gson();
        ObservacionesLista observacionesLista = gson.fromJson(json, ObservacionesLista.class);
        return observacionesLista;
    }
}
