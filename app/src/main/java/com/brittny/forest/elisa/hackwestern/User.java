package com.brittny.forest.elisa.hackwestern;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;

/**
 * Created by elisakazan on 2016-10-15.
 */
public class User
{
    private String name;
    private String email;
    private String twitter;
    private String linkedin;
    private String pic;
    private String[] codes;
    private String[] connections;
    private JSONObject jsonObj;

    public User(){
        this.name = "";
        this.email = "";
        this.twitter = "";
        this.linkedin = "";
        this.pic = "";
    }

    public User(String name, String email)
    {
        this.name = name;
        this.email = email;
        twitter = "";
        linkedin = "";
        pic = "";
    }

    public User(String name, String email, String twitter, String linkedin, String pic)
    {
        this.name = name;
        this.email = email;
        this.twitter = twitter;
        this.linkedin = linkedin;
        this.pic = pic;
    }

    public void saveData(){

    }

    public String getDataString() throws JSONException {
        JSONObject userJSON = new JSONObject();
        userJSON.put("name", this.name);
        userJSON.put("email", this.email);
        userJSON.put("twitter", this.twitter);
        userJSON.put("linkedin", this.linkedin);
        userJSON.put("pic", this.pic);
        userJSON.put("connections", this.connections);

        String userJSONString = userJSON.toString();
        return userJSONString;

        /*public static final String MyPrefs = "MyPrefs";
        SharedPreferences sp = getSharedPreferences(MyPrefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userData", userJSONString);
        editor.commit();*/

            /*
                //PROCESS FOR ADDING STUF TO JSON
                //open file and put contents in buff
                String FILENAME = "user_file.json";

                InputStream is = getAssets().open(FILENAME);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                //create string from buff and create obj from string
                String jsonString = new String(buffer, "UTF-8");
                JSONObject userJSONObj = new JSONObject(jsonString);

                //use userJSONObj.put("key", "val") to create or overwrite a key val pair

                jsonString = userJSONObj.toString();
                System.out.println(jsonString);

                //Write to json file
                FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE); //overwrite
                fos.write(jsonString.getBytes());
                fos.close();
            */

    }

    public void addConnection(String data){
        //TODO: Call this when camera returns data
        //Save to jsonobj "connections" array?
    }

    public void getConnections(){

    }

    public String[] generateCodes ()
    {
        final int charMax = 20;
        String content = createContentString();
        String[] arrStrings = new String[content.length() / charMax + 1];

        for (int i = 0; i < content.length() / charMax + 1; i++) {
            String currContent = "";
            if (i == 0) {
                currContent = currContent + "0" + (content.length() / charMax + 1);
            } else {
                currContent = currContent + "" + i;
            }

            if (i < content.length() / charMax)
                currContent = currContent + "" + content.substring(i * charMax, (i + 1) * charMax);
            else
                currContent = currContent + "" + content.substring(i * charMax);

            arrStrings[i] = currContent;
            System.out.println(currContent);
        }
        return arrStrings;
    }

    public String createContentString()
    {
        String content = name + "," + email + "," + twitter + "," + linkedin + "," + pic;
        return content;
    }

    public String getName(){
        return name;
    };

    public String getEmail(){
        return email;
    };

    public String getTwitter(){
        return twitter;
    };

    public String getLinkedIn(){
        return linkedin;
    };

    public String getPic(){
        return pic;
    };

    public String[] getCodes(){
        return codes;
    };


    public void setName(String name){
        this.name = name;
    };

    public void setEmail(String email){
        this.email = email;
    };

    public void setTwitter(String twitter){
        this.twitter = twitter;
    };

    public void setLinkedIn(String linkedin){
        this.linkedin = linkedin;
    };

    public void setPic(){};

    public void setCodes(){};
}
