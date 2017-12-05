package App;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

 public class EmailCardCell extends ListCell<String> {
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Label label = new Label(item);
        if (item != null) {
            setGraphic(label);
        }
    }
}