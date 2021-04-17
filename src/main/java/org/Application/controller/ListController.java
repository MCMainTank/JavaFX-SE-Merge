package org.Application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    private ListView<String> StuList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> stu = FXCollections.observableArrayList();
        stu.addAll("1","1","1","1","1");
        StuList.setItems(stu);
    }
}
