package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class SceneManager extends Application{
    public void start(Stage primaryStage) throws Exception {}
    private StackPane root =new StackPane();
    private GridPane pane = null;
    private static Account account = new Account();

    private static AccountManager manager = new AccountManager();

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

    public void signupView()
    {
        try{
            SignupController signup = (SignupController) changeScene("Signup.fxml") ;
            signup.setWindow(this);
            System.out.println("dfsfsgehth");

        }catch (Exception e)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

    }

    public Parent createWindow()
    {
        loginView();
        return root;
    }

    public boolean checkLogin(String username, String password)
    {
        account = manager.login(username,password);
        if(account==null)
        {

            return false ;
        }
        else

            return true;
    }

    public boolean checkSignup(String username, String password)
    {
        if(1==0)
        {

            return true ;

        }
        else
            return false;
    }

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

}
