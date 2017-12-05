/**
 *   Account.java
 *   This class have member variables and methods for create account
 *
 *
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */

package App;

import java.lang.String;

public class Account
{
    /** email of account */
    private String email;

    /** password of account */
    private String password;

    /** time of last refreshed of account */
    private String lastRefresh;

    /** Constructor method*/
    Account()
    {
        this.email = null;
        this.password = null;
        this.lastRefresh = null;
    }

    /**
     *  The constructor for create new account
     *  @param username username in account
     *  @param password password from user in this account
     *  @param lastRefresh Date/Time that user fetch the email
     */
    Account(String username,String password,String lastRefresh)
    {
        this.email = username;
        this.password = password ;
        this.lastRefresh = lastRefresh;
    }

    /**
     *  Get Date/Time from getDateTime in IOUtils for set setLastUpdate.
     */
    public void setLastUpdate()
    {
        lastRefresh = IOUtils.getDateTime();
    }

    /**
     *  Return EmailAddress of this Account
     *  @return email(username) of this accoiunt
     */
    public String getEmail()
    {
        return email;
    }

    /**
     *  Return EmailAddress of this Account
     *  @return password of this account
     */
    public String getPassword()
    {
        return password;
    }

    /**
     *  Return the last date of this Account's logout
     *  @return last update of this account
     */
    public String getLastUpdate()
    {
        return lastRefresh;
    }

    /**
     *  Send the email to database
     *  @param msg message that wants to send
     *  @return status of this sending
     */
    public boolean sendMessage(EmailMessage msg)
    {
        DBConnection.createMessage(msg);
        return true;
    }
}
