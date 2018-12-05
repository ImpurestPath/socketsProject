package ru.ifmo.db.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    public Label lblName;
    public Label lblBalance;
    public TableColumn columnName;
    public TableColumn columnType;
    public TableColumn columnStart;
    public TableColumn columnFinish;
    public TableColumn columnCost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnBackClicked(ActionEvent actionEvent) {
    }

    public void btnRechargeClicked(ActionEvent actionEvent) {
    }
}
