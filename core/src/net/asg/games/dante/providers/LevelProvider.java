package net.asg.games.dante.providers;

import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.Constants;
import net.asg.games.dante.models.MovingGameObject;
import net.asg.games.dante.models.MovingGameObjectFactory;
import net.asg.games.dante.states.GameScreenState;
import net.asg.games.dante.states.GameScreenState.LevelState;

public class LevelProvider {
    public static final int FIREBALL_LEVEL_VALUE = 0;
    public static final int ROCK_LEVEL_VALUE = 1;
    public static final int LAVA_LEVEL_VALUE = 2;
    public static final int GOAL_LEVEL_VALUE = 3;
    public static final int SLIDE_ROCK_LEVEL_VALUE = 4;
    public static final int EASY_ROCK_LEVEL_VALUE = 5;
    public static final int FAST_ROCK_LEVEL_VALUE = 6;
    public static final int FAST_LAVA_LEVEL_VALUE = 7;
    public static final int EASY_MISSILE_LEVEL_VALUE = 8;
    public static final int HARD_MISSILE_LEVEL_VALUE = 9;
    public static final int EASY_MISSLE_FLIPPED_LEVEL_VALUE = 10;

    private PhaseProvider phase;
    private int counter;
    private boolean missleFlip = false;

    public MovingGameObject getNextObject(MovingGameObjectFactory movingGameObjectFactory,
                                          GameScreenState st) {

        MovingGameObject mObj;

        if (TimeUtils.millis() > st.roundEndTime && !st.isPaused && !st.isDead) {
            st.levelReset();
        }

        switch (st.stageType) {
            case GOAL_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getGoal();
                clearFlags();
                break;
            case FIREBALL_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getFireBall();
                st.spawnTime = Constants.FIREBALL_SPAWN_TIME;
                break;
            case LAVA_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getFireWall();
                st.spawnTime = Constants.DYNAMIC_FIREWALL_SPAWN_TIME;
                // mObj.setMoveSpeed(100);
                break;
            case ROCK_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.FIREWALL_SPAWN_TIME;
                mObj = movingGameObjectFactory.getRandomNormalRockWall();
                break;
            case SLIDE_ROCK_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.FIREWALL_SPAWN_TIME;
                mObj = movingGameObjectFactory.getRandomEasyMediumSlidingWall();
                break;
            case EASY_ROCK_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.FIREWALL_SPAWN_TIME;
                mObj = movingGameObjectFactory.getRandomEasyRockWall();
                break;
            case EASY_MISSILE_LEVEL_VALUE:
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.MISSLE_SPAWN_TIME;
                if(counter > st.missileCounter){
                    mObj = movingGameObjectFactory.getEasyActiveMissle();
                } else {
                    mObj = movingGameObjectFactory.getEasyLaunchMissle();
                }
                counter++;

                mObj.setMoveSpeed(Constants.MISSLE_MOVESPEED);
                break;
            default:
                throw new RuntimeException("This level type does not exist " + st.stageType);
        }
        return mObj;
    }

    public void flushPhases(){
        phase.flush();
    }

    public void clearFlags(){
        counter = 0;
        missleFlip = false;
    }

    public void doLevelTransition(LevelState state, GameScreenState st) {
        switch (state) {
            case FIREBALLHIT:
                if (st.isLevelStarted) {
                    st.isDead = true;
                }
                break;
            case GOALHIT:
                if (!st.isLevelStarted) {
                    if(phase == null){
                        phase = new PhaseProvider();
                    }

                    if(!phase.hasNext()){
                        phase.buildPhase(st);
                    }

                    st.stageType = phase.nextStage();

                    st.roundEndTime = st.getRoundEndTime(Constants.ROUND_TIME_DURATION);
                    st.roundCount++;
                    st.isLevelStarted = true;
                }
                break;
            case WALLHIT:
                break;
            default:
                break;
        }
    }

    //private long getRoundEndTime(int stageType){
        //int[] easyStages = {8,9};
        //MovingGameObjectType.EasyRockWall.getValue()
        //if(easyStages.)
        //st.setRoundEndTime(Constants.ROUND_TIME_DURATION);
    //}
}
