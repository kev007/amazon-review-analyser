package Controller;

import Model.Item;
import Model.Review;
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

    /**
     * put here some debug stuff
     */
    public void debug() {
        String itemID = "B00A378L4C";
        String domain = "www.amazon.com";
        String url = "http://" + domain + "/product-reviews/" + itemID + "/?showViewpoints=0&sortBy=byRankDescending&pageNumber=" + 1;
        File input = new File("test/test.html");

        // Get the max number of review pages;
        org.jsoup.nodes.Document reviewpage1 = null;

        try {
//            reviewpage1 = getHTTPresponse(url).parse();
            reviewpage1 = Jsoup.parse(input, "UTF-8", url);;

//            System.out.println(reviewpage1);

            String itemName = reviewpage1.select("div.a-row.product-title").select("a.a-size-large.a-link-normal").text();
            System.out.println("Name: " + itemName);

            Elements pagelinks = reviewpage1.select("a[href*=pageNumber=]");
            if (reviewpage1.select("a[href*=pageNumber=]").isEmpty()) {
//                    File errorHTML = new File("ERROR" + itemID + progress + ".html");
//                    FileUtils.writeStringToFile(errorHTML, reviewpage1.toString());
//                    Desktop.getDesktop().open(errorHTML);
//                    Desktop.getDesktop().browse(r1.url().toURI());

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
                Item test = new Item(itemID, itemName);
                Review theReview = test.cleanReviewBlock(reviewBlock);
                System.out.println(theReview);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection.Response getHTTPresponse (String url) throws IOException, URISyntaxException {
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

    /**
     * cleans the html block that contains a review
     *
     * @param reviewBlock
     *            a html review block (Jsoup Element)
     * @return
     * @throws ParseException
     *//*
    public static Review cleanReviewBlock(Element reviewBlock) throws ParseException {
        String reviewID = "";
        String customerName = "";
        String customerID = "";
        String title = "";
        int rating = 0;
        int fullRating = 5;
        int helpfulVotes = 0;
        int totalVotes = 0;
        boolean verifiedPurchase = false;
        String realName = "N/A"; //Note 2015-11-14 : Amazon seems to got rid of the real name badge

        String content = "";

        // review id
        reviewID = reviewBlock.id();
//        System.out.println(reviewID);
        // customer name and id
        Elements customerIDs = reviewBlock.getElementsByAttributeValueContaining(
                "href", "/gp/pdp/profile/");
        if (customerIDs.size() > 0) {
            Element customer = customerIDs.first();
            String customerhref = customer.attr("href");
            String patternString = "(/gp/pdp/profile/)(.+)";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(customerhref);
            matcher.find();
            // cutomer id;
            customerID = matcher.group(2);
            // customer name;
            customerName = customer.text();
        }
        // title
        Element reviewTitle = reviewBlock.select("a.review-title").first();
        title = reviewTitle.text();

        // rating
        Element star = reviewBlock.select("i.a-icon-star").first();
        String starinfo = star.text();
        rating = Integer.parseInt(starinfo.substring(0, 1));

        // usefulness voting
        Elements votes = reviewBlock.select("span.votingStripe");
        if (votes.size() > 0) {
            String votingtext = votes.first().text();
            Pattern pattern2 = Pattern.compile("(\\S+)( of )(\\S+)");
            Matcher matcher2 = pattern2.matcher(votingtext);
            matcher2.find();
            // customer id;
            helpfulVotes = Integer.parseInt(matcher2.group(1).replaceAll(",", ""));
            totalVotes = Integer.parseInt(matcher2.group(3).replaceAll(",", ""));
        }

        // verified purchase
        Elements verified = reviewBlock.select("span.a-size-mini:contains(Verified Purchase)");
        if (verified.size() > 0){
            verifiedPurchase = true;
        }


        // review date
        Elements date = reviewBlock.select("span.review-date");
        String datetext = date.first().text();
        datetext = datetext.substring(3); // remove "On "
        Date reviewDate = new Date();
        String[] formatStrings = {"d MMMM yyyy", "MMMM d, yyyy"};
        for (String formatString : formatStrings) {
            try {
                reviewDate = new SimpleDateFormat(formatString, Locale.ENGLISH).parse(datetext);
//                System.out.println(reviewDate);
            }
            catch (ParseException e) {}
        }

        // review content
        Element contentDoc = reviewBlock.select("span.review-text").first();
        content = contentDoc.text();
        Review thereview = new Review("", reviewID, customerName,
                customerID, title, rating, fullRating, helpfulVotes,
                totalVotes, verifiedPurchase, realName, reviewDate, content);
        return thereview;
    }
*/


    /**
     * put here some debug stuff
     */
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
