package ru.ifmo.db.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ru.ifmo.db.gui.Film;
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
        listViewFilms.setCellFactory(params -> {

            FilmListCell listCell = new FilmListCell();
            listCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                if (event.getButton() == MouseButton.PRIMARY && (!listCell.isEmpty())) {
                    System.out.println("clicked" + listCell.getItem());
                }
            });
            return listCell;
        });
        listViewFilms.setItems(FXCollections.observableArrayList(new ArrayList<Film>()));
        listViewFilms.getItems().addAll(new Film("abc", "smbd", (short) 1999, (short) 89));
    }

    public void setFilms(List<Film> films) {
        //listViewFilms.setItems(FXCollections.observableArrayList(films));

    }

    public void btnSearchClicked(ActionEvent actionEvent) {

    }
}
