package App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

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
    AnchorPane allPane;
    @FXML
    AnchorPane unreadPane;
    @FXML
    AnchorPane readPane;
    @FXML
    AnchorPane sentPane;
    @FXML
    ListView<Button> sentList = new ListView<>();
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
    Label detailPane;
    @FXML
    SplitPane splitPane;
    @FXML
    GridPane gridPane;
    @FXML
    ScrollPane scrollPane;

    private SceneManager application;

    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        detailPane.setText("noiorhiogh0iwhgfgdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff\n\n\n\nn\n\n\n\n\n\\n\nn\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nn\\n\nn\n\n\n\n\n\n\n\n\nfffffffffffffffffffffffffgidfgj  erojojjj");
        splitPane.prefHeightProperty().bind(gridPane.heightProperty());
        splitPane.prefWidthProperty().bind(gridPane.widthProperty());
       // scrollPane.prefViewportHeightProperty().bind(gridPane.heightProperty());

    }




}
