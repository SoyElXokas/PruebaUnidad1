package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;

public class MapaTarkov extends AppCompatActivity {

    private MapView soyElMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_tarkov);
        soyElMapa = findViewById(R.id.mapView);
        soyElMapa.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
    }
}





