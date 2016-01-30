package ui;

import crawler.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Field;

/**
 * Created by Fechler on 30.01.16.
 */
public class CrawlerListCell extends ListCell<Field> {

    /** Cell Image*/
    //private ImageView cellImage;
    /** Labels ID, Name, Loading-Info*/
    private Label cellID, cellName;      //cellInfo;
    /** Break Button*/
    //private Button cellButton;

    /** */
    public CrawlerListCell(Item item){
        cellID.setText(item.itemID);
        cellName.setText(item.itemName);
    }

}
