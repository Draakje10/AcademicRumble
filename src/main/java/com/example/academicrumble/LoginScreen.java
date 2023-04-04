package com.example.academicrumble;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class LoginScreen extends FXGLMenu {

    private TextField usernameField;
    private PasswordField passwordField;

    private static final int SIZE = 150;

    public LoginScreen() {
        super(MenuType.MAIN_MENU);
        getContentRoot().setTranslateX(FXGL.getAppWidth() / 2.0 - SIZE);
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE);
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        usernameField = new TextField();
        passwordField = new PasswordField();
        Button loginButton = new Button("Login");
//        loginButton.setOnAction(e -> handleLogin());

        // Add elements to screen
        getContentRoot().getChildren().addAll(
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton
        );
    }

//    @Override
//    public void start() {
//        // Set up UI elements
//        Label usernameLabel = new Label("Username:");
//        Label passwordLabel = new Label("Password:");
//        usernameField = new TextField();
//        passwordField = new PasswordField();
//        Button loginButton = new Button("Login");
////        loginButton.setOnAction(e -> handleLogin());
//
//        // Add elements to screen
//        getContentRoot().getChildren().addAll(
//                usernameLabel,
//                usernameField,
//                passwordLabel,
//                passwordField,
//                loginButton
//        );
//    }

//    private void handleLogin() {
//        // Check if credentials are valid
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//        if (isValidCredentials(username, password)) {
//            // Navigate to game menu screen
//            FXGL.getDisplay().showDefaultMenu();
//        } else {
//            // Display error message
//            FXGL.getNotificationService().pushNotification("Invalid username or password");
//        }
//    }

//    private boolean isValidCredentials(String username, String password) {
//        // Check if username and password match a valid user
//        // Return true if valid, false otherwise
//    }

}
