package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
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
    }

    public void initialize(int[] hitBoxConfig){
        this.offSetX = hitBoxConfig[GAME_OBJECT_X];
        this.offSetY = hitBoxConfig[GAME_OBJECT_Y];

        rect = new Rectangle();
        hitboxBounds = new Rectangle();

        int rectConfig[] = {0,0,textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight()};

        setRectSize(rect, rectConfig);
        setRectSize(hitboxBounds, hitBoxConfig);
        reset();
        setHitboxActive(true);
        setStatefulPosition();
        setMoveSpeed(Constants.FIREBALL_SPEED);
        imageProvider.setAnimations(textureRegions, ImageProvider.FIREBALL_ID);
    }

    public void reset() {
        time = 0;
        isCollided = false;
        isSoundTriggered = false;
        frame = 0;
        setHitboxActive(false);
        setObjectPosition(imageProvider.getScreenWidth(), 0);
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
