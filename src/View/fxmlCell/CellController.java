package View.fxmlCell;

import Controller.Main;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
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
    private BorderPane borderPane;

    @FXML
    private Label labelID;

    @FXML
    private Button delButton;

    @FXML
    void delItem(ActionEvent event) {

    }

//    public CellController() {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewCell.fxml"));
//        fxmlLoader.setController(this);
//        try
//        {
//            fxmlLoader.load();
//        }
//        catch (IOException e)
//        {
//            throw new RuntimeException(e);
//        }
//
////        delButton.setOnAction(new EventHandler<ActionEvent>() {
////            public void handle(ActionEvent event) {
////                System.out.println(tempLabel.getText() + " : delete"); // + event
////            }
////        });
//    }

    public CellController() {
        System.out.printf("Starting CellController\n");
        //this constructor is run before the GUI
        //put stuff in initialize() instead
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize CellController\n");
    }
}
