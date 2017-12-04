/**
 *    SceneManager
 *       A class that manage the scene of this program
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */
package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneManager extends Application{
    /** Declear the interface method */
    public void start(Stage primaryStage) throws Exception {}
    /** Stack pane to contain every pane */
    private StackPane root =new StackPane();
    /** Grid pane to get main of frame in FXML */
    private GridPane pane = null;
    /** Account for user */
    private static Account account = new Account();
    /** To manage the account manager */
    private static AccountManager manager = new AccountManager();

    /** A method that invoke the Login scene */
    public void loginView()
    {
        try{
            LoginController login = (LoginController) changeScene("Login.fxml") ;

            login.setWindow(this);

        }catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }
    /** A method that invoke the Logout scene */
    public void logoutView()
    {
        try{
            LoginController login = (LoginController) changeScene("Login.fxml") ;
            login.setWindow(this);

        }catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }
    /** A method that invoke the Signup scene */
    public void signupView()
    {
        try{
            SignupController signup = (SignupController) changeScene("Signup.fxml") ;
            signup.setWindow(this);

        }catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }

    /** A method that invoke the editor scene */
    public void editorView(EmailMessage msg,int order)
    {
        try{
            EditorController ecitor = (EditorController) changeScene("Editor.fxml") ;
            ecitor.setAccountManager(manager);
            ecitor.setAccount(account);
            ecitor.setOrder(order);
            if(order>1)
            {
                ecitor.setMsg(msg);
                if(order==2)
                {
                    ecitor.setToAccount(msg.getFromEmail());
                }
            }
            ecitor.setWindow(this);

        }catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }


    /** A method that invoke the dashboard scene */
    public void dashboardView()
    {
        try{
            DashboardController dashboard = (DashboardController) changeScene("Dashboard.fxml") ;
            dashboard.setWindow(this);

        }catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * A method that initial the window
     * @return  the parent of login view which is root
     */
    public Parent createWindow()
    {
        loginView();
        return root;
    }

    /**
     * A method that check the authentication
     * @param
     * username is username of user
     * password is passwod of user's account
     * @return  status of checking
     */
    public boolean checkLogin(String username, String password)
    {
        account = manager.login(username,password);
        if(account==null)
        {
            return false ;
        }
        dashboardView();
        return true;
    }
    /**
     * A method that check the signup and existing email
     * @param
     * username is username of user
     * password is passwod of user's account
     * @return  status of checking, true means email isn't exist
     */
    public boolean checkSignUp(String username)
    {
        account = DBConnection.getAccount(username);
        if(account==null)
        {

            return true ;

        }
        else
            return false;
    }
    /**
     * A method that change the scene
     * @param filename file of FXML which is scene that need to load
     * @return  controller
     */
    public Initializable changeScene(String filename) throws Exception{

        String path = ".."+ File.separator+"FXML"+ File.separator+filename ;
        InputStream file = getClass().getResourceAsStream(path);
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(path));
        if(pane!=null)
            root.getChildren().removeAll(pane);
        System.out.println("path = " + path);
        try{
            pane = loader.load(file);
        }finally {
            file.close();
        }

        root.getChildren().addAll(pane);
        return (Initializable) loader.getController();

    }
    /** An account getter method */
    public static Account getAccount()
    {
        return account;
    }
}
