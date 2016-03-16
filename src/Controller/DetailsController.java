package Controller;

import Model.Item;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {

    @FXML ListView<Item> listView;

    /**
     * Construktor
     *
     * is running before the gui starts
     */
    public DetailsController() {
        System.out.println("Starting DetailsController");
        //this constructor is run before the GUI
        //put stuff in initialize() instead
    }

    /**
    *  Controller Method
    *
    *  put some stuff here
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
