package Controller;

import Model.Item;
import View.ItemCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class NavController implements Initializable {
    public EventHandler navEvent;

    @FXML
    private TextField amazonField;
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
        assert amazonField != null : "fx:id=\"asin\" was not injected: check your FXML file 'MainView.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'MainView.fxml'.";

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String ASIN = listView.getSelectionModel().getSelectedItem().itemID;
                System.out.println("Item clicked: " + ASIN);
                amazonField.setText(ASIN);
            }
        });

        navEvent = new EventHandler() {
            @Override
            public void handle(Event event) {
                Parent p = (Parent) event.getSource();

                String ASIN = p.getId();

                System.out.println("Delete button clicked: " + ASIN);
//                event.consume();
                amazonField.setText(ASIN);

                Main.IM.remove(p.getId());
                updateListView();
            }
        };

        amazonField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    getItem();
                }
            }
        });

//        Main.IM.get("B00NMJJXU4");
//        updateListView();
//        CellController CC = new CellController();

        listView.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            public ListCell<Item> call(ListView<Item> listView) {
                return new ItemCell(navEvent);
//                return new ListViewCell(CC);
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                amazonField.requestFocus();
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
    public void getItem() {
        /**
         * Read user input from GUI
         */
        String input = amazonField.getText();     //Test value: "B00NMJJXU4"
        String ASIN = input;


        /**
         * REGEX TESTERS:
         * https://regex101.com/
         * http://www.ocpsoft.org/tutorials/regular-expressions/java-visual-regex-tester/
         * http://regexr.com/
         *
         * REGEX TEST: http:\/\/(?:www\.|)amazon\.com\/(?:gp\/product|[^\/]+\/dp|dp)\/([^\/]+)
         */

        String regex="http:\\/\\/(?:www\\.|)amazon\\.com\\/(?:gp\\/product|[^\\/]+\\/dp|dp)\\/([^\\/]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            ASIN = matcher.group(1);
        } else {
//            System.out.println("NO AMAZON URL MATCH FOUND FOR:  " + input);
        }

        /**
         * Basic input check
         * Execute crawl command
         * get all ASIN and give them too the listView for the GUI
         */
        if (ASIN.length() == 10) {
            Main.IM.get(ASIN);
            amazonField.setText("");
            updateListView();
        } else {
            System.out.print("INVALID INPUT\n");
        }
    }
}
