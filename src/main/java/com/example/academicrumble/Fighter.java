package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;

@SuppressWarnings("unused")
public class Fighter extends Sprite {

    private final EntityTypes type;

    public Fighter(String path, Point2D pos, EntityTypes type) {
        super(path, pos);
        this.type = type;

        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .viewWithBBox(this.path)
                .with(physics)
                .type(this.type)
                .collidable()
                .build();
    }

    public Fighter(String path, Point2D pos, Point2D scale, EntityTypes type) {
        super(path, pos, scale);
        this.type = type;

        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .viewWithBBox(this.path)
                .scale(this.scale)
                .with(physics)
                .type(this.type)
                .collidable()
                .build();
    }
}
