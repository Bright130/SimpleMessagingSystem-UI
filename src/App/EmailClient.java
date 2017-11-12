package App;
public class EmailClient
{
    private static Account account = new Account();

    private static AccountManager manager = new AccountManager();

    static public void main(String args[])
    {
        int number;

        manager.initialize();
        System.out.println("Simple Messaging System");
        System.out.println("    1. Login");
        System.out.println("    2. SignUp");
        number = IOUtils.getInteger("Choose : ");

        switch (number)
        {
            case 1 :
                System.out.println("Login");
                Loginview loginview = new Loginview();
                account = loginview.login(manager);
                break;

            case 2 :
                System.out.println("SignUp");
                SignUpView signUpView = new SignUpView();
                account = signUpView.signUp(manager);
                break;

            default: break;
        }

        DashBoard myDashBoard = new DashBoard(account);
        myDashBoard.showList();

    }
}
