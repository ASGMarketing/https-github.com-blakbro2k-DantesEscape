package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

/**
 * Created by eboateng on 3/31/2017.
 */

public class SlidingRockWall extends RockWall {
    public static final int LONG_GAP_DEPTH = 25;
    public static final int MEDIUM_GAP_DEPTH = 55;
    public static final int SHORT_GAP_DEPTH = 7;

    protected int isMovingDown = 0;
    private boolean shook = false;
    private int lastShookValue = 0;
    private long lastShookTime = 0;
    private int gapDepth;

    public SlidingRockWall(ImageProvider imageProvider,
                           TextureRegion[] textureRegions,
                           SoundProvider soundProvider,
                           MovingGameObjectState state,
                           int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);
    }

    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        lowerWall.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds();

        switch (isMovingDown) {
            case 0:
                rect.y += Constants.WALL_GAP_SPEED * delta;
                lowerWall.y += Constants.WALL_GAP_SPEED * delta;
                if (rect.y + gapDepth > this.imageProvider.getScreenHeight())
                    isMovingDown = 1;
                break;
            case 1:
                rect.y -= Constants.WALL_GAP_SPEED * delta;
                lowerWall.y -= Constants.WALL_GAP_SPEED * delta;
                if (lowerWall.y + lowerWall.height - gapDepth < 0)
                    isMovingDown = 0;
                break;
        }

        // state.setPosY((int) rect.y);
        time += delta;
        if (time > animationPeriod) {
            time -= animationPeriod;
            frame += 1;
            if (frame >= textureRegions.length) {
                frame = 0;
            }
        }
        setStatefulPosition();
    }

    public void draw(SpriteBatch batch) {
        shakeSprite(shook);
        super.draw(batch);
    }

    public void shakeSprite(boolean shook){
        if(lastShookTime == 0){
            lastShookTime = TimeUtils.millis();
        }

        if(TimeUtils.millis() - lastShookTime > 30){
            lastShookTime = TimeUtils.millis();
            int shakeValue;
            if(!shook){
                this.shook = true;
                shakeValue = MathUtils.random(2,8);
                lastShookValue = shakeValue;

            } else {
                this.shook = false;
                shakeValue = -1 * lastShookValue;
            }

            rect.x += shakeValue;
            lowerWall.x += shakeValue;
            setHitboxBounds();
        }
    }

    public String toString(){
        return "Upper Wall: " + rect + "\n" +
                "Lower Wall: " + lowerWall + "\n" +
                "position: " + position + "\n" +
                "holeSize: " + holeSize + "\n" +
                "moveSpeed: " + moveSpeed + "\n" +
                "isMovingDown: " + isMovingDown + "\n";
    }
}
