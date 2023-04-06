package com.example.academicrumble;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;
import static com.example.academicrumble.Utils.getEnemy;
import static com.example.academicrumble.Utils.getPlayer;

public class Game extends GameApplication {

    public boolean isColliding;

    @Override
    protected void initSettings(@NotNull GameSettings settings) {
        settings.setSceneFactory(new MySceneFactory());
        settings.setMainMenuEnabled(true);
        settings.setTitle(Const.NAME);
        settings.setVersion("1.0");
        settings.setWidth(Const.SCR_WIDTH);
        settings.setHeight(Const.SCR_HEIGHT);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("enemyHealth", 100);
        vars.put("playerHealth", 100);
        vars.put("GameTime", 0);
        vars.put("bestName", "");
        vars.put("bestScore", 0);
        vars.put("currentName", Globals.username);
        vars.put("currentScore", 0);

    }

    protected void initUI() {
        var PH = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        var EH = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        var GT = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);

        PH.setTranslateX(50);
        PH.setTranslateY(50);
        EH.setTranslateX(1230);
        EH.setTranslateY(50);
        GT.setTranslateX(600);
        GT.setTranslateY(50);

        PH.textProperty().bind(FXGL.getip("playerHealth").asString());
        EH.textProperty().bind(FXGL.getip("enemyHealth").asString());
        GT.textProperty().bind(FXGL.getip("GameTime").asString());

        FXGL.addUINode(EH);
        FXGL.addUINode(PH);
        FXGL.addUINode(GT);
    }

    @Override
    protected void initGame(){
        GameWorldController.addFactoryToWorld(new AcademicRumbleFactory());

        switch (Globals.selectionFlag) {
            case 1 -> GameWorldController.loadTilemap("hallwaylvl1.tmx");
            case 2 -> GameWorldController.loadTilemap("classroomlvl2.tmx");
            case 3 -> GameWorldController.loadTilemap("gymlvl3.tmx");
            case 4 -> GameWorldController.loadTilemap("oudsidelvl4.tmx");
        }

        GameWorldController.spawn("Player", new SpawnData(200,100));
        GameWorldController.spawn("Enemy", new SpawnData(800,100));
        FXGL.getGameTimer().runAtInterval(()-> {FXGL.inc("GameTime" , 1);}, Duration.seconds(1));
        FXGL.play("round1.wav");
        FXGL.play("backgound-slow.wav");
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);

        if (FXGL.geti("enemyHealth") <= 0) {
            getEnemy().getComponent(FighterComponent.class).death();
            FXGL.play("fatality.wav");
            getLeaderboard(true);
        }
        if (FXGL.geti("playerHealth") <= 0) {
            getPlayer().getComponent(FighterComponent.class).death();
            FXGL.play("fatality.wav");
            getLeaderboard(true);
        }
    }

    @Override
    protected void initInput(){
        Input input = getInput();
        Action pAction = new PlayerAction();

        pAction.moveRight("Player Move Right");
        pAction.addAction(input, KeyCode.D);

        pAction.moveLeft("Player Move Left");
        pAction.addAction(input, KeyCode.A);

        pAction.attack("Player Attack");
        pAction.addAction(input, KeyCode.TAB);

        pAction.upDown("Player Up Down");
        pAction.addAction(input, KeyCode.SPACE);

        Action eAction = new EnemyAction();

        eAction.moveRight("Enemy Move Right");
        eAction.addAction(input, KeyCode.RIGHT);

        eAction.moveLeft("Enemy Move Left");
        eAction.addAction(input, KeyCode.LEFT);

        eAction.attack("Enemy Attack");
        eAction.addAction(input, KeyCode.SLASH);

        eAction.upDown("Enemy Up Down");
        eAction.addAction(input, KeyCode.UP);

    }

    @Override
    protected void initPhysics(){
        FXGL.getPhysicsWorld().setGravity(0,150);
//        FXGL.getGameTimer().runAtInterval(() -> {
//        },Duration.seconds(1));
//        FXGL.getPhysicsWorld().setGravity(0,400);
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.ENEMY, EntityTypes.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                isColliding = true;
            }
        });
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.ENEMY, EntityTypes.PLAYER) {
            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                isColliding = false;
            }
        });

    }

    private void getLeaderboard(boolean reachedEndOfGame) {
        FXGL.set("currentScore", FXGL.geti("GameTime"));
//        if(Globals.bestScore >= FXGL.geti("currentScore")) {
//            Globals.bestScore =  FXGL.geti("currentScore");
//            Globals.bestName = FXGL.gets("currentName");
//        }
        int previousScore = 0;
        String previousName = "";

        for (int i = 0; i < Globals.leaderboard.length; i++) {
            if (FXGL.geti("currentScore") <= Globals.leaderboard[i].bestScore) {
                // save previous best score and name
                int tempScore = Globals.leaderboard[i].bestScore;
                String tempName = Globals.leaderboard[i].bestName;

                // update leaderboard with current score and name
                Globals.leaderboard[i].bestScore = FXGL.geti("currentScore");
                Globals.leaderboard[i].bestName = FXGL.gets("currentName");

                // shift subsequent elements down by one index
                for (int j = i+1; j < Globals.leaderboard.length; j++) {
                    int nextScore = Globals.leaderboard[j].bestScore;
                    String nextName = Globals.leaderboard[j].bestName;
                    Globals.leaderboard[j].bestScore = previousScore;
                    Globals.leaderboard[j].bestName = previousName;
                    previousScore = nextScore;
                    previousName = nextName;
                }

                // put the previous value one down
                if (i < Globals.leaderboard.length-1) {
                    Globals.leaderboard[i+1].bestScore = tempScore;
                    Globals.leaderboard[i+1].bestName = tempName;
                }
                break;
            } else {
                // save current best score and name
                previousScore = Globals.leaderboard[i].bestScore;
                previousName = Globals.leaderboard[i].bestName;
            }
        }
//        for (int i = 0; i < Globals.leaderboard.length; i++) {
//            if(FXGL.geti("currentScore") <= Globals.leaderboard[i].bestScore){
//                Globals.leaderboard[i].bestScore = FXGL.geti("currentScore");
//                Globals.leaderboard[i].bestname = FXGL.gets("currentName");
//                break;
//
//            }
//        }
        StringBuilder builder = new StringBuilder();
        builder.append("Game Over!\n\n");
        if (reachedEndOfGame) {
            builder.append("You have reached the end of the game!\n\n");
        }

        for (int i = 0; i < Globals.leaderboard.length; i++) {
            if (Globals.leaderboard[i].bestName != "") {
                builder.append(Globals.leaderboard[i].bestName + ": " + Globals.leaderboard[i].bestScore + "\n");
            }

        }
        builder.append("\nYour score: ")
               .append(FXGL.gets("currentName") + ": ")
               .append(FXGL.geti("currentScore"));

        FXGL.getDialogService().showMessageBox(builder.toString(), () -> FXGL.getGameController().gotoMainMenu());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
