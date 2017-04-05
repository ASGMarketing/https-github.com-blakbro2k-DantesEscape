package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;


public class MovingGameObjectFactory {

    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private boolean isHitboxActive = Constants.NO_CLIP_MODE_OFF;

    public MovingGameObjectFactory(ImageProvider imageProvider, SoundProvider soundProvider) {
        this.imageProvider = imageProvider;
        this.soundProvider = soundProvider;
        //System.out.println(SoundProvider);
    }

    public FireBallMovingGameObject getFireball() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREBALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREBALL);

        soundProvider.playflameBurstSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.Fireball);

        return new FireBallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.FIREBALL_HITBOX);
    }

    public RockWallMovingGameObject getRockWall(int position, int holeSize) {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.RockWall);

        return new RockWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX, position, holeSize);
    }

    public FireWallMovingGameObject getFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.LavaWall);

        return new FireWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX, -1, -1);
    }

    public GoalMovingGameObject getGoal() {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getGoal();

        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.GoalWall);

        return new GoalMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.GOAL_HITBOX);
    }

    public SlidingRockWallMovingGameObject getSlidingRockWall(int position, int holeSize, int gapDepth) {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.SlidingRockWall);

        return new SlidingRockWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX, position, holeSize, gapDepth);
    }

    public RockWallMovingGameObject getRandomEasyMediumSlidingWall(){
        return getSlidingRockWall(getRandomWallPosition(2,3), RockWallMovingGameObject.EASY_GAP_SIZE,
                SlidingRockWallMovingGameObject.MEDIUM_GAP_DEPTH);
    }

    public RockWallMovingGameObject getRandomEasyLongSlidingWall(){
        return getSlidingRockWall(getRandomWallPosition(2,3), RockWallMovingGameObject.EASY_GAP_SIZE,
                SlidingRockWallMovingGameObject.LONG_GAP_DEPTH);
    }

    public RockWallMovingGameObject getRandomEasyShortSlidingWall(){
        return getSlidingRockWall(getRandomWallPosition(2,3), RockWallMovingGameObject.EASY_GAP_SIZE,
                SlidingRockWallMovingGameObject.SHORT_GAP_DEPTH);
    }
    public RockWallMovingGameObject getRandomEasyRockWall(){
        return getRockWall(getRandomWallPosition(1,4), RockWallMovingGameObject.EASY_GAP_SIZE);
    }

    public RockWallMovingGameObject getRandomNormalRockWall(){
        return getRockWall(getRandomWallPosition(1,5), RockWallMovingGameObject.NORMAL_GAP_SIZE);
    }

    public RockWallMovingGameObject getRandomHardRockWall(){
        return getRockWall(getRandomWallPosition(1,5), RockWallMovingGameObject.HARD_GAP_SIZE);
    }

    private int getRandomWallPosition(int min, int max){
        int wallPosition;

        switch (MathUtils.random(min, max)){
            case 1:
                wallPosition = RockWallMovingGameObject.WALL_POSITION_ONE;
                break;
            case 2:
                wallPosition = RockWallMovingGameObject.WALL_POSITION_TWO;
                break;
            case 3:
                wallPosition = RockWallMovingGameObject.WALL_POSITION_THREE;
                break;
            case 4:
                wallPosition = RockWallMovingGameObject.WALL_POSITION_FOUR;
                break;
            case 5:
                wallPosition = RockWallMovingGameObject.WALL_POSITION_FIVE;
                break;
            case 6:
                wallPosition = RockWallMovingGameObject.WALL_POSITION_SIX;
                break;
            default:
                throw new IllegalArgumentException("Bad wall position was entered");
        }
        return  wallPosition;
    }
}
