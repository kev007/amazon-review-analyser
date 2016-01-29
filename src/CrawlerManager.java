import crawler.Item;

/**
 * Created by Kevin on 29.01.2016.
 */
public class CrawlerManager {
    public void crawl(String ASIN) {
        System.out.println("Starting " +  ASIN);

        Crawler C = new Crawler(ASIN);

        Thread t = new Thread (C);
        t.start();
    }

    class Crawler implements Runnable{
        private Item item;
        private String ASIN;

        Crawler(String ASIN){
            this.ASIN = ASIN;

            item = new Item(this.ASIN);
            System.out.println("Creating " +  this.ASIN);
        }

        public void run() {
            System.out.println("Running " +  this.ASIN );
            this.item.fetchReview();
            Main.DBM.addItem(this.item);
            System.out.println("Thread " +  this.ASIN + " exiting.");
        }
    }
}




