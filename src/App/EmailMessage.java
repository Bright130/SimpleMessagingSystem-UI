/**
 *    EmailMessage
 *      This class have member variables and methods for create an email
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */

package App;
import java.lang.String;

public class EmailMessage
{
    /**
     *  The member variables.
     */

    private int id;

    private String lastModified;

    private String toAddress;

    private String fromAddress;

    private String subject;

    private String bodyText;

    private int isRead;

    private int isSenderDel;

    private int isReaderDel;

    EmailMessage()
    {
    }

    /**
     *  The constructor for create new email
     */
    EmailMessage(String lastModified,String toAddress,String fromAddress,String subject,String bodyText,int isRead,int isSenderDel,int isReaderDel)
    {
        this.lastModified = lastModified;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.bodyText = bodyText;
        this.isRead = isRead;
        this.isReaderDel = isReaderDel;
        this.isSenderDel = isSenderDel;
    }

    /**
     *  The constructor for create email that using in database
     */
    EmailMessage(int id,String lastModified,String toAddress,String fromAddress,String subject,String bodyText,int isRead,int isSenderDel,int isReaderDel){
        this.id = id;
        this.lastModified = lastModified;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.bodyText = bodyText;
        this.isRead = isRead;
        this.isReaderDel = isReaderDel;
        this.isSenderDel = isSenderDel;
    }

    /**
     *  The methods
     */

    /**
     *  Get Integer from changed in DashboardController for set setIsRead.
     */
    public void setIsRead(int isRead)
    {
        this.isRead = isRead;
    }

    /**
     *  Get Integer from deleteMessage in DashboardController for set isReaderDel.
     */
    public void setIsReaderDel(int delRead)
    {
        this.isReaderDel = delRead;
    }

    /**
     *  Get Integer from deleteMessage in DashboardController for set isSenderDel.
     */
    public void setIsSenderDel(int delSend)
    {
        this.isSenderDel = delSend;
    }

    /**
     *  Return id that use for database
     */
    public int getId() { return id; }

    /**
     *  Return the last date of user's login
     */
    public String getLastModified()
    {
        return lastModified;
    }

    /**
     *  Return ToEmail of this email
     */
    public String getToEmail()
    {
        return toAddress;
    }

    /**
     *  Return FromEmail of this email
     */
    public String getFromEmail()
    {
        return fromAddress;
    }

    /**
     *  Return Subject of this email
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     *  Return BodyText of this email
     */
    public String getBodyText()
    {
        return bodyText;
    }

    /**
     *  Return the status of Read Email(Unread/read)
     */
    public int getIsRead()
    {
        return isRead;
    }

    /**
     *  Return the status of Delete from Reader
     */
    public int getIsReaderDel()
    {
        return isReaderDel;
    }

    /**
     *  Return the status of Delete from Sender
     */
    public int getIsSenderDel()
    {
        return isSenderDel;
    }

}
