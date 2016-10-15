package com.brittny.forest.elisa.hackwestern;

/**
 * Created by elisakazan on 2016-10-15.
 */
public class User
{
    String name;
    String email;
    String twitter;
    String linkedin;
    String pic;
    String[] codes;

    public User()
    {
        name = "Elisa";
        email = "elisa.kazan@gmail.com";
        twitter = "@elisakazan";
        linkedin = "Incomplete";
        pic = "Incomplete";
    }

    public void generateCodes ()
    {
        final int charMax = 50;
        String content = createContentString();
        String[] arrStrings = new String[content.length() / charMax + 1];

        for (int i = 0; i < content.length() / charMax + 1; i++) {
            String currContent = "";
            if (i == 0) {
                currContent = currContent + "0" + content.length() / charMax + 1;

                if (name != null) {
                    currContent = currContent + "1";
                }
                else {
                    currContent = currContent + "0";
                }

                if (email != null) {
                    currContent = currContent + "1";
                }
                else {
                    currContent = currContent + "0";
                }

                if (twitter != null) {
                    currContent = currContent + "1";
                }
                else {
                    currContent = currContent + "0";
                }

                if (linkedin != null) {
                    currContent = currContent + "1";
                }
                else {
                    currContent = currContent + "0";
                }

                if (pic != null) {
                    currContent = currContent + "1";
                }
                else {
                    currContent = currContent + "0";
                }

            }
            else {
                currContent = currContent + "" + i;
            }

            if (i < content.length() / charMax)
                currContent = currContent + "" + content.substring(i * charMax, (i + 1) * charMax);
            else
                currContent = currContent + "" + content.substring(i * charMax);

            arrStrings[i] = currContent;
        }
        codes = arrStrings;
    }

    public String createContentString()
    {
        String content = name + "," + email + "," + twitter + "," + linkedin + "," + pic;
        return content;
    }
}
