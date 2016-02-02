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
public class DetailsCell extends ListCell<Item> {
    DetailsController DC;

    public DetailsCell(EventHandler navEvent, String FXML) {
        super();

        this.DC = new DetailsController(navEvent, FXML);
    }

    @Override
    public void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
//        this.item = item;

        if (empty) {
            setGraphic(null);
        } else {
            DC.labelID.setText(item!=null ? " " + item.itemID + " " : "<null>");
            DC.labelName.setText(item!=null ? item.itemName : "<null>");
//            System.out.println("ID: " + item.itemID);
            DC.button.setId(item.itemID);
            if (item.progress <= 0) {
                if (item.progress < 0) {
                    DC.progress.setText("ERROR");
                }
            } else {
                DC.progress.setText(item.progress + "/" + item.total);
                DC.percent.setText(item.progress*100/item.total + "%");
            }
            DC.pb.setProgress((double) item.progress/item.total);

            setGraphic(DC.vbox);
        }
    }
}
