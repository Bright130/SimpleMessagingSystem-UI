/**
 *    SignupController
 *       A class that control the flow of program when user interacts in
 *      Signup view
 *
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController extends Parent implements Initializable {
    @FXML
    /** Username text field */
    private TextField username;
    @FXML
    /** Password text field */
    private PasswordField password;
    @FXML
    /** Lable for display notification text */
    private Label notice;
    /** This window application */
    private SceneManager application;
    /** User account */
    private Account account;

    /** setter method for window application */
    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }

    @Override
    /** An override method to initial this window */
    public void initialize(URL location, ResourceBundle resources) {
        notice.setText("");
        username.setPromptText("Username");
        password.setPromptText("Password");
    }

    /** Method that validates text field arguments and check existing email */
    public void checkSignup()
    {
        String email = username.getText();  /* email that get from text field */
        String pass = password.getText();   /* password that get from text field */
        if(IOUtils.checkEmail(email))
        {
            if(IOUtils.checkPassword(pass))
            {
                if(!application.checkSignUp(email))
                {
                    notice.setStyle("-fx-text-fill:#f44336;");
                    notice.setText("This email is already used");
                }
                else
                {
                    notice.setStyle("-fx-text-fill:#00E676;");
                    notice.setText("Sign Up Success!!");
                    account = new Account(email,pass,IOUtils.getDateTime());
                    DBConnection.createAccount(account);
                    application.loginView();
                }
            }
            else
            {
                notice.setStyle("-fx-text-fill:#f44336;");
                notice.setText("Password must have 8-12 character.");
            }
        }
        else
        {
            notice.setStyle("-fx-text-fill:#f44336;");
            notice.setText("Email is not valid!!");
        }
    }

    /** Method that redirects to login view */
    public void goLoginView(){

        application.loginView();
    }
}
