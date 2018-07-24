package com.demoyageek.alzhapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InviteCodeActivity extends AppCompatActivity {
    String name, email, password, isSharing,code;
    Uri imageUri;
    TextView txtCircleCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);

        txtCircleCode = findViewById(R.id.txtCircleCode);

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
        
    }
}
