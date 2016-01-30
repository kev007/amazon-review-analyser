package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class CellController {
    @FXML
    private Button delButton;

    @FXML
    private Label tempLabel;

    @FXML
    private BorderPane borderPane;

    public CellController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        delButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(tempLabel.getText() + " : delete"); // + event
            }
        });
    }

    public void setInfo(String string) {
        tempLabel.setText(string);
    }

    public BorderPane getPane() {
        return borderPane;
    }
}
