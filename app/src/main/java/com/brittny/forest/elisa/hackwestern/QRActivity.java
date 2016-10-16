package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class QRActivity extends AppCompatActivity
{

    private ImageView imageView;
    private EditText nameText;
    private EditText emailText;
    User user;
    public static final String MyPrefs = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        final Context context = this;
        SharedPreferences sp = getSharedPreferences(MyPrefs, Context.MODE_PRIVATE);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String name = intent.getStringExtra("name");

        emailText = (EditText) this.findViewById(R.id.emailText);
        nameText = (EditText) this.findViewById(R.id.nameText);

        imageView = (ImageView)this.findViewById(R.id.imageView);
        user = new User(name, email);
        user.generateCodes();

        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString("userData", user.getDataString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();

        String testDataString = sp.getString("userData", "{}");
        try {
            JSONObject testObj = new JSONObject(testDataString);
            emailText.setText(testObj.getString("email"));
            nameText.setText(testObj.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String text2qr = user.getCodes()[0];
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(text2qr, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
