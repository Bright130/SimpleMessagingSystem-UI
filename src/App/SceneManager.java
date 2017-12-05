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

public class SceneManager extends Application
{
    /** Declare the abstract method */
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
        try
        {
            Main.addMoreTitleText("");
            LoginController login = (LoginController) changeScene("Login.fxml") ;
            login.setWindow(this);

        }
        catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }
    /** A method that invoke the Logout scene */
    public void logoutView()
    {
        loginView();

    }
    /** A method that invoke the Signup scene */
    public void signupView()
    {
        try
        {
            Main.addMoreTitleText("");
            SignupController signup = (SignupController) changeScene("Signup.fxml") ;
            signup.setWindow(this);

        }
        catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * A method that invoke the editor scene
     * @param msg email message which program uses to display information on editor
     * @param order the number of operation
     *     1 = new
     *     2 = reply
     *     3 = forward
     */
    public void editorView(EmailMessage msg,int order)
    {
        try
        {

            EditorController editor = (EditorController) changeScene("Editor.fxml") ;
            editor.setAccountManager(manager);
            editor.setAccount(account);

            if(order>1)
            {
                editor.setMsg(msg);
                if(order==2)
                {
                    editor.setToAccount(msg.getFromEmail());
                }
            }

            editor.setOrder(order);
            editor.setWindow(this);

        }
        catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }


    /** A method that invoke the dashboard scene */
    public void dashboardView()
    {
        try
        {
            Main.addMoreTitleText(" : Hello! "+account.getEmail());
            DashboardController dashboard = (DashboardController) changeScene("Dashboard.fxml") ;

            dashboard.setWindow(this);

        }
        catch (Exception e)
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
     * @param username is username of user
     * @param password is passwod of user's account
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
     * A method that change the scene
     * @param filename file of FXML which is scene that need to load
     * @return  controller
     */
    public Initializable changeScene(String filename) throws Exception
    {

        String path = ".."+ File.separator+"FXML"+ File.separator+filename ;
        InputStream file = getClass().getResourceAsStream(path);
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(path));
        if(pane!=null)
            root.getChildren().removeAll(pane);
        try
        {
            pane = loader.load(file);
        }
        finally
        {
            file.close();
        }

        root.getChildren().addAll(pane);
        return (Initializable) loader.getController();

    }
    /**
     * An account getter method
     * @return account
     */
    public static Account getAccount()
    {
        return account;
    }
}
