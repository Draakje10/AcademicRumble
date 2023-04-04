package com.example.academicrumble;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.entity.level.tiled.TiledMap;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.example.academicrumble.utils.*;

public class Game extends GameApplication {

    public boolean isColliding;


    @Override
    protected void initSettings(@NotNull GameSettings settings) {
        settings.setTitle(Const.NAME);
        settings.setVersion("1.0");
        settings.setWidth(Const.SCR_WIDTH);
        settings.setHeight(Const.SCR_HEIGHT);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("enemyHealth", 100);
        vars.put("playerHealth", 100);

    }

    protected void initUI() {
        var PH = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        var EH = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);

        PH.setTranslateX(50);
        PH.setTranslateY(50);
        EH.setTranslateX(1230);
        EH.setTranslateY(50);

        PH.textProperty().bind(FXGL.getip("playerHealth").asString());
        EH.textProperty().bind(FXGL.getip("enemyHealth").asString());

        FXGL.addUINode(EH);
        FXGL.addUINode(PH);
    }

    @Override
    protected void initGame(){
        GameWorldController.addFactoryToWorld(new AcademicRumbleFactory());
        FXGL.setLevelFromMap("naamloos.tmx");
        GameWorldController.spawn("Player", new SpawnData(200,200));
        GameWorldController.spawn("Enemy", new SpawnData(400,200));
        FXGL.getGameTimer().runAtInterval(()-> {
        }, Duration.seconds(2));

    }
    @Override
    protected void initInput(){
        FXGL.onKey(KeyCode.A, "Move Left", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityX(-Const.SPEED));
        FXGL.onKey(KeyCode.D, "Move Right", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityX(Const.SPEED));
        FXGL.onKeyDown(KeyCode.SPACE, "Move Up", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityY(-Const.SPEED));
        FXGL.onKeyDown(KeyCode.TAB, () -> {
            if (isColliding){
                FXGL.inc("enemyHealth", -5);
            }
        });
        FXGL.onKey(KeyCode.LEFT, "eMove Left", () -> getEnemy().getComponent(PhysicsComponent.class).setVelocityX(-Const.SPEED));
        FXGL.onKey(KeyCode.RIGHT, "eMove Right", () -> getEnemy().getComponent(PhysicsComponent.class).setVelocityX(Const.SPEED));
        FXGL.onKeyDown(KeyCode.UP, "eMove Up", () -> getEnemy().getComponent(PhysicsComponent.class).setVelocityY(-Const.SPEED));
        FXGL.onKeyDown(KeyCode.SLASH, () -> {
            if (isColliding){
                FXGL.inc("playerHealth", -5);
            }
        });
    }

    @Override
    protected void initPhysics(){
        FXGL.getPhysicsWorld().setGravity(0,98);
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
