package net.asg.games.dante.models;

import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.providers.ImageProvider;
import com.badlogic.gdx.math.MathUtils;


public class MovingGameObjectFactory {
    private MovingGameObjectPool gameObjectPool;
    private ImageProvider imageProvider;

    public MovingGameObjectFactory(DantesEscapeGame game) {
        imageProvider = game.getImageProvider();
        this.gameObjectPool = new MovingGameObjectPool(imageProvider, game.getSoundProvider());
    }

    private FireBall getFireBallObj() {
        return gameObjectPool.FireBallPool.obtain();
    }

    private RockWall getRockWallObj() {
        return gameObjectPool.RockWallPool.obtain();
    }

    public FireWall getFireWallObj() {
        return gameObjectPool.FireWallPool.obtain();
    }

    private Missile getMissleObj() {
        return gameObjectPool.MissilePool.obtain();
    }

    public Goal getGoalObj() {
        return gameObjectPool.GoalPool.obtain();
    }

    private SlidingRockWall getSlidingRockWallObj() {
        return gameObjectPool.SlidingRockWallPool.obtain();
    }

    public FireBall getRandomFireBall(){
        return getFireBall(imageProvider.getScreenWidth(),
                MathUtils.random(0, imageProvider.getScreenHeight()));
    }

    public FireBall getFireBall(int x, int y){
        FireBall obj = getFireBallObj();
        obj.setObjectPosition(x, y);
        obj.setHitboxActive(true);
        return obj;
    }

    public Missile getEasyLaunchMissle() {
        Missile obj = getMissleObj();
        obj.setInactive();
        return obj;
    }

    public Missile getEasyActiveMissle() {
        Missile obj = getMissleObj();
        obj.setActive();
        obj.setFalling();
        return obj;
    }

    /*private MovingGameObjectType getMissleType(int missleType) {
        switch (missleType){
            case ImageProvider.HARD_MISSILE_ID:
            case ImageProvider.HARD_MISSILE_FLIPPED_ID:
                return MovingGameObjectType.HardMissile;
            case ImageProvider.EASY_MISSILE_ID:
            case ImageProvider.EASY_MISSILE_FLIPPED_ID:
            default:
                return MovingGameObjectType.EasyMissile;
        }
    }*/

    public SlidingRockWall getRandomEasyMediumSlidingWall(){
        SlidingRockWall obj = getSlidingRockWallObj();
        obj.setHoleSize(SlidingRockWall.NORMAL_GAP_SIZE);
        obj.setObjectPosition(imageProvider.getScreenWidth(),getRandomWallPosition(3,4));
        obj.setHitboxActive(true);
        return obj;
    }

    public SlidingRockWall getRandomEasyLongSlidingWall(){
        SlidingRockWall obj = getSlidingRockWallObj();
        return obj;
    }

    public SlidingRockWall getRandomEasyShortSlidingWall(){
        SlidingRockWall obj = getSlidingRockWallObj();
        return obj;
    }
    public RockWall getRandomEasyRockWall(){
        RockWall obj = getRockWallObj();
        obj.setHoleSize(RockWall.EASY_GAP_SIZE);
        obj.setObjectPosition(imageProvider.getScreenWidth(),getRandomWallPosition(1,5));
        obj.setHitboxActive(true);
        return obj;
    }

    public RockWall getRandomNormalRockWall(){
        RockWall obj = getRockWallObj();
        obj.setHoleSize(RockWall.NORMAL_GAP_SIZE);
        obj.setObjectPosition(imageProvider.getScreenWidth(),getRandomWallPosition(1,5));
        obj.setHitboxActive(true);
        return obj;
    }

    public RockWall getRandomHardRockWall(){
        SlidingRockWall obj = getSlidingRockWallObj();
        return obj;
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
            default:
                throw new IllegalArgumentException("Bad wall position was entered");
        }
        return  wallPosition;
    }

    public void freeObject(MovingGameObject mObj){
        if(mObj instanceof FireBall){
            gameObjectPool.FireBallPool.free((FireBall) mObj);
        } else if(mObj instanceof SlidingRockWall){
            gameObjectPool.SlidingRockWallPool.free((SlidingRockWall) mObj);
        } else if(mObj instanceof Goal){
            gameObjectPool.GoalPool.free((Goal) mObj);
        } else if(mObj instanceof Missile){
            gameObjectPool.MissilePool.free((Missile) mObj);
        } else if(mObj instanceof FireWall){
            gameObjectPool.FireWallPool.free((FireWall) mObj);
        } else if(mObj instanceof RockWall){
            gameObjectPool.RockWallPool.free((RockWall) mObj);
        } else {
            throw new IllegalArgumentException("Cannot free object: " + mObj);
        }
    }

    @Override
    public String toString() {
        return "ObjectFactory Pool: \n" + gameObjectPool;
    }
}
