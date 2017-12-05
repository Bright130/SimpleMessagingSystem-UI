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

    /** Date/Time when it is modified */
    private String lastModified;

    /** to email address */
    private String toAddress;

    /** from email address */
    private String fromAddress;

    /** subject in email address */
    private String subject;

    /** bodyText in email address */
    private String bodyText;

    /** this message is read or not */
    private int isRead;

    /** this message was deleted by sender or not */
    private int isSenderDel;

    /** this message was deleted by reader or not */
    private int isReaderDel;

    /**
     *  The constructor for create new email
     *  @param lastModified Date/Time when it is modified
     *  @param toAddress to email address
     *  @param fromAddress fromemail address
     *  @param subject subject in this email
     *  @param bodyText body in email
     *  @param isRead this message is read or not
     *  @param isSenderDel this message was deleted by sender or not
     *  @param isReaderDel this message was deleted by reader or not
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
     *  @param id id in database
     *  @param lastModified Date/Time when it is modified
     *  @param toAddress to email address
     *  @param fromAddress fromemail address
     *  @param subject subject in this email
     *  @param bodyText body in email
     *  @param isRead this message is read or not
     *  @param isSenderDel this message was deleted by sender or not
     *  @param isReaderDel this message was deleted by reader or not
     */
    EmailMessage(int id,String lastModified,String toAddress,String fromAddress,String subject,String bodyText,int isRead,int isSenderDel,int isReaderDel)
    {
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
     *  @param isRead set isRead logic
     */
    public void setIsRead(int isRead)
    {
        this.isRead = isRead;
    }

    /**
     *  Get Integer from deleteMessage in DashboardController for set isReaderDel.
     *  @param delRead logic delete from reader
     */
    public void setIsReaderDel(int delRead)
    {
        this.isReaderDel = delRead;
    }

    /**
     *  Get Integer from deleteMessage in DashboardController for set isSenderDel
     *  @param delSend logic delete from sender
     */
    public void setIsSenderDel(int delSend)
    {
        this.isSenderDel = delSend;
    }

    /**
     *  Return id that use for database
     *  @return id of email
     */
    public int getId()
    {
        return id;
    }

    /**
     *  Return the last date of user's login
     *  @return lastModified
     */
    public String getLastModified()
    {
        return lastModified;
    }

    /**
     *  Return ToEmail of this email
     *  @return to email address
     */
    public String getToEmail()
    {
        return toAddress;
    }

    /**
     *  Return FromEmail of this email
     *  @return from email address
     */
    public String getFromEmail()
    {
        return fromAddress;
    }

    /**
     *  Return Subject of this email
     *  @return subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     *  Return BodyText of this email
     *  @return bodyText in email
     */
    public String getBodyText()
    {
        return bodyText;
    }

    /**
     *  Return the status of Read Email(Unread/read)
     *  @return  isRead
     */
    public int getIsRead()
    {
        return isRead;
    }

    /**
     *  Return the status of Delete from Reader
     *  @return isReaderDel
     */
    public int getIsReaderDel()
    {
        return isReaderDel;
    }

    /**
     *  Return the status of Delete from Sender
     *  @return isSender
     */
    public int getIsSenderDel()
    {
        return isSenderDel;
    }

}
