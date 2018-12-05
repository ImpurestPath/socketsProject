package ru.ifmo.db.gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.ifmo.db.gui.Main;
import ru.ifmo.db.gui.UserManager;
import ru.ifmo.db.gui.entity.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    public TextField txtUsername;
    public Label lblAttention;
    private UserManager userManager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    private void cleanScene(){
        txtUsername.setText("");
        //txtPassword.setText("");
        lblAttention.setText("");
    }
    public void btnSignInClicked(ActionEvent actionEvent) {
        try {
            User potentialUser = userManager.getByName(txtUsername.getText().toLowerCase());
            if (potentialUser != null){
                userManager.setCurrent(potentialUser);
                Platform.runLater(Main::loadNext);
                cleanScene();
            }
            else {
                lblAttention.setText("No such user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSignUpClicked(ActionEvent actionEvent) {
        try {
            userManager.add( //Sign up new user
                    new User(-1,
                            txtUsername.getText(),
                            0.0,
                            new ArrayList<>(),
                            new ArrayList<>()));
            lblAttention.setText("Successful. Please log in.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
