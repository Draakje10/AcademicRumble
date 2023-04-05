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

import static com.example.academicrumble.Const.SPEED;

@SuppressWarnings("unused")
public class PlayerComponent extends Component implements CharacterComponent {

    private final double x;
    private final double y;

    private AnimatedTexture texture;
    private final AnimationChannel idle;
    private final AnimationChannel left;
    private final AnimationChannel right;
    private final AnimationChannel upDown;
    private final AnimationChannel attack;
    private boolean isJumping = false;
    private boolean attacking = false;

    public PhysicsComponent physics;

    public PlayerComponent(double x, double y) {
        this.x = x;
        this.y = y;
        idle = new AnimationChannel(FXGL.image("player/Idle.png"), Duration.seconds(0.5), 8);
        left = new AnimationChannel(FXGL.image("player/Run.png"), Duration.seconds(0.5), 8);
        right = new AnimationChannel(FXGL.image("player/Run.png"), Duration.seconds(0.5), 8);
        upDown = new AnimationChannel(FXGL.image("player/Jump.png"), Duration.seconds(0.5), 2);
        attack = new AnimationChannel(FXGL.image("player/attack1.png"), Duration.seconds(0.5), 6);
        texture = new AnimatedTexture(upDown);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(idle);
    }

    @Override
    public void attack() {
        System.out.println("attack");
        attacking = true;
        texture.playAnimationChannel(attack);
        Point2D enemyPos = utils.getEnemy().getPosition();
        Point2D playerPos = utils.getPlayer().getPosition();
        System.out.println(playerPos.subtract(enemyPos));
        if(playerPos.distance(enemyPos) < 140) {
            Vec2 dir = new Vec2(playerPos.subtract(enemyPos).normalize()).mul(25);
            dir.x = -dir.x;
            physics.applyBodyForceToCenter(dir);
            FXGL.inc("enemyHealth", -5);
        }
        texture.setOnCycleFinished(() -> {
            attacking = false;
            texture.loopAnimationChannel(idle);
        });
    }

    @Override
    public void idle() {
        physics.setVelocityX(0);
        if (texture.getAnimationChannel() != idle && !attacking) {
            texture.loopAnimationChannel(idle);
        }
    }

    @Override
    public void left() {
        physics.setVelocityX(-SPEED);
        if (texture.getAnimationChannel() != left  && !attacking) {
            texture.loopAnimationChannel(left);
        }
    }

    @Override
    public void right() {
        physics.setVelocityX(SPEED);
        if (texture.getAnimationChannel() != right  && !attacking) {
            texture.loopAnimationChannel(right);
        }
    }

    @Override
    public void up() {
//        physics.setVelocityY(-SPEED);
        if (physics.getVelocityY() == 0) {
            System.out.println("ground");
            physics.applyBodyForceToCenter(new Vec2(0, 15));
//            isJumping = false;
        }
        if (texture.getAnimationChannel() != upDown  && !attacking) {
            texture.loopAnimationChannel(upDown);
        }
    }

    @Override
    public void down() {
        physics.setVelocityY(SPEED);
        if (texture.getAnimationChannel() != upDown  && !attacking) {
            texture.loopAnimationChannel(upDown);
        }
    }

    @Override
    public void respawn() {
        entity.removeFromWorld();
        GameWorldController.spawn("Player", new SpawnData(x, y));
    }
}
