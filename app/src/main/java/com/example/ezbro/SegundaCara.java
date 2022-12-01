package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class SegundaCara extends AppCompatActivity {

    private FirebaseAuth mataca;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_cara);
        mataca = FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario = mataca.getCurrentUser();
        if (usuario == null)
        {
            TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(SegundaCara.this, LoginFireBase.class);
                    startActivity(intent);
                    finish();
                }
            };
            Timer tiempo = new Timer();
            tiempo.schedule(tarea, 2000);
        }
        else{
            startActivity(new Intent(SegundaCara.this, segundoActivity.class));
        }
    }
}
