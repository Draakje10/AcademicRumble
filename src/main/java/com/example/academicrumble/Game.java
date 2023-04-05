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
    protected void initGameVars(@NotNull Map<String, Object> vars) {
        vars.put("enemyHealth", 100);
        vars.put("playerHealth", 100);
        vars.put("GameTime", 1);

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
        System.out.println(Globals.selectionFlag);

        GameWorldController.addFactoryToWorld(new AcademicRumbleFactory());

        switch (Globals.selectionFlag) {
            case 1 -> GameWorldController.loadTilemap("hallwaylvl1.tmx");
            case 2 -> GameWorldController.loadTilemap("classroomlvl2.tmx");
            case 3 -> GameWorldController.loadTilemap("gymlvl3.tmx");
            case 4 -> GameWorldController.loadTilemap("oudsidelvl4.tmx");
        }

        GameWorldController.spawn("Player", new SpawnData(200,200));
        GameWorldController.spawn("Enemy", new SpawnData(800,200));
        FXGL.getGameTimer().runAtInterval(()-> {FXGL.inc("GameTime" , 1);}, Duration.seconds(1));

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
        FXGL.getPhysicsWorld().setGravity(Const.GRAVITY.getX(), Const.GRAVITY.getY());
//        FXGL.getGameTimer().runAtInterval(() -> {
//        },Duration.seconds(1));
//        FXGL.getPhysicsWorld().setGravity(0,400);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
