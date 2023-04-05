package com.example.academicrumble;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;



public class utils {
    @NotNull
    public static Entity getPlayer() {
        return GameWorldController.world().getSingleton(EntityTypes.PLAYER);
    }

    @NotNull
    public static Entity getEnemy() {
        return GameWorldController.world().getSingleton(EntityTypes.ENEMY);
    }

//    public static boolean isKeyPressed(KeyCode code) {
//        Input input = getInput();
//        return input.is(KeyCode.SPACE);
//    }
}
