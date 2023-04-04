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
        GameWorldController.spawn("Player", new SpawnData(200,500));



    }
    @Override
    protected void initInput(){
        FXGL.onKey(KeyCode.D,() -> {
            getPlayer().getComponent(PhysicsComponent.class).setVelocityX(5);
        });
        FXGL.onKey(KeyCode.A,() -> {
            getPlayer().getComponent(PhysicsComponent.class).setVelocityX(-5);
        });
        FXGL.onKeyDown(KeyCode.SPACE, () -> {
            getPlayer().getComponent(PhysicsComponent.class).setVelocityY(10);
        });
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
