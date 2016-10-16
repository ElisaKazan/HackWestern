package com.brittny.forest.elisa.hackwestern;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Forest on 2016-10-16.
 */

public class ContactPageActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "Contacts";

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);


        SharedPreferences pref = getSharedPreferences(PREFS_NAME, 0);
        String contacts = pref.getString("contacts", "missingPref");

        System.out.println("here");
        if (!contacts.equals("missingPref")) {
            String[] prefStrings = contacts.split(",");
            System.out.println(contacts);
            String out = "";

            for (int i = 0; i < prefStrings.length; i+=3) {
                out = out + "" + prefStrings[i] + "\n" + prefStrings[i + 1] + "\n" + prefStrings[i + 2];
                if (i + 3 < prefStrings.length) {
                    out = out + "\n\n";
                }
            }

            TextView textViewToChange = (TextView) findViewById(R.id.tempContacts);
            textViewToChange.setText(out);
        }
        else {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("contacts", "Forest Anderson,wizcardforest@gmail.com,@angelonfira,Eliza Kazan,elizakazan@gmail.com,@elisakazan");
            editor.commit();
        }
        /*
        FileInputStream in = null;
        try {
            in = getApplicationContext().openFileInput("Contacts");
        } catch (FileNotFoundException e) {
            FileOutputStream out = null;
            try {
                out = getApplicationContext().openFileOutput("Contacts", Context.MODE_PRIVATE);
                out.write(("2").getBytes());
                out.write(("Forest Anderson,wizcardforest@gmail.com,@angelonfira").getBytes());
                out.write(("Eliza Kazan,elizakazan@gmail.com,@elisakazan").getBytes());
                out.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                in = getApplicationContext().openFileInput("Contacts");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        //int contactNum = in.read()
        try {
            System.out.println(in.toString());
            System.out.println(in.read());
            in.
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


}
