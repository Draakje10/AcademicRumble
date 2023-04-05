package com.example.academicrumble;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.StartupScene;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Game extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Inloggen");

        settings.setSceneFactory(new SceneFactory() {

            @Override
            public StartupScene newStartup(int width, int height) {
                return new MyStartupScene(width, height);
            }
        });
    }

        private class MyStartupScene extends StartupScene {

            // Note: in startup scene no services are ready, so don't call FXGL.*
            public MyStartupScene(int appWidth, int appHeight) {
                super(appWidth, appHeight);

                Rectangle bg = new Rectangle(appWidth, appHeight);

                Text textCompanyName = new Text("Academic Rumble");
                textCompanyName.setFill(Color.WHITE);
                textCompanyName.setFont(Font.font("Playfair Display",64));

                getContentRoot().getChildren().addAll(new StackPane(bg, textCompanyName));
            }
        }

    @Override
    protected void initGame() {
        GridPane grid = new GridPane();
        grid.setMinWidth(500);
        grid.setMinHeight(500);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Image backgroundImage = new Image("assets/textures/....);
        ImageView backgroundImageView = new ImageView(backgroundImage);

        grid.setBackground(new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        Text sceneTitle = new Text("Inloggen");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Naam:");
        userName.setFont(new Font(20));
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Log in");
        userName.setFont(new Font(20));
        grid.add(btn, 1, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("Succes " + username + "!");

            alert.getDialogPane().setStyle("-fx-background-color: #4169E1; " +
                                            "-fx-text-fill: #FFFFFF; " +
                                            "-fx-font-size: 30px;");
            alert.getDialogPane().setGraphic(null);

            alert.showAndWait();
        });
        FXGL.getGameScene().addUINode(grid);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

