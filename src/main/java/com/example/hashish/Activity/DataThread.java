package com.example.hashish.Activity;

import android.bluetooth.BluetoothSocket;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hashish.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.PriorityQueue;

public class DataThread extends Thread{

    private BluetoothSocket mmSocket;
    private InputStream mmInStream;
    //private final OutputStream mmOutStream;

    protected Button onButton;
    protected Button offButton;
    protected Button save;
    protected Button next;

    protected String temp;
    String newTemp;

    PriorityQueue<String> q = new PriorityQueue<String>();

    public DataThread(PriorityQueue q)
    {
        this.q = q;
       // this.mmSocket = this.mmSocket;

    }

    public DataThread(BluetoothSocket socket)
    {
        mmSocket = socket;
        InputStream tmpIn = null;
       // this.q = q;

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
                    temp = "";
                    for (int i = 0; i < 2; i++) {
                        temp = temp + (char) mmInStream.read();
                       //newTemp = temp;
                        //System.out.print((char) mmInStream.read());

                    }

                    q.offer(temp);

                } while (true);
            } catch (IOException e) { break; }


        }
    }


    public void setTemp(String t)
    {
        temp = t;

    }


    public String  getValue(){
        return temp;

    }

}
