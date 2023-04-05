package com.example.academicrumble;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import static com.example.academicrumble.utils.getPlayer;

public class PlayerAction implements Action {
    private UserAction action;
    private boolean isAttacking = false;

    //@Override
    public void moveLeft(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
                getPlayer().getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getPlayer().getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getPlayer().getComponent(PlayerComponent.class).idle();
            }
        };
    }

    //@Override
    public void moveRight(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
                getPlayer().getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getPlayer().getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getPlayer().getComponent(PlayerComponent.class).idle();
            }
        };
    }

    //@Override
    public void upDown(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
                getPlayer().getComponent(PlayerComponent.class).up();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getPlayer().getComponent(PlayerComponent.class).idle();
            }
        };
    }

    //@Override
    public void attack(String name) {

        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getPlayer().getComponent(PlayerComponent.class).attack();
                isAttacking = true;
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getPlayer().getComponent(PlayerComponent.class).idle();
                isAttacking = false;
            }
        };
    }

    @Override
    public void addAction(@NotNull Input input, KeyCode code) {
        input.addAction(action, code);
        action = null;
    }

    public UserAction getAction() {
        return action;
    }
}
