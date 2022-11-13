package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class IngresoPag extends AppCompatActivity {

    EditText usuario, contra, contraDos;
    Button login ;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresopag);

        usuario = (EditText) findViewById(R.id.txtUsuario);
        contra = (EditText) findViewById(R.id.txtContra);
        contraDos = (EditText) findViewById(R.id.txtContraDos);
        login = (Button) findViewById(R.id.btnLogin1);

        DB = new DB(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = contra.getText().toString();
                String repass = contraDos.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(IngresoPag.this, "Ingrese datos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.chequeo(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertarDatos(user, pass);
                            if(insert==true) {
                                Toast.makeText(IngresoPag.this, "Registro completo", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), segundoActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(IngresoPag.this, "Registro fallida", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(IngresoPag.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                        }}
                    else{
                        Toast.makeText(IngresoPag.this, "Contraseña no valida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
}}
