/**
 *    dashboardController
 *      This class have member variables and methods for filter email
 *      and separate them to correct email type(Read, UnRead and Send).
 *      Method for Show the information of an email
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */
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
import javafx.scene.control.TextArea;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.util.Callback;

public class DashboardController extends Parent implements Initializable
{
    /** Group of tabs in each category */
    @FXML
    Tab all;
    @FXML
    Tab unread;
    @FXML
    Tab read;
    @FXML
    Tab sent;

    /** Group of list view in each category */
    @FXML
    ListView<String> allList = new ListView<>();
    @FXML
    ListView<String> unreadList = new ListView<>();
    @FXML
    ListView<String> readList = new ListView<>();
    @FXML
    ListView<String> sentList = new ListView<>();

    @FXML
    /** Display detail of email pane */
            TextArea detailPane;

    @FXML
    /** Split pane */
            SplitPane splitPane;

    @FXML
    /** Grid pane for main program */
            GridPane gridPane;

    @FXML
    /** Scroll pane that contain detail in email */
            ScrollPane scrollPane;

    @FXML
    /** right box that contains many button */
            VBox rightBox;

    /** This window application */
    private SceneManager application;

    /**
     *  The Group of variables that store message in each categories
     */
    private ArrayList<EmailMessage> rawMsg;

    private ArrayList<EmailMessage> allMsg;

    private ArrayList<EmailMessage> readMsg;

    private ArrayList<EmailMessage> unReadMsg;

    private ArrayList<EmailMessage> sentMsg;
    /**
     *  The Group of variables that store subtitle of messages in each categories
     */
    private ArrayList<String> subSentMsg = new ArrayList<>();

    private ArrayList<String> subAllMsg = new ArrayList<>();

    private ArrayList<String> subReadMsg = new ArrayList<>();

    private ArrayList<String> subUnreadMsg = new ArrayList<>();

    /** User's account */
    protected Account myAccount;

    /** Current message that user views */
    private EmailMessage currentMsg;


    /** setter method for window application */
    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }

    /**
     *  Connect the email from database after that filter them and separate to correct category
     */
    private void fetchEmail()
    {
        myAccount = SceneManager.getAccount();
        myAccount.setLastUpdate();
        rawMsg = DBConnection.getMessage(myAccount);
        allMsg = new ArrayList<>();
        readMsg = new ArrayList<>();
        unReadMsg = new ArrayList<>();
        sentMsg = new ArrayList<>();
        String subjectDetail;

        for (EmailMessage m : rawMsg)
        {
            if(myAccount.getEmail().equals(m.getFromEmail())&&m.getFromEmail().equals(m.getToEmail()))
            {
                if(m.getIsRead()==0)
                {
                    subjectDetail="";
                    unReadMsg.add(m);
                    subjectDetail+="(*)From : "+m.getToEmail();
                    subjectDetail+="\n"+m.getSubject();
                    subjectDetail+="\n"+m.getLastModified();
                    subUnreadMsg.add(subjectDetail);
                    subAllMsg.add(subjectDetail);
                    allMsg.add(m);
                }
                else if(m.getIsRead()==1)
                {
                    if(m.getIsReaderDel()==1)
                    {
                        subjectDetail="";
                        sentMsg.add(m);
                        subjectDetail+="Send : "+m.getToEmail();
                        subjectDetail+="\n"+m.getSubject();
                        subjectDetail+="\n"+m.getLastModified();
                        subSentMsg.add(subjectDetail);
                        subAllMsg.add(subjectDetail);
                        allMsg.add(m);
                    }
                    else if(m.getIsSenderDel()==1)
                    {
                        subjectDetail="";
                        readMsg.add(m);
                        subjectDetail+="From : "+m.getToEmail();
                        subjectDetail+="\n"+m.getSubject();
                        subjectDetail+="\n"+m.getLastModified();
                        subReadMsg.add(subjectDetail);
                        subAllMsg.add(subjectDetail);
                        allMsg.add(m);
                    }
                }
            }
            else if(m.getIsRead()==0&&myAccount.getEmail().equals(m.getToEmail()))
            {
                subjectDetail="";
                unReadMsg.add(m);
                subjectDetail+="(*)From : "+m.getFromEmail();
                subjectDetail+="\n"+m.getSubject();
                subjectDetail+="\n"+m.getLastModified();
                subUnreadMsg.add(subjectDetail);
                subAllMsg.add(subjectDetail);
                allMsg.add(m);
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
                allMsg.add(m);
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
                allMsg.add(m);
            }
        }
    }

    @Override
    /** An override method to initial this window */
    public void initialize(URL location, ResourceBundle resources)
    {
        fetchEmail();
        detailPane.setText("                                        HELLO "+myAccount.getEmail());
        splitPane.prefHeightProperty().bind(gridPane.heightProperty());
        splitPane.prefWidthProperty().bind(gridPane.widthProperty());
        detailPane.prefWidthProperty().bind(gridPane.heightProperty().subtract(30));
        scrollPane.prefWidthProperty().bind(gridPane.heightProperty().subtract(0));
        detailPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(0));
        generateEmailList(allList,subAllMsg,allMsg);
        generateEmailListUnread(unreadList,subUnreadMsg,unReadMsg);
        generateEmailList(readList,subReadMsg,readMsg);
        generateEmailList(sentList,subSentMsg,sentMsg);
        unread.setText("Unread("+subUnreadMsg.size()+")");
        all.setText("All("+subAllMsg.size()+")");
        read.setText("Read("+subReadMsg.size()+")");
        sent.setText("Sent("+subSentMsg.size()+")");
    }

    /**
     *  Generate the list of email
     *  @param listView list view id name in FXML
     *  @param subMsg header messages
     *  @param msg messages in the category
     * */
    private void generateEmailList( ListView<String> listView,ArrayList<String> subMsg,ArrayList<EmailMessage> msg )
    {
        ObservableList<String> data = FXCollections.observableArrayList(subMsg);  /* header messages */
        listView.getSelectionModel();
        listView.setItems(data);
        listView.setCellFactory(new Callback<ListView<String>,
                                        ListCell<String>>()
                                {
                                    @Override
                                    public ListCell<String> call(ListView<String> list)
                                    {
                                        return new EmailCardCell();
                                    }
                                }
        );

        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>()
                {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val)
                    {
                        currentMsg = msg.get(listView.getSelectionModel().getSelectedIndex());
                        detailPane.setText(getDetailMessage(currentMsg));
                    }
                });

    }
    /**
     *  Generate the list of email only unread category and once email was read, it's also update read status
     *  @param listView list view id name in FXML
     *  @param subMsg header messages
     *  @param msg messages in the category
     * */
    private void generateEmailListUnread( ListView<String> listView,ArrayList<String> subMsg,ArrayList<EmailMessage> msg )
    {
        ObservableList<String> data = FXCollections.observableArrayList(subMsg);
        listView.getSelectionModel();
        listView.setItems(data);
        listView.setCellFactory(new Callback<ListView<String>,
                                        ListCell<String>>()
                                {
                                    @Override
                                    public ListCell<String> call(ListView<String> list)
                                    {
                                        return new EmailCardCell();
                                    }
                                }
        );

        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>()
                {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val)
                    {
                        currentMsg = msg.get(listView.getSelectionModel().getSelectedIndex());
                        detailPane.setText(getDetailMessage(currentMsg));
                        if(myAccount.getEmail().equals(currentMsg.getToEmail()))
                        {
                            currentMsg.setIsRead(1);
                        }
                        if(currentMsg.getFromEmail().equals(currentMsg.getToEmail()))
                        {
                            currentMsg.setIsRead(1);
                        }
                        DBConnection.updateStatusMessage(currentMsg);
                    }
                });

    }

    /**
     * Get the detail of the message
     * @param   email   EmailMessage that hold the information
     * @return true String about information of email for show in the board
     */
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

    /**
     *  Send email that want to reply and order for reply method and go to editor
     */
    public void goReplyView()
    {
        if(currentMsg!=null)
        {
            application.editorView(currentMsg,2);
        }
    }

    /**
     *  Send email that want to forward and order for forward method and go to editor
     */
    public void goForwardView()
    {
        if(currentMsg!=null)
        {
            application.editorView(currentMsg,3);
        }
    }

    /**
     *  Send order for NewMessage method and go to editor
     */
    public void goNewMessageView()
    {

        currentMsg = null;
        application.editorView(currentMsg,1);
    }

    /**
     *  Delete the current email that user select and refresh
     */
    public void deleteMessage()
    {
        if(currentMsg!=null)
        {
            if(currentMsg.getFromEmail().equals(currentMsg.getToEmail()))
            {
                ArrayList<EmailMessage> removeEmail = new ArrayList<>();
                removeEmail.add(currentMsg);
                DBConnection.deleteMessages(removeEmail);
            }
            else if(myAccount.getEmail().equals(currentMsg.getToEmail()))
            {
                currentMsg.setIsReaderDel(1);
                DBConnection.updateStatusMessage(currentMsg);
            }
            else if(myAccount.getEmail().equals(currentMsg.getFromEmail()))
            {
                currentMsg.setIsSenderDel(1);
                DBConnection.updateStatusMessage(currentMsg);
            }
            if(currentMsg.getIsReaderDel()==1&&currentMsg.getIsSenderDel()==1)
            {
                ArrayList<EmailMessage> removeEmail = new ArrayList<>();
                removeEmail.add(currentMsg);
                DBConnection.deleteMessages(removeEmail);
            }
            application.dashboardView();
        }
    }

    /**
     *  Method that redirects to login view
     */
    public void goLogoutView()
    {
        application.logoutView();
    }

    /**
     *  Method that redirects to dashboard view
     */
    public void goDashboardView()
    {
        application.dashboardView();
    }
}
