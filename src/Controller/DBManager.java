package Controller;

import Model.Item;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by Fechler on 29.01.16.
 */
public class DBManager extends Thread {

    public LinkedList<Item> writeQueue;

    /**
     * Database Name
     */
    private String dbName;

    /**
     * Setup sql - lite Database default by name
     * default.db
     */
    public DBManager() {
        this.setDaemon(true);
        this.dbName = "default.db";
    }

    /**
     * Setup SQLite database by name
     *
     * @param dbName
     */
    public DBManager(String dbName) {
        this.setDaemon(true);
        this.dbName = dbName;
        this.writeQueue = new LinkedList<Item>();

        System.out.println("Starting DBManager: " + this.dbName);
    }

    /**
     * Getter Database Tables
     *
     * @return DB - Tables
     */
    public String getDBTables() {
        String tables = "no tables!!!!!";
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            tables = "";
            while (rs.next()) {
                tables += rs.getString("TABLE_NAME");
                tables += "\n";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return tables;
    }

    /**
     * Getter Database Name
     *
     * @return DB - Name
     */
    public String getDBName() {
        return this.dbName;
    }

    /**
     * Add Amazon ASIN to Model
     */
    public void addItem(Item asinItem) {
        this.writeQueue.add(asinItem);
    }

    /**
     * A private Method to write
     * Items into SQL Lite DB
     */
    private void writeDB(){
        try {
            writeQueue.removeFirst().writeReviewsToDatabase(this.dbName, false);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  The Deamon DBManager Loop
     *  Thread is waiting for Items
     */
    @Override
    public void run() {

        while (true) {
            try {
                if (writeQueue.size() == 0){
                    this.sleep(400);
                }
                else {
                    System.out.println("Writing to DB: " + this.dbName);
                    writeDB();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    
}
