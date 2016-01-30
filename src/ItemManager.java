import crawler.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    int thread = 0;

    public ItemManager() {
        System.out.println("Starting ItemManager");

        this.localItems = new HashMap();
    }

    /**
     * return localItems for corresponding ASIN, crawl and return if not locally available
     */
    public void get(String ASIN) {
        if (localItems.containsKey(ASIN)) {
            System.out.println(ASIN + " found locally in localItems: " + localItems.get(ASIN).itemID);
        } else {
            thread++;
            localItems.put(ASIN, crawl(thread, ASIN));
        }
    }

    public void printLocal() {
        int total = 0;
        Set set = localItems.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            total++;
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }

        System.out.println("total items: " + total);
    }

    /**
     * start new crawler thread for given ASIN
     */
    public Item crawl(int thread, String ASIN) {
        Item item = new Item(ASIN);
        Crawler C = new Crawler(thread, item);

        Thread t = new Thread (C);
        t.start();

        return item;
    }

    /**
     * runnable crawler: crawl amazon.com for customer reviews for given ASIN
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
            System.out.println(thread + ". Running " +  this.ASIN );
            this.item.fetchReview();
            System.out.println(thread + ". Crawler for " +  this.ASIN + " done!");

            Main.DBM.addItem(this.item);
        }
    }
}




