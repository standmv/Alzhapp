package com.demoyageek.alzhapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignUp;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            setContentView(R.layout.activity_main);

            btnLogin = findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(this);

            btnSignUp = findViewById(R.id.btnSignUp);
            btnSignUp.setOnClickListener(this);
        }
        else{
            Intent navigationDrawerIntent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
            startActivity(navigationDrawerIntent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSignUp) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnLogin) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
