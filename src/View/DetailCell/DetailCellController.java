package View.DetailCell;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fechler on 16.03.16.
 */
public class DetailCellController implements Initializable {

    /** FXML Elements**/
    @FXML private Label titleLabel;
    @FXML private Button closeButton;
    @FXML private ImageView urlImage;
    @FXML private TextField rating1, rating2, comments;

    /**
     * Construktor
     *
     * is running before the gui starts
     */
    public DetailCellController(){
        System.out.println("Starting DetailCellController");
    }

    /**
     * Controller Method
     *
     * put some stuff here
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
