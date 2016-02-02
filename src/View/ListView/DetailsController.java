package View.ListView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class DetailsController implements Initializable{
    @FXML
    public HBox hbox;
    @FXML
    public VBox vbox;
    @FXML
    public Label labelID;
    @FXML
    public Label labelName;
    @FXML
    public Label progress;
    @FXML
    public Label percent;
    @FXML
    public ProgressBar pb;
    @FXML
    public Button button;

    public DetailsController(EventHandler navEvent, String FXML) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        button.addEventHandler(ActionEvent.ACTION, navEvent);
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }
}
