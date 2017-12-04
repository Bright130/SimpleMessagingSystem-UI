

package App;


public class AccountManager
{
    private  Account account = null;


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
    // TODO: 20/11/2017 Logout
//
//    public boolean logout(String email, Date upDateTime)
//    {
//
//    }

}


