package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText usuario, contra, contraDos;
    Button login, registro;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.txtUsuario);
        contra = (EditText) findViewById(R.id.txtContra);
        contraDos = (EditText) findViewById(R.id.txtContraDos);

        login = (Button) findViewById(R.id.btnLogin);
        registro = (Button) findViewById(R.id.btnRegistro);

        DB = new DB(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = contra.getText().toString();
                String repass = contraDos.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Login.this, "Porfavor yia kie pero komo", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.chequeo(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertarDatos(user, pass);
                            if(insert==true) {
                                Toast.makeText(Login.this, "Registro completo", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), segundoActivity.class);
                                startActivity(intent);
                            }
                                else
                                {
                                    Toast.makeText(Login.this, "Registro fallida", Toast.LENGTH_SHORT).show();
                                }
                            }
                        else {
                            Toast.makeText(Login.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                        }}
                    else{
                        Toast.makeText(Login.this, "Contraseña no valida", Toast.LENGTH_SHORT).show();
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
    }
}