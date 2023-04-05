package com.example.academicrumble;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Game extends GameApplication {

    public boolean isColliding;

    @Override
    protected void initSettings(@NotNull GameSettings settings) {
        settings.setSceneFactory(new MySceneFactory());
        settings.setMainMenuEnabled(true);
        settings.setTitle(Const.NAME);
        settings.setVersion("1.0");
        settings.setWidth(Const.SCR_WIDTH);
        settings.setHeight(Const.SCR_HEIGHT);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("enemyHealth", 100);
        vars.put("playerHealth", 100);
        vars.put("GameTime", 60);

    }

    protected void initUI() {
        var PH = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        var EH = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        var GT = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);

        PH.setTranslateX(50);
        PH.setTranslateY(50);
        EH.setTranslateX(1230);
        EH.setTranslateY(50);
        GT.setTranslateX(600);
        GT.setTranslateY(50);

        PH.textProperty().bind(FXGL.getip("playerHealth").asString());
        EH.textProperty().bind(FXGL.getip("enemyHealth").asString());
        GT.textProperty().bind(FXGL.getip("GameTime").asString());

        FXGL.addUINode(EH);
        FXGL.addUINode(PH);
        FXGL.addUINode(GT);
    }

    @Override
    protected void initGame(){
        GameWorldController.addFactoryToWorld(new AcademicRumbleFactory());
        GameWorldController.loadTilemap("naamloos.tmx");
        GameWorldController.spawn("Player", new SpawnData(450,200));
        GameWorldController.spawn("Enemy", new SpawnData(400,200));
        FXGL.getGameTimer().runAtInterval(()-> {}, Duration.seconds(1));

    }

    @Override
    protected void initInput(){
        Input input = getInput();
        Action pAction = new PlayerAction();

        pAction.moveRight("Player Move Right");
        pAction.addAction(input, KeyCode.D);

        pAction.moveLeft("Player Move Left");
        pAction.addAction(input, KeyCode.A);

        pAction.attack("Player Attack");
        pAction.addAction(input, KeyCode.TAB);

        pAction.upDown("Player Up Down");
        pAction.addAction(input, KeyCode.SPACE);

        Action eAction = new EnemyAction();

        eAction.moveRight("Enemy Move Right");
        eAction.addAction(input, KeyCode.RIGHT);

        eAction.moveLeft("Enemy Move Left");
        eAction.addAction(input, KeyCode.LEFT);

        eAction.attack("Enemy Attack");
        eAction.addAction(input, KeyCode.SLASH);

        eAction.upDown("Enemy Up Down");
        eAction.addAction(input, KeyCode.UP);

    }

    @Override
    protected void initPhysics(){
        FXGL.getPhysicsWorld().setGravity(0,500);
//        FXGL.getGameTimer().runAtInterval(() -> {
//        },Duration.seconds(1));
//        FXGL.getPhysicsWorld().setGravity(0,400);
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.ENEMY, EntityTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                isColliding = true;
            }
        });
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.ENEMY, EntityTypes.PLAYER) {
            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                isColliding = false;
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
