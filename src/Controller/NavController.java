package Controller;

import View.ItemCell;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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


    public static EventHandler navEvent;

    @FXML
    private TextField fieldASIN;
    @FXML
    private Button getButton;
    @FXML
    private ListView<String> listView;

    public NavController() {
        System.out.printf("Starting NavController\n");
        //this constructor is run before the GUI
        //put stuff in initialize() instead
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        assert fieldASIN != null : "fx:id=\"asin\" was not injected: check your FXML file 'MainView.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'MainView.fxml'.";

        this.navEvent = new EventHandler() {
            @Override
            public void handle(Event event) {
              System.out.println("handle event");
                event.consume();
            }
        };


        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(ListView<String> listView) {
//                return new ListViewCell();
                return new ItemCell();
            }
        });
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
            listView.setItems(Main.IM.getAllStrings());
        } else {
            System.out.print("INVALID INPUT\n");
        }
    }
}