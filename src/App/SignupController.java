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

public class SignupController extends Parent implements Initializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button signup;
    @FXML
    Label error;

    private SceneManager application;

    private Account account;

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

    public void checkSignup(ActionEvent event)
    {
        String email = username.getText();
        String pass = password.getText();
        if(IOUtils.checkEmail(email))
        {
            if(IOUtils.checkPassword(pass))
            {
                if(!application.checkSignUp(email))
                {
                    error.setText("This email is already used");
                }
                else
                {
                    error.setText("SignUp success");
                    account = new Account(email,pass,IOUtils.getDateTime());
                    DBConnection.createAccount(account);
                }
            }
            else
            {
                error.setText("Password Should has 8-12 character.");
            }
        }
        else
        {
            error.setText("Email not valid!!");
        }
    }

    public void goLoginView(ActionEvent event){

        application.loginView();
    }
}
