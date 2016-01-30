import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import ui.ItemCell;
import ui.ListViewCell;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {
    @FXML
    private TextField fieldASIN;

    @FXML
    private Button getButton;

    @FXML
    private MenuItem menuPrintLocal;

    @FXML
    private ListView<String> listView;

    public Controller()
    {
        System.out.printf("Starting Controller\n");
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

        listView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
            public ListCell<String> call(ListView<String> listView) {
//                return new ListViewCell();
                return new ItemCell();
            }
        });
    }
}
