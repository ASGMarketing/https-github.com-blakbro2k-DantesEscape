/**
 *
 */
package net.asg.games.dante.states;

import net.asg.games.dante.Constants;
import net.asg.games.dante.states.MovingGameObjectState;
import net.asg.games.dante.models.MovingGameObjectType;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * @author Blakbro2k
 */
public class GameScreenState implements Serializable {
    public enum LevelState {
        GOALHIT, FIREBALLHIT, WALLHIT, DYING, DEAD, NOLCLIP
    }

    public int score;
    public int bobX;
    public int bobY;
    public int spawnTime;
    public int roundCount;
    public int stageType;
    public int lastStageType;
    public int standardMovingBonus = 1000;
    public int totalDeaths = 0;
    public int levelGroup = 0;
    public int missileCounter = 10;

    public boolean isPaused;
    public boolean isLevelStarted;
    public boolean isDead;
    public boolean isEndless;

    //public LevelState gameState = LevelState.NOTRUNNING;

    public float gameSpeed = Constants.DEFAULT_GAME_SPEED;

    public long lastGameObjTime = 0;
    public long roundEndTime;

    public Array<MovingGameObjectState> movingObjectStates;

    public GameScreenState() {
        movingObjectStates = new Array<MovingGameObjectState>();
    }

    public float getBackgroundSpeed() {
        return Constants.BACKGROUND_SPEED * gameSpeed;
    }

    public float getMiddlegroundSpeed() {
        return Constants.MIDGROUND_SPEED * gameSpeed;
    }

    public float getForegroundSpeed() {
        return Constants.FOREGROUND_SPEED * gameSpeed;
    }

    public void levelReset() {
        lastStageType = stageType;
        stageType = MovingGameObjectType.GoalWall.getValue();
        spawnTime = Constants.STARTING_SPAWNTIME;
        isLevelStarted = false;
        //gameState = LevelState.RUNNING;
    }

    public void gameReset() {
        levelReset();
        lastStageType = stageType; //set to goal
        score = 0;
        roundCount = 0;
        isLevelStarted = false;
        lastGameObjTime = 0;
        bobX = -1;
        bobY = -1;
        isDead = false;
        roundEndTime = getRoundEndTime(Constants.ROUND_TIME_DURATION);
        levelGroup = 0;
        //gameState = LevelState.RUNNING;
    }

    public long getRoundEndTime(long duration){
        return TimeUtils.millis() + duration;
    }

    @Override
    public void write(Json json) {
        json.writeValue("score", score);
        json.writeValue("bobX", bobX);
        json.writeValue("bobY", bobY);
        json.writeValue("spawnTime", spawnTime);
        json.writeValue("roundCount", roundCount);
        json.writeValue("stageType", stageType);
        json.writeValue("lastStageType", lastStageType);
        json.writeValue("standardMovingBonus", standardMovingBonus);
        json.writeValue("isPaused", isPaused);
        json.writeValue("isLevelStarted", isLevelStarted);
        json.writeValue("isDead", isDead);
        json.writeValue("isEndless", isEndless);
        json.writeValue("gameSpeed", gameSpeed);
        json.writeValue("lastGameObjTime", lastGameObjTime);
        json.writeValue("roundEndTime", roundEndTime);
        json.writeValue("totalDeaths", totalDeaths);
        json.writeValue("levelGroup", levelGroup);
        json.writeValue("missileCounter", missileCounter);

        json.writeArrayStart("movingObjects");
        for (MovingGameObjectState movingObjectState : movingObjectStates) {
            json.writeValue(movingObjectState, MovingGameObjectState.class);
        }
        json.writeArrayEnd();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(Json json, JsonValue jsonData) {
        score = json.readValue("score", Integer.class, jsonData);
        bobX = json.readValue("bobX", Integer.class, jsonData);
        bobY = json.readValue("bobY", Integer.class, jsonData);
        spawnTime = json.readValue("spawnTime", Integer.class, jsonData);
        roundCount = json.readValue("roundCount", Integer.class, jsonData);
        stageType = json.readValue("stageType", Integer.class, jsonData);
        lastStageType = json.readValue("lastStageType", Integer.class, jsonData);
        standardMovingBonus = json.readValue("standardMovingBonus", Integer.class, jsonData);
        isPaused = json.readValue("isPaused", Boolean.class, jsonData);
        isLevelStarted = json.readValue("isLevelStarted", Boolean.class, jsonData);
        isDead = json.readValue("isDead", Boolean.class, jsonData);
        isEndless = json.readValue("isEndless", Boolean.class, jsonData);
        gameSpeed = json.readValue("gameSpeed", Long.class, jsonData);
        lastGameObjTime = json.readValue("lastGameObjTime", Long.class, jsonData);
        roundEndTime = json.readValue("roundEndTime", Long.class, jsonData);
        totalDeaths = json.readValue("totalDeaths", Integer.class, jsonData);
        levelGroup = json.readValue("levelGroup", Integer.class, jsonData);
        missileCounter = json.readValue("missileCounter", Integer.class, jsonData);

        movingObjectStates = json.readValue("movingObjects", Array.class,
                MovingGameObjectState.class, jsonData);
    }

    public String toString() {
        return "score: " + score + "\n" +
                "bobX: " + bobX + "\n" +
                "bobY: " + bobY + "\n" +
                "spawnTime: " + spawnTime + "\n" +
                "roundCount: " + roundCount + "\n" +
                "stageType: " + stageType + "\n" +
                "lastStageType: " + lastStageType + "\n" +
                "standardMovingBonus: " + standardMovingBonus + "\n" +
                "isPaused: " + isPaused + "\n" +
                "isLevelStarted: " + isLevelStarted + "\n" +
                "isDead: " + isDead + "\n" +
                "isEndless: " + isEndless + "\n" +
                //"gameState: " + gameState + "\n" +
                "gameSpeed: " + gameSpeed + "\n" +
                "lastGameObjTime: " + lastGameObjTime + "\n" +
                "roundEndTime: " + roundEndTime + "\n" +
                "totalDeaths: " + totalDeaths + "\n" +
                "levelGroup: " + levelGroup + "\n" +
                "CurrentTime: " + TimeUtils.millis();
    }
}
