package com.sc.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Date;

public class Observacion {


    String nombre;
    int foto;
    int categoria;
    Date fecha;

    public Observacion(String nombre, int foto, int categoria, Date fecha) {
        this.nombre = nombre;
        this.foto = foto;
        this.categoria = categoria;
        this.fecha = fecha;
    }
}