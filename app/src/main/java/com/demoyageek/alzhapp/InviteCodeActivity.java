package com.demoyageek.alzhapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demoyageek.alzhapp.Encapsulation.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InviteCodeActivity extends AppCompatActivity implements View.OnClickListener {
    String name, email, password, isSharing,code;
    Uri imageUri;
    TextView txtCircleCode;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String userId;
    Button btnCircleCodeDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);

        btnCircleCodeDone = findViewById(R.id.btnCircleCodeDone);
        txtCircleCode = findViewById(R.id.txtCircleCode);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        Intent inviteCodeIntent = getIntent();
        if (inviteCodeIntent != null) {
            name = inviteCodeIntent.getStringExtra("name");
            email = inviteCodeIntent.getStringExtra("email");
            password = inviteCodeIntent.getStringExtra("password");
            isSharing = inviteCodeIntent.getStringExtra("isSharing");
            code = inviteCodeIntent.getStringExtra("code");
            imageUri = inviteCodeIntent.getParcelableExtra("imageUri");
            // inviteCodeIntent.putExtra("date", date);
        }

        txtCircleCode.setText(code);
        btnCircleCodeDone.setOnClickListener(this);
    }

    public void registerUser(View v){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User newUser = new User(name, email, password, code, "false", "na", "na", "na");
                    firebaseUser = firebaseAuth.getCurrentUser();
                    userId = firebaseUser.getUid();

                    databaseReference.child(userId).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Usuario Creado Satisfactoriamente", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent navigationIntent = new Intent(InviteCodeActivity.this, NavigationDrawerActivity.class);
                                startActivity(navigationIntent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Error al crear usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        registerUser(v);
    }
}
