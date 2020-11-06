package com.example.hashish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hashish.Activity.DeviceProfile;
import com.example.hashish.R;

public class MainActivity extends AppCompatActivity {
    protected Button button;
    protected TextView text;
    protected ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
    }

    protected void setUpUI() {

        button = findViewById(R.id.wifi);
        //text = findViewById(R.id.TitleText);
        //image = findViewById(R.id.weedview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeviceProfile();
            }
        });

    }

    private void openDeviceProfile(){
        Intent intent = new Intent(this, DeviceProfile.class);
        startActivity(intent);

    }


}