package View;

import Controller.Main;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/a/15691104
 */
public class ItemCell extends ListCell<Item> {
    Item item;
    HBox hbox = new HBox();
    Label labelID = new Label("(ID)");
    Label labelName = new Label("(Name)");
    Pane pane = new Pane();
    Button button = new Button("delete");
    ProgressBar pb = new ProgressBar(0.0);

    public ItemCell() {
        super();

        hbox.getChildren().addAll(pb, labelID, labelName, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Main.IM.remove(labelID.getText());
                System.out.println(labelID.getText() + " : delete"); // + event
                updateItem(item, true);
            }
        });
    }

    public void updateItem(Item item, boolean remove) {
        this.item = item;
        super.updateItem(item, remove);
        setText(null);  // No text in label of super class
        if (remove) {
            setGraphic(null);
        } else {
            labelID.setText(item!=null ? item.itemID + " " : "<null>");
            labelName.setText(item!=null ? item.itemName : "<null>");
            setGraphic(hbox);
        }
    }
}