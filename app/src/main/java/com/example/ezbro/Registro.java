package com.example.ezbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private EditText user, mail, celular, password;
    private Button registro;
    private TextView login;
    private String userID;
    private FirebaseAuth fireBase;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = findViewById(R.id.txtUsuario);
        mail = findViewById(R.id.txtCorreo1);
        celular = findViewById(R.id.txtCel);
        registro = findViewById(R.id.btnRegistro);
        password = findViewById(R.id.txtContra);
        fireBase = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        registro.setOnClickListener(view ->{
            crearUsuario();
        });

    }
    public void crearUsuario()
    {
        String nombre = user.getText().toString();
        String email = mail.getText().toString();
        String cel = celular.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(nombre))
        {
            user.setError("Ingrese un Nombre");
            user.requestFocus();
        }else if (TextUtils.isEmpty(email))
        {
            mail.setError("Ingrese un Correo");
            mail.requestFocus();
        }else if (TextUtils.isEmpty(cel))
        {
            celular.setError("Ingrese un Teléfono");
            celular.requestFocus();
        }else if (TextUtils.isEmpty(pass))
        {
            password.setError("Ingrese una Contraseña");
            password.requestFocus();
        }
        else {
        fireBase.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    userID = fireBase.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userID);
                    Map<String,Object> user=new HashMap<>();
                    user.put("Nombre", nombre);
                    user.put("Correo", email);
                    user.put("Teléfono", cel);
                    user.put("Contraseña", pass);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("TAG", "onSuccess: Datos registrados"+userID);
                        }
                    });
                    Toast.makeText(Registro.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registro.this, LoginFireBase.class));
                }else {
                    Toast.makeText(Registro.this, "Usuario no registrado"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}}