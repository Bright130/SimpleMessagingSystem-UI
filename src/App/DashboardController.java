package App;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
public class DashboardController extends Parent implements Initializable {
    @FXML
    Tab all;
    @FXML
    Tab unread;
    @FXML
    Tab read;
    @FXML
    Tab sent;
    @FXML
    ListView<String> allList = new ListView<>();
    @FXML
    ListView<String> unreadList = new ListView<>();
    @FXML
    ListView<String> readList = new ListView<>();
    @FXML
    ListView<String> sentList = new ListView<>();
    @FXML
    Button refresh;
    @FXML
    Button newMessage;
    @FXML
    Button reply;
    @FXML
    Button forward;
    @FXML
    Button logout;
    @FXML
    TextArea detailPane;
    @FXML
    SplitPane splitPane;
    @FXML
    GridPane gridPane;
    @FXML
    GridPane gridPane2;
    @FXML
    ScrollPane scrollPane;
    @FXML
    VBox rightBox;



    private SceneManager application;

    private ArrayList<EmailMessage> allMsg;

    private ArrayList<EmailMessage> readMsg;

    private ArrayList<EmailMessage> unReadMsg;

    private ArrayList<EmailMessage> sentMsg;

    private EmailMessage currentMsg;

    private ArrayList<String> subSentMsg = new ArrayList<String>();

    private ArrayList<String> subAllMsg = new ArrayList<String>();

    private ArrayList<String> subReadMsg = new ArrayList<String>();

    private ArrayList<String> subUnreadMsg = new ArrayList<String>();

    private Account myAccount;



    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }

    private void fetchEmail()
    {
        myAccount = SceneManager.getAccount();
        allMsg = DBConnection.getMessage(myAccount);
        readMsg = new ArrayList<EmailMessage>();
        unReadMsg = new ArrayList<EmailMessage>();
        sentMsg = new ArrayList<EmailMessage>();
        String subjectDetail = null;

        for (EmailMessage m : allMsg)
        {
            if(m.getIsRead()==0&&myAccount.getEmail().equals(m.getToEmail()))
            {
                subjectDetail="";
                unReadMsg.add(m);
                subjectDetail+="(*)From : "+m.getFromEmail();
                subjectDetail+="\n"+m.getSubject();
                subjectDetail+="\n"+m.getLastModified();
                subUnreadMsg.add(subjectDetail);
                subAllMsg.add(subjectDetail);
            }
            else if(m.getIsRead()==1&&m.getIsReaderDel()==0&&myAccount.getEmail().equals(m.getToEmail()))
            {
                subjectDetail="";
                readMsg.add(m);
                subjectDetail+="From : "+m.getFromEmail();
                subjectDetail+="\n"+m.getSubject();
                subjectDetail+="\n"+m.getLastModified();
                subReadMsg.add(subjectDetail);
                subAllMsg.add(subjectDetail);
            }
            else if(myAccount.getEmail().equals(m.getFromEmail())&&m.getIsSenderDel()==0)
            {
                subjectDetail="";
                sentMsg.add(m);
                subjectDetail+="Send : "+m.getToEmail();
                subjectDetail+="\n"+m.getSubject();
                subjectDetail+="\n"+m.getLastModified();
                subSentMsg.add(subjectDetail);
                subAllMsg.add(subjectDetail);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        fetchEmail();
        detailPane.setText("noiorhiogh0iwhgfgdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff\n\n\n\nn\n\n\n\n\n\\n\nn\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nn\\n\nn\n\n\n\n\n\n\n\n\nfffffffffffffffffffffffffgidfgj  erojojjj");
        splitPane.prefHeightProperty().bind(gridPane.heightProperty());
        splitPane.prefWidthProperty().bind(gridPane.widthProperty());
        detailPane.prefWidthProperty().bind(gridPane.heightProperty().subtract(30));
        scrollPane.prefWidthProperty().bind(gridPane.heightProperty().subtract(0));
        detailPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(0));
        generateEmailList(allList,subAllMsg,allMsg);
        generateEmailListUnread(unreadList,subUnreadMsg,unReadMsg);
        generateEmailList(readList,subReadMsg,readMsg);
        generateEmailList(sentList,subSentMsg,sentMsg);
    }

    private void generateEmailList( ListView<String> listView,ArrayList<String> subMsg,ArrayList<EmailMessage> msg ){
        ObservableList<String> data = FXCollections.observableArrayList(subMsg);
        listView.getSelectionModel();
        listView.setItems(data);
        listView.setCellFactory(new Callback<ListView<String>,
                                        ListCell<String>>() {
                                    @Override
                                    public ListCell<String> call(ListView<String> list) {
                                        return new EmailCardCell();
                                    }
                                }
        );

        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        // System.out.println("Val ="+ new_val);
                        currentMsg = msg.get(listView.getSelectionModel().getSelectedIndex());
                        detailPane.setText(getDetailMessage(currentMsg));
                        //detailPane.setText(allMsg.get(sentList.getSelectionModel().getSelectedIndex()).getBodyText());
                    }
                });

    }

    private void generateEmailListUnread( ListView<String> listView,ArrayList<String> subMsg,ArrayList<EmailMessage> msg ){
        ObservableList<String> data = FXCollections.observableArrayList(subMsg);
        listView.getSelectionModel();
        listView.setItems(data);
        listView.setCellFactory(new Callback<ListView<String>,
                                        ListCell<String>>() {
                                    @Override
                                    public ListCell<String> call(ListView<String> list) {
                                        return new EmailCardCell();
                                    }
                                }
        );

        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        // System.out.println("Val ="+ new_val);
                        currentMsg = msg.get(listView.getSelectionModel().getSelectedIndex());
                        detailPane.setText(getDetailMessage(currentMsg));
                        currentMsg.setIsRead(1);
                        DBConnection.updateStatusMessage(currentMsg);
                        //detailPane.setText(allMsg.get(sentList.getSelectionModel().getSelectedIndex()).getBodyText());
                    }
                });

    }


    private String getDetailMessage(EmailMessage email)
    {
        String textUI = "";
        textUI+="From : ";
        textUI+=email.getFromEmail();
        textUI+="\nDate : ";
        textUI+=email.getLastModified();
        textUI+="\nSubject  : ";
        textUI+=email.getSubject();
        textUI+="\nTo   : ";
        textUI+=email.getToEmail();
        textUI+="\n\n";
        textUI+=email.getBodyText();
        return textUI;
    }

    static class EmailCardCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Label label = new Label(item);
            if (item != null) {
                setGraphic(label);
            }
        }
    }



    public void goReplyView(ActionEvent event){

        application.editorView();
    }

    public void goForwardView(ActionEvent event){

        application.editorView();
    }
    public void goNewMessageView(ActionEvent event){

        application.editorView();
    }

    public void deleteMessage(ActionEvent event){

        application.editorView();
    }

    // TODO: 11/20/2017 Event close immediately


    public void goLogoutView(ActionEvent event){

       application.logoutView();
    }

    // TODO: 11/20/2017  Update refresh time in account
    public void goDashboardView(ActionEvent event){

        application.dashboardView();
    }
}
