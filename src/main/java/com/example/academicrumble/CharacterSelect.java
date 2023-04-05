package com.example.academicrumble;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class CharacterSelect extends FXGLMenu {

    private static final int SIZE = FXGL.getAppHeight() /2 -100;
    private TextField usernameField;

    public CharacterSelect(@NotNull MenuType type) {
        super(type);

        // BG--------------------------------------------------------
        Image image = new Image("assets/textures/background.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(FXGL.getAppWidth());
        imageView.setFitHeight(FXGL.getAppHeight());
        imageView.setLayoutX(-420);
        imageView.setLayoutY(-FXGL.getAppHeight() / 2.0 + 120);
        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        getContentRoot().setTranslateX(FXGL.getAppWidth() / 2.0 - SIZE);
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE + 100);
        // LOGIN ------------------------------------------------------

        usernameField = new TextField();
//        usernameField.setPrefWidth(SIZE*2);
        usernameField.setPrefSize(SIZE*2,50);
        usernameField.setTranslateY(-100);

        Text usernameText = FXGL.getUIFactoryService().newText("USERNAME", Color.WHITE, FontType.GAME, 24.0);

        usernameText.setTranslateY(-120);
        usernameText.setMouseTransparent(true);

        var topLeft = Shape.subtract(new Circle(SIZE, SIZE, SIZE), new Rectangle(0, SIZE, SIZE*2, SIZE));

        var topRight = Shape.subtract(topLeft, new Rectangle(0, 0, SIZE, SIZE));

        topLeft = Shape.subtract(topLeft, new Rectangle(SIZE, 0, SIZE, SIZE));

        var bottom = Shape.subtract(new Circle(SIZE, 0, SIZE), new Rectangle(0, -SIZE, SIZE*2, SIZE));
        var bottomRight = Shape.subtract(bottom, new Rectangle(0, 0, SIZE, SIZE));
        bottomRight.setStrokeWidth(2.5);
        bottomRight.strokeProperty().bind(
                Bindings.when(bottomRight.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        bottomRight.fillProperty().bind(
                Bindings.when(bottomRight.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        bottomRight.setOnMouseClicked(e -> loadLevel(1,usernameField.getText()));
        bottomRight.setTranslateY(SIZE);

        var bottomLeft = Shape.subtract(bottom, new Rectangle(SIZE, 0, SIZE, SIZE));
        bottomLeft.setStrokeWidth(2.5);
        bottomLeft.strokeProperty().bind(
                Bindings.when(bottomLeft.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        bottomLeft.fillProperty().bind(
                Bindings.when(bottomLeft.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        bottomLeft.setOnMouseClicked(e -> loadLevel(2,usernameField.getText()));
        bottomLeft.setTranslateY(SIZE);
        topLeft.setStrokeWidth(2.5);
        topLeft.strokeProperty().bind(
                Bindings.when(topLeft.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        topLeft.fillProperty().bind(
                Bindings.when(topLeft.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        topLeft.setOnMouseClicked(e -> loadLevel(3,usernameField.getText()));

        topRight.setStrokeWidth(2.5);
        topRight.strokeProperty().bind(
                Bindings.when(topRight.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        topRight.fillProperty().bind(
                Bindings.when(topRight.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );
        topRight.setOnMouseClicked(e -> loadLevel(4,usernameField.getText()));

//        var shape3 = new Rectangle(SIZE*2, SIZE / 2);
//        shape3.setStrokeWidth(2.5);
//        shape3.strokeProperty().bind(
//                Bindings.when(shape3.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
//        );
//
//        shape3.fillProperty().bind(
//                Bindings.when(shape3.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
//        );
//
//        shape3.setTranslateY(SIZE);

//        Text textResume = FXGL.getUIFactoryService().newText("RESUME", Color.WHITE, FontType.GAME, 24.0);
//        textResume.setTranslateX(50);
//        textResume.setTranslateY(100);
//        textResume.setMouseTransparent(true);
//
//        Text textExit = FXGL.getUIFactoryService().newText("EXIT", Color.WHITE, FontType.GAME, 24.0);
//        textExit.setTranslateX(200);
//        textExit.setTranslateY(100);
//        textExit.setMouseTransparent(true);
//
//        Text textOptions = FXGL.getUIFactoryService().newText("OPTIONS", Color.WHITE, FontType.GAME, 24.0);
//        textOptions.setTranslateX(110);
//        textOptions.setTranslateY(195);
//        textOptions.setMouseTransparent(true);

        getContentRoot().getChildren().addAll(pane, topLeft, topRight, bottomRight,bottomLeft, usernameText,usernameField);
    }

    private void loadLevel(int levelNumb, String username){
        if(username != ""){
            Globals.selectionFlag = levelNumb;
            Globals.username = username;
            getController().startNewGame();
            FXGL.set("currentName", username);
        }
    }

}
