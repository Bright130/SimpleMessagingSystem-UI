/**
 *    EditorController
 *      This class have member variables and methods for create an email
 *      for send in 3 types New Message, Reply Message and Forward
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */
package App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class EditorController extends Parent implements Initializable
{
    @FXML
    /** Text field for typing to Account */
    private TextField toAccount;

    @FXML
    /** Text field for typing from Account */
    private TextField fromAccount;

    @FXML
    /** Text field for typing subject */
    private TextField subject;

    @FXML
    /** Text area for typing body */
    private TextArea body;

    @FXML
    /** Grid pane for main window */
    private GridPane gridPane;

    @FXML
    /** Grid pane for main scene */
    private GridPane gridPaneScene;

    @FXML
    private HBox titleBox;

    @FXML
    /** A frame of from account */
    private HBox fromBox;

    @FXML
    /** A frame of to account */
    private HBox toBox;

    @FXML
    /** A frame of subject */
    private HBox subjectBox;

    @FXML
    /** Label of error */
    private Label error;

    @FXML
    /** Scroll panel that contains text area of body detail */
    private ScrollPane scrollPane;

    /** This window application */
    private SceneManager application;

    /** User's account */
    private Account account;

    /** Account manager */
    private AccountManager accountManager;

    /** Email message */
    private EmailMessage msg;

    /** Operation order */
    private int order = 0;

    /** Email messages */
    private ArrayList<EmailMessage> multiEmail = new ArrayList<>();

    /** Error text which will be notified */
    private String errorText="" ;

    /** setter method for window application */
    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    /** An override method to initial this window */
    public void initialize(URL location, ResourceBundle resources)
    {

        gridPaneScene.prefHeightProperty().bind(gridPane.heightProperty());
        gridPaneScene.prefWidthProperty().bind(gridPane.widthProperty());
        scrollPane.prefHeightProperty().bind(gridPaneScene.heightProperty().subtract(toBox.heightProperty()).subtract(fromBox.heightProperty()).subtract(titleBox.heightProperty()).subtract(subjectBox.prefHeightProperty()).subtract(28));
        body.prefHeightProperty().bind(gridPaneScene.heightProperty().subtract(toBox.heightProperty()).subtract(fromBox.heightProperty()).subtract(titleBox.heightProperty()).subtract(subjectBox.prefHeightProperty()).subtract(30));
        body.prefWidthProperty().bind(gridPaneScene.widthProperty().subtract(2));

        toAccount.prefWidthProperty().bind(toBox.widthProperty().subtract(57));
        fromAccount.prefWidthProperty().bind(fromBox.widthProperty().subtract(57));
        subject.prefWidthProperty().bind(subjectBox.widthProperty().subtract(57));
        error.prefWidthProperty().bind(gridPaneScene.widthProperty().subtract(164));
        error.setText("");

    }
    /**
     *  Method that redirects to dashboard view
     */
    public void goDashboardView()
    {

        application.dashboardView();
    }

    /**
     *  Concatenate an error text
     *  @param text an error text
     */
    private void setTextError(String text)
    {
        if(errorText.isEmpty())
        {
            errorText="ERROR!! : "+text;
        }
        else
        {
            errorText+=", "+text;
        }
    }

    /**
     * Send emailMessage have 3 main method New message, Reply message and forward message
     */
    public void sendMessage()
    {

        int checkEmail=1; /* boolean for validate to email */
        String allContact = toAccount.getText();       /* every to email address */
        String fields[] = allContact.split(",");  /* each to email address */
        String text;                                    /* text in text field */
        errorText = "";

        EmailMessage msg;
        multiEmail.clear();

        /*
        * Check variable before send an email
        */
        text=toAccount.getText();
        if(!IOUtils.checkEmail(text))
        {
            toAccount.setStyle("-fx-border-color:#f44336;");
            setTextError("Invalid to address");
            checkEmail=0;
        }
        else
        {
            toAccount.setStyle("-fx-border-color:none;");
        }
        text=subject.getText();
        if(text.trim().isEmpty())
        {
            subject.setStyle("-fx-border-color:#f44336;");
            setTextError("Subject can't be empty");
            checkEmail=0;
        }
        else
        {
            subject.setStyle("-fx-border-color:none;");
        }
        text=body.getText();
        if(text.trim().isEmpty())
        {
            body.setStyle("-fx-border-color:#f44336;");
            setTextError("Body can't be empty");
            checkEmail=0;
        }
        else
        {
            body.setStyle("-fx-border-color:none;");
        }

        if(checkEmail==1)
        {
            for(String contact: fields)
            {
                if(!accountManager.checkEmail(contact))
                {
                    toAccount.setStyle("-fx-border-color:#f44336;");
                    setTextError("Invalid contact => '"+contact+"'");
                    checkEmail=0;
                    break;
                }

                if(contact.equals(account.getEmail()))
                {
                    msg = new EmailMessage(IOUtils.getDateTime(),contact,account.getEmail(),subject.getText(),body.getText(),0,1,0);
                    multiEmail.add(msg);
                    msg = new EmailMessage(IOUtils.getDateTime(),contact,account.getEmail(),subject.getText(),body.getText(),1,0,1);
                    multiEmail.add(msg);
                }
                else
                {
                    msg = new EmailMessage(IOUtils.getDateTime(),contact,account.getEmail(),subject.getText(),body.getText(),0,0,0);
                    multiEmail.add(msg);
                }
            }
        }

        if(checkEmail==1)
        {
            for(EmailMessage e:multiEmail)
            {
                account.sendMessage(e);
            }
            application.dashboardView();
        }
        error.setText(errorText);
    }

    /**
     *  Get accountManager from changed SceneManager for set setAccountManager.
     *  @param accountManager A manager of account
     */
    public void setAccountManager(AccountManager accountManager)
    {
        this.accountManager = accountManager;
    }

    /**
     *  Get account from changed SceneManager for set setAccount.
     *  @param account user's account
     */
    public void setAccount(Account account)
    {
        this.account = account;
        fromAccount.setText(account.getEmail());
    }

    /**
     *  Get account from Dashboard for set setMsg for do reply or forward method.
     *  @param msg email message
     */
    public void setMsg(EmailMessage msg)
    {
        this.msg = msg;
    }

    /**
     *  Get account from Dashboard for set setOrder for do confirm user's action.
     *  @param order order of operation
     */
    public void setOrder(int order)
    {
        String originalMessage = body.getText();
        this.order = order;
        if(order==2)
        {
            subject.setText("RE:"+msg.getSubject());
            toAccount.setText(msg.getFromEmail());
            originalMessage+="\n\n\n-------------------Original message----------------------";
            originalMessage+="\nFrom : ";
            originalMessage+=msg.getFromEmail();
            originalMessage+="\nDate : ";
            originalMessage+=msg.getLastModified();
            originalMessage+="\nSubject  : ";
            originalMessage+=msg.getSubject();
            originalMessage+="\nTo   : ";
            originalMessage+=msg.getToEmail();
            originalMessage+="\n\n";
            originalMessage+=msg.getBodyText();
            body.setText(originalMessage);
        }
        else if(order==3)
        {
            subject.setText("FW:"+msg.getSubject());
            originalMessage+="\n\n\n-------------------Forwarded message----------------------";
            originalMessage+="\nFrom : ";
            originalMessage+=msg.getFromEmail();
            originalMessage+="\nDate : ";
            originalMessage+=msg.getLastModified();
            originalMessage+="\nSubject  : ";
            originalMessage+=msg.getSubject();
            originalMessage+="\nTo   : ";
            originalMessage+=msg.getToEmail();
            originalMessage+="\n\n";
            originalMessage+=msg.getBodyText();
            body.setText(originalMessage);
        }
    }

    /**
     *  Get toAccountEmail from Dashboard for set toAccount for confirm toEmail in reply method.
     *  @param toAccount to account email address
     */
    public void setToAccount(String toAccount)
    {
        this.toAccount.setText(toAccount);
        this.toAccount.setEditable(false);
    }

}
