package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;

@SuppressWarnings("unused")
public class Sprite {
    private Point2D pos;
    private final Entity entity;
    private final String path;
    public Sprite(String path, Point2D pos) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        this.path = path;
        this.pos = pos;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .viewWithBBox(this.path)
                .with(physics)
                .build();
    }

    public Sprite(String path, Point2D pos, Point2D scale) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        this.path = path;
        this.pos = pos;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .viewWithBBox(this.path)
                .with(physics)
                .build();
    }

    public void translate(Point2D vector) {
        this.entity.getTransformComponent().translate(vector);
    }

    public Point2D getPosition() {
        return this.entity.getTransformComponent().getPosition();
    }

    public void setPosition(Point2D pos) {
        this.pos = pos;
        this.entity.getTransformComponent().setPosition(this.pos);
    }

    public Entity getEntity() {
        return this.entity;
    }

    public String getPath() {
        return this.path;
    }
}
