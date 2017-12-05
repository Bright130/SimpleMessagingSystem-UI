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
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class EditorController extends Parent implements Initializable{


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

    /** setter method for window application */
    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    /** An override method to initial this window */
    public void initialize(URL location, ResourceBundle resources) {

        gridPaneScene.prefHeightProperty().bind(gridPane.heightProperty());
        gridPaneScene.prefWidthProperty().bind(gridPane.widthProperty());
        body.prefHeightProperty().bind(gridPaneScene.heightProperty().subtract(107));
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
    public void goDashboardView(){

        application.dashboardView();
    }

    /**
     * Send emailMessage have 3 main method New message, Reply message and forward message
     */
    public void sendMessage(){

        int checkEmail=1; /* boolean for validate to email */

        /*
         * If order > 1 it mean this program will do reply or forward
         * before send new email it will write old message
         */
        if(order>1)
        {
            String originalMessage = body.getText();
            if(order==2)
            {
                toAccount.setText(msg.getFromEmail());
                originalMessage+="\n\n-------------------Original message----------------------";
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
                originalMessage+="\n\n-------------------Forwarded message----------------------";
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

        String allContact = toAccount.getText();       /* every to email address */
        String fields[] = allContact.split(",");  /* each to email address */

        String text;                                    /* text in text field */

        multiEmail.clear();

        /*
        * Check variable before send an email
        */
        text=toAccount.getText();
        if(text.trim().isEmpty())
        {
            toAccount.setStyle("-fx-border-color:#f44336;");
            error.setText("ERROR!! Miss ToAccount");
            checkEmail=0;
        }
        text=subject.getText();
        if(text.trim().isEmpty())
        {
            subject.setStyle("-fx-border-color:#f44336;");
            error.setText("ERROR!! Miss subject");
            checkEmail=0;
        }
        text=body.getText();
        if(text.trim().isEmpty())
        {
            body.setStyle("-fx-border-color:#f44336;");
            error.setText("ERROR!! Miss body");
            checkEmail=0;
        }
        if(checkEmail==1)
        {
            for(String contact: fields)
            {
                if(!accountManager.checkEmail(contact))
                {
                    toAccount.setStyle("-fx-border-color:#f44336;");
                    error.setText("ERROR!! Miss contact => '"+contact+"' !!");
                    checkEmail=0;
                    break;
                }
                EmailMessage msg = new EmailMessage(IOUtils.getDateTime(),contact,account.getEmail(),subject.getText(),body.getText(),0,0,0);
                multiEmail.add(msg);
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
        this.order = order;
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
