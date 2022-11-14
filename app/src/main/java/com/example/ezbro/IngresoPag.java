package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngresoPag extends AppCompatActivity {

    EditText usuario, password;
    Button btnlogin;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_pag);

        usuario = (EditText) findViewById(R.id.txtUsuario1);
        password = (EditText) findViewById(R.id.txtContra1);
        btnlogin = (Button) findViewById(R.id.btnLogin1);
        DB = new DB(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = usuario.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(IngresoPag.this, "El yia kie pero komo", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.chequeoPass(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(IngresoPag.this, "Registro completo", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),SegundaCara.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(IngresoPag.this, "Credencial invalida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}