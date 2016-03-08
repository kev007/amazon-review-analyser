package Model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.sql.*;

/**
 * Update or insert all reviews and product relating information to a SQLite
 * database
 * 
 * @author Feng Mai
 * 
 */
public class DatabaseUpdater {

	private static final Lock lock = new ReentrantLock();

	/**
	 * @param database
	 *            file name of the SQLite database
	 * @param reviews
	 *            an ArralyList of reviews
	 * @param itemID
	 *            ID of the item
	 * @param itemInfo
	 *            item info (in XML string) returned by Product Advertising API
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void doUpdate(String database, ArrayList<Review> reviews,
			String itemID, String itemName, String itemInfo) throws SQLException,
			ClassNotFoundException, IOException {
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");

			// if database not exist
			if (!(new File(database).isFile())) {
				createDB(database);
			}

			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement insertreview = conn
					.prepareStatement("insert into review (addedDate, reviewDate, realName, verifiedPurchase, totalVotes, "
							+ "helpfulVotes, fullRating, rating, title, customerID, customerName, reviewID, itemID, content) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14);");
			for (Review areview : reviews) {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String nowtime = dateFormat.format(date);
				insertreview.setString(1, nowtime);
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
				String reviewdatestring = dateFormat2
						.format(areview.reviewDate);
				insertreview.setString(2, reviewdatestring);
				insertreview.setString(3, String.valueOf(areview.realName));
				insertreview.setString(4,
						String.valueOf(areview.verifiedPurchase));
				insertreview.setInt(5, areview.totalVotes);
				insertreview.setInt(6, areview.helpfulVotes);
				insertreview.setInt(7, (int) areview.fullRating);
				insertreview.setInt(8, (int) areview.rating);
				insertreview.setString(9, areview.title);
				insertreview.setString(10, areview.customerID);
				insertreview.setString(11, areview.customerName);
				insertreview.setString(12, areview.reviewID);
				insertreview.setString(13, areview.itemID);
				insertreview.setString(14, areview.content);
				insertreview.addBatch();
			}
			conn.setAutoCommit(false);
			insertreview.executeBatch();

			// insert item information from Product Advertisement API's Large
			// Response
			// in raw XML format
			PreparedStatement insertitemXML = conn
					.prepareStatement("insert into iteminfo(itemID, itemName, itemXMLInfo) values (?1, ?2 ,?3);");
			insertitemXML.setString(1, itemID);
			insertitemXML.setString(2, itemName);
			insertitemXML.setString(3, itemInfo);
			insertitemXML.addBatch();
			insertitemXML.executeBatch();
			conn.commit();
			conn.close();
		} finally {
			lock.unlock();
		}
	}

	public static void createDB(String database) {
		Statement stmt = null;
		try {
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:" + database);
			stmt = conn.createStatement();
			String sql = "CREATE TABLE review ( [KEY] INTEGER PRIMARY KEY, addedDate TEXT, reviewDate TEXT, realName TEXT, verifiedPurchase TEXT, totalVotes NUMERIC, helpfulVotes NUMERIC, fullRating NUMERIC, rating NUMERIC, title TEXT, customerID TEXT, customerName TEXT, reviewID TEXT UNIQUE ON CONFLICT REPLACE, itemID TEXT, content TEXT );";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE iteminfo ( id INTEGER PRIMARY KEY AUTOINCREMENT, itemID TEXT UNIQUE ON CONFLICT IGNORE, itemName TEXT, itemXMLInfo TEXT );";
			stmt.executeUpdate(sql);
			sql = "CREATE INDEX idx_review ON review ( reviewID );";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": "
					+ e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
}
