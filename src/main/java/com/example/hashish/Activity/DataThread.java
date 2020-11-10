package com.example.hashish.Activity;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataThread extends Thread{

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    //private final OutputStream mmOutStream;


    public DataThread(BluetoothSocket socket)
    {
        mmSocket = socket;
        InputStream tmpIn = null;

        try {
            tmpIn = socket.getInputStream();
           // tmpOut = socket.getOutputStream();
        } catch(IOException e) { }

        mmInStream = tmpIn;


    }


    public void run()
    {
        while (true) {


            try {
                //inputStream.skip(inputStream.available());

                do {
                    for (int i = 0; i < 2; i++) {
                        char temp = (char) mmInStream.read();
                        System.out.print(temp);
                    }
                    ///System.out.println();
                } while (true);
            } catch (IOException e) { break; }


        }
    }



}
