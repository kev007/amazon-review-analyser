package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Created by kev_s on 30.01.2016.
 */
public class ListViewCell extends ListCell<String> {
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string, empty);
        if(string != null)
        {
            CellController CC = new CellController();
            CC.setInfo(string);
            setGraphic(CC.getBox());
        }
    }
}
