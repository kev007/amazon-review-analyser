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
            DC.labelName.setText(item!=null ? item.itemName : "<null>");
//            DC.buttonClose.setId(item.itemID);

            setGraphic(DC.bp);
        }
    }
}
