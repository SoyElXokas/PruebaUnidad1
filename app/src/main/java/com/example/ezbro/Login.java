package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity
{
    CheckBox juan;
    NotificationManagerCompat notificationManagerCompat;
    Notification notificacion;

    EditText usuario, password;
    Button login,registro;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.txtUsuario1);
        password = (EditText) findViewById(R.id.txtContra1);
        login = (Button) findViewById(R.id.btnLogin);
        registro = (Button) findViewById(R.id.btnRegistro);
        DB = new DB(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Porfavor ingresa tus datos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.chequeouserPass(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Has ingresado sin problemas", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),segundoActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "Credencial invalida", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IngresoPag.class);
                startActivity(intent);
            }
        });

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