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
    protected Point2D pos;
    protected Entity entity;
    protected final String path;
    protected final Point2D scale;

    public Sprite() {
        this.pos = null;
        this.entity = null;
        this.path = null;
        this.scale = null;
    }


    public Sprite(Point2D pos) {
        this.path = null;
        this.pos = pos;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .build();
        scale = null;
    }

    public Sprite(String path, Point2D pos) {
        this.path = path;
        this.pos = pos;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .view(this.path)
                .build();
        scale = null;
    }

    public Sprite(String path, Point2D pos, Point2D scale) {
        this.path = path;
        this.pos = pos;
        this.scale = scale;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .view(this.path)
                .scale(this.scale)
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

    public void setEntity(Entity e) {
        this.entity = e;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public String getPath() {
        return this.path;
    }
}
