import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import crawler.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldASIN;

    @FXML
    private Button crawlButton;

    @FXML
    void startCrawl(ActionEvent event) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        String ID = fieldASIN.getText();     //Test value: "B00NMJJXU4"
        if (ID.length() == 10) {
            System.out.print("Starting Crawler ...\n");
            Item an_item = new Item(ID);
            an_item.fetchReview();
            an_item.writeReviewsToDatabase("test.db", false);
        } else {
            System.out.print("CHECK ASIR\n");
        }
    }

    @FXML
    void initialize() {
        assert fieldASIN != null : "fx:id=\"asir\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert crawlButton != null : "fx:id=\"crawlButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";

    }
}
