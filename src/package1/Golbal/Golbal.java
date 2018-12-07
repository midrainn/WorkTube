package package1.Golbal;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Golbal {
    public static boolean isPhonenumber(String phonenumber)
    {
        String match="^[1](77|59|83|76|52)[0-9]{8}$";
        Pattern p=Pattern.compile(match);
        Matcher m=p.matcher(phonenumber);
        if(m.find())return true;
        else return false;
    }
    public static boolean isUserName(String username)
    {
        String match1="^[a-zA-Z][a-zA-Z0-9]{7,14}$";

        Pattern p=Pattern.compile(match1);
        Matcher m=p.matcher(username);
        if(m.find())
        {
            String match2 = "^[A-Za-z0-9]+$";
            p = Pattern.compile(match2);
            m = p.matcher(username);
            if (m.find()) return true;
            return false;
        }
        else {

            return false;
        }
    }
    public static boolean isName(String name)
    {
        if(name.length()>15||name.length()<2)return false;
        String match1="^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,}$";
        Pattern p=Pattern.compile(match1);
        Matcher m=p.matcher(name);
        if(m.find())
        {
            return true;
        }
        else {

            return false;
        }
    }
    public static String BuildUID()
    {
        Random random=new Random();
        char a='a';
        int rndnum=0;
        String UID="";
        for(int i=0;i<30;++i)
        {
            rndnum=random.nextInt(3);
            switch (rndnum)
            {
                case 0:a=(char)((int)'0'+random.nextInt(10));
                    break;
                case 1:a=(char)((int)'a'+random.nextInt(26));
                    break;
                case 2:a=(char)((int)'A'+random.nextInt(26));
                    break;
            }
            UID+=a;
        }
        return UID;
    }
}
