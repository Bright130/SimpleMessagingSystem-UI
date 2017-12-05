/*********************************************
 *  DBConnection
 *   A class that connects to SQLite database
 *   to read and write data.
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       11 Nov. 2017
 *
 */
package App;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection
{

    /** Open database */
    private static Connection openDB()
    {
        Connection connection = null;  /* DB connection */
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return connection ;
    }

    /** Get account from specific email
     *  @param email email that wants to get account
     *  @return Account that will be created
     */
    public static Account getAccount(String email)
    {

        Connection connection = openDB(); /* DB connection */
        if(connection == null)
            return null;
        Account account = null;           /* Account */
        Statement statement = null;       /* Statement of sql */
        ResultSet resultSet = null;       /* Query result set */
        try
        {
            statement = connection.createStatement();

            resultSet = statement.executeQuery( "SELECT * FROM Account WHERE username like '"+email+"' ;");

            if ( resultSet.next())
            {

                account =  new Account(resultSet.getString("username"),
                        resultSet.getString("passwordUser"),
                        resultSet.getString("lastRefresh")
                );
            }

            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Read successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        return account;
    }
    /** Create account
     *  @param account account that wants to create
     *  @return status of create an account
     */
    public static boolean createAccount(Account account)
    {

        Connection connection = openDB();  /* DB connection */
        if(connection == null)
            return false;
        Statement statement = null;        /* Statement of sql */

        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Account (username,passwordUser,lastRefresh) " +
                    "VALUES  ('"+account.getEmail()+"'" +
                    ",'"+account.getPassword()+"'" +
                    ",'"+account.getLastUpdate()+"');"
            );

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Insert successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    /** Get message from specific account
     *  @param account account that wants to get message
     *  @return list of email messages
     */
    public static ArrayList<EmailMessage> getMessage(Account account)
    {

        Connection connection = openDB();   /* DB connection */
        if(connection == null)
            return null;
        ArrayList<EmailMessage> messages = new ArrayList<EmailMessage>();
        Statement statement = null;       /* Statement of sql */
        ResultSet resultSet = null;       /* Query result set */

        try
        {
            account.setLastUpdate();
            statement = connection.createStatement();
            resultSet = statement.executeQuery( "SELECT * FROM Message WHERE  fromEmail like '"+ account.getEmail() +"' or toEmail like '"+account.getEmail()+"' ORDER BY lastModified DESC ;");

            while ( resultSet.next())
            {

                messages.add(new EmailMessage(
                        resultSet.getInt("id"),
                        resultSet.getString("lastModified"),
                        resultSet.getString("toEmail"),
                        resultSet.getString("fromEmail"),
                        resultSet.getString("subject"),
                        resultSet.getString("body"),
                        resultSet.getInt("isRead"),
                        resultSet.getInt("isSenderDel"),
                        resultSet.getInt("isReaderDel")
                ));
            }


            resultSet.close();
            System.out.println("Read successfully");

            System.out.println("account.getLastUpdate() = " + account.getEmail());
            statement.executeUpdate("UPDATE Account set "+
                    "lastRefresh = '"+account.getLastUpdate()+"' "+
                    "WHERE username like '"+account.getEmail()+"' "+
                    " ;"
            );
            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Update time successfully");

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        return messages;
    }

    /** create message and send to that account
     *  @param message message that wants to create
     *  @return status of created message
     */
    public static boolean createMessage(EmailMessage message)
    {

        Connection connection = openDB();   /* DB connection */
        if(connection == null)
            return false;
        Statement statement = null;         /* Statement of sql */

        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Message (lastModified,toEmail,fromEmail,subject,body,isReaderDel,isSenderDel,isRead) " +
                    "VALUES  ('"+message.getLastModified()+"'" +
                    ",'"+message.getToEmail()+"'" +
                    ",'"+message.getFromEmail()+"'" +
                    ",'"+message.getSubject()+"'" +
                    ",'"+message.getBodyText()+"'" +
                    ",'"+message.getIsReaderDel()+"'" +
                    ",'"+message.getIsSenderDel()+"'" +
                    ",'"+message.getIsRead()+"');"
            );

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Insert successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    /** update status of message
     *  @param message message that wants to update
     *  @return status of updated message
     */
    public static boolean updateStatusMessage(EmailMessage message)
    {

        Connection connection = openDB();   /* DB connection */
        if(connection == null)
            return false;
        Statement statement = null;         /* Statement of sql */

        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Message set "+
                    "isRead = '"+message.getIsRead()+"', "+
                    "isSenderDel = '"+message.getIsSenderDel()+"', "+
                    "isReaderDel = '"+message.getIsReaderDel()+"' "+
                    "WHERE id = '"+message.getId()+"' "+
                    " ;"
            );

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Update successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    /** Delete message
     *  @param messages message that want to delete
     *  @return status of deleting message
     */
    public static boolean deleteMessages(ArrayList<EmailMessage> messages)
    {

        Connection connection = openDB();   /* DB connection */
        if(connection == null)
            return false;
        Statement statement = null;         /* Statement of sql */

        try
        {

            statement = connection.createStatement();
            for (EmailMessage e: messages        )
            {


                statement.executeUpdate("DELETE from Message " +
                        "WHERE id = '" + e.getId() + "' " +
                        " ;"
                );
            }

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Delete successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }
}
