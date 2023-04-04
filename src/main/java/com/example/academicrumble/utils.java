package com.example.academicrumble;

import com.almasb.fxgl.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class utils {
    @NotNull
    public static Entity getPlayer() {
        return GameWorldController.world().getSingleton(EntityTypes.PLAYER);
    }
}
