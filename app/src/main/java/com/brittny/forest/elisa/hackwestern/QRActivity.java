package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class QRActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText nameText;
    private EditText emailText;
    User user;
    public static final String MyPrefs = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        final Context context = this;
        SharedPreferences sp = getSharedPreferences(MyPrefs, Context.MODE_PRIVATE);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String name = intent.getStringExtra("name");

        emailText = (EditText) this.findViewById(R.id.emailText);
        nameText = (EditText) this.findViewById(R.id.nameText);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        user = new User(name, email);
        String[] codeQRStrings = user.generateCodes();

        Bitmap[] QRBitMap = makeQRBitMap(codeQRStrings);

        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString("userData", user.getDataString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();

        ViewFlipper imageFlipper = (ViewFlipper) findViewById(R.id.image_flipper);

        for (int i = 0; i < codeQRStrings.length; i++) {
            System.out.println(codeQRStrings[i]);
            ImageView image = new ImageView(getApplicationContext());
            image.setImageBitmap(QRBitMap[i]);
            imageFlipper.addView(image);
        }

        imageFlipper.setFlipInterval(500);
        imageFlipper.startFlipping();
    }

    public Bitmap[] makeQRBitMap(String[] codesQR) {
        Bitmap[] codeQRBitMap = new Bitmap[codesQR.length];
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Display mDisplay = this.getWindowManager().getDefaultDisplay();
        try {
            for (int i = 0; i < codeQRBitMap.length; i++) {
                BitMatrix bitMatrix = multiFormatWriter.encode(codesQR[i], BarcodeFormat.QR_CODE, mDisplay.getWidth(), mDisplay.getWidth());
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                codeQRBitMap[i] = barcodeEncoder.createBitmap(bitMatrix);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return codeQRBitMap;
    }
}