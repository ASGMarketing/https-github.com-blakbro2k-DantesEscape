package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

/**
 * Created by eboateng on 3/31/2017.
 */

public class SlidingRockWallMovingGameObject extends RockWallMovingGameObject {
    public SlidingRockWallMovingGameObject(ImageProvider imageProvider, TextureRegion[] textureRegions, SoundProvider soundProvider, int width, int height, boolean isHitboxActive, MovingGameObjectState state, int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, width, height, isHitboxActive, state, hitBoxConfig);
        isMovingDown = 1;
    }
}
