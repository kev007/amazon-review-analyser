package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class CellController {
    @FXML
    Button delButton;

    @FXML
    Label tempLabel;

    @FXML
    BorderPane borderPane;

    public CellController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/ItemCell.fxml"));
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

    public void setInfo(String string)
    {
        tempLabel.setText(string);
    }

    public BorderPane getPane()
    {
        return borderPane;
    }
}
