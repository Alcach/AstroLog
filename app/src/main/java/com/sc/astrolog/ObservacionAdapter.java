package com.sc.astrolog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class ObservacionAdapter extends ArrayAdapter {
    Context context;
    int idLayoutItem;
    List<Observacion> Observacion;

    public ObservacionAdapter(@NonNull Context context, int idLayoutItem, @NonNull List<Observacion> personas) {
        super(context, idLayoutItem, personas);
        this.context = context;
        this.idLayoutItem = idLayoutItem;
        this.Observacion = personas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Crear vista de esta fila
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(idLayoutItem, parent, false);

        // Persona en esta posición
        Observacion observacion = Observacion.get(position);

        // Poner el nombre
        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        nombreTextView.setText(observacion.nombre);

        // Poner la foto
        ImageView fotoImageView = convertView.findViewById(R.id.FotoPlantilla);
        fotoImageView.setImageDrawable(context.getDrawable(observacion.foto));

        Log.i("getView", "fila: " + position + " nombre: "+ observacion.nombre);
        return convertView;
    }
}