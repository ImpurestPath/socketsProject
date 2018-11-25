package ru.ifmo.db.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ifmo.db.gui.Actor;
import ru.ifmo.db.gui.Film;
import ru.ifmo.db.gui.Genre;
import ru.ifmo.db.gui.listCell.FilmListCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    public Label lblName;
    public Label lblYear;
    public Label lblRating;
    public Label lblReggiseur;
    public TableView tableViewGenres;
    public TableColumn tableColumnGenres;
    public TableView tableViewActors;
    public TableColumn tableColumnActors;
    public ListView listViewFilms;
    public TextField txtSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnActors.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
        tableColumnGenres.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
        listViewFilms.setCellFactory(params -> {

            FilmListCell listCell = new FilmListCell();
            listCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                if (event.getButton() == MouseButton.PRIMARY && (!listCell.isEmpty())) {
                    System.out.println("clicked" + listCell.getItem());
                    Film item = listCell.getItem();
                    lblName.setText(item.getName());
                    lblRating.setText(Short.toString(item.getRating()));
                    lblYear.setText(Short.toString(item.getYear()));
                    lblReggiseur.setText(item.getReggiseur());

                    tableViewActors.setItems(FXCollections.observableArrayList(item.getActors()));
                    tableViewGenres.setItems(FXCollections.observableArrayList(item.getGenres()));
                }
            });
            return listCell;
        });
    }

    public void setFilms(List<Film> films) {
        listViewFilms.setItems(FXCollections.observableArrayList(films));

    }

    public void btnSearchClicked(ActionEvent actionEvent) {

    }

    public void btnDetailsClicked(ActionEvent actionEvent) {
        Film item = (Film) listViewFilms.getSelectionModel().getSelectedItem();
        if (item == null) return;
        Stage info = new Stage();
        info.initOwner(listViewFilms.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/filmInfo.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            FilmInfoController filmInfoController = loader.getController();
            filmInfoController.setFilm(item);
            info.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
