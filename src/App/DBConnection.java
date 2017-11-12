/*********************************************
 *  DBConnection
 *   A class that connect to SQLite database
 *   to read and write data.
 *
 *   Created by Chainarong Tumapha (Bright) 58070503409
 *     11 November 2017
 *
 */
package App;

import java.sql.*;
import java.util.ArrayList;


public class DBConnection {

    /**  Tester  **/
    public static void main(String[] args) {
        Account b = getAccount("bbb@b.com");
        ArrayList<EmailMessage> messages = getMessage(b);
        for (EmailMessage m : messages) {
            System.out.println("m.getSubject() = " + m.getSubject());
            System.out.print("m = " + m.getIsReaderDel() +""+m.getIsSenderDel());

        }


    }

    private static Connection openDB(){
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return connection ;
    }

    public static Account getAccount(String email) {

        Connection connection = openDB();
        if(connection == null)
            return null;
        Account account = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();

            resultSet = statement.executeQuery( "SELECT * FROM Account WHERE username like '"+email+"' ;");

            if ( resultSet.next()) {

                account =  new Account(resultSet.getString("username"),
                                   resultSet.getString("passwordUser"),
                                   resultSet.getString("lastRefresh")
                                  );
            }

            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Read successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        return account;
    }

    public static boolean createAccount(Account account) {

        Connection connection = openDB();
        if(connection == null)
            return false;
        Statement statement = null;

        try{
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
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    public static boolean updateAccount(Account account) {

        Connection connection = openDB();
        if(connection == null)
            return false;
        Statement statement = null;

        try{
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Account set "+
                                        "passwordUser = '"+account.getPassword()+"',"+
                                        "lastRefresh = '"+account.getLastUpdate()+"' "+
                                        "WHERE username like '"+account.getEmail()+"' "+
                                        " ;"
            );

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Update successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    public static boolean deleteAccount(Account account) {

        Connection connection = openDB();
        if(connection == null)
            return false;
        Statement statement = null;

        try{

            statement = connection.createStatement();
            statement.executeUpdate("DELETE from Account "+
                    "WHERE username like '"+account.getEmail()+"' "+
                    " ;"
            );

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Delete successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }




    public static ArrayList<EmailMessage> getMessage(Account account) {

        Connection connection = openDB();
        if(connection == null)
            return null;
        ArrayList<EmailMessage> messages = new ArrayList<EmailMessage>();
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            account.setLastUpdate(IOUtils.getDateTime());
            statement = connection.createStatement();
            resultSet = statement.executeQuery( "SELECT * FROM Message WHERE  fromEmail like '"+ account.getEmail() +"' or toEmail like '"+account.getEmail()+"' ORDER BY lastModified DESC ;");

            while ( resultSet.next()) {

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

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        return messages;
    }

    public static boolean createMessage(EmailMessage message) {

        Connection connection = openDB();
        if(connection == null)
            return false;
        Statement statement = null;

        try{
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Message (lastModified,toEmail,fromEmail,subject,body,isRead) " +
                    "VALUES  ('"+message.getLastModified()+"'" +
                    ",'"+message.getToEmail()+"'" +
                    ",'"+message.getFromEmail()+"'" +
                    ",'"+message.getSubject()+"'" +
                    ",'"+message.getBodyText()+"'" +
                    ",'"+message.getIsRead()+"');"
            );

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Insert successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    public static boolean updateStatusMessage(EmailMessage message) {

        Connection connection = openDB();
        if(connection == null)
            return false;
        Statement statement = null;

        try{
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
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    public static boolean deleteMessages(ArrayList<EmailMessage> messages) {

        Connection connection = openDB();
        if(connection == null)
            return false;
        Statement statement = null;

        try{

            statement = connection.createStatement();
            for (EmailMessage e: messages        ) {


                statement.executeUpdate("DELETE from Message " +
                        "WHERE id = '" + e.getId() + "' " +
                        " ;"
                );
            }

            statement.close();
            connection.commit();
            connection.close();
            System.out.println("Delete successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }
//    public static ArrayList<Account> getAccounts() {
//
//        Connection connection = openDB();
//        if(connection == null)
//            return null;
//        ArrayList<Account> accounts = new ArrayList<Account>();
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try{
//            statement = connection.createStatement();
//
//            resultSet = statement.executeQuery( "SELECT * FROM Account  ;");
//
//            while ( resultSet.next() ) {
//
//                accounts.add(new Account(resultSet.getString("username"),
//                        resultSet.getString("passwordUser"),
//                        resultSet.getString("lastRefresh")
//                ));
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//            System.out.println("Records created successfully");
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//
//        }
//        return accounts;
//    }






}
