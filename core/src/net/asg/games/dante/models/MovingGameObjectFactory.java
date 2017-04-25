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

    private FireBall getFireBall() {
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

    public FireBall getRandomFireBall(){
        FireBall obj = getFireBall();
        obj.setHitboxActive(true);
        obj.setObjectPosition(imageProvider.getScreenWidth(),
                MathUtils.random(0, imageProvider.getScreenHeight() - ((int)obj.getPosition().height))
        );
        return obj;
    }

    /*public FireBall getFireBall(int x, int y){
        FireBall obj = getFireBall();
        obj.setHitboxActive(true);
        obj.setObjectPosition(x,y);
        return obj;
    }*/

    public Missile getEasyLaunchMissle() {
        Missile obj = getMissle();
        obj.setInactive();
        return obj;
    }

    public Missile getEasyActiveMissle() {
        Missile obj = getMissle();
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
        SlidingRockWall obj = getSlidingRockWall();
        return obj;
    }

    public SlidingRockWall getRandomEasyLongSlidingWall(){
        SlidingRockWall obj = getSlidingRockWall();
        return obj;
    }

    public SlidingRockWall getRandomEasyShortSlidingWall(){
        SlidingRockWall obj = getSlidingRockWall();
        return obj;
    }
    public RockWall getRandomEasyRockWall(){
        SlidingRockWall obj = getSlidingRockWall();
        return obj;
    }

    public RockWall getRandomNormalRockWall(){
        SlidingRockWall obj = getSlidingRockWall();
        return obj;
    }

    public RockWall getRandomHardRockWall(){
        SlidingRockWall obj = getSlidingRockWall();
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
            case 6:
                wallPosition = RockWall.WALL_POSITION_SIX;
                break;
            default:
                throw new IllegalArgumentException("Bad wall position was entered");
        }
        return  wallPosition;
    }

    @Override
    public String toString() {
        return "ObjectFactory Pool: \n" + gameObjectPool;
    }
}
