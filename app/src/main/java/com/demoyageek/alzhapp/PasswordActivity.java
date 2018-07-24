package com.demoyageek.alzhapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {
    String email;
    EditText editRegisterPassword;
    Button btnRegisterPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        editRegisterPassword = findViewById(R.id.editRegisterPassword);

        btnRegisterPassword = findViewById(R.id.btnRegisterPassword);
        btnRegisterPassword.setOnClickListener(this);

        Intent passwordIntent = getIntent();
        if (passwordIntent != null){
            email = passwordIntent.getStringExtra("email");
        }
    }

    public void namePickActivity(View v){
        if(editRegisterPassword.getText().toString().length() >= 6){
            Intent nameRegisterIntent = new Intent(PasswordActivity.this, NameActivity.class);
            nameRegisterIntent.putExtra("email", email);
            nameRegisterIntent.putExtra("password", editRegisterPassword.getText().toString());
            startActivity(nameRegisterIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "La contraseña debe tener un tamano mayor a 6 caracteres", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        namePickActivity(v);
    }
}
