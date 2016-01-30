import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

public class Controller {
    @FXML
    private TextField fieldASIN;

    @FXML
    private Button getButton;

    @FXML
    private MenuItem menuPrintLocal;

    @FXML
    public ListView<String> crawlerList;

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
            crawlerList.setItems(Main.IM.getAllStrings());
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
    void initialize() {
        assert fieldASIN != null : "fx:id=\"asin\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";
    }
}
