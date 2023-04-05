package com.example.academicrumble;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class CharacterSelect extends FXGLMenu{

    private static final int SIZE = FXGL.getAppWidth() / 2 -100;

    public CharacterSelect(@NotNull MenuType type) {
        super(type);

        Image image = new Image("assets/textures/background.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(FXGL.getAppWidth());
        imageView.setFitHeight(FXGL.getAppWidth());
        imageView.setLayoutX(-100);
        imageView.setLayoutY(-100);
        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        getContentRoot().setTranslateX(FXGL.getAppWidth() / 2.0 - SIZE);
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE);

        var shape = Shape.subtract(new Circle(SIZE, SIZE, SIZE), new Rectangle(0, SIZE, SIZE*2, SIZE));

        var shape2 = Shape.subtract(shape, new Rectangle(0, 0, SIZE, SIZE));

        shape = Shape.subtract(shape, new Rectangle(SIZE, 0, SIZE, SIZE));

        var bottom = Shape.subtract(new Circle(SIZE, 0, SIZE), new Rectangle(0, -SIZE, SIZE*2, SIZE));
        var bottomRight = Shape.subtract(bottom, new Rectangle(0, 0, SIZE, SIZE));
        bottomRight.setStrokeWidth(2.5);
        bottomRight.strokeProperty().bind(
                Bindings.when(bottomRight.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        bottomRight.fillProperty().bind(
                Bindings.when(bottomRight.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        bottomRight.setOnMouseClicked(e -> getController().startNewGame());
        bottomRight.setTranslateY(SIZE);
        var bottomLeft = Shape.subtract(bottom, new Rectangle(SIZE, 0, SIZE, SIZE));
        bottomLeft.setStrokeWidth(2.5);
        bottomLeft.strokeProperty().bind(
                Bindings.when(bottomLeft.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        bottomLeft.fillProperty().bind(
                Bindings.when(bottomLeft.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        bottomLeft.setOnMouseClicked(e -> fireResume());
        bottomLeft.setTranslateY(SIZE);
        shape.setStrokeWidth(2.5);
        shape.strokeProperty().bind(
                Bindings.when(shape.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        shape.fillProperty().bind(
                Bindings.when(shape.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        shape.setOnMouseClicked(e -> fireResume());

        shape2.setStrokeWidth(2.5);
        shape2.strokeProperty().bind(
                Bindings.when(shape2.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        shape2.fillProperty().bind(
                Bindings.when(shape2.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );
        shape2.setOnMouseClicked(e -> FXGL.getGameController().exit());

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

        getContentRoot().getChildren().addAll(pane,shape, shape2, bottomRight,bottomLeft);
    }
}
