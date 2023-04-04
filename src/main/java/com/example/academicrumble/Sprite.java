package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;

@SuppressWarnings("unused")
public class Sprite {
    private Point2D pos;
    private final Entity entity;
    private final String path;
    public Sprite(String path, Point2D pos) {
        this.path = path;
        this.pos = pos;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .view(this.path)
                .build();
    }

    public Sprite(String path, Point2D pos, Point2D scale) {
        this.path = path;
        this.pos = pos;
        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .view(this.path)
                .scale(scale)
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
