package ru.ifmo.db.gui.controllers;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.UserPurchaseDTO;
import ru.ifmo.db.gui.FilmRepository;
import ru.ifmo.db.gui.UserManager;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    public Label lblName;
    public Label lblBalance;
    public TableColumn columnName;
    public TableColumn columnType;
    public TableColumn columnStart;
    public TableColumn columnFinish;
    public TableView tableView;

    private UserManager userManager;
    private FilmRepository filmRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnStart.setCellValueFactory(new PropertyValueFactory<UserPurchaseDTO, Date>("start"));
        columnType.setCellValueFactory((Callback<TableColumn.CellDataFeatures<UserPurchaseDTO, ReadOnlyStringWrapper>, ReadOnlyStringWrapper>) param -> new ReadOnlyStringWrapper(param.getValue().getType().toString()));
        columnFinish.setCellValueFactory(new PropertyValueFactory<UserPurchaseDTO, Date>("finish"));
        columnName.setCellValueFactory((Callback<TableColumn.CellDataFeatures<UserPurchaseDTO, ReadOnlyStringWrapper>, ReadOnlyStringWrapper>) param -> new ReadOnlyStringWrapper(filmRepository.getById(param.getValue().getIdPurchase()).getName()));
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void load() {
        lblName.setText(userManager.getCurrent().getUsername());
        lblBalance.setText(Double.toString(userManager.getCurrent().getBalance()));
        tableView.setItems(FXCollections.observableArrayList(userManager.getCurrent().getFilms()));
        tableView.getItems().addAll(userManager.getCurrent().getSubscriptions());
    }

    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void btnBackClicked(ActionEvent actionEvent) {
        ((Stage) lblName.getScene().getWindow()).close();
    }

    public void btnRechargeClicked(ActionEvent actionEvent) {
    }
}
