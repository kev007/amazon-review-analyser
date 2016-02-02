package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private MenuItem menuPrintLocal;

    @FXML
    private MenuItem menuAddItems;

    public static NavController NC;

    public MainController() {
        System.out.printf("Starting MainController\n");
        //this constructor is run before the GUI
        //put stuff in initialize() instead
        Main.MC = this;
    }

    public void refresh() {
        NC.refresh();
    }

    /**
     * print all crawled stuff for debuggging purposes
     * @param event
     */
    @FXML
    public void debugPrintLocal(ActionEvent event) {
        Main.IM.printLocal();
    }

    @FXML
    public void debugAddItems(ActionEvent event) {
        Main.IM.get("B00A378L4C");
        Main.IM.get("B0143UM4TC");
        Main.IM.get("B01606IDL0");
        Main.IM.get("B0163GS05Q");

        NC.updateListView();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }
}