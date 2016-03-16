package Controller;

import Model.Item;
import View.ListView.DetailsCell;
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
    /** Static Controller **/
    public static NavController NC;
    public static DetailsController DC;

    /** Event Handler**/
    public EventHandler navEvent;

    /** FXML Elements**/
    @FXML private MenuItem menuPrintLocal;
    @FXML private MenuItem menuAddItems;
    @FXML public ListView<Item> detailsList;

    /**
     * Construktor
     *
     * is running before the gui starts
     */
    public MainController() {
        System.out.printf("Starting MainController\n");
        Main.MC = this;
    }

    /**
     * refresh NavigationController
     * and DetailsController
     */
    public void refresh() {

        // refresh Navigation
        NC.refresh();
        detailsList.refresh();
        detailsList.setItems(NC.getDetailsData());

        // refresh Detail
        // TODO DC refresh
        //DC.refresh();
    }

    /**
     * print all crawled stuff for debuggging purposes
     * @param event
     */
    @FXML public void debugPrintLocal(ActionEvent event) {
        Main.IM.printLocal();
    }

    /**
     * debug Method, add some Items to NavigationList
     */
    @FXML public void debugAddItems() {
        Main.IM.get("B00A378L4C", "", false);
        Main.IM.get("B0143UM4TC", "", false);
        Main.IM.get("B01606IDL0", "", false);
        Main.IM.get("B0163GS05Q", "", false);

        NC.updateListView();
    }

    /**
     * read Database and update ListView
     */
    @FXML public void readDB() {

        // TODO ugly method...
        Main.IM.readDatabase();
        NC.updateListView();
    }

    /**
     *  Controller Method
     *
     *  put some stuff here
     */
    @FXML public void initialize(URL location, ResourceBundle resources) {
        navEvent = event -> {
            Parent p = (Parent) event.getSource();

            String ASIN = p.getId();

            System.out.println("Details clicked: " + ASIN);
        };

        detailsList.setCellFactory(navList1 -> new DetailsCell(navEvent, "DetailsCell.fxml"));
    }
}