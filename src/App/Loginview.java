package App;
public class Loginview
{
    public Account login(AccountManager manager)
    {
        Account account = null;
        String email;
        String password;

       do
       {
           email = IOUtils.getEmail();
           password = IOUtils.getPassword();
           account = manager.login(email,password);
           if(account==null)
           {
                System.out.println("Your login email or password is wrong!!");
                System.out.println("Please try again");
           }
       }while (account==null);

        return account;
    }
}
