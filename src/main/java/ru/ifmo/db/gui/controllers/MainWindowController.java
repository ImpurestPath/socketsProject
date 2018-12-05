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
import ru.ifmo.db.gui.entity.Actor;
import ru.ifmo.db.gui.entity.Film;
import ru.ifmo.db.gui.FilmRepository;
import ru.ifmo.db.gui.UserManager;
import ru.ifmo.db.gui.listCell.FilmListCell;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    public Label lblName;
    public Label lblYear;
    public Label lblRating;
    public Label lblReggiseur;
    public TableView tableViewGenres;
    public TableColumn tableColumnGenres;
    public ListView listViewFilms;
    public TextField txtSearch;
    public FilmRepository filmRepository;
    public UserManager userManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

                    tableViewGenres.setItems(FXCollections.observableArrayList(item.getGenres()));
                }
            });
            return listCell;
        });
    }
    public void setFilmRepository(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }
    public void setUserManager(UserManager userManager) {this.userManager = userManager;}

    public void setFilms(List<Film> films) {
        listViewFilms.setItems(FXCollections.observableArrayList(films));
    }

    public void btnSearchClicked(ActionEvent actionEvent) {
        if (!txtSearch.getText().equals("")){
            listViewFilms.setItems(FXCollections.observableArrayList(filmRepository.getAllByPartOfName(txtSearch.getText())));
        }
        else {
            listViewFilms.setItems(FXCollections.observableArrayList(filmRepository.getAll()));
        }
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
            filmInfoController.setUserManager(userManager);
            info.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnClearClicked(ActionEvent actionEvent) {
        listViewFilms.setItems(FXCollections.observableArrayList(filmRepository.getAll()));
    }

    public void btnMyFilmsClicked(ActionEvent actionEvent) {
        listViewFilms.setItems(FXCollections.observableArrayList(filmRepository.getUserFilms(userManager.getCurrent())));
    }

    public void btnMyProfileClicked(ActionEvent actionEvent) {
        Stage info = new Stage();
        info.initOwner(listViewFilms.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader profile = new FXMLLoader(getClass().getResource("/fxml/user.fxml"));
            Parent parent = profile.load();
            info.setScene(new Scene(parent));
            FilmInfoController filmInfoController = profile.getController();
            filmInfoController.setFilm(item);
            filmInfoController.setUserManager(userManager);
            info.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
