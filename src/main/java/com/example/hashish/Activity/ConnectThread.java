package com.example.hashish.Activity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ConnectThread extends Thread{

    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");




    public ConnectThread(BluetoothDevice device) {
        BluetoothSocket tmp = null;
        InputStream inputStream = null;
        mmDevice = device;


        try {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) { }
        mmSocket = tmp;



    }

    public void run() {
      //  mBluetoothAdapter.cancelDiscovery();
        try {
            mmSocket.connect();
        } catch (IOException connectException) {
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }


        DataThread mDataThread = new DataThread(mmSocket);
        mDataThread.start();
    }




}
