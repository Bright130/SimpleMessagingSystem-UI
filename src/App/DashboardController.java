package App;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
public class DashboardController extends Parent implements Initializable {
    @FXML
    Tab all;
    @FXML
    Tab unread;
    @FXML
    Tab read;
    @FXML
    Tab sent;
    @FXML
    AnchorPane allPane;
    @FXML
    AnchorPane unreadPane;
    @FXML
    AnchorPane readPane;
    @FXML
    AnchorPane sentPane;
    @FXML
    ListView<String> sentList = new ListView<>();
    @FXML
    Button refresh;
    @FXML
    Button newMessage;
    @FXML
    Button reply;
    @FXML
    Button forward;
    @FXML
    Button logout;
    @FXML
    Label detailPane;
    @FXML
    SplitPane splitPane;
    @FXML
    GridPane gridPane;
    @FXML
    ScrollPane scrollPane;

    ListView<String> list = new ListView<String>();


    private SceneManager application;

    public void setWindow(SceneManager application)
    {
        this.application = application ;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        detailPane.setText("noiorhiogh0iwhgfgdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff\n\n\n\nn\n\n\n\n\n\\n\nn\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nn\\n\nn\n\n\n\n\n\n\n\n\nfffffffffffffffffffffffffgidfgj  erojojjj");
        splitPane.prefHeightProperty().bind(gridPane.heightProperty());
        splitPane.prefWidthProperty().bind(gridPane.widthProperty());
        ObservableList<String> data = FXCollections.observableArrayList(
                "chocolaoooooooooooooooooooooooooooooooooooote", "salmon", "gold", "coral", "darkorchid",
                "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
                "blueviolet", "brown");



        sentList.setItems(data);

        sentList.setCellFactory(new Callback<ListView<String>,
                                        ListCell<String>>() {
                                    @Override
                                    public ListCell<String> call(ListView<String> list) {
                                        return new ColorRectCell();
                                    }
                                }
        );

        sentList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        System.out.println("Val ="+ new_val);
                        detailPane.setText(new_val);
                    }
                });


    }

    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Label rect = new Label("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooop");
            if (item != null) {
                setGraphic(rect);
            }
        }


    }




}
