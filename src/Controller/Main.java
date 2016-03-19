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


public class Main extends Application {
    /** Manager to handle the Logic **/
    public static DBManager DBM;
    public static ItemManager IM;
    public static MainController MC;
    public static Scene scene;
    public static Parent root;

    /**
     * config Frontend and start GUI
     *
     * @param primaryStage
     * @throws IOException
     * @throws ParseException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */
    @Override public void start(Stage primaryStage) throws IOException, ParseException,
            ClassNotFoundException, SQLException, InvalidKeyException,
            NoSuchAlgorithmException, InterruptedException {

        root = FXMLLoader.load(getClass().getResource("/View/MainView.fxml"));
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Amazon Review Analyser");
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * Start Application
     *
     * @param args some configs
     */
    public static void main(String[] args) {
        startup(args);      //start logic

//        Debug.fastRun();
//        Debug.debug();
//        Debug.debug2();
    }

    /**
     * init and start Database-Manager and
     * Item-Manager
     */
    public static void startup(String[] args) {
        DBM = new DBManager("test3.db");
        DBM.start();
        IM = new ItemManager();

        System.out.println("Reading from Database");
        IM.readDatabase();

        for (String s: args) {
            System.out.println(s);
//            Main.IM.get(s, "", false);
        }

        launch(args);   //start interface
    }
}