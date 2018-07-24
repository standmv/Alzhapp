package com.demoyageek.alzhapp;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {
    String email, password;
    EditText editRegisterName;
    Button btnRegisterName;
    CircleImageView circleImageView;
    Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        circleImageView = findViewById(R.id.circleImageView);
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
        generateCode(v);
    }

    public void generateCode(View v){
        Date myDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
        String date = format.format(myDate);
        Random random = new Random();

        int n = 100000 + random.nextInt(900000);
        String code = String.valueOf(n);

        if (resultUri!=null){
            Intent inviteCodeIntent = new Intent(NameActivity.this, InviteCodeActivity.class);
            inviteCodeIntent.putExtra("name", editRegisterName.getText().toString());
            inviteCodeIntent.putExtra("email", email);
            inviteCodeIntent.putExtra("password", password);
            inviteCodeIntent.putExtra("date", date);
            inviteCodeIntent.putExtra("isSharing", "false");
            inviteCodeIntent.putExtra("imageUri", resultUri);
            inviteCodeIntent.putExtra("code", code);
            startActivity(inviteCodeIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Por favor seleccione una imagen", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectImage(View v){
        Intent imageIntent = new Intent();
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        imageIntent.setType("image/*");
        startActivityForResult(imageIntent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 12 && resultCode == RESULT_OK && data != null){
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                circleImageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
