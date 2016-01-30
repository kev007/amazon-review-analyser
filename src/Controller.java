import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import ui.ItemCell;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField fieldASIN;

    @FXML
    private Button getButton;

    @FXML
    private MenuItem menuPrintLocal;

    @FXML
    public ListView<String> crawlerList;

    @FXML
    private HBox hbox;

    ListView<String> listview;

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
         * get all ASIN and give them too the listview for the GUI
         */
        if (ASIN.length() == 10) {
            Main.IM.get(ASIN);
            listview.setItems(Main.IM.getAllStrings());
        } else {
            System.out.print("CHECK INPUT\n");
        }


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
    public void initialize(URL location, ResourceBundle resources) {
        assert fieldASIN != null : "fx:id=\"asin\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";

        listview = new ListView<>(Main.IM.getAllStrings());

        listview.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(ListView<String> param) {
                return new ItemCell();
            }
        });

        hbox.getChildren().add(listview);
        HBox.setHgrow(listview, Priority.ALWAYS);
    }
}
