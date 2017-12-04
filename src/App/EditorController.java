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

    public void sendMessage(ActionEvent event){

        int checkEmail=1;

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

        multiEmail.clear();
        for(String contact: fields)
        {
            if(!accountManager.checkEmail(contact))
            {
                toAccount.setStyle("-fx-border-color:#f44336;");
                error.setText("ERROR!! Miss contact => '"+contact+"' !!");
                checkEmail=0;
            }
            EmailMessage msg = new EmailMessage(IOUtils.getDateTime(),contact,account.getEmail(),subject.getText(),body.getText(),0,0,0);
            multiEmail.add(msg);
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

    public void setAccountManager(AccountManager accountManager)
    {
        this.accountManager = accountManager;
    }

    public void setAccount(Account account)
    {
        this.account = account;
        fromAccount.setText(account.getEmail());
    }

    public void setMsg(EmailMessage msg)
    {
        this.msg = msg;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public void setToAccount(String toAccount)
    {
        this.toAccount.setText(toAccount);
        this.toAccount.setEditable(false);
    }

}
