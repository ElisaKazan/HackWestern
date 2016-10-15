/**
 * Created by elisakazan on 2016-10-15.
 */

public class User
{
    String name = "Elisa";
    String email = "elisa.kazan@gmail.com";
    String twitter = "@elisakazan";
    String linkedin = "Incomplete";
    String pic = "Incomplete";

    public String userToString()
    {
        String stringToSend = "";



        return stringToSend;
    }

    public String[] createFormatString(String content)
    {
        final int charMax = 50;

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
        return arrStrings;
    }

    public String createContentString()
    {
        String content = name + "," + email + "," + twitter + "," + linkedin + "," + pic;
        return content;
    }
}

