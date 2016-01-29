import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private TextField fieldASIN;

    @FXML
    private Button crawlButton;

    @FXML
    void startCrawl(ActionEvent event) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        String ASIN = fieldASIN.getText();     //Test value: "B00NMJJXU4"

        if (ASIN.length() == 10) {
            System.out.print("Starting Crawler ...\n");
            Main.CM.crawl(ASIN);
        } else {
            System.out.print("CHECK ASIR\n");
        }
    }

    @FXML
    void get(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert fieldASIN != null : "fx:id=\"asir\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert crawlButton != null : "fx:id=\"crawlButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";


    }
}
