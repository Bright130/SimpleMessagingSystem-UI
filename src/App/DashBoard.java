package App;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DashBoard
{
    private AccountManager manager;

    private ArrayList<EmailMessage> allMsg;

    private ArrayList<EmailMessage> readMsg;

    private ArrayList<EmailMessage> unReadMsg;

    private ArrayList<EmailMessage> sentMsg;

    private ArrayList<String> subSentMsg;

    private ArrayList<String> subAllMsg;

    private ArrayList<String> subReadMsg;

    private ArrayList<String> subUnreadMsg;

    //private ObservableList<String> subSentMsg;

    private Account myAccount;

    private EmailMessage message;

    DashBoard(Account account)
    {
        this.myAccount = account;
        readEmail();
    }

    public void readEmail()
    {
        System.out.println(myAccount.getEmail());
        allMsg = DBConnection.getMessage(myAccount);
        readMsg = new ArrayList<EmailMessage>();
        unReadMsg = new ArrayList<EmailMessage>();
        sentMsg = new ArrayList<EmailMessage>();

        for (EmailMessage m : allMsg)
        {
            if(m.getIsRead()==1&&myAccount.getEmail().equals(m.getToEmail()))
            {
                unReadMsg.add(m);
                subUnreadMsg.add(m.getSubject());
                subAllMsg.add(m.getSubject());
            }
            else if(m.getIsRead()==0&&m.getIsReaderDel()==0&&myAccount.getEmail().equals(m.getToEmail()))
            {
                readMsg.add(m);
                subReadMsg.add(m.getSubject());
                subAllMsg.add(m.getSubject());
            }
            else if(myAccount.getEmail().equals(m.getFromEmail())&&m.getIsSenderDel()==0)
            {
                sentMsg.add(m);
                subSentMsg.add(m.getSubject());
                subAllMsg.add(m.getSubject());
            }
        }
    }

    public void menu(ArrayList<EmailMessage> emailList)
    {
        EmailMessage temp = null;
        int number;

        number = IOUtils.getInteger("\nSelect an email for read : ");
        temp = emailList.get(number-1);
        showDetail(temp);

        System.out.println("    1. Forward     2. Reply      3. Remove    4. Exit");
        number = IOUtils.getInteger("\nChoose : ");
        switch (number)
        {
            case 1 :
                System.out.println("Forward");
                Editor forwardMsg = new Editor(myAccount);
                ArrayList<EmailMessage> forwardEmail = new ArrayList<EmailMessage>();
                forwardEmail=forwardMsg.forward(temp);
                for(EmailMessage e:forwardEmail)
                {
                    myAccount.sendMessage(e);
                }
                break;
            case 2 :
                System.out.println("Reply");
                Editor replyMsg = new Editor(myAccount);
                message=replyMsg.reply(temp);
                myAccount.sendMessage(message);
                break;
            case 3 :
                System.out.println("Remove");
                if(myAccount.getEmail().equals(temp.getToEmail()))
                {
                    temp.setIsReaderDel(1);
                    DBConnection.updateStatusMessage(temp);
                }
                else if(myAccount.getEmail().equals(temp.getFromEmail()))
                {
                    temp.setIsSenderDel(1);
                    DBConnection.updateStatusMessage(temp);
                }
                if(temp.getIsReaderDel()==1&&temp.getIsSenderDel()==1)
                {
                    ArrayList<EmailMessage> removeEmail = new ArrayList<EmailMessage>();
                    removeEmail.add(temp);
                    DBConnection.deleteMessages(removeEmail);
                }
                break;
            case 4 :
                System.out.println("Exit");
                break;

            default: break;
        }
    }

    public void showDetail(EmailMessage email)
    {
        boolean check = false;

        System.out.println("From    : "+email.getFromEmail());
        System.out.println("Date    : "+email.getLastModified());
        System.out.println("Subject : "+email.getSubject());
        System.out.println("To      : "+email.getToEmail());
        System.out.println("\n\n"+email.getBodyText()+"\n\n");
        do
        {
            String response = IOUtils.getString("\nClose?(y/n) ");
            if ((response.startsWith("Y")) || (response.startsWith("y")))
            {
                check = true;
            }
        }while (check==false);
        if(myAccount.getEmail().equals(email.getToEmail()))
            {
                email.setIsRead(0);
                DBConnection.updateStatusMessage(email);
            }
    }

    public boolean deleteEmail()
    {
        return true;
    }

    public void showList()
    {

        readEmail();
        int number;
        int i=1;

        do
        {
            System.out.println("Hello  "+myAccount.getEmail());
            System.out.println("    1. Show all message         "+allMsg.size());
            System.out.println("    2. Show new message         "+unReadMsg.size());
            System.out.println("    3. Show read message        "+readMsg.size());
            System.out.println("    4. Show all sent message    "+sentMsg.size());
            System.out.println("    5. New message    ");
            System.out.println("    6. Multi message    ");
            System.out.println("    7. Log out    ");
            number = IOUtils.getInteger("\nChoose : ");
            switch (number)
            {
                case 1 :
                    System.out.println("Show all message");
                    for (EmailMessage m : allMsg)
                    {
                        System.out.print(""+(i++)+". Subject : "+m.getSubject());
                        System.out.print("          From : "+m.getFromEmail());
                        System.out.println("    "+m.getLastModified());
                    }
                    if(allMsg.size()>0)
                        menu(allMsg);
                    break;

                case 2 :
                    System.out.println("Show new message");
                    for (EmailMessage m : unReadMsg)
                    {
                        System.out.print(""+(i++)+". Subject : "+m.getSubject());
                        System.out.print("          From : "+m.getFromEmail());
                        System.out.println("    "+m.getLastModified());
                    }
                    if(unReadMsg.size()>0)
                        menu(unReadMsg);
                    break;

                case 3 :
                    System.out.println("Show read message");
                    for (EmailMessage m : readMsg)
                    {
                        System.out.print(""+(i++)+". Subject : "+m.getSubject());
                        System.out.print("          From : "+m.getFromEmail());
                        System.out.println("    "+m.getLastModified());
                    }
                    if(readMsg.size()>0)
                        menu(readMsg);
                    break;

                case 4 :
                    System.out.println("Show sent message");
                    for (EmailMessage m : sentMsg)
                    {
                        System.out.print(""+(i++)+". Subject : "+m.getSubject());
                        System.out.print("          To : "+m.getToEmail());
                        System.out.println("    "+m.getLastModified());
                    }
                    if(sentMsg.size()>0)
                        menu(sentMsg);
                    break;

                case 5 :
                    System.out.println("New message");
                    Editor msg = new Editor(myAccount);
                    message = msg.newMsg();
                    myAccount.sendMessage(message);
                    break;

                case 6 :
                    System.out.println("Sent multi email");
                    ArrayList<EmailMessage> multiEmail = new ArrayList<EmailMessage>();
                    Editor all = new Editor(myAccount);
                    multiEmail = all.multiMsg();
                    for(EmailMessage e:multiEmail)
                    {
                        myAccount.sendMessage(e);
                    }
                    break;

                case 7 :
                    System.out.println("Log out");
                    break;

                default: break;
            }
            i=1;
            readEmail();
        }while (number!=7);
    }

    public ArrayList<String> getSubAllMsg()
    {
        return subAllMsg;
    }

    public ArrayList<String> getSubReadMsg()
    {
        return subReadMsg;
    }

    public ArrayList<EmailMessage> getSubSentMsg() {
        return sentMsg;
    }

    public ArrayList<String> getSubUnreadMsg()
    {
        return subUnreadMsg;
    }

    public void printTest()
    {
        for(String a: subAllMsg)
        {
            System.out.println(a);
        }
    }
}
