package App;

import java.lang.String;

public class Account
{
    private String email;

    private String password;

    private String lastRefresh;

    Account()
    {
        this.email = null;
        this.password = null;
        this.lastRefresh = null;
    }

    Account(String username,String password,String lastRefresh)
    {
        this.email = username;
        this.password = password ;
        this.lastRefresh = lastRefresh;
    }

    public void setLastUpdate()
    {
        lastRefresh = IOUtils.getDateTime();
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLastUpdate()
    {
        return lastRefresh;
    }

    public boolean sendMessage(EmailMessage msg)
    {
        DBConnection.createMessage(msg);
        return true;
    }
}
