/**
 *    LoginController
 *       A class that control the flow of program when user interacts in
 *      Login view
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


public class LoginController extends Parent implements Initializable
{
    @FXML
    /** Username text field */
    private TextField username;

    @FXML
    /** Password text field */
    private PasswordField password;

    @FXML
    /** Lable for display error text */
    private Label error;

    /** This window application */
    private SceneManager application;

    /** setter method for window application */
    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    /** An override method to initial this window */
    public void initialize(URL location, ResourceBundle resources)
    {
        error.setText("");
        username.setPromptText("Username");
        password.setPromptText("Password");
    }

    /** Method that checks the authentication */
    public void checkLogin()
    {
        String email = username.getText();
        String pass = password.getText();
        if(IOUtils.checkEmail(email))
        {
            if(IOUtils.checkPassword(pass))
            {
                if(!application.checkLogin(email,pass))
                {
                    error.setText("Invalid Email/Password");
                }
            }
            else
            {
                error.setText("Password is not valid!!");
            }
        }
        else
        {
            error.setText("Email is not valid!!");
        }
    }

    /** Method that redirects to Signup view */
    public void goSignupView()
    {

        application.signupView();
    }
}
