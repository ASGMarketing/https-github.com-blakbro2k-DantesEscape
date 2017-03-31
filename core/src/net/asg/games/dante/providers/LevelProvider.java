package net.asg.games.dante.providers;

import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.Constants;
import net.asg.games.dante.models.MovingGameObject;
import net.asg.games.dante.models.MovingGameObjectFactory;
import net.asg.games.dante.states.GameScreenState;
import net.asg.games.dante.states.GameScreenState.LevelState;

public class LevelProvider {

    private PhaseProvider phase;

    public MovingGameObject getNextObject(MovingGameObjectFactory movingGameObjectFactory,
                                          GameScreenState st) {

        MovingGameObject mObj = null;

        if (TimeUtils.millis() > st.roundEndTime && !st.isPaused && !st.isDead) {
            st.levelReset();
        }

        switch (st.stageType) {
            case 3:  //is Goal Level
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getGoal();
                break;
            case 0:  //is Fireball Level
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getFireball();
                st.spawnTime = Constants.FIREBALL_SPAWN_TIME;
                break;
            case 2:  //is Lava wall Level
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getFireWall();
                st.spawnTime = Constants.DYNAMIC_FIREWALL_SPAWN_TIME;
                // mObj.setMoveSpeed(100);
                break;
            case 1:  //is Rock wall Level
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.FIREWALL_SPAWN_TIME;
                mObj = movingGameObjectFactory.getRockWall();
                break;
            case 4:  //is Sliding Rock Level
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.FIREWALL_SPAWN_TIME;
                mObj = movingGameObjectFactory.getSlidingRockWall();
                break;
        }

        return mObj;
    }

    public void flushPhases(){
        phase.flush();
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
                    st.roundEndTime = TimeUtils.millis() + Constants.ROUND_TIME_DURATION;
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
}
