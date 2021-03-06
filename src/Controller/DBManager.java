package Controller;

import Model.DatabaseUpdater;
import Model.Item;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Fechler on 29.01.16.
 */
public class DBManager extends Thread {

    /** Manager Attributes **/
    private LinkedList<Item> writeQueue;
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

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    /**
     * get all importend items
     * (all items which include itemID and itemInfo)
     *
     * @return
     */
    public HashMap<String, Item> getItems(){
        HashMap<String, Item> dbItems = new HashMap<String, Item>();
        try {
            Class.forName("org.sqlite.JDBC");

            // if database not exist
            if (!(new File(this.dbName).isFile())) {
                DatabaseUpdater.createDB(this.dbName);
            }

            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
//            DatabaseMetaData dbmd = conn.getMetaData();

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT itemID, itemName, numReviews FROM iteminfo;");

            while (rs.next()){
                Item item;
                item = new Item(rs.getString(1), rs.getString(2));
                item.total = rs.getInt(3);

                dbItems.put(rs.getString(1), item);
            }

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dbItems;
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
     * Get Item by Name
     */
    /*
    public Item getItems(String asinItem){
        //SQLiteDatabase db = new SQLiteDatabase();

    }*/

    /**
     * A private Method to write
     * Items into SQL Lite DB
     */
    private void writeDB(){
        try {
            Item item = writeQueue.removeFirst();
            if (item.crawlSuccess) {
                item.writeReviewsToDatabase(this.dbName, false);
            } else {
                System.out.println("FAILED writing to Database: incomplete crawl");
            }
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
