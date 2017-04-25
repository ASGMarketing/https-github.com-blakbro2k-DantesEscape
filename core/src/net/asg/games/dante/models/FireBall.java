package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Blakbro2k
 */
public class FireBall extends MovingGameObject {

    public FireBall(ImageProvider imageProvider,
                    TextureRegion[] textureRegions,
                    SoundProvider soundProvider,
                    MovingGameObjectState state,
                    int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);
        this.setMoveSpeed(Constants.FIREBALL_SPEED);

        imageProvider.setAnimations(textureRegions, ImageProvider.FIREBALL_ID);
    }

    public void fireSound(){
        soundProvider.playflameBurstSound();
    }

    public String toString() {
        return "FireBall: \n" +
                "Bounds: " + rect + "\n" +
                "moveSpeed: " + moveSpeed + "\n" +
                "isCollided: " + isCollided + "\n" +
                "isSoundTriggered: " + isSoundTriggered + "\n" +
                "isHitboxActive: " + isHitboxActive + "\n" +
                "frame: " + frame + "\n";
    }
}
