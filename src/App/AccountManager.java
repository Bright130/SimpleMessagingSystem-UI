/**
 *   AccountManager.java
 *   This class have a method for find and return an account
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


public class AccountManager
{
    private  Account account = null;


    /**
     * Check the email that correctly following the pattern form should be 8-12 character
     * @param   email      String of email
     * @param   password   String of password
     * @return if the program can find an account it will return that account but return null
     */
    public Account login(String email,String password)
    {
        Account temp;
        temp = DBConnection.getAccount(email);
        if(temp!=null)
        {
            if(password.equals(temp.getPassword()))
            {
                account = temp;
                return account;
            }
        }
        return account;
    }

    /**
     * Check the email that correctly following the pattern form should be 8-12 character
     * @param   email      String of email
     * @param   password   String of password
     * @return if the program can find an account it will return that account but return null
     */
    public boolean checkEmail(String email)
    {
        Account temp;
        temp = DBConnection.getAccount(email);
        if(temp!=null)
        {
            return true;
        }
        return false;
    }
    // TODO: 20/11/2017 Logout
//
//    public boolean logout(String email, Date upDateTime)
//    {
//
//    }

}


