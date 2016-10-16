package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
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
    private Button cameraBtn;
    private Button connectionsBtn;
    User user;
    public static final String MyPrefs = "MyPrefs";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        Button cameraBtn;
        Button connectionsBtn;

        imageView = (ImageView) this.findViewById(R.id.imageView);
        user = new User(name, email);
        user.generateCodes();

        SharedPreferences.Editor editor = sp.edit();
        try
        {
            editor.putString("userData", user.getDataString());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        editor.commit();

        String testDataString = sp.getString("userData", "{}");
        try
        {
            JSONObject testObj = new JSONObject(testDataString);
            emailText.setText(testObj.getString("email"));
            nameText.setText(testObj.getString("name"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        cameraBtn = (Button) this.findViewById(R.id.cameraBtn);
        connectionsBtn = (Button) this.findViewById(R.id.connectionsBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, QRReader.class);
                context.startActivity(intent);
            }
        });

        connectionsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("Go to connections");
            }
        });

        String text2qr = user.getCodes()[0];
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = null;
        try
        {
            bitMatrix = multiFormatWriter.encode(text2qr, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e)
        {
            e.printStackTrace();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("QR Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
