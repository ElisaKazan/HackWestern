package com.brittny.forest.elisa.hackwestern;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class QRActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        String[] codeQRStrings = getIntent().getStringArrayExtra("pic");

        Bitmap[] QRBitMap = makeQRBitMap(codeQRStrings);

        ViewFlipper imageFlipper = (ViewFlipper)findViewById( R.id.image_flipper );

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
