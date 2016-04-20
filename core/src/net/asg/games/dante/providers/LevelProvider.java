package net.asg.games.dante.providers;

import net.asg.games.dante.Constants;
import net.asg.games.dante.screens.GameScreenState;
import net.asg.games.dante.screens.GameScreenState.LevelState;
import net.asg.games.dante.models.FireBallMovingGameObject;
import net.asg.games.dante.models.FireWallMovingGameObject;
import net.asg.games.dante.models.MovingGameObject;
import net.asg.games.dante.models.MovingGameObjectFactory;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class LevelProvider {

    public MovingGameObject getNextObject(MovingGameObjectFactory movingGameObjectFactory,
                                          GameScreenState st) {

        MovingGameObject mObj = null;

        if (TimeUtils.millis() > st.roundEndTime && !st.isPaused && !st.isDead) {
            st.levelReset();
        }

        switch (st.stageType) {
            case 0:
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getGoal();
                break;
            case 1:
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getFireball();
                st.spawnTime = Constants.FIREBALL_SPAWN_TIME;
                break;
            case 2:
                st.lastGameObjTime = TimeUtils.millis();
                mObj = movingGameObjectFactory.getDynamicFireWall();
                st.spawnTime = Constants.DYNAMIC_FIREWALL_SPAWN_TIME;
                // mObj.setMoveSpeed(100);
                break;
            case 3:
                st.lastGameObjTime = TimeUtils.millis();
                st.spawnTime = Constants.FIREWALL_SPAWN_TIME;
                mObj = movingGameObjectFactory.getFireWall();
                break;
        }

        return processLevelDesign(mObj, st);
    }

    public MovingGameObject processLevelDesign(MovingGameObject mObj, GameScreenState st) {
        if (mObj instanceof FireWallMovingGameObject) {
            if (st.roundCount > 2 && (MathUtils.random(0, 2) == 1))
                ((FireWallMovingGameObject) mObj).isMobile = true;
        }

        if (mObj instanceof FireBallMovingGameObject) {
            if (st.roundCount > 6)
                mObj.setAnimationSpeed(500);
        }

        return mObj;
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

                    Array<Integer> temp = new Array<Integer>();
                    for (int x = 1; x < 4; x++) {
                        if (x != st.lastStageType) {
                            temp.add(x);
                        }
                    }
                    st.stageType = temp.get(MathUtils.random(0, temp.size - 1));
                    //stageType = 3;
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
