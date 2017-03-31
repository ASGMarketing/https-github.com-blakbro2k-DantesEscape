package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
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
                                    boolean isHitboxActive, net.asg.games.dante.states.MovingGameObjectState state, int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, width, height, isHitboxActive, state, hitBoxConfig);
        this.setMoveSpeed(Constants.FIREBALL_SPEED);

        rect.y = MathUtils.random(0, imageProvider.getScreenHeight() - height);
        setHitboxBounds(rect);
    }
}
