package View;

import Controller.Main;
import Controller.NavController;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class ItemCell extends ListCell<Item> {
    EventHandler navEvent;
    ItemCell cell;
    Item item;
    HBox hbox = new HBox();
    Label labelID = new Label("(ID)");
    Label labelName = new Label("(Name)");
    Pane pane = new Pane();
    Button button = new Button("delete");
    ProgressBar pb = new ProgressBar(0.0);

    /**
     *
     */
    public ItemCell(EventHandler navEvent) {
        super();
        this.navEvent = navEvent;
        cell = this;

        labelName.setMaxWidth(300);
        hbox.getChildren().addAll(pb, labelID, labelName, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);

        button.addEventHandler(ActionEvent.ACTION, navEvent);

//        button.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                Main.IM.remove(labelID.getText());
//                System.out.println(labelID.getText() + " : delete"); // + event
//                updateItem(item, true);
//            }
//        });
    }

    public void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        this.item = item;
        setText(null);  // No text in label of super class

        if (empty) {
            setGraphic(null);
            return;
        } else {
            labelID.setText(item!=null ? " " + item.itemID + " " : "<null>");
            labelName.setText(item!=null ? item.itemName : "<null>");
            button.setId(item.itemID);
            setGraphic(hbox);
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
