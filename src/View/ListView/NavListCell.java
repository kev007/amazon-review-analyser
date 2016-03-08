package View.ListView;

import Model.Item;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 * http://stackoverflow.com/a/23126356
 */
public class NavListCell extends ListCell<Item> {
    CellController CC;

    public NavListCell(EventHandler navEvent, String FXML) {
        super();

        this.CC = new CellController(navEvent, FXML);
    }

    @Override
    public void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
//        this.item = item;

        if (empty) {
            setGraphic(null);
        } else {
            CC.labelID.setText(item!=null ? " " + item.itemID + " " : "<null>");
            CC.labelName.setText(item!=null ? item.itemName : "<null>");
//            System.out.println("ID: " + item.itemID);
            CC.button.setId(item.itemID);
            if (item.progress <= 0) {
                if (item.progress < 0) {
                    CC.progress.setText("ERROR");
                }
            } else if (item.crawlSuccess) {
                CC.progress.setText(Integer.toString(item.total));
                CC.percent.setText(item.progress*100/item.pages + "%");
            } else{
                CC.progress.setText(item.progress + "/" + item.pages);
                CC.percent.setText(item.progress*100/item.pages + "%");
            }
            CC.pb.setProgress((double) item.progress/item.pages);

            setGraphic(CC.hbox);
        }
    }

//    @Override
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
