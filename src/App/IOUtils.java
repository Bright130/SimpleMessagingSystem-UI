package App;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils
{
    /**
     * Asks for a string and returns it as the value of the function
     * @param   prompt    String to print, telling which coordinate
     * @return  The string the user entered (maximum 100 chars long)
     */
    public static String getString(String prompt)
    {
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        System.out.println(prompt);
        try
        {
            readBytes = System.in.read(buffer,0,200);
        }
        catch (IOException ioe)
        {
            System.out.println("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        int pos = inputString.indexOf("\n");
        if (pos > 0)
            inputString = inputString.substring(0,pos);
        return inputString;
    }

    public static String getEmail()
    {
        String email;
        int check = 0;

        do
        {
            check = 0;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter an Email : ");
            email = in.next();


            Pattern p = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
            Matcher m = p.matcher(email);

            if (!m.find())
            {
                System.out.println("Email not valid!!");
                check=1;
            }

        }while (check==1);

        return email;
    }

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

    public static String getPassword()
    {
        String password;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter your password : ");
        password = in.next();

        /*if(password.length()<8 || password.length()>12)
        {
            System.out.println("Password must has 8-12 character!!");
        }*/

        return password;
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
     * Reads a string and returns it as the value of the function,
     * without any prompt. Remove the newline before returning.
     * @param   prompt    String to print, telling which coordinate
     * @return  The string the user entered (maximum 100 chars long)
     */
    public static String getBareString()
    {
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        try
        {
            readBytes = System.in.read(buffer,0,200);
        }
        catch (IOException ioe)
        {
            System.out.println("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        int pos = inputString.indexOf("\n");
        if (pos > 0)
            inputString = inputString.substring(0,pos);
        return inputString;
    }

    /**
     * Asks for an integer and returns it as the value of the function
     * @param   prompt    String to print, telling which coordinate
     * @return value entered. If not an integer, prints an error message
     * and returns -999
     */
    public static int getInteger(String prompt)
    {
        int value = -999;
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        System.out.println(prompt);
        try
        {
            readBytes = System.in.read(buffer,0,200);
        }
        catch (IOException ioe)
        {
            System.out.println("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        try
        {
            int pos = inputString.indexOf("\n");
            if (pos > 0)
                inputString = inputString.substring(0,pos);
            value = Integer.parseInt(inputString);
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("Bad number entered");
        }
        return value;
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
