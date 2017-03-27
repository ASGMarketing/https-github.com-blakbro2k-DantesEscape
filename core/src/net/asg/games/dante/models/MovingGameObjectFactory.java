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
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.Fireball);

        return new FireBallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }

    public FireWallMovingGameObject getFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.FireWall);

        return new FireWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }

    public DynamicFireWallMovingGameObject getDynamicFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[Constants.DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES];

        imageProvider.setAnimations(textureRegions, imageProvider, ImageProvider.ObjectType.FIREWALL);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.DynamicWall);

        return new DynamicFireWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }

    public GoalMovingGameObject getGoal() {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getGoal();

        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.GoalWall);

        return new GoalMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }
}
