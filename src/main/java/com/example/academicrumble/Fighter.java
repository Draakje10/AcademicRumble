package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;

@SuppressWarnings("unused")
public class Fighter extends Sprite {

    private final EntityTypes type;

    public <T extends Component> Fighter(
            Point2D pos,
            EntityTypes type,
            BoundingShape shape,
            T component
    ) {
        super("", pos);
        this.type = type;

        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(30).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .with(physics)
                .bbox(new HitBox(shape)) // Add new bbox size
                .with(component)
                .type(this.type)
                .collidable()
                .build();
    }

    public <T extends Component> Fighter(
            Point2D pos,
            Point2D scale,
            EntityTypes type,
            BoundingShape shape,
            T component
    ) {
        super("", pos, scale);
        this.type = type;

        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(30).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        this.entity = FXGL.entityBuilder()
                .at(this.pos)
                .bbox(new HitBox(shape)) // Add new bbox size
                .with(physics)
                .with(component)
                .scale(this.scale)
                .type(this.type)
                .collidable()
                .build();
    }
}
