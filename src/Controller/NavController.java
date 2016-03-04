package Controller;

import Model.Item;
import View.ListView.NavListCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
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
    public ObservableList<Item> detailsData;

    @FXML
    private TextField amazonField;
    @FXML
    private Button getButton;
    @FXML
    public ListView<Item> navList;

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

        detailsData = FXCollections.observableArrayList();

        navList.setOnMouseClicked(event -> {
            if (navList.getSelectionModel().isEmpty()) {
                System.out.println("No listview item selected");
            } else {
                String ASIN = navList.getSelectionModel().getSelectedItem().itemID;
                System.out.println("Item clicked: " + ASIN);
                amazonField.setText(ASIN);

                detailsData.add(Main.IM.getItem(ASIN));
                refresh();
            }
        });

        navEvent = event -> {
            Parent p = (Parent) event.getSource();

            String ASIN = p.getId();

            System.out.println("Delete button clicked: " + ASIN);
//                event.consume();
            amazonField.setText(ASIN);

            Main.IM.remove(p.getId());
            updateListView();
        };

        amazonField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                getItem();
            }
        });

//        Main.IM.get("B0143UM4TC");
//        updateListView();
//        CellController CC = new CellController();

        navList.setCellFactory(navList1 -> new NavListCell(navEvent, "NavListCell.fxml"));
//        navList.setCellFactory(navList1 -> new ItemCell(navEvent));

        Platform.runLater(() -> {
            amazonField.requestFocus();
            updateListView();
        });
    }

    public void updateListView() {
        navList.setItems(Main.IM.getAllCollection());
//        navList.refresh();
    }

    public void refresh() {
        navList.refresh();
        Main.MC.detailsList.refresh();
        Main.MC.detailsList.setItems(detailsData);
    }

    @FXML
    public void selectAll() {
        amazonField.selectAll();
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

        String regex=".*?amazon\\.com\\/(?:gp\\/product|[^\\/]+\\/dp|dp)\\/([^\\/]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            ASIN = matcher.group(1);
        }

        /**
         * Basic input check
         * Execute crawl command
         * get all ASIN and give them too the navList for the GUI
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
