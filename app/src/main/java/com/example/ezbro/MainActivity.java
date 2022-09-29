package com.example.ezbro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {


    CheckBox juan;
    NotificationManagerCompat notificationManagerCompat;
    Notification notificacion;

    Spinner strDeptos;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == event.KEYCODE_BACK)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Deseas salir?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
            .setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("idNotificacion", "Primera notificación", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder format = new NotificationCompat.Builder(this, "idNotificacion")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("Primera notificacion")
                .setContentText("¡Este es un mensaje de notificacion de mi app! ");

        notificacion = format.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);


        TextView usuario = findViewById(R.id.txtUsuario);
        TextView clave = findViewById(R.id.txtContra);
        Button ingreso = (Button) findViewById(R.id.btnLogin);
        ingreso.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(usuario.getText().toString().equals("admin") && clave.getText().toString().equals("admin"))
                {
                    Toast.makeText(MainActivity.this, "Ingreso Correcto!",Toast.LENGTH_LONG).show();
                    Intent newActivity = new Intent(MainActivity.this, segundoActivity.class);
                    startActivity(newActivity);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Ingreso erroneo, intente denuevo",Toast.LENGTH_LONG).show();
                }

            }
        });

        juan = (CheckBox)findViewById(R.id.checkNotificacion);
        juan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(juan.isChecked())
                {
                    notificationManagerCompat.notify(1, notificacion);
                }
                else
                {
                }
            }
        });

         strDeptos = (Spinner)findViewById(R.id.ctnSpinner);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.strDeptos, android.R.layout.simple_spinner_item);
         strDeptos.setAdapter(adapter);

        tercerDialogo();
        segundoDialogo();
        mostrarDialogo();

}
    private void mostrarDialogo()
    {
        new AlertDialog.Builder(this)
                .setTitle("Hola esto es un anuncio!")
                .setMessage("Esta app esta hecha con la intencion de informar datos y lore de Escape From Tarkov. ¡Disfrutalo!")
                .setPositiveButton("Esta bien", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.dismiss();
                    }
                })
                .create().show();
    }

    private void segundoDialogo()
    {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Recuerda que esto es un proyecto, no consideres que esté terminado, porque no lo esta jaja´nt")
                .setPositiveButton("Comprendo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.dismiss();
                    }
                })
                .create().show();
    }
    private void tercerDialogo()
    {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Tambien te recuerdo que soy Simon Sanchez, mas conocido como Chivo, ¡SALUDOS!")
                .setPositiveButton("Rico", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.dismiss();
                    }
                })
                .create().show();
    }
}