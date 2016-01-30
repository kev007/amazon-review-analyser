import crawler.Item;

import java.util.LinkedList;

/**
 * Created by Kevin on 29.01.2016.
 */
public class ItemManager {
    public LinkedList<Crawler> crawlQueue;

    public void get(String ASIN) {
        Crawler C = new Crawler(crawlQueue.size()+1, ASIN);;
        crawlQueue.add(C);

        Thread t = new Thread (C);
        t.start();
    }

    class Crawler implements Runnable{
        private int index;
        private Item item;
        private String ASIN;

        Crawler(int index, String ASIN){
            this.index = index;
            this.ASIN = ASIN;

            item = new Item(this.ASIN);
        }

        public void run() {
            System.out.println(index + ". Running " +  this.ASIN );
            this.item.fetchReview();
            Main.DBM.addItem(this.item);
            System.out.println(index + ". Crawler for " +  this.ASIN + " done!");
        }
    }
}




