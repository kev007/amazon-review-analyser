package View.fxmlCell;

import Controller.Main;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 * http://stackoverflow.com/a/23126356
 */
public class ListViewCell extends ListCell<Item> {
    Item item;
    CellController CC;
    private Parent itemRoot;

    @FXML
    public Button delButton;

    @FXML
    public Label labelID;

    @FXML
    public BorderPane borderPane;

    public ListViewCell(CellController CC) {
        super();

        this.CC = CC;
    }

    @Override
    public void updateItem(Item item, boolean remove) {
        this.item = item;
        super.updateItem(item, remove);

        if (remove) {
            setGraphic(null);
        } else {
            try {
                itemRoot = FXMLLoader.load(getClass().getResource(("ListViewCell.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("!!!! ITEM; " + item.itemID);
            labelID.setText(item!=null ? item.itemID + " " : "<null>");
            setGraphic(borderPane);
        }
    }

///    @Override
//    protected void updateItem(AppBean app, boolean empty) {
//        super.updateItem(app, empty);
//        if (app == null) {
//            setText(null);
//            setGraphic(null);
//            return;
//        }
//        if (null == itemRoot) {
//            try {
//                itemRoot = FXMLLoader.load(getClass().getResource(("fxml/appList_item.fxml")));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            label_AppName = (Label) itemRoot.lookup("#item_Label_AppName");
//            imgv_AppIcon = (ImageView) itemRoot.lookup("#item_ImageView_AppIcon");
//            itemRoot.setOnMouseClicked(clickHandler);
//        }
//        //  set user data. like android's setTag(Object).
//        itemRoot.setUserData(app);
//        label_AppName.setText(app.name);
//        imgv_AppIcon.setImage(new Image(getClass().getResource("img/icon_64.png").toExternalForm()));
//        setGraphic(itemRoot);
//    }
}
