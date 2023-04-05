package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;;
import com.almasb.fxgl.entity.level.tiled.TiledMap;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
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
                BoundingShape.box(30, 60),
                new FighterComponent(
                        new String[]{
                                "player/Idle.png",
                                "player/Run.png",
                                "player/Run.png",
                                "player/Jump.png",
                                "player/Attack1.png",
                        },
                        new int[]{
                                8,
                                8,
                                8,
                                2,
                                6
                        },
                        EntityTypes.PLAYER,
                        data.getX(),
                        data.getY()
                )
        );
        return player.getEntity();
    }

    @Spawns("Enemy")
    public Entity spawnEnemy(@NotNull SpawnData data) {
        Sprite enemy = new Fighter(
                new Point2D(data.getX(), data.getY()),
                EntityTypes.ENEMY,
                BoundingShape.box(30, 60),
                new FighterComponent(
                        new String[]{
                                "enemy/Idle.png",
                                "enemy/Run.png",
                                "enemy/Run.png",
                                "enemy/Jump.png",
                                "enemy/Attack1.png",
                        },
                        new int[]{
                                4,
                                8,
                                8,
                                2,
                                4
                        },
                        EntityTypes.ENEMY,
                        data.getX(),
                        data.getY()
                )

        );
        return enemy.getEntity();
    }

    @Spawns("Wall")
    public Entity spawnWall(SpawnData data) {
        Sprite wall = new Sprite();
        Polygon polyline = data.get("polygon");
        double[] points = polyline.getPoints().stream().mapToDouble(Double::doubleValue).toArray();
        wall.setEntity(
            FXGL.entityBuilder(data)
                .type(EntityTypes.WALL)
                .at(data.getX(), data.getY())
                .bbox(new HitBox(BoundingShape.polygon(points)))
                .with(new PhysicsComponent())
                .build()
        );
        return wall.getEntity();
    }
}
