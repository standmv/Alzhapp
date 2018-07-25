package com.demoyageek.alzhapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demoyageek.alzhapp.Encapsulation.Circle;
import com.demoyageek.alzhapp.Encapsulation.User;
import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class JoinCircleActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnJoinCircleSubmit;
    Pinview pinView;
    DatabaseReference databaseReference, currentReference, circleReference;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    String currentUserId, joinUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_circle);

        pinView = (Pinview) findViewById(R.id.pinView);
        btnJoinCircleSubmit = findViewById(R.id.btnJoinCircleSubmit);
        btnJoinCircleSubmit.setOnClickListener(this);
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
        Query query = databaseReference.orderByChild("code").equalTo(pinView.getValue());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    User user = null;
                    for(DataSnapshot childDss: dataSnapshot.getChildren()){
                        user = childDss.getValue(User.class);
                        joinUserId = user.getUserId();

                        circleReference = FirebaseDatabase.getInstance().getReference().child("Users").child(joinUserId).child("CircleMembers");

                        Circle currentUserCircle = new Circle(currentUserId);
                        Circle joinUserCircle = new Circle(joinUserId);

                        circleReference.child(firebaseUser.getUid()).setValue(currentUserCircle).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Usuario se ha unido satisfactoriamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Codigo de Circulo no existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //si el codigo es valido, encontrar el usuario y crear nodo (Circle Members)
    }

    @Override
    public void onClick(View v) {
        submitButton(v);
    }
}
