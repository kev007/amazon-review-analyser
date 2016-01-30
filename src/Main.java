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
    public static DBManager DBM;
    public static ItemManager IM;

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        Parent root = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        DBM = new DBManager("test.db");
        DBM.start();
        IM = new ItemManager();

        launch(args);

        //Main.CM.crawl("B00A378L4C");
    }
}
