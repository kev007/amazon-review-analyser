package View.fxmlCell;

import Controller.Main;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class CellController implements Initializable{
    @FXML
    public HBox hbox;
    @FXML
    public Label labelID;
    @FXML
    public Label labelName;
    @FXML
    public Label progress;
    @FXML
    public Label percent;
    @FXML
    public ProgressBar pb;
    @FXML
    public Button button;

    public CellController(EventHandler navEvent) {
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

        button.addEventHandler(ActionEvent.ACTION, navEvent);
    }

//    public CellController() {
//        System.out.printf("Starting CellController\n");
//        //this constructor is run before the GUI
//        //put stuff in initialize() instead
//    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }
}
