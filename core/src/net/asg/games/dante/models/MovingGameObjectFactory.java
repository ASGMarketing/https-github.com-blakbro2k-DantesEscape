package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;


import com.badlogic.gdx.graphics.g2d.TextureRegion;


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
        net.asg.games.dante.states.MovingGameObjectState model = new net.asg.games.dante.states.MovingGameObjectState();
        model.setType(MovingGameObjectType.Fireball);

        return new FireBallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.FIREBALL_HITBOX);
    }

    public RockWallMovingGameObject getRockWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        net.asg.games.dante.states.MovingGameObjectState model = new net.asg.games.dante.states.MovingGameObjectState();
        model.setType(MovingGameObjectType.RockWall);

        return new RockWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX);
    }

    public FireWallMovingGameObject getFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        net.asg.games.dante.states.MovingGameObjectState model = new net.asg.games.dante.states.MovingGameObjectState();
        model.setType(MovingGameObjectType.LavaWall);

        return new FireWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX);
    }

    public GoalMovingGameObject getGoal() {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getGoal();

        net.asg.games.dante.states.MovingGameObjectState model = new net.asg.games.dante.states.MovingGameObjectState();
        model.setType(MovingGameObjectType.GoalWall);

        return new GoalMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.GOAL_HITBOX);
    }

    public SlidingRockWallMovingGameObject getSlidingRockWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        net.asg.games.dante.states.MovingGameObjectState model = new net.asg.games.dante.states.MovingGameObjectState();
        model.setType(MovingGameObjectType.SlidingRockWall);

        return new SlidingRockWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model, Constants.ROCKWALL_HITBOX);
    }
}
