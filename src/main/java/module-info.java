module com.example.academicrumble {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires annotations;
    opens assets.textures;
    opens assets.levels;
    opens assets.textures.player;
    opens com.example.academicrumble to javafx.fxml;
    exports com.example.academicrumble;
}