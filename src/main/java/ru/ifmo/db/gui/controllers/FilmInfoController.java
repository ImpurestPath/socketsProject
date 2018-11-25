package ru.ifmo.db.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import ru.ifmo.db.domain.dataAccessServices.dataAccessDTO.FilmCostDTO;
import ru.ifmo.db.gui.Actor;
import ru.ifmo.db.gui.Film;

import java.net.URL;
import java.util.ResourceBundle;

public class FilmInfoController implements Initializable {
    public TableView tableViewGenres;
    public TableColumn tableColumnGenres;
    public TableView tableViewActors;
    public TableColumn tableColumnActors;
    public Label lblYear;
    public Label lblReggiseur;
    public PieChart chartReviews;
    public TableView tableViewCost;
    public TableColumn tableColumnCost;
    public TableColumn tableColumnDuration;
    public Label lblName;
    public Label lblRating;
    private Film film;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnActors.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
        tableColumnGenres.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
        tableColumnCost.setCellValueFactory(new PropertyValueFactory<FilmCostDTO,Double>("cost"));
        tableColumnDuration.setCellValueFactory(new PropertyValueFactory<FilmCostDTO,Integer>("duration"));
    }

    public void setFilm(Film film) {
        this.film = film;
        tableViewCost.setItems(FXCollections.observableArrayList(film.getCosts()));
        tableViewActors.setItems(FXCollections.observableArrayList(film.getActors()));
        tableViewGenres.setItems(FXCollections.observableArrayList(film.getGenres()));
        lblName.setText(film.getName());
        lblRating.setText(Short.toString(film.getRating()));
        if (film.getRating() > 80) lblRating.setTextFill(Color.color(0.01,0.9,0.01));
        else if (film.getRating() > 50) lblRating.setTextFill(Color.color(0.9,0.5,0.01));
        else lblRating.setTextFill(Color.color(0.8,0.1,0.11));
        lblYear.setText(Short.toString(film.getYear()));
        lblReggiseur.setText(film.getReggiseur());
        chartReviews.getData().add(new PieChart.Data("Positive",70));
        chartReviews.getData().add(new PieChart.Data("Neutral",30));
        chartReviews.getData().add(new PieChart.Data("Negative",10));
    }

    public void btnBuyFilmClicked(ActionEvent actionEvent) {
    }

    public void btnBackClicked(ActionEvent actionEvent) {
    }
}
