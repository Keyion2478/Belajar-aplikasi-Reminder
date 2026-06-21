package com.example.reminderapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirm;

    Button btnRegister;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);

        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DBHelper(this);

        btnRegister.setOnClickListener(v -> {

            String username =
                    etUsername.getText().toString();

            String email =
                    etEmail.getText().toString();

            String password =
                    etPassword.getText().toString();

            String confirm =
                    etConfirm.getText().toString();

            if(!password.equals(confirm)){

                Toast.makeText(
                        this,
                        "Password tidak sama",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            boolean success =
                    dbHelper.registerUser(
                            username,
                            email,
                            password
                    );

            if(success){

                Toast.makeText(
                        this,
                        "Registrasi berhasil",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            }else{

                Toast.makeText(
                        this,
                        "Email sudah digunakan",
                        Toast.LENGTH_SHORT
                ).show();

            }

        });

    }
}