import crawler.Item;

/**
 * Created by Kevin on 29.01.2016.
 */
public class CrawlerManager {
    public void crawl(String ASIN) {
        Crawler C1 = new Crawler(ASIN);
        C1.start();
    }
}

class Crawler implements Runnable{
    private Thread t;
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

    public void start ()
    {
        System.out.println("Starting " +  this.ASIN );
        if (t == null)
        {
            t = new Thread (this, this.ASIN);
            t.start ();
        }
    }


    public void crawl(Item an_item) {

    }
}



