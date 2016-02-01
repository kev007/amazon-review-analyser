package Controller;

import Model.Item;
import View.ItemCell;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class NavController implements Initializable {
    public EventHandler navEvent;

    @FXML
    private TextField fieldASIN;
    @FXML
    private Button getButton;
    @FXML
    public ListView<Item> listView;

    public NavController() {
        System.out.printf("Starting NavController\n");
        //this constructor is run before the GUI
        //put stuff in initialize() instead
        Main.MC.NC = this;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        assert fieldASIN != null : "fx:id=\"asin\" was not injected: check your FXML file 'MainView.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'MainView.fxml'.";

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Item clicked: " + listView.getSelectionModel().getSelectedItem().itemID);
            }
        });

        navEvent = new EventHandler() {
            @Override
            public void handle(Event event) {
                Parent p = (Parent) event.getSource();

                System.out.println("Delete button clicked: " + p.getId());
//                event.consume();

                Main.IM.remove(p.getId());
                updateListView();
            }
        };

//        Main.IM.get("B00NMJJXU4");
//        updateListView();
//        CellController CC = new CellController();

        listView.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            public ListCell<Item> call(ListView<Item> listView) {
                return new ItemCell(navEvent);
//                return new ListViewCell(CC);
            }
        });
    }

    public void updateListView() {
        listView.setItems(Main.IM.getAllCollection());
        listView.refresh();
    }

    public void refresh() {
        listView.refresh();
    }

    @FXML
    public void debugFunc() {
        System.out.println("DEBUG button pressed");
//        updateListView();
        refresh();
    }

    @FXML
    public void getItem(ActionEvent event) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        /**
         * Read user input from GUI
         */
        String ASIN = fieldASIN.getText();     //Test value: "B00NMJJXU4"

        /**
         * Basic input check
         * Execute crawl command
         * get all ASIN and give them too the listView for the GUI
         */
        if (ASIN.length() == 10) {
            Main.IM.get(ASIN);
            updateListView();
        } else {
            System.out.print("INVALID INPUT\n");
        }
    }


}
