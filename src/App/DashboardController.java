package App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends Parent implements Initializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button signup;
    @FXML
    Label error;

    private SceneManager application;

    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setText("");
        username.setPromptText("Username");
        password.setPromptText("Password");
    }

    public void checkSignup(ActionEvent event){

        if(!application.checkSignup(username.getText(),password.getText())){
            error.setText("Someone already used this username");
        }
    }

    public void goLoginView(ActionEvent event){

        application.loginView();
    }
}
