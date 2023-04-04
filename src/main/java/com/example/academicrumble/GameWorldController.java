package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class GameWorldController {
    private static final ArrayList<Sprite> sprites = new ArrayList<>();

    public static <T extends EntityFactory> void  addFactoryToWorld(T factory) {
        FXGL.getGameWorld().addEntityFactory(factory);
    }

    public static <T extends EntityFactory> void  removeFactoryFromWorld(T factory) {
        FXGL.getGameWorld().removeEntityFactory(factory);
    }

    public static <T extends Sprite> void addSpriteToWorld(@NotNull T sprite) {
        FXGL.getGameWorld().addEntity(sprite.getEntity());
        GameWorldController.sprites.add(sprite);
    }

    public static <T extends Sprite> void removeSpriteFromWorld(@NotNull T sprite) {
        FXGL.getGameWorld().removeEntity(sprite.getEntity());
        GameWorldController.sprites.remove(sprite);
    }

    public static void addCollisionHandler(double gravX, double gravY, CollisionHandler handler) {
        PhysicsWorld physics = FXGL.getPhysicsWorld();
        physics.setGravity(gravX, gravY);
        physics.addCollisionHandler(handler);
    }

    public static void spawn(String name, SpawnData data) {
        FXGL.spawn(name, data);
    }

    @NotNull
    public static GameWorld world() {
        return FXGL.getGameWorld();
    }

    public ArrayList<Sprite> getWorldSprites() {
        return GameWorldController.sprites;
    }
}
