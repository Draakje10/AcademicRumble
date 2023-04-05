package com.example.academicrumble;

import com.almasb.fxgl.input.Input;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

public interface Action {
    void moveLeft(String name);
    void moveRight(String name);
    void upDown(String name);
    void attack(String name);
    void addAction(@NotNull Input input, KeyCode code);
}
