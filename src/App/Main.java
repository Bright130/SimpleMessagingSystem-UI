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


public class Main extends SceneManager {

    @Override
    /** An override method that use to start the program
     *  @param primary stage the main state of program */
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("BJ Simple Messaging");
        primaryStage.setScene(new Scene(createWindow(),750, 420));
        primaryStage.getIcons().add(new Image(File.separator+"IMG"+File.separator+"icon.png"));
        primaryStage.show();
    }


    /** main method */
    public static void main(String[] args) {
        launch(args);
    }
}
