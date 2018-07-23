package com.demoyageek.alzhapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editRegisterEmail;
    Button btnRegisterEmail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        editRegisterEmail = findViewById(R.id.editRegisterEmail);

        btnRegisterEmail = findViewById(R.id.btnRegisterEmail);
        btnRegisterEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        goToPasswordActivity(v);
    }

    public void goToPasswordActivity(View v){
        auth.fetchSignInMethodsForEmail(editRegisterEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()){
                    boolean check = !task.getResult().getSignInMethods().isEmpty();

                    if(!check){
                        Intent passwordIntent = new Intent(RegisterActivity.this, PasswordActivity.class);
                        passwordIntent.putExtra("email", editRegisterEmail.getText().toString());
                        startActivity(passwordIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Este correo ya esta en uso", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
