package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Brittny on 10/15/2016.
 */

public class SetupActivity extends AppCompatActivity {
    private Button setupButton, QrScanner, contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        final Context context = this;
        setupButton = (Button) this.findViewById(R.id.setupBtn);
        QrScanner = (Button) this.findViewById(R.id.ScanQR);
        contacts = (Button) this.findViewById(R.id.contacts);

        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                Intent intent = new Intent(context, EmailInputActivity.class);
                context.startActivity(intent);
            }
        });

        QrScanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                Intent intent = new Intent(context, QRReader.class);
                context.startActivity(intent);
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                Intent intent = new Intent(context, ContactPageActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
