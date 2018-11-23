package ru.ifmo.db.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
        /*listViewFilms.setItems(FXCollections.observableArrayList(new ArrayList<Film>()));
        List<Actor> actors = new ArrayList<>();
        actors.add(new Actor(-1, "1"));
        actors.add(new Actor(-1, "2"));
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(-1, "Drama"));
        genres.add(new Genre(-1, "Comedy"));
        listViewFilms.getItems().add(new Film("abc", "smbd", (short) 1999, (short) 89, actors, genres));
        listViewFilms.getItems().add(new Film("abc2", "smbd2", (short) 2002, (short) 55, actors, genres));*/
    }

    public void setFilms(List<Film> films) {
        listViewFilms.setItems(FXCollections.observableArrayList(films));

    }

    public void btnSearchClicked(ActionEvent actionEvent) {

    }
}
