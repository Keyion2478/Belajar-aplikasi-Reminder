package com.example.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView txtRegister;

    DBHelper dbHelper;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(this);

        if(session.isLoggedIn()){

            startActivity(
                    new Intent(
                            this,
                            MainActivity.class
                    )
            );

            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(v -> {

            String email =
                    etEmail.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            boolean login =
                    dbHelper.loginUser(
                            email,
                            password
                    );

            if(login){

                session.setLogin(true);

                startActivity(
                        new Intent(
                                LoginActivity.this,
                                MainActivity.class
                        )
                );

                finish();

            }else{

                Toast.makeText(
                        this,
                        "Email atau Password salah",
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        txtRegister.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            this,
                            RegisterActivity.class
                    )
            );

        });
    }
}