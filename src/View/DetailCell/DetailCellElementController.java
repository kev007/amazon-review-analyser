package View.DetailCell;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fechler on 16.03.16.
 */
public class DetailCellElementController implements Initializable {

    /** FXML Elements**/
    @FXML private Label elementTitle;
    @FXML private TextField elementRatingAmazon;
    @FXML private TextField elementRatingAnalyse;
    @FXML private TextField elementComment;

    /**
     * Construktor
     *
     * is running before the gui starts
     */
    public DetailCellElementController(){

    }

    /**
     *  Controller Method
     *
     *  put some stuff here
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
