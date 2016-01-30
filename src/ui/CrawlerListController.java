package ui;

import crawler.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;

/**
 * Created by Fechler on 30.01.16.
 */
public class CrawlerListController {

    private ObservableList<String>  items;
    private ListView<String>        list;

    public CrawlerListController(){
        items = FXCollections.observableArrayList("test1", "test2");
        list = new ListView<>(items);
        list.setCellFactory(TextFieldListCell.forListView());
        list.setEditable(false);
    }

    public void addItem(String temp){
        list.getItems().add(temp);
    }

}
