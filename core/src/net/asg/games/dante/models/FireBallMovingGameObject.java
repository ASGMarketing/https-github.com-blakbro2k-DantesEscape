package net.asg.games.dante.models;

import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * @author Blakbro2k
 */
public class FireBallMovingGameObject extends MovingGameObject {

    public FireBallMovingGameObject(ImageProvider imageProvider,
                                    TextureRegion[] textureRegions, SoundProvider soundProvider, int width, int height,
                                    boolean isHitboxActive, MovingGameObjectState state) {
        super(imageProvider, textureRegions, soundProvider, width, height, isHitboxActive, state);
        this.setMoveSpeed(820);

        rect.y = MathUtils.random(0, imageProvider.getScreenHeight() - height);
    }
}
