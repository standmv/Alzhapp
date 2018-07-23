package com.demoyageek.alzhapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {
    String email, password;
    EditText editRegisterName;
    Button btnRegisterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        editRegisterName = findViewById(R.id.editRegisterName);

        btnRegisterName = findViewById(R.id.btnRegisterName);
        btnRegisterName.setOnClickListener(this);

        Intent nameRegisterIntent = getIntent();
        if (nameRegisterIntent != null){
            email = nameRegisterIntent.getStringExtra("email");
            password = nameRegisterIntent.getStringExtra("password");

        }
    }

    @Override
    public void onClick(View v) {

    }

    public void generateCode(View v){
        Date myDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
    }
}
