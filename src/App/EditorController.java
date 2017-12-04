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

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class EditorController extends Parent implements Initializable{

    @FXML
    Button send;
    @FXML
    Button close;
    @FXML
    VBox vbox ;
    @FXML
    TextField toAccount;
    @FXML
    TextField fromAccount;
    @FXML
    TextField subject;
    @FXML
    TextArea body;
    @FXML
    GridPane gridPane;
    @FXML
    GridPane gridPane2;
    @FXML
    HBox fromBox;
    @FXML
    HBox toBox;
    @FXML
    HBox subjectBox;
    @FXML
    Label toText;
    @FXML
    Label fromText;
    @FXML
    Label subjectText;
    @FXML
    Label error;

    /**
     *  The member variables.
     */
    private SceneManager application;

    private Account account;

    private AccountManager accountManager;

    private EmailMessage msg;

    private int order = 0;

    ArrayList<EmailMessage> multiEmail = new ArrayList<>();


    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gridPane2.prefHeightProperty().bind(gridPane.heightProperty());
        gridPane2.prefWidthProperty().bind(gridPane.widthProperty());
        body.prefHeightProperty().bind(gridPane2.heightProperty().subtract(107));
        body.prefWidthProperty().bind(gridPane2.widthProperty().subtract(2));

        toAccount.prefWidthProperty().bind(toBox.widthProperty().subtract(57));
        fromAccount.prefWidthProperty().bind(fromBox.widthProperty().subtract(57));
        subject.prefWidthProperty().bind(subjectBox.widthProperty().subtract(57));
        error.prefWidthProperty().bind(gridPane2.widthProperty().subtract(164));
        error.setText("");

    }

    public void goDashboardView(ActionEvent event){

        application.dashboardView();
    }

    /**
     * Send emailMessage have 3 main method New message, Reply message and forward message
     */
    public void sendMessage(ActionEvent event){

        int checkEmail=1;

        /**
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

        String allContact = toAccount.getText();
        String fields[] = allContact.split(",");

        String a;

        multiEmail.clear();

        /**
        * Check variable before send an email
        * */
        a=toAccount.getText();
        if(a.trim().isEmpty())
        {
            toAccount.setStyle("-fx-border-color:#f44336;");
            error.setText("ERROR!! Miss ToAccount");
            checkEmail=0;
        }
        a=subject.getText();
        if(a.trim().isEmpty())
        {
            subject.setStyle("-fx-border-color:#f44336;");
            error.setText("ERROR!! Miss subject");
            checkEmail=0;
        }
        a=body.getText();
        if(a.trim().isEmpty())
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
     */
    public void setAccountManager(AccountManager accountManager)
    {
        this.accountManager = accountManager;
    }

    /**
     *  Get account from changed SceneManager for set setAccount.
     */
    public void setAccount(Account account)
    {
        this.account = account;
        fromAccount.setText(account.getEmail());
    }

    /**
     *  Get account from Dashboard for set setMsg for do reply or forward method.
     */
    public void setMsg(EmailMessage msg)
    {
        this.msg = msg;
    }

    /**
     *  Get account from Dashboard for set setOrder for do confirm user's action.
     */
    public void setOrder(int order)
    {
        this.order = order;
    }

    /**
     *  Get toAccountEmail from Dashboard for set toAccount for confirm toEmail in reply method.
     */
    public void setToAccount(String toAccount)
    {
        this.toAccount.setText(toAccount);
        this.toAccount.setEditable(false);
    }

}
