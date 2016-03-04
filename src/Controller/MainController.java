package Controller;

import Model.Item;
import View.ListView.DetailsCell;
import View.ListView.NavListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static NavController NC;
    public EventHandler navEvent;

    @FXML
    private MenuItem menuPrintLocal;

    @FXML
    private MenuItem menuAddItems;

    @FXML
    public ListView<Item> detailsList;

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
    public void readDB(ActionEvent event) {
        Main.IM.readDatabase();
//        Main.IM.add("0000000123", "TEST ITEM 123");

        NC.updateListView();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        navEvent = event -> {
            Parent p = (Parent) event.getSource();

            String ASIN = p.getId();

            System.out.println("Details clicked: " + ASIN);
        };

        detailsList.setCellFactory(navList1 -> new DetailsCell(navEvent, "DetailsCell.fxml"));
    }
}