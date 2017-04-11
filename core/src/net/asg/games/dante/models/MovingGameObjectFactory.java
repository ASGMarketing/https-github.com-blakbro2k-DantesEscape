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


    public FireBall getFireball() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREBALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, ImageProvider.FIREBALL_ID);

        soundProvider.playflameBurstSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.Fireball);

        return new FireBall(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.FIREBALL_HITBOX);
    }

    public RockWall getRockWall(int position, int holeSize) {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getRockWall();

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.RockWall);

        return new RockWall(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX, position, holeSize);
    }

    public FireWall getFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, ImageProvider.FIREWALL_ID);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.LavaWall);

        return new FireWall(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX, -1, -1);
    }

    public Goal getGoal() {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getGoal();

        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.GoalWall);

        return new Goal(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.GOAL_HITBOX);
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

    private Missile getMissle(int missleId) {
        TextureRegion[] textureRegions = new TextureRegion[Constants.MISSLE_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, missleId);

        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.Missile);

        return new Missile(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.MISSLE_HITBOX);
    }

    private SlidingRockWall getSlidingRockWall(int position, int holeSize, int gapDepth) {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getRockWall();

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.SlidingRockWall);

        return new SlidingRockWall(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX, position, holeSize, gapDepth);
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
