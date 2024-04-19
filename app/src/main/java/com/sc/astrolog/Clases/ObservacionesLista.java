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
        observaciones.add(new Observacion("nombre1",R.drawable.abrazado_a,1, new Date()));
        Log.i("prueba: ",observaciones.toString());
    }

    public String convertirAJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public static ObservacionesLista convertirAJava(String json) {
        Gson gson = new Gson();
        ObservacionesLista observacionesLista = gson.fromJson(json, ObservacionesLista.class);
        return observacionesLista;
    }
}
