package ui;

import javafx.scene.control.ListCell;

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
            setGraphic(CC.getPane());
        }
    }
}
