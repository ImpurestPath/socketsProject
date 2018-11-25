package ru.ifmo.db.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.db.gui.controllers.MainWindowController;
import ru.ifmo.db.domain.guiServices.domainDTO.Film;
import ru.ifmo.db.gui.mappers.TransformerToGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainwindow.fxml"));
        Parent root = loader.load();
        Client client = Client.getInstance();
        MainWindowController mainWindowController = loader.getController();
        List<Film> films = client.getAll(Film.class);
        List<ru.ifmo.db.gui.Film> filmsGUI = new ArrayList<>();
        Map<Integer, Actor> actors = new HashMap<>();
        Map<Integer, Genre> genres = new HashMap<>();
        for (Film film : films){
            for (int i : film.getActors()){
                if (!actors.containsKey(i))
                    actors.put(i,TransformerToGUI.toActor(client.get(i, ru.ifmo.db.domain.guiServices.domainDTO.Actor.class)));
            }
            for (int i : film.getGenres()){
                if (!genres.containsKey(i))
                    genres.put(i, TransformerToGUI.toGenre(client.get(i, ru.ifmo.db.domain.guiServices.domainDTO.Genre.class)));
            }
            List<Actor> actorsGUI = new ArrayList<>();
            List<Genre> genreGUI = new ArrayList<>();
            for (int i : film.getActors()) actorsGUI.add(actors.get(i));
            for (int i : film.getGenres()) genreGUI.add(genres.get(i));
            filmsGUI.add(TransformerToGUI.toFilm(film,actorsGUI,genreGUI));
        }

        mainWindowController.setFilms(filmsGUI);
        Scene scene = new Scene(root);
        stage.setMinHeight(720);
        stage.setMinWidth(1080);
        stage.setScene(scene);
        stage.show();
    }
}