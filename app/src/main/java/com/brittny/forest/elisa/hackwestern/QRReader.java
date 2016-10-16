package com.brittny.forest.elisa.hackwestern;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Forest on 2016-10-15.
 */

public class QRReader extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    static final int CAMERA_REQUEST = 1;

    private ZXingScannerView mScannerView;
    private int numPackets = -1;
    private List<String> packetList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }
        QrScanner(mScannerView);
    }

    public void QrScanner(View view) {
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        System.out.println(Camera.getNumberOfCameras());
        //mScannerView.start
        mScannerView.startCamera();         // Start camera
        System.out.println(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA));
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();   // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        String packet = rawResult.getText();
        int packetNum = Integer.parseInt(packet.substring(0, 1));
        if (packetNum == 0) {
            if (!packetList.contains(packet)) {
                numPackets = Integer.parseInt(packet.substring(1, 2));
                packetList.add(packet);
            }
        } else {
            if (!packetList.contains(packet)) {
                packetList.add(packet);
            }
        }
        System.out.println(packet);

        if (numPackets == -1 || numPackets > packetList.size()) {
            mScannerView.resumeCameraPreview(this);
        }
        else {
            parsePackets(packetList);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    System.out.println("Granted");

                } else {

                    System.out.println("Not Granted");
                }
                return;
            }
        }
    }

    public void parsePackets(List<String> packetStrings) {
        String inOrder = "";
        for (int i = 0; i < packetStrings.size(); i++) {
            for (int j = 0; j < packetStrings.size(); j++) {
                if (Integer.parseInt(packetStrings.get(j).substring(0, 1)) == i) {
                    if (i == 1) {
                        inOrder = inOrder + "" + packetStrings.get(j).substring(2);
                    }
                    else {
                        inOrder = inOrder + "" + packetStrings.get(j).substring(1);
                    }
                }
            }
        }
        System.out.println(inOrder);

        String[] userData = inOrder.split(",");
        User parsedUser = new User();

        parsedUser.setName(userData[0]);
        if (parsedUser.name == "") {
            parsedUser.name = "Name Unavailable";
        }
        parsedUser.setEmail(userData[1]);
        if (parsedUser.email == "") {
            parsedUser.email = "Email Unavailable";
        }
        parsedUser.setTwitter(userData[2]);
        if (parsedUser.twitter == "") {
            parsedUser.twitter = "Twitter Unavailable";
        }

        System.out.print("Name: " + parsedUser.name + "/n" + "Email: " + parsedUser.email + "/n" + "Twitter" + parsedUser.twitter + "/n");

    }
}
