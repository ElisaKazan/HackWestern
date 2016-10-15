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
    private int numPackets;
    private List<String> packetList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("here");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Empty");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Still empty");
        }
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
            if (!packetList.contains(packetNum)) {
                numPackets = Integer.parseInt(packet.substring(1, 2));
                packetList.add(packet);
            }
        } else {
            if (!packetList.contains(packetNum)) {
                packetList.add(packet);
            }
        }
        System.out.println(packet);
        mScannerView.resumeCameraPreview(this);
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
}
