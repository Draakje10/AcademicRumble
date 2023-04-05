package com.example.academicrumble;

import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import static com.example.academicrumble.utils.getEnemy;

public class EnemyAction implements Action {
    private UserAction action;

    @Override
    public void moveLeft(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
                getEnemy().getComponent(FighterComponent.class).left();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getEnemy().getComponent(FighterComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getEnemy().getComponent(FighterComponent.class).idle();
            }
        };
    }

    @Override
    public void moveRight(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
                getEnemy().getComponent(FighterComponent.class).right();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getEnemy().getComponent(FighterComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getEnemy().getComponent(FighterComponent.class).idle();
            }
        };
    }

    @Override
    public void upDown(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
                getEnemy().getComponent(FighterComponent.class).up();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getEnemy().getComponent(FighterComponent.class).idle();
            }
        };
    }

    @Override
    public void attack(String name) {

        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getEnemy().getComponent(FighterComponent.class).attack();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
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
