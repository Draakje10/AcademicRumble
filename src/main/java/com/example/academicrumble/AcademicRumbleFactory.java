package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class AcademicRumbleFactory implements EntityFactory {
    @Spawns("Background")
    public Entity spawnBackground(SpawnData data) {
        Sprite background = new Sprite();
        background.setEntity(
            FXGL.entityBuilder(data)
                .view(new Rectangle(data.<Integer>get("width"), data.<Integer>get("height"), Color.BLACK))
                .with(new IrremovableComponent())
                .zIndex(-100)
                .build()
        );
        return background.getEntity();
    }

    @Spawns("Player")
    public Entity spawnPlayer(@NotNull SpawnData data) {
        Sprite player = new Fighter(
                new Point2D(data.getX(), data.getY()),
                EntityTypes.PLAYER,
                BoundingShape.box(100, 200),
                new PlayerComponent(data.getX(), data.getY())
        );
        return player.getEntity();
    }

    @Spawns("Enemy")
    public Entity spawnEnemy(@NotNull SpawnData data) {
        Sprite enemy = new Fighter(
                new Point2D(data.getX(), data.getY()),
                EntityTypes.ENEMY,
                BoundingShape.box(20, 20),
                new EnemyComponent("pinky", data.getX(), data.getY())

        );
        return enemy.getEntity();
    }

    @Spawns("Wall")
    public Entity spawnWall(SpawnData data) {
        Sprite wall = new Sprite();
        wall.setEntity(
            FXGL.entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(40 * 32, 20 * 32)))
                .with(new PhysicsComponent())
                .build()
        );
        return wall.getEntity();
    }
}
