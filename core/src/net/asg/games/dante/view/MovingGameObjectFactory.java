package net.asg.games.dante.view;

import net.asg.games.dante.Constants;
import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.models.MovingGameObjectType;
import net.asg.games.dante.sound.SoundManager;
import net.asg.games.dante.view.MovingGameObjectState;



import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MovingGameObjectFactory {

	private ImageProvider imageProvider;
	private SoundManager soundManager;
	public boolean isHitboxActive = Constants.NO_CLIP_MODE_OFF;

	public MovingGameObjectFactory(ImageProvider imageProvider, SoundManager soundManager){
        this.imageProvider = imageProvider;
        this.soundManager = soundManager;
		//System.out.println(soundManager);
	}
	
    public FireBallMovingGameObject getFireball() {
        TextureRegion [] textureRegions = new TextureRegion[7];
        textureRegions[0] = imageProvider.getFireBall(1);
        textureRegions[1] = imageProvider.getFireBall(2);
        textureRegions[2] = imageProvider.getFireBall(3);
        textureRegions[3] = imageProvider.getFireBall(4);
        textureRegions[4] = imageProvider.getFireBall(5);
        textureRegions[5] = imageProvider.getFireBall(6);
        textureRegions[6] = imageProvider.getFireBall(7);
        
		soundManager.playflameBurstSound();
		MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.Fireball);
        
        return new FireBallMovingGameObject(imageProvider, textureRegions, soundManager,
        		textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
        		isHitboxActive, model);
    }
    
    public FireWallMovingGameObject getFireWall() {
        TextureRegion [] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);
        
		soundManager.playfirewooshSound();
		MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.FireWall);
        
        return new FireWallMovingGameObject(imageProvider, textureRegions, soundManager,
        		textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
        		isHitboxActive, model);
    }
    
    public DynamicFireWallMovingGameObject getDynamicFireWall() {
        TextureRegion [] textureRegions = new TextureRegion[3];
        textureRegions[0] = imageProvider.getFireWall(1);
        textureRegions[1] = imageProvider.getFireWall(2);
        textureRegions[2] = imageProvider.getFireWall(3);

		soundManager.playfirewooshSound();
		MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.DynamicWall);
        
        return new DynamicFireWallMovingGameObject(imageProvider, textureRegions, soundManager,
        		textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
        		isHitboxActive, model);
    }

	public GoalMovingGameObject getGoal() {
        TextureRegion [] textureRegions = new TextureRegion[1];
        textureRegions[0] = imageProvider.getGoal();
        
		MovingGameObjectState model = new MovingGameObjectState();
        model.setType(MovingGameObjectType.GoalWall);

        return new GoalMovingGameObject(imageProvider, textureRegions, soundManager,
        		textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(),
        		isHitboxActive, model);
	}
}
