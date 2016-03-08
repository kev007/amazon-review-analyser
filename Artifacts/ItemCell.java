package View.unused;

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
//    ItemCell cell;
//    Item item;
    HBox hbox = new HBox();
    Label labelID = new Label("(ID)");
    Label labelName = new Label("(Name)");
    Pane separator = new Pane();
    Pane separator2 = new Pane();
    Button button = new Button("delete");
    ProgressBar pb = new ProgressBar(0.0);
    Label progress = new Label("Starting");
    Label percent = new Label("0%");

    /**
     *
     */
    public ItemCell(EventHandler navEvent) {
        super();
        this.navEvent = navEvent;
//        cell = this;
        progress.setText("Starting");
        percent.setText("0%");

        labelID.setMinWidth(90);
        labelName.setMaxWidth(200);
        progress.setMinWidth(40);
        percent.setMinWidth(40);

        hbox.getChildren().addAll(progress, pb, percent, separator, labelID, labelName, separator2, button);
        HBox.setHgrow(separator2, Priority.ALWAYS);

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
//        this.item = item;
        setText(null);  // No text in label of super class

        if (empty) {
            setGraphic(null);
            return;
        } else {
            labelID.setText(item!=null ? " " + item.itemID + " " : "<null>");
            labelName.setText(item!=null ? item.itemName : "<null>");
            button.setId(item.itemID);
            if (item.progress <= 0) {
                if (item.progress < 0) {
                    progress.setText("ERROR");
                }
            } else {
                progress.setText(item.progress + "/" + item.total);
                percent.setText(item.progress*100/item.total + "%");
            }
            pb.setProgress((double) item.progress/item.total);

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
