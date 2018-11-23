package ru.ifmo.db.gui.listCell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ru.ifmo.db.gui.Film;

import java.io.IOException;

public class FilmListCell extends ListCell<Film> {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblName;
    @FXML
    private Label lblInfo;
    @FXML
    private Label lblRating;
    @FXML
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Film film, boolean empty) {
        super.updateItem(film, empty);
        if(empty || film == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listcellfilm.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lblName.setText(film.getName());
            lblInfo.setText(String.format("Year: %d",film.getYear()));
            lblRating.setText(Short.toString(film.getRating()));
            if (film.getRating() > 80) lblRating.setTextFill(Color.color(0.01,0.9,0.01));
            else if (film.getRating() > 50) lblRating.setTextFill(Color.color(0.001,0.001,0.99));
            else lblRating.setTextFill(Color.color(0.8,0.1,0.1));
            setText(null);
            setGraphic(anchorPane);
        }

    }
}
