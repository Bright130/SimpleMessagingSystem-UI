/**
 *    Main
 *      This main of code in program
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       24 Oct. 2017
 */

package App;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;


public class Main extends SceneManager
{

    /** primary stage of the program */
    private static Stage primaryStage ;

    /**
     *  add sub-fix to the title bar
     *  @param text a sub-fix text
     */
    public static void addMoreTitleText(String text)
    {
        primaryStage.setTitle("BJ Simple Messaging"+text);
    }

    /**
     * Set minimum size of window
     * @param width minimum width
     * @param height minimum height
     */
    public static void setMinWindow(double width,double height)
    {
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
    }

    @Override
    /**
     * An override method that use to start the program
     *  @param primary stage the main state of program
     */
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BJ Simple Messaging");
        this.primaryStage.setScene(new Scene(createWindow(),750, 420));
        this.primaryStage.getIcons().add(new Image(File.separator+"IMG"+File.separator+"icon.png"));
        this.primaryStage.show();
    }


    /** main method */
    public static void main(String[] args)
    {
        launch(args);
    }
}
