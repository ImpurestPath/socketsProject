package ru.ifmo.db.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.ifmo.db.gui.Film;

import java.net.URL;
import java.util.ResourceBundle;

public class FilmPageController implements Initializable {
    public TextField txtName;
    public TextField txtReggiseur;
    public TextField txtYear;
    public TextField txtRating;
    private Film film;
    public enum Mode{
        CREATE,
        CHANGE
    }
    private Mode mode = Mode.CREATE;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setFilm(Film film){
        this.film = film;
        txtName.setText(film.getName());
        txtReggiseur.setText(film.getReggiseur());
        txtRating.setText(Short.toString(film.getRating()));
        txtYear.setText(Short.toString(film.getYear()));
    }


    public void btnOKClicked(ActionEvent actionEvent) {
        if (mode == Mode.CREATE){

        }
        else {

        }
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
    }
}
