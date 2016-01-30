import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import ui.CrawlerListController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Controller {
    @FXML
    private TextField fieldASIN;

    @FXML
    private Button getButton;

    @FXML
    private MenuItem menuPrintLocal;

    public CrawlerListController CLC;

    @FXML
    public void getItem(ActionEvent event) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        ObservableList data = FXCollections.observableArrayList();

        /**
         * Read user input from GUI
         */
        String ASIN = fieldASIN.getText();     //Test value: "B00NMJJXU4"

        /**
         * Basic input check
         * Execute crawl command
         */
        if (ASIN.length() == 10) {
            Main.IM.get(ASIN);
            data = Main.IM.getAll();
            CLC.addItem(ASIN);
            CLC.addAll(data);
        } else {
            System.out.print("CHECK ASIR\n");
        }

        Set set = Main.IM.localItems.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.println(mentry.getValue());
//            CLC.addItem(mentry.getKey().toString());
        }


    }

    @FXML
    public void debugPrintLocal(ActionEvent event) {
        Main.IM.printLocal();
    }

    @FXML
    void initialize() {
        assert fieldASIN != null : "fx:id=\"asin\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert getButton != null : "fx:id=\"getButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";

        CLC = new CrawlerListController();
    }
}
