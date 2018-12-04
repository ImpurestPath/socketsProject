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
        ActorManager actorManager = new ActorManager(client);
        GenreManager genreManager = new GenreManager(client);
        FilmManager filmManager = new FilmManager(client,actorManager,genreManager);
        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setFilms(filmManager.getAll());
        mainWindowController.setFilmManager(filmManager);
        UserManager userManager = new UserManager(client);
        userManager.setNow(userManager.getByName("Admin"));
        mainWindowController.setUserManager(userManager);
        Scene scene = new Scene(root);
        stage.setMinHeight(720);
        stage.setMinWidth(1080);
        stage.setScene(scene);
        stage.show();
    }
}