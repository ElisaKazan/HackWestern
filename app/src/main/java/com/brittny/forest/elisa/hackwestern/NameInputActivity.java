package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by Forest on 10/15/2016.
 */

public class NameInputActivity extends AppCompatActivity {
    private Button genButton;
    private EditText nameText;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        final Context context = this;
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");

        genButton = (Button) this.findViewById(R.id.generateBtn);
        nameText = (EditText) this.findViewById(R.id.nameText);

        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                Intent intent = new Intent(context, QRActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                context.startActivity(intent);
            }
        });
    }
}
