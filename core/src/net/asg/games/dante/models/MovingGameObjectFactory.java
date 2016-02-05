package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;


import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MovingGameObjectFactory {

    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    public boolean isHitboxActive = Constants.NO_CLIP_MODE_OFF;

    public MovingGameObjectFactory(ImageProvider imageProvider, SoundProvider soundProvider) {
        this.imageProvider = imageProvider;
        this.soundProvider = soundProvider;
        //System.out.println(SoundProvider);
    }

    public FireBallMovingGameObject getFireball() {
        TextureRegion[] textureRegions = new TextureRegion[7];
        textureRegions[0] = imageProvider.getFireBall(1);
        textureRegions[1] = imageProvider.getFireBall(2);
        textureRegions[2] = imageProvider.getFireBall(3);
        textureRegions[3] = imageProvider.getFireBall(4);
        textureRegions[4] = imageProvider.getFireBall(5);
        textureRegions[5] = imageProvider.getFireBall(6);
        textureRegions[6] = imageProvider.getFireBall(7);

        soundProvider.playflameBurstSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.Fireball);

        return new FireBallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }

    public net.asg.games.dante.models.FireWallMovingGameObject getFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.FireWall);

        return new net.asg.games.dante.models.FireWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }

    public net.asg.games.dante.models.DynamicFireWallMovingGameObject getDynamicFireWall() {
        TextureRegion[] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);

        soundProvider.playfirewooshSound();
        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.DynamicWall);

        return new net.asg.games.dante.models.DynamicFireWallMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }

    public net.asg.games.dante.models.GoalMovingGameObject getGoal() {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getGoal();

        MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.GoalWall);

        return new net.asg.games.dante.models.GoalMovingGameObject(imageProvider, textureRegions, soundProvider,
                textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                isHitboxActive, model);
    }
}
