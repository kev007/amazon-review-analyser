package View;

import Controller.Main;
import Model.Item;
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
 *
 * http://stackoverflow.com/a/15691104
 */
public class ItemCell extends ListCell<Item> {
    Item item;
    HBox hbox = new HBox();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    Button button = new Button("delete");

    public ItemCell() {
        super();

        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Main.IM.remove(label.getText());
                System.out.println(label.getText() + " : delete"); // + event
                updateItem(item, true);
            }
        });
    }

    public void updateItem(Item item, boolean empty) {
        this.item = item;
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            setGraphic(null);
        } else {
            label.setText(item!=null ? item.itemID : "<null>");
            setGraphic(hbox);
        }
    }
}