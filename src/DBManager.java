import crawler.Item;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Fechler on 29.01.16.
 */
public class DBManager extends Thread{

    /** Database Name*/
    private String dbName;

    /** DB Item*/
    private Item crawlerItem;

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
     * @param dbName
     */
    public DBManager(String dbName){
        this.setDaemon(true);
        this.dbName = dbName;
    }

    /**
     * Getter Database Name
     * @return DB - Name
     */
    public String getDBName(){
        return this.dbName;
    }

    /**
     * Add Amazon ASIN to crawler
     */
    public void addItem(String asin){
        try {
            this.crawlerItem = new Item(asin);
            this.crawlerItem.fetchReview();
            this.crawlerItem.writeReviewsToDatabase(this.dbName, false);


            System.out.print("Item : " + asin + "eingefügt");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            this.crawlerItem = null;
        }
    }

    public Item getItem(){
        return null;
    }

    /**
     *
     */
    @Override
    public void run() {

        while(true){
            try {
                this.sleep(400);
                System.out.println("deamon run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
