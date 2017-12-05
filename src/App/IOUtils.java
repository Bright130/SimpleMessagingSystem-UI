/**
 *    IOUtils
 *       A class that check the pattern of email and password and return machine's time
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */

package App;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils
{
    /**
     * Check the email that correctly following the pattern form "a-z,A-Z,0-9:@:a-z,A-Z,0-9:.:a-z,A-Z{1-6}"
     * @param   email   String of email
     * @return true if the email correctly following the pattern if not return false
     */
    public static boolean checkEmail(String email)
    {
        /* pattern of regex */
        Pattern p = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
        Matcher m = p.matcher(email); /* string that match the pattern */

        if (!m.find()||email.trim().isEmpty())
        {
            System.out.println("Email is not valid!!");
            return false;
        }
        return true;
    }

    /**
     * Check the email that correctly following the pattern form should be 8-12 character
     * @param   password   String of password
     * @return true if the password correctly following the pattern if not return false
     */
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
     *  @return date/time string in the form "yyyy-mm-dd hh:mm:ss"
     */
    public static String getDateTime()
    {
        Date now = new Date();
        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now);
    }
}
