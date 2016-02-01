package View.DEPRECATEDfxmlCell;

import javafx.scene.control.ListCell;

/**
 * Created by kev_s on 30.01.2016.
 *
 * http://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
 */
public class ListViewCell extends ListCell<String> {
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string, empty);
        if(string != null)
        {
            CellController CC = new CellController();
            CC.setInfo(string);
            setGraphic(CC.getPane());
//            setGraphic(CC.getPane());
        }
    }

//    public void updateItem(String ASIN, boolean empty) {
//        super.updateItem(ASIN, empty);
//        setText(null);  // No text in label of super class
//        if (empty) {
//            setGraphic(null);
//        } else {
//            label.setText(ASIN);
//            setGraphic(hbox);
//        }
//    }
}
