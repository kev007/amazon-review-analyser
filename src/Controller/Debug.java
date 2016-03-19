package Controller;

import Model.Item;
import Model.Review;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by kev_s on 19.03.2016.
 */
public class Debug {
    public static void fastRun() {
        Main.DBM = new DBManager("test3.db");
        Main.DBM.start();
        Main.IM = new ItemManager();

        Main.IM.get("B00GG1ACX2", "www.amazon.com", true);

        while(true){

        }
    }

    public static void debug() {
        String itemID = "B00A378L4C";
        String domain = "www.amazon.com";
        String url = "http://" + domain + "/product-reviews/" + itemID + "/?showViewpoints=0&sortBy=byRankDescending&pageNumber=" + 1;
        File input = new File("test/test.html");

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://kev007.com");

        try {
            org.jsoup.nodes.Document reviewpage1 = null;
    //            reviewpage1 = getHTTPresponse(url).parse();
            reviewpage1 = Jsoup.parse(input, "UTF-8", url);;

    //            System.out.println(reviewpage1);

            String itemName = reviewpage1.select("div.a-row.product-title").select("a.a-size-large.a-link-normal").text();
            System.out.println("Name: " + itemName);

            Item test = new Item(itemID, itemName);

            Elements pagelinks = reviewpage1.select("a[href*=pageNumber=]");
            if (reviewpage1.select("a[href*=pageNumber=]").isEmpty()) {
                throw new Exception("THIS SHIT'S EMPTY, YO!");
            }

            if (pagelinks.size() != 0) {
                ArrayList<Integer> pagenum = new ArrayList<Integer>();
                for (Element link : pagelinks) {
                    try {
                        pagenum.add(Integer.parseInt(link.text()));
                    } catch (NumberFormatException nfe) {
                    }
                }
                System.out.println("pages: " + Collections.max(pagenum));
            }

            Elements reviewsHTMLs = reviewpage1.select(
                    "div.a-section.review");
            for (Element reviewBlock : reviewsHTMLs) {
                Review theReview = test.cleanReviewBlock(reviewBlock);
                System.out.println(theReview);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection.Response getHTTPresponse (String url) throws IOException, URISyntaxException {
        Connection.Response r1 = null;
        r1 = Jsoup.connect(url)
                .timeout(1000)
                .followRedirects(true)
                .userAgent("Mozilla/17.0")
                .execute();

        File errorHTML = new File("ERROR" + "test" + ".html");
        FileUtils.writeStringToFile(errorHTML, r1.parse().toString());
        Desktop.getDesktop().open(errorHTML);
        Desktop.getDesktop().browse(r1.url().toURI());

        return r1;
    }

    public static void debug2() {
        String url = "www.amazon.com/CM-Storm-QuickFire-TK-Mechanical/dp/B00A378L4C/ref=pd_sim_147_2?ie=UTF8&dpID=31wokVpI8eL&dpSrc=sims&preST=_AC_UL160_SR160%2C160_&refRID=1C10ZYGJTRPXCSBW90PM";
        String ASIN = "ERROR";
        String domain = "ERROR";

//        String regex=".*?amazon\\.com\\/(?:gp\\/product|[^\\/]+\\/dp|dp)\\/([^\\/]+)";

        String regex = ".*?(www\\.amazon\\.*.+?)/(?:gp/product|[^/]+/dp|dp)/([^/]+)";
//        String regex = ".*?(www\\.amazon\\.*.+?)/";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            domain = matcher.group(1);
            ASIN = matcher.group(2);
        } else {
            System.out.println("NO MATCH");
        }
        System.out.println("Domain: " + domain);
        System.out.println("ASIN: " + ASIN);




        //Main.IM.get("B00A378L4C");
        //Main.IM.get("B00NMJJXU4");
        //while(true){ }
    }
}
