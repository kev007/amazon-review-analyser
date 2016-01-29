import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.text.ParseException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException,
    ClassNotFoundException, SQLException, InvalidKeyException,
    NoSuchAlgorithmException, InterruptedException {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        //launch(args);

        //example: write all reviews for an item (defined by its ASIN) to a SQLite database
        Item an_item = new Item("B00EB7UIZU");
        an_item.fetchReview();
        an_item.writeReviewsToDatabase("test.db", false);
    }
}
