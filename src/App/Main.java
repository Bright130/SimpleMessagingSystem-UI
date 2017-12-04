package App;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends SceneManager {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("BJ Simple Messaging");
        primaryStage.setScene(new Scene(createWindow(),750, 420));
        primaryStage.getIcons().add(new Image("/IMG/icon.png"));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
