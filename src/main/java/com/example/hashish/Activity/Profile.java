package com.example.hashish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.PriorityQueue;
import java.util.UUID;


import com.example.hashish.R;

public class Profile extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    protected  Button onButton;
    protected Button offButton;
    protected Button save;
    protected Button next;
    protected TextView tempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUpUI();


        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        System.out.println("ID IS : ******" + btAdapter.getBondedDevices());

        BluetoothDevice hc05 = btAdapter.getRemoteDevice("00:18:91:D7:28:92");
        System.out.println(hc05.getName());

        BluetoothSocket btSocket = null;

        PriorityQueue<String> q = new PriorityQueue<String>();


        //Creation of Thread for bluetooth
        ConnectThread mConnectThread = new ConnectThread(hc05);
        mConnectThread.start();

        DataThread d = new DataThread(q);

        System.out.println(d.q);

        for(int i=0; i<100; i++){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {



                //System.out.println("Temp is : "+ mConnectThread.getTemp());
                tempView.setText(mConnectThread.getTemp());
            }
        }, 3000*i);}
    }

/*
    protected void setUpBluetooth(){
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        System.out.println("ID IS : ******" + btAdapter.getBondedDevices());

        BluetoothDevice hc05 = btAdapter.getRemoteDevice("00:18:91:D7:28:92");
        System.out.println(hc05.getName());

        BluetoothSocket btSocket = null;

        //Creation of Thread for bluetooth
        ConnectThread mConnectThread = new ConnectThread(hc05);
        mConnectThread.start();


            try {
                btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                System.out.println(btSocket);
                btSocket.connect();

                System.out.println("Is bluetooth connected: "+ btSocket.isConnected());
            } catch (IOException e) {
                e.printStackTrace();
            }

        try {
            btSocket.close();
            System.out.println(btSocket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



    protected void setUpUI(){

        save = findViewById(R.id.save);
        next = findViewById(R.id.nextPage);
        onButton = findViewById(R.id.onButton);
        offButton = findViewById(R.id.onButton);
        tempView = findViewById(R.id.tempView);

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