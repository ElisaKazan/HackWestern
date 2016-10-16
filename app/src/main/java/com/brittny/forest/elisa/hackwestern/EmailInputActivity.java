package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.MultiFormatWriter;

/**
 * Created by Forest on 10/15/2016.
 */

public class EmailInputActivity extends AppCompatActivity {
    private Button nextButton;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_email);
        final Context context = this;

        nextButton = (Button) this.findViewById(R.id.nextBtn);
        emailText = (EditText) this.findViewById(R.id.emailText);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                Intent intent = new Intent(context, NameInputActivity.class);
                String email = emailText.getText().toString();
                intent.putExtra("email", email);
                context.startActivity(intent);
            }
        });

    }
}
