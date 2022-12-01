package com.example.ezbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFireBase extends AppCompatActivity
{

    SensorManager sm;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    CheckBox juan;
    NotificationManagerCompat notificationManagerCompat;
    Notification notificacion;

    private EditText correo, usuario;
    private EditText pass;
    private Button login;
    private TextView registro;

    private FirebaseAuth baseFire;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire_base);
        usuario = findViewById(R.id.txtUsuario1);
        correo = findViewById(R.id.txtCorreo);
        pass = findViewById(R.id.txtContra1);
        registro = findViewById(R.id.btnRegistro);
        login = findViewById(R.id.btnLogin);

        baseFire = FirebaseAuth.getInstance();

        login.setOnClickListener(view ->{
            userLogin();
                });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openRegisterActivity();}
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


        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensor == null)
            finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                if(sensorEvent.values[0]<sensor.getMaximumRange()){
                    Toast.makeText(LoginFireBase.this, "Alejate de la pantalla, estas muy cerca", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == event.KEYCODE_BACK)
        {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
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


        public void openRegisterActivity()
        {
            Intent intent = new Intent(LoginFireBase.this, Registro.class);
            startActivity(intent);
        }
        public void userLogin()
        {
            String user = usuario.getText().toString();
            String mail = correo.getText().toString();
            String password = pass.getText().toString();

            if(TextUtils.isEmpty(mail))
            {
                correo.setError("Ingrese un correo");
                correo.requestFocus();
            }
            else if(TextUtils.isEmpty(password))
            {
                Toast.makeText(LoginFireBase.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(user))
            {
                Toast.makeText(this, "Ingrese un usuario", Toast.LENGTH_SHORT).show();
            }
            else
            {
                baseFire.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginFireBase.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent (LoginFireBase.this, SegundaCara.class));
                    }
                        else
                        {
                            Log.w("TAG", "Error:", task.getException());
                        }
                }

            });
            }
        }
    }