package App;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils
{

    public static boolean checkEmail(String email)
    {
        Pattern p = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
        Matcher m = p.matcher(email);

        if (!m.find())
        {
            System.out.println("Email not valid!!");
            return false;

        }
        return true;
    }

    public static boolean checkPassword(String password)
    {
        if(password.length()<8 || password.length()>12)
        {
            System.out.println("Password must has 8-12 character!!");
            return false;
        }
        return true;
    }

    /**
     *  Creates and returns a string with the current date
     *  and time, to use as a time stamp.
     * @return date/time string in the form "yyyy-mm-dd hh:mm:ss"
     */
    public static String getDateTime()
    {
        Date now = new Date();
        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now);
    }
}
