package com.example.hashish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hashish.R;

public class DeviceProfile extends AppCompatActivity {
    protected EditText editText;
    protected TextView textview;
    protected Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_profile);

        setUpUI();
        textview.setText("Make sur you are not 10 ft away of the device");
    }

    protected void setUpUI(){

        editText = findViewById(R.id.Listdevice);
        textview = findViewById(R.id.message);
        button = findViewById((R.id.connection));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });


    }
    protected void onStart() {

        super.onStart();
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(DeviceProfile.this);
        String name = sharedPreferenceHelper.getProfileName();
        if(name == null){
            openProfile();
        }
    }

    private void openProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);

    }
}