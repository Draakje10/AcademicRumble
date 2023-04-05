package com.example.academicrumble;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import static com.example.academicrumble.utils.getPlayer;

public class FighterAction {
    private static UserAction action;
    private static Component component;
    private static boolean isAttacking = false;

    public static <T extends Component> void init(T component) {
        FighterAction.component = component;
    }

    public static void moveLeft(String name) {
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

    public static void moveRight(String name) {
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

    public static void upDown(String name) {
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

    public static void attack(String name) {
        action = new UserAction(name) {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                getPlayer().getComponent(PlayerComponent.class).attack();
                FighterAction.isAttacking = true;
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                getPlayer().getComponent(PlayerComponent.class).idle();
                FighterAction.isAttacking = false;
            }
        };
    }

    public static void addAction(@NotNull Input input, KeyCode code) {
        input.addAction(action, code);
        action = null;
    }

    public static UserAction getAction() {
        return action;
    }
}
