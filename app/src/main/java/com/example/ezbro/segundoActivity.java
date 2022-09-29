package com.example.ezbro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class segundoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        cuartoDialogo();

    }

    public void nuevaPag (View view)
    {
        Intent quienEs = new Intent(this, Armas.class);
        startActivity(quienEs);
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
                .setPositiveButton("Rico", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.dismiss();
                    }
                })
                .create().show();
    }


}