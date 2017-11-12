package App;
public class SignUpView
{
    public Account signUp(AccountManager manager)
    {
        Account account = null;
        String email;
        String password;
        String created;

        do
        {
            email = IOUtils.getEmail();
            account = DBConnection.getAccount(email);
            if(account!=null)
            {
                System.out.println("Can not use this email!!");
                System.out.println("Please try again");
            }
            else
            {
                System.out.println("You can use this email");
            }
        }while (account!=null);

        password = IOUtils.getPassword();
        created = IOUtils.getDateTime();

        account = new Account(email,password,created);

        DBConnection.createAccount(account);

        return account;
    }
}
