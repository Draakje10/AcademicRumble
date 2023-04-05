package com.example.academicrumble;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import static com.example.academicrumble.Const.*;

@SuppressWarnings("unused")
public class FighterComponent extends Component implements CharacterComponent {

    private final double x;
    private final double y;

    private AnimatedTexture texture;
    private final EntityTypes type;
    private final AnimationChannel idle;
    private final AnimationChannel left;
    private final AnimationChannel right;
    private final AnimationChannel upDown;
    private final AnimationChannel attack;
    private final AnimationChannel death;
    private boolean isJumping = false;
    private boolean attacking = false;
    private boolean isDeath = false;

    public PhysicsComponent physics;

    public FighterComponent(@NotNull String[] animations, @NotNull int[] maxFrames, EntityTypes type, double x, double y) {
        this.x = x;
        this.y = y;
        this.type = type;
        idle = new AnimationChannel(FXGL.image(animations[0]), Duration.seconds(0.5), maxFrames[0]);
        left = new AnimationChannel(FXGL.image(animations[1]), Duration.seconds(0.5), maxFrames[1]);
        right = new AnimationChannel(FXGL.image(animations[2]), Duration.seconds(0.5), maxFrames[2]);
        upDown = new AnimationChannel(FXGL.image(animations[3]), Duration.seconds(0.5), maxFrames[3]);
        attack = new AnimationChannel(FXGL.image(animations[4]), Duration.seconds(0.5), maxFrames[4]);
        death = new AnimationChannel(FXGL.image(animations[5]), Duration.seconds(0.5), maxFrames[5]);
        texture = new AnimatedTexture(upDown);
        isDeath = false;
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(idle);
    }

    @Override
    public void attack() {
        attacking = true;
        texture.playAnimationChannel(attack);
        Point2D enemyPos = Utils.getEnemy().getPosition();
        Point2D playerPos = Utils.getPlayer().getPosition();
//        System.out.println(enemyPos);
        if (this.type == EntityTypes.PLAYER) {
            if (playerPos.distance(enemyPos) < 300) {
                Vec2 dir = new Vec2(playerPos.subtract(enemyPos).normalize()).mul(LUNGE);
                dir.x = -dir.x;
                physics.applyBodyForceToCenter(dir);
                FXGL.inc("enemyHealth", -10);
            }
        }
        else if (this.type == EntityTypes.ENEMY) {
            if (enemyPos.distance(playerPos) < 300) {
                Vec2 dir = new Vec2(enemyPos.subtract(playerPos).normalize()).mul(LUNGE);
                dir.x = -dir.x;
                physics.applyBodyForceToCenter(dir);
                FXGL.inc("playerHealth", -5);
            }
        }

        texture.setOnCycleFinished(() -> {
            attacking = false;
            texture.loopAnimationChannel(idle);
        });
    }

    @Override
    public void idle() {
        physics.setVelocityX(0);
        if (texture.getAnimationChannel() != idle && !attacking && !isDeath) {
            texture.loopAnimationChannel(idle);
        }
    }

    @Override
    public void left() {
        physics.setVelocityX(-SPEED);
        if (texture.getAnimationChannel() != left  && !attacking && !isDeath) {
            texture.loopAnimationChannel(left);
        }
    }

    @Override
    public void right() {
        physics.setVelocityX(SPEED);
        if (texture.getAnimationChannel() != right  && !attacking && !isDeath) {
            texture.loopAnimationChannel(right);
        }
    }

    @Override
    public void up() {
        if (physics.getVelocityY() == 0) {
            physics.applyBodyForceToCenter(new Vec2(0, JUMP_HEIGHT));
        }
        if (texture.getAnimationChannel() != upDown  && !attacking && !isDeath) {
            texture.loopAnimationChannel(upDown);
        }
    }

    @Override
    public void down() {
        physics.setVelocityY(SPEED);
        if (texture.getAnimationChannel() != upDown  && !attacking && !isDeath) {
            texture.loopAnimationChannel(upDown);
        }
    }

    @Override
    public void death() {
        isDeath = true;
        if (texture.getAnimationChannel() != death) {
            texture.playAnimationChannel(attack);
        }
    }

    @Override
    public void respawn() {
        entity.removeFromWorld();
        GameWorldController.spawn("Player", new SpawnData(x, y));
    }
}
