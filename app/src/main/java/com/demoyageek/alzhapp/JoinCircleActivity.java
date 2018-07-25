package com.demoyageek.alzhapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demoyageek.alzhapp.Encapsulation.User;
import com.goodiebag.pinview.Pinview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class JoinCircleActivity extends AppCompatActivity {
    Button btnJoinCircleSubmit;
    Pinview pinView;
    DatabaseReference databaseReference, currentReference;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_circle);

        pinView = (Pinview) findViewById(R.id.pinView);
        btnJoinCircleSubmit = findViewById(R.id.btnJoinCircleSubmit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        currentUserId = firebaseUser.getUid();

        //apunta hacia la tabla Users en la bd
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        //apunta hacia el usuario que esta utilizando el activity
        currentReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);


    }

    public void submitButton(View view){
        //verificar si el codigo del circulo digitado existe en la bd.
        Query query = databaseReference.orderByChild("circlecode").equalTo(pinView.getValue());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    User user = null;
                    for(DataSnapshot childDss: dataSnapshot.getChildren()){
                        user = childDss.getValue(User.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //si el codigo es valido, encontrar el usuario y crear nodo (Circle Members)
    }
}
