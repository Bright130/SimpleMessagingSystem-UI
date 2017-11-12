package App;
import java.util.ArrayList;

public class Editor
{
    private EmailMessage msg;

    private Account currentAccount;

    public Editor(Account account)
    {
        this.currentAccount = account;
    }

//    public Editor(EmailMessage msg,AccountManager mng)
//    {
//
//    }

    public ArrayList<EmailMessage> multiMsg()
    {
        ArrayList<EmailMessage> multiMsg = new ArrayList<EmailMessage>();
        EmailMessage body = new EmailMessage();


        System.out.println("From : "+currentAccount.getEmail());
        String allContact = IOUtils.getString("==> Enter To forward email(using',')" );
        String fields[] = allContact.split(",");
        String subject = IOUtils.getString("==> Enter email subject");
        System.out.println("==> Enter message text below. Type END to  finish.");
        while (true)
        {
            String line = IOUtils.getBareString();
            if (line.compareTo("END") == 0)
                break;
            body.setBodyText(line);
        }
        for(String n: fields)
        {
            EmailMessage msg = new EmailMessage(IOUtils.getDateTime(),n,currentAccount.getEmail(),subject,body.getBodyText(),1,0,0);
            multiMsg.add(msg);
        }
        String response = IOUtils.getString("\nSend? ");
        if ((response.startsWith("Y")) ||
                (response.startsWith("y")))
        {
            for(String n: fields)
            {
                System.out.println("Sent to "+n);
            }
        }

        return multiMsg;
    }

    public ArrayList<EmailMessage> forward(EmailMessage email)
    {
        ArrayList<EmailMessage> forwardMsg = new ArrayList<EmailMessage>();
        EmailMessage bodyMsg = new EmailMessage();
        String forwardMessage = "-------------------Forwarded message----------------------";
        forwardMessage+="\nFrom : ";
        forwardMessage+=email.getFromEmail();
        forwardMessage+="\nDate : ";
        forwardMessage+=email.getLastModified();
        forwardMessage+="\nSubject  : ";
        forwardMessage+=email.getSubject();
        forwardMessage+="\nTo   : ";
        forwardMessage+=email.getToEmail();
        forwardMessage+="\n\n";
        forwardMessage+=email.getBodyText();

        System.out.println("From : "+currentAccount.getEmail());
        String allContact = IOUtils.getString("==> Enter To forward email(using',')" );
        String fields[] = allContact.split(",");
        String subject = IOUtils.getString("==> Enter email subject");
        System.out.println("==> Enter message text below. Type END to  finish.");
        while (true)
        {
            String line = IOUtils.getBareString();
            if (line.compareTo("END") == 0)
                break;
            line+="\n\n";
            line+=forwardMessage;
            bodyMsg.setBodyText(line);
        }


        for(String n: fields)
        {
            EmailMessage msg = new EmailMessage(IOUtils.getDateTime(),n,currentAccount.getEmail(),subject,bodyMsg.getBodyText(),1,0,0);
            forwardMsg.add(msg);
        }
        String response = IOUtils.getString("\nSend? ");
        if ((response.startsWith("Y")) ||
                (response.startsWith("y")))
        {
            for(String n: fields)
            {
                System.out.println("Sent to "+n);
            }
        }

        return forwardMsg;
    }

    public EmailMessage reply(EmailMessage email)
    {
        EmailMessage msg = new EmailMessage();
        String replyMessage = "-------------------Original message----------------------";

        replyMessage+="\nFrom : ";
        replyMessage+=email.getFromEmail();
        replyMessage+="\nDate : ";
        replyMessage+=email.getLastModified();
        replyMessage+="\nSubject  : ";
        replyMessage+=email.getSubject();
        replyMessage+="\nTo   : ";
        replyMessage+=email.getToEmail();
        replyMessage+="\n\n";
        replyMessage+=email.getBodyText();

        System.out.println("From : "+currentAccount.getEmail());
        msg.setFromEmail(currentAccount.getEmail());
        System.out.println("To  : "+email.getFromEmail());
        msg.setToEmail(email.getFromEmail());
        String subject = IOUtils.getString("==> Enter email subject");
        msg.setSubject(subject);
        System.out.println("==> Enter message text below. Type END to  finish.");
        while (true)
        {
            String line = IOUtils.getBareString();
            if (line.compareTo("END") == 0)
                break;
            line+="\n\n";
            line+=replyMessage;
            msg.setBodyText(line);
        }
        msg.setIsRead(1);
        msg.setLastModified(IOUtils.getDateTime());
        String response = IOUtils.getString("\nSend? ");
        if ((response.startsWith("Y")) ||
                (response.startsWith("y")))
        {
            msg.send();
            System.out.println("Sent message to " +
                    msg.getToEmail());
        }
        return msg;
    }

    public EmailMessage newMsg()
    {
        EmailMessage msg = new EmailMessage();
        System.out.println("From : "+currentAccount.getEmail());
        msg.setFromEmail(currentAccount.getEmail());
        String to = IOUtils.getString("==> Enter To email address");
        msg.setToEmail(to);
        String subject = IOUtils.getString("==> Enter email subject");
        msg.setSubject(subject);
        System.out.println("==> Enter message text below. Type END to  finish.");
        while (true)
        {
            String line = IOUtils.getBareString();
            if (line.compareTo("END") == 0)
                break;
            msg.setBodyText(line);
        }
        msg.setIsRead(1);
        msg.setLastModified(IOUtils.getDateTime());
        String response = IOUtils.getString("\nSend? ");
        if ((response.startsWith("Y")) ||
                (response.startsWith("y")))
        {
            msg.send();
            System.out.println("Sent message to " +
                    msg.getToEmail());
        }
        return msg;
    }
}
