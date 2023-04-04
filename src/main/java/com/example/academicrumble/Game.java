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
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import static com.example.academicrumble.utils.*;

public class Game extends GameApplication {


    @NotNull
    private static Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityTypes.PLAYER);
    }

    @Override
    protected void initSettings(@NotNull GameSettings settings) {
        settings.setTitle(Const.NAME);
        settings.setVersion("1.0");
        settings.setWidth(Const.SCR_WIDTH);
        settings.setWidth(Const.SCR_HEIGHT);
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
        FXGL.onKey(KeyCode.W, "Move Up", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityY(-Const.SPEED));
        FXGL.onKey(KeyCode.S, "Move Down", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityY(Const.SPEED));
    }

    @Override
    protected void initPhysics(){
        FXGL.getPhysicsWorld().setGravity(0,5);
        FXGL.getPhysicsWorld();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
