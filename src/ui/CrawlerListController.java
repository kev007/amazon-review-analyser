package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Created by Fechler on 30.01.16.
 */
public class CrawlerListController {

    private ObservableList<String>  items;
    static ListView<String> list;
    static ListView list2;

    public CrawlerListController(){
        items = FXCollections.observableArrayList("test1", "test2");
        list = new ListView<>(items);
        list2 = new ListView<>(items);
    }

    public static void addItem(String ASIN){
        list.getItems().add(ASIN);
    }

    public static void addAll(ObservableList data){
        list2.setItems(data);
    }

}
