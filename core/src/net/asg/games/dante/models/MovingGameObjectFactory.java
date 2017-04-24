package net.asg.games.dante.models;

import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.providers.ImageProvider;
import com.badlogic.gdx.math.MathUtils;


public class MovingGameObjectFactory {
    private MovingGameObjectPool gameObjectPool;

    public MovingGameObjectFactory(DantesEscapeGame game) {
        this.gameObjectPool = new MovingGameObjectPool(game.getImageProvider(), game.getSoundProvider());
    }

    public FireBall getFireBall() {
        return gameObjectPool.FireBallPool.obtain();
    }

    private RockWall getRockWall() {
        return gameObjectPool.RockWallPool.obtain();
    }

    public FireWall getFireWall() {
        return gameObjectPool.FireWallPool.obtain();
    }

    private Missile getMissle() {
        return gameObjectPool.MissilePool.obtain();
    }

    public Goal getGoal() {
        return gameObjectPool.GoalPool.obtain();
    }

    private SlidingRockWall getSlidingRockWall() {
        return gameObjectPool.SlidingRockWallPool.obtain();
    }

    public Missile getEasyLaunchMissle() {
        Missile temp = getMissle(ImageProvider.EASY_MISSILE_ID);
        temp.setInactive();
        return temp;
    }

    public Missile getEasyActiveMissle() {
        Missile temp = getMissle(ImageProvider.EASY_MISSILE_FLIPPED_ID);
        temp.setActive();
        temp.setFalling();
        return temp;
    }

    private MovingGameObjectType getMissleType(int missleType) {
        switch (missleType){
            case ImageProvider.HARD_MISSILE_ID:
            case ImageProvider.HARD_MISSILE_FLIPPED_ID:
                return MovingGameObjectType.HardMissile;
            case ImageProvider.EASY_MISSILE_ID:
            case ImageProvider.EASY_MISSILE_FLIPPED_ID:
            default:
                return MovingGameObjectType.EasyMissile;
        }
    }

    public RockWall getRandomEasyMediumSlidingWall(){
        return getSlidingRockWall(getRandomWallPosition(2,3), RockWall.EASY_GAP_SIZE,
                SlidingRockWall.MEDIUM_GAP_DEPTH);
    }

    public RockWall getRandomEasyLongSlidingWall(){
        return getSlidingRockWall(getRandomWallPosition(2,3), RockWall.EASY_GAP_SIZE,
                SlidingRockWall.LONG_GAP_DEPTH);
    }

    public RockWall getRandomEasyShortSlidingWall(){
        return getSlidingRockWall(getRandomWallPosition(2,3), RockWall.EASY_GAP_SIZE,
                SlidingRockWall.SHORT_GAP_DEPTH);
    }
    public RockWall getRandomEasyRockWall(){
        return getRockWall(getRandomWallPosition(1,4), RockWall.EASY_GAP_SIZE);
    }

    public RockWall getRandomNormalRockWall(){
        return getRockWall(getRandomWallPosition(1,5), RockWall.NORMAL_GAP_SIZE);
    }

    public RockWall getRandomHardRockWall(){
        return getRockWall(getRandomWallPosition(1,5), RockWall.HARD_GAP_SIZE);
    }

    private int getRandomWallPosition(int min, int max){
        int wallPosition;

        switch (MathUtils.random(min, max)){
            case 1:
                wallPosition = RockWall.WALL_POSITION_ONE;
                break;
            case 2:
                wallPosition = RockWall.WALL_POSITION_TWO;
                break;
            case 3:
                wallPosition = RockWall.WALL_POSITION_THREE;
                break;
            case 4:
                wallPosition = RockWall.WALL_POSITION_FOUR;
                break;
            case 5:
                wallPosition = RockWall.WALL_POSITION_FIVE;
                break;
            case 6:
                wallPosition = RockWall.WALL_POSITION_SIX;
                break;
            default:
                throw new IllegalArgumentException("Bad wall position was entered");
        }
        return  wallPosition;
    }
}
