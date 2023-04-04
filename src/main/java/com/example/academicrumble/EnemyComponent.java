package com.example.academicrumble;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;

public class EnemyComponent extends Component {

    private final String name;
    private final double x;
    private final double y;

    private final Texture left;
    private final Texture right;
    private final Texture upDown;

    public EnemyComponent(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
        left = FXGL.texture(name + "-left.png");
        right = FXGL.texture(name + "-right.png");
        upDown = FXGL.texture(name + "-up-down.png");
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(upDown);
    }

}
