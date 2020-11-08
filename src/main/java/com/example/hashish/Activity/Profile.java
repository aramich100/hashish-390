package com.example.hashish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


import com.example.hashish.R;

public class Profile extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    protected  Button onButton;
    protected Button offButton;
    protected Button save;
    protected Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUpUI();
        setUpBluetooth();




    }


    protected void setUpBluetooth(){
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        System.out.println("ID IS : ******" + btAdapter.getBondedDevices());

        BluetoothDevice hc05 = btAdapter.getRemoteDevice("00:18:91:D7:28:92");
        System.out.println(hc05.getName());

        BluetoothSocket btSocket = null;


            try {
                btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                System.out.println(btSocket);
                btSocket.connect();

                System.out.println("Is bluetooth connected: "+ btSocket.isConnected());
            } catch (IOException e) {
                e.printStackTrace();
            }

/*
        try {
            OutputStream outputStream = btSocket.getOutputStream();
            outputStream.write(48);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        InputStream inputStream = null;
        try {

           // byte[] rawBytes = new byte[2];
           // inputStream.read(rawBytes);
           // final String temp = new String(rawBytes,"UTF-8");

            inputStream = btSocket.getInputStream();
            //inputStream.skip(inputStream.available());

            for(int j=0; j<10; j++){
                System.out.print("Temp is : ");
            for(int i=0; i<2; i++) {
                char temp = (char) inputStream.read();
                System.out.print(temp);
            }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            btSocket.close();
            System.out.println(btSocket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected void setUpUI(){

        save = findViewById(R.id.save);
        next = findViewById(R.id.nextPage);
        onButton = findViewById(R.id.onButton);
        offButton = findViewById(R.id.onButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInformation();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    }

}