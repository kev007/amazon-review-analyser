package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by kev_s on 30.01.2016.
 */
public class ItemCell extends ListCell<String> {
    HBox hbox = new HBox();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    Button button = new Button("delete");

    @FXML
    private Button delButton;

    @FXML
    private Label tempLabel;

//    public ItemCell() {
//        super();
//
//        hbox.getChildren().addAll(label, pane, button);
//        HBox.setHgrow(pane, Priority.ALWAYS);
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                System.out.println(label.getText() + " : delete"); // + event
//            }
//        });
//    }

    public ItemCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemCell.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(label.getText() + " : delete"); // + event
            }
        });
    }

    protected void updateItem(String ASIN, boolean empty) {
        super.updateItem(ASIN, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            setGraphic(null);
        } else {
            label.setText(ASIN);
            setGraphic(hbox);
        }
    }
}
