package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

/**
 * Created by Blakbro2k on 4/11/2017.
 */

public class MovingGameObjectPool {
    private ImageProvider imageProvider;
    private SoundProvider soundProvider;

    public MovingGameObjectPool(ImageProvider imageProvider, SoundProvider soundProvider){
        this.imageProvider = imageProvider;
        this.soundProvider = soundProvider;
    }

    public final Pool<Goal> GoalPool = new Pool<Goal>() {
        @Override
        protected Goal newObject() {
            TextureRegion[] textureRegions = new TextureRegion[1];
            textureRegions[0] = imageProvider.getGoal();

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.GoalWall);

            return new Goal(imageProvider, textureRegions, soundProvider, model, Constants.GOAL_HITBOX);
        }
    };

    public final Pool<FireBall> FireBallPool = new Pool<FireBall>() {
        @Override
        protected FireBall newObject() {
            TextureRegion[] textureRegions = new TextureRegion[Constants.FIREBALL_TOTAL_ANIMATION_FRAMES];

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.Fireball);

            return new FireBall(imageProvider, textureRegions, soundProvider, model, Constants.FIREBALL_HITBOX);
        }
    };

    public final Pool<FireWall> FireWallPool = new Pool<FireWall>() {
        @Override
        protected FireWall newObject() {
            TextureRegion[] textureRegions = new TextureRegion[Constants.DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES];

            imageProvider.setAnimations(textureRegions, ImageProvider.FIREWALL_ID);

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.LavaWall);

            return new FireWall(imageProvider, textureRegions, soundProvider,
                    textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                    model, Constants.ROCKWALL_HITBOX, FireWall.WALL_POSITION_ONE, FireWall.EASY_GAP_SIZE);
        }
    };

    public final Pool<Missile> MissilePool = new Pool<Missile>() {
        @Override
        protected Missile newObject() {
            TextureRegion[] textureRegions = new TextureRegion[Constants.MISSLE_TOTAL_ANIMATION_FRAMES];

            imageProvider.setAnimations(textureRegions, ImageProvider.MISSILE_ID);

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.EasyMissile);

            return new Missile(imageProvider, textureRegions, soundProvider,
                    textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                    model, Constants.MISSLE_HITBOX);
        }
    };

    public final Pool<RockWall> RockWallPool = new Pool<RockWall>() {
        @Override
        protected RockWall newObject() {
            TextureRegion[] textureRegions = new TextureRegion[1];
            textureRegions[0] = imageProvider.getRockWall();

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.RockWall);

            return new RockWall(imageProvider, textureRegions, soundProvider,
                    textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                    model, Constants.ROCKWALL_HITBOX, RockWall.WALL_POSITION_ONE, RockWall.EASY_GAP_SIZE);
        }
    };

    public final Pool<SlidingRockWall> SlidingRockWallPool = new Pool<SlidingRockWall>() {
        @Override
        protected SlidingRockWall newObject() {
            TextureRegion[] textureRegions = new TextureRegion[1];
            textureRegions[0] = imageProvider.getRockWall();

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.SlidingRockWall);

            return new SlidingRockWall(imageProvider, textureRegions, soundProvider,
                    textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
                    model, Constants.ROCKWALL_HITBOX, SlidingRockWall.WALL_POSITION_ONE, SlidingRockWall.EASY_GAP_SIZE, SlidingRockWall.LONG_GAP_DEPTH);
        }
    };
}