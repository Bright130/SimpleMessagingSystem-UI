/*********************************************
 *  EmailCardCell
 *   A class that makes a cell in list view
 *
 *   Created by Chainarong Tumapha (Bright)  58070503409 AND
 *              Paween Surimittragool (Jarb) 58070503457
 *
 *       Group BJ
 *       11 Nov. 2017
 *
 */
package App;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class EmailCardCell extends ListCell<String>
{
    @Override
    /** Override method that checks the list item view */
    public void updateItem(String item, boolean empty)
    {
        super.updateItem(item, empty);
        Label label = new Label(item);
        if (item != null)
        {
            setGraphic(label);
        }
    }
}