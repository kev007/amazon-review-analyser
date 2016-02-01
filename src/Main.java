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
    public static Scene scene;
    public static Parent root;

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        root = FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Amazon Review Analyser");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DBM = new DBManager("test.db");
        DBM.start();
        IM = new ItemManager();

        launch(args);


        //Main.IM.get("B00A378L4C");
        //Main.IM.get("B00NMJJXU4");
        //while(true){ }
    }
}
