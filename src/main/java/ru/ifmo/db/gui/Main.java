package ru.ifmo.db.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainwindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setMinHeight(720);
        stage.setMinWidth(1080);
        stage.setScene(scene);
        stage.show();
    }
}