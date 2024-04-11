package com.sc.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button botonNuevaObs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonNuevaObs = findViewById(R.id.BotonAIrMeterObs);
        botonNuevaObs.setOnClickListener(view -> IntroducirNuevaObs());
    }
    private void IntroducirNuevaObs()
    {
        Intent intent = new Intent(this, MeterObservacionNueva.class);
        startActivity(intent);
    }
}