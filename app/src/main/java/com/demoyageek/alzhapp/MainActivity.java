package com.demoyageek.alzhapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignUp;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    PermissionManager permissionManager;

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

            permissionManager = new PermissionManager() {};
            permissionManager.checkAndRequestPermissions(this);
        }
        else{
            Intent navigationDrawerIntent = new Intent(MainActivity.this, UserLocationMainActivity.class);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionManager.checkResult(requestCode, permissions, grantResults);

        ArrayList<String> deniedPermissions = permissionManager.getStatus().get(0).denied;

        if(deniedPermissions.isEmpty()){
            Toast.makeText(getApplicationContext(), "Permisos Activados", Toast.LENGTH_SHORT).show();
        }
    }
}
