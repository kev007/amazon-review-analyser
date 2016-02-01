package View.fxmlCell;

import Controller.Main;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class ListViewCell extends ListCell<Item> {
    Item item;
    CellController CC;

    @FXML
    public Button delButton;

    @FXML
    public Label labelID;

    @FXML
    public BorderPane borderPane;

    public ListViewCell(CellController CC) {
        super();

        this.CC = CC;
    }

    @Override
    public void updateItem(Item item, boolean remove) {
        this.item = item;
        super.updateItem(item, remove);

        if (remove) {
            setGraphic(null);
        } else {
            System.out.println("!!!! ITEM; " + item.itemID);
            labelID.setText(item!=null ? item.itemID + " " : "<null>");
            setGraphic(borderPane);
        }
    }

//    public void updateItem(Item item, boolean remove) {
//        this.item = item;
//        super.updateItem(item, remove);
//        setText(null);  // No text in label of super class
//        if (remove) {
//            setGraphic(null);
//        } else {
//            labelID.setText(item!=null ? item.itemID + " " : "<null>");
//            labelName.setText(item!=null ? item.itemName : "<null>");
//            setGraphic(hbox);
//        }
//    }
}
