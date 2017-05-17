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

    public final Pool<Goal> GoalPool = new Pool<Goal>(1, 2) {
        @Override
        protected Goal newObject() {
            System.out.println("!!!!!New Goal!!!!");
            TextureRegion[] textureRegions = new TextureRegion[1];
            textureRegions[0] = imageProvider.getGoal();

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.GoalWall);

            return new Goal(imageProvider, textureRegions, soundProvider, model, Constants.GOAL_HITBOX);
        }
    };

    public final Pool<FireBall> FireBallPool = new Pool<FireBall>(16, 16) {
        @Override
        protected FireBall newObject() {
            System.out.println("!!!!!New FireBall!!!!");
            TextureRegion[] textureRegions = new TextureRegion[Constants.FIREBALL_TOTAL_ANIMATION_FRAMES];

            imageProvider.setAnimations(textureRegions, ImageProvider.FIREBALL_ID);

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.Fireball);

            return new FireBall(imageProvider, textureRegions, soundProvider, model, Constants.FIREBALL_HITBOX);
        }
    };

    public final Pool<FireWall> FireWallPool = new Pool<FireWall>(16, 16) {
        @Override
        protected FireWall newObject() {
            System.out.println("!!!!!New FireWall!!!!");
            TextureRegion[] textureRegions = new TextureRegion[Constants.DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES];

            imageProvider.setAnimations(textureRegions, ImageProvider.FIREWALL_ID);

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.LavaWall);

            return new FireWall(imageProvider, textureRegions, soundProvider, model, Constants.ROCKWALL_HITBOX);
        }
    };

    public final Pool<Missile> MissilePool = new Pool<Missile>(16, 16) {
        @Override
        protected Missile newObject() {
            System.out.println("!!!!!New Missile!!!!");
            TextureRegion[] textureRegions = new TextureRegion[Constants.MISSLE_TOTAL_ANIMATION_FRAMES];

            imageProvider.setAnimations(textureRegions, ImageProvider.MISSILE_ID);

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.EasyMissile);

            return new Missile(imageProvider, textureRegions, soundProvider, model, Constants.MISSLE_HITBOX);
        }
    };

    public final Pool<RockWall> RockWallPool = new Pool<RockWall>(16, 16) {
        @Override
        protected RockWall newObject() {
            System.out.println("!!!!!New RockWall!!!!");
            TextureRegion[] textureRegions = new TextureRegion[1];
            textureRegions[0] = imageProvider.getRockWall();

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.RockWall);

            return new RockWall(imageProvider, textureRegions, soundProvider, model, Constants.ROCKWALL_HITBOX);
        }
    };

    public final Pool<SlidingRockWall> SlidingRockWallPool = new Pool<SlidingRockWall>(16, 16) {
        @Override
        protected SlidingRockWall newObject() {
            System.out.println("!!!!!New SlidingRockWall!!!!");
            TextureRegion[] textureRegions = new TextureRegion[1];
            textureRegions[0] = imageProvider.getRockWall();

            MovingGameObjectState model = new MovingGameObjectState();
            model.setType(MovingGameObjectType.SlidingRockWall);

            return new SlidingRockWall(imageProvider, textureRegions, soundProvider, model, Constants.ROCKWALL_HITBOX);
        }
    };

    @Override
    public String toString(){
        return "Goal free: " + ( GoalPool.peak) + " out of " + GoalPool.getFree() + "\n"
                + "FireBall free: " + ( FireBallPool.peak) + " out of " + FireBallPool.getFree() + "\n"
                + "RockWall free: " + ( RockWallPool.peak) + " out of " + RockWallPool.getFree() + "\n"
                + "FireWall free: " + ( FireWallPool.peak) + " out of " + FireWallPool.getFree() + "\n"
                + "Missile free: " +  ( MissilePool.peak) + " out of " + MissilePool.getFree() + "\n"
                + "Sliding free: " +  ( SlidingRockWallPool.peak) + " out of " + SlidingRockWallPool.getFree() + "\n"
                ;
    }
}