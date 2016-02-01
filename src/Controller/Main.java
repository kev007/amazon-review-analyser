package Controller;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    public static DBManager DBM;
    public static ItemManager IM;
    public static MainController MC;
    public static Scene scene;
    public static Parent root;

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        root = FXMLLoader.load(getClass().getResource("/View/MainView.fxml"));
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Amazon Review Analyser");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        debug();
        DBM = new DBManager("test.db");
        DBM.start();
        IM = new ItemManager();
        launch(args);
    }

    public static void debug() {
        String ASIN = "http://www.amazon.com/CM-Storm-QuickFire-TK-Mechanical/dp/B00A378L4C/ref=pd_sim_147_2?ie=UTF8&dpID=31wokVpI8eL&dpSrc=sims&preST=_AC_UL160_SR160%2C160_&refRID=1C10ZYGJTRPXCSBW90PM";

        String regex="http:\\/\\/(?:www\\.|)amazon\\.com\\/(?:gp\\/product|[^\\/]+\\/dp|dp)\\/([^\\/]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ASIN);
        if (matcher.find()) {
            ASIN = matcher.group(1);
        } else {
            System.out.println("NO MATCH");
        }
        System.out.println("ASIN REGEX: " + ASIN);

        //Main.IM.get("B00A378L4C");
        //Main.IM.get("B00NMJJXU4");
        //while(true){ }
    }
}
