package net.asg.games.dante.view;

import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * @author Blakbro2k
 *
 */
public class FireBallMovingGameObject extends MovingGameObject{

	public FireBallMovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager, int width, int height,
			boolean isHitboxActive, MovingGameObjectState state) {
		super(imageProvider, textureRegions, soundManager, width, height, isHitboxActive, state);
		this.setMoveSpeed(820);

		rect.y = MathUtils.random(0, imageProvider.getScreenHeight() - height);
	}	
}
