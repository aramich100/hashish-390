package com.example.hashish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hashish.R;

public class Profile extends AppCompatActivity {
   // protected EditText name;
   // protected EditText age;
   // protected EditText plant;
    protected Button save;
    protected Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUpUI();
    }

    protected void setUpUI(){

        //name = findViewById(R.id.nameEditText);
        //age = findViewById(R.id.ageEditText);
       // plant = findViewById(R.id.plantEditText);
        save = findViewById(R.id.save);
        next = findViewById(R.id.nextPage);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInformation();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(Profile.this);
                //String names = name.getText().toString();
               // int ages = Integer.parseInt(age.getText().toString());
              //  String plants = plant.getText().toString();

               // sharedPreferenceHelper.saveAge(ages);
               // sharedPreferenceHelper.saveProfileName(names);
                //sharedPreferenceHelper.savePlant(plants);

                Toast toast = Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    public void openInformation(){
        Intent intent = new Intent(this,Information.class);
        startActivity(intent);
    }

    protected void onStart() {


        super.onStart();
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(Profile.this);
       // name.setText(sharedPreferenceHelper.getProfileName());
       // age.setText(Integer.toString(sharedPreferenceHelper.getAge()));
       // plant.setText(sharedPreferenceHelper.getPlantName());
    }

}