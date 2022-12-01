package com.example.ezbro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class segundoActivity extends AppCompatActivity {

    private Button btnSalir;

    private FirebaseAuth fireBase;

    SensorManager sm;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        cuartoDialogo();
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensor == null)
            finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                if(sensorEvent.values[0]<sensor.getMaximumRange()){
                    Toast.makeText(segundoActivity.this, "Alejate de la pantalla, estas muy cerca", Toast.LENGTH_SHORT).show();
                }
                else{
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy)
            {

            }
        };
        empezar();

        btnSalir = findViewById(R.id.btnSalir);
        fireBase = FirebaseAuth.getInstance();

        btnSalir.setOnClickListener(view ->{
            fireBase.signOut();
            startActivity(new Intent (this, LoginFireBase.class));
        });
    }
    public void empezar()
    {
        sm.registerListener(sensorEventListener, sensor, 2000*1000);
    }
    public void detener()
    {
        sm.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        detener();
        super.onPause();
    }

    @Override
    protected void onResume() {
        empezar();
        super.onResume();
    }

    public void nuevaPag (View view)
    {
        Intent quienEs = new Intent(this, Armas.class);
        startActivity(quienEs);
    }
    public void irAlMapa (View view)
    {
        Intent mapTarkov = new Intent(this, MapaTarkov.class);
        startActivity(mapTarkov);
    }
    public void Descripcion(View view)
    {
        switch(view.getId())
        {
            case R.id.weapon:
                Toast.makeText(this, "Armas (Proximamente)",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rig:
                Toast.makeText(this, "Rig (Proximamente)",Toast.LENGTH_SHORT).show();
                break;
            case R.id.armor:
                Toast.makeText(this, "Armaduras (Proximamente)",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void cuartoDialogo()
    {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("¡En esta parte verás información resumida de que es EFT y futuros cambios! ")
                .setPositiveButton("EZ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.dismiss();
                    }
                })
                .create().show();
    }


}