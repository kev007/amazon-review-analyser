package Controller;

import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * Created by Kevin on 29.01.2016.
 */
public class ItemManager {
    /**
     * http://stackoverflow.com/a/17708526
     *
     * HashMap localItems is used to store all the Item objects.
     * <key, value> where the key is the ASIN and trhe value is the Item object
     */
    public HashMap<String, Item> localItems;
    public int thread;

    /**
     * ItemManager constructor
     * inits the Hashmap
     * sets the total of launched threads to 0
     */
    public ItemManager() {
        System.out.println("Starting ItemManager");

        System.out.println("Reading from Database");
        readDatabase();

        localItems = new HashMap<String, Item>();
        thread = 0;
    }

    public static void readDatabase() {
        System.out.println(Main.DBM.getDBTables());
        //TODO: finish
//        for (:
//             ) {
//            add(ASIN);
//        }

        Main.DBM.getItems();
    }

    /**
     * adds empty Item to localItems
     * @param ASIN
     */
    public void add(String ASIN) {
        if (localItems.containsKey(ASIN)) {
            System.out.println(ASIN + " found locally in localItems: " + localItems.get(ASIN).itemID);
        } else {
            System.out.println("adding " + ASIN);
            //TODO: fill items
        }
    }

    /**
     * return localItems for corresponding ASIN, crawl and return if not locally available
     * @param ASIN
     */
    public void get(String ASIN) {
        if (localItems.containsKey(ASIN)) {
            System.out.println(ASIN + " found locally in localItems: " + localItems.get(ASIN).itemID);
        } else {
            thread++;
            localItems.put(ASIN, crawl(thread, ASIN));
        }
    }

    /**
     * returns Item for specified ASIN
     * @param ASIN
     */
    public Item getItem(String ASIN) {
        return localItems.get(ASIN);
    }

    /**
     * return localItems for corresponding ASIN, crawl and return if not locally available
     * @param ASIN
     */
    public void remove(String ASIN) {
        if (localItems.containsKey(ASIN)) {
            System.out.println(ASIN + " found locally in localItems and deleted: " + localItems.get(ASIN).itemID);
            localItems.remove(ASIN);
        } else {
            System.out.println(ASIN + " not found locally in localItems: ");
        }
    }

    /**
     * for a given ASIN, removes it from localItems
     * @param ASIN
     */
    public void delete(String ASIN) {
        if (localItems.containsKey(ASIN)) {
            localItems.remove(ASIN);
            System.out.println(ASIN + " found locally and removed!");
        } else {
            System.out.println(ASIN + " not found locally");
        }
    }

    /**
     * for debugging purposes, iterates through localItems and prints them to console
     */
    public void printLocal() {
        int total = 0;
        Set set = localItems.entrySet();
        for (Object aSet : set) {
            total++;
            Map.Entry entry = (Map.Entry) aSet;
            System.out.print(total + ". " + entry.getKey() + " - ");
            System.out.println(entry.getValue());
        }

        System.out.println("total items: " + total);
    }

    /**
     * start new Model thread for given ASIN
     */
    public Item crawl(int thread, String ASIN) {
        Item item = new Item(ASIN);
        Crawler C = new Crawler(thread, item);

        Thread t = new Thread (C);
        t.start();

        return item;
    }

    /**
     * returns all ASIN as Strings by iterating through the localItems hashmap
     * @return
     */
    public ObservableList getAllStrings() {
        ObservableList data = FXCollections.observableArrayList();

        for (Object o : localItems.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            data.add(entry.getKey().toString());
        }
        return data;
    }

    /**
     * returns all Items an Item Collection by iterating through the localItems hashmap
     * @return
     */
    public ObservableList getAllCollection() {
        ObservableList data = FXCollections.observableArrayList();

        for (Object o : localItems.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            data.add(entry.getValue());
        }
        return data;
    }


    /**
     * runnable Model: crawl amazon.com for customer reviews for given ASIN
     */
    class Crawler implements Runnable{
        private int thread;
        private Item item;
        private String ASIN;

        Crawler(int index, Item item){
            this.thread = index;
            this.item = item;
            this.ASIN = item.itemID;
        }

        public void run() {
            System.out.println(thread + ". Running " + (char)27 + "[32m" + this.ASIN + (char)27 + "[0m");

            Boolean success = this.item.fetchReview();

            if (success) {
                System.out.println(thread + ". Crawler " + (char)27 + "[32m" + "COMPLETE" +  (char)27 + "[0m" + " for item: " + (char)27 + "[32m" + this.item.itemName + (char)27 + "[0m");
            } else {
                System.out.println(thread + ". Crawler " + (char)27 + "[31m" + "FAILED"   +  (char)27 + "[0m" + " for item: " + (char)27 + "[32m" + this.item.itemName + (char)27 + "[0m");
            }

            Main.DBM.addItem(item);

//            Main.NC.updateListView();
            Main.MC.refresh();
        }
    }
}




