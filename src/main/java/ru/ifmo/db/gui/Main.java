package ru.ifmo.db.gui;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.db.gui.controllers.AuthController;
import ru.ifmo.db.gui.controllers.LoadingController;
import ru.ifmo.db.gui.controllers.MainWindowController;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static Stage stage;
    private static List<Scene> scenes;
    private static int currentScene = -1;

    public static void main(String[] args) {

        launch(args);
    }

    public static void loadNext() {
        currentScene = (currentScene + 1) % scenes.size();
        stage.setScene(scenes.get(currentScene));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        FXMLLoader loading = new FXMLLoader(getClass().getResource("/fxml/loading.fxml"));
        //LoadingController loadingController = loading.getController();
        Parent loadingScreen = loading.load();
        Scene loadingScene = new Scene(loadingScreen);
        stage.setScene(loadingScene);
        stage.show();

        scenes = new ArrayList<>();
        Client client = Client.getInstance();
        ActorManager actorManager = new ActorManager(client);
        GenreManager genreManager = new GenreManager(client);
        UserManager userManager = new UserManager(client);
        FilmRepository filmRepository = new FilmRepository(client, actorManager, genreManager);

        FXMLLoader auth = new FXMLLoader(getClass().getResource("/fxml/auth.fxml"));
        Parent authP = auth.load();
        AuthController authController = auth.getController();
        authController.setUserManager(userManager);
        scenes.add(new Scene(authP));

        FXMLLoader main = new FXMLLoader(getClass().getResource("/fxml/mainwindow.fxml"));
        Parent root = main.load();
        MainWindowController mainWindowController = main.getController();
        mainWindowController.setFilms(filmRepository.getAll());
        mainWindowController.setFilmRepository(filmRepository);
        mainWindowController.setUserManager(userManager);
        scenes.add(new Scene(root));

        stage.setMinHeight(720);
        stage.setMinWidth(1080);
        loadNext();
    }

    @Override
    public void stop() {
        Client.close();
        System.out.println("Stage is closing");
    }
}