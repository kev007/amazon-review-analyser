import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.text.ParseException;
import java.io.IOException;

public class DEPRECATEDcrawler {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, ParseException,
			ClassNotFoundException, SQLException, InvalidKeyException,
			NoSuchAlgorithmException, InterruptedException {

		//example: write all reviews for an item (defined by its ASIN) to a SQLite database
		Item an_item = new Item("B00EB7UIZU");
		an_item.fetchReview();
		an_item.writeReviewsToDatabase("test.db", false);

	}

}
