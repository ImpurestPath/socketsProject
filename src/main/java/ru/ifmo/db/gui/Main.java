package ru.ifmo.db.gui;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.db.gui.controllers.MainWindowController;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loading = new FXMLLoader(getClass().getResource("/fxml/loading.fxml"));

        FXMLLoader main = new FXMLLoader(getClass().getResource("/fxml/mainwindow.fxml"));
        Parent root = main.load();
        Client client = Client.getInstance();
        ActorManager actorManager = new ActorManager(client);
        GenreManager genreManager = new GenreManager(client);
        FilmRepository filmRepository = new FilmRepository(client,actorManager,genreManager);
        MainWindowController mainWindowController = main.getController();
        mainWindowController.setFilms(filmRepository.getAll());
        mainWindowController.setFilmRepository(filmRepository);
        UserManager userManager = new UserManager(client);
        userManager.setCurrent(userManager.getByName("Admin"));
        mainWindowController.setUserManager(userManager);
        Scene scene = new Scene(root);
        stage.setMinHeight(720);
        stage.setMinWidth(1080);
        stage.setScene(scene);
        stage.show();
    }
}