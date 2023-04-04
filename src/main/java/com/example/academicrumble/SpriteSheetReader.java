package com.example.academicrumble;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SpriteSheetReader {

    @NotNull
    public static Image[] loadSprites(String spriteSheetPath, int spriteWidth, int spriteHeight, int numSprites) {
        Image spriteSheet = new Image(spriteSheetPath);
        int numRows = (int) spriteSheet.getHeight() / spriteHeight;
        int numCols = (int) spriteSheet.getWidth() / spriteWidth;
        int spriteIndex = 0;
        Image[] sprites = new Image[numSprites];
        PixelReader pixelReader = spriteSheet.getPixelReader();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (spriteIndex < numSprites) {
                    int x = col * spriteWidth;
                    int y = row * spriteHeight;
                    WritableImage sprite = new WritableImage(pixelReader, x, y, spriteWidth, spriteHeight);
                    sprites[spriteIndex] = sprite;
                    spriteIndex++;
                } else {
                    break;
                }
            }
        }

        return sprites;
    }
}