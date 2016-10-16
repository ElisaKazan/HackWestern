package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button button, QrScanner;
    private EditText nameText;
    private EditText emailText;
    private EditText twitterText;
    private EditText linkedinText;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        nameText = (EditText) this.findViewById(R.id.nameText);
        emailText = (EditText) this.findViewById(R.id.emailText);
        twitterText = (EditText) this.findViewById(R.id.twitterText);
        nameText = (EditText) this.findViewById(R.id.nameText);

        button = (Button) this.findViewById(R.id.generateBtn);
        QrScanner = (Button) this.findViewById(R.id.ScanQR);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = new User();
                user.setName(nameText.getText().toString());
                user.setEmail(emailText.getText().toString());
                user.setTwitter(twitterText.getText().toString());

                String[] codesQR = user.generateCodes();

                Intent intent = new Intent(context, QRActivity.class);
                intent.putExtra("pic", codesQR);
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
    }


}
