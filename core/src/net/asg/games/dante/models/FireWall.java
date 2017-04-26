package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

public class FireWall extends RockWall {
    private boolean isClosingType;

    public FireWall(ImageProvider imageProvider,
                    TextureRegion[] textureRegions,
                    SoundProvider soundProvider,
                    MovingGameObjectState state,
                    int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);

        setWallAsClosingType(isClosingType);
        this.setAnimationSpeed(0.11f);
    }

    public void setWallAsClosingType(boolean wallType) {
        isClosingType = wallType;
        if (isClosingType) {
            rect.y = imageProvider.getScreenHeight() - position;
            lowerWall.y = 0 - height - position;
        } else {
            rect.y = imageProvider.getScreenHeight() / 2;
            lowerWall.y = 0 - height - position;
        }
    }

    public void setClosingType() {
        isClosingType = true;
    }

    public void setOpeningType() {
        isClosingType = false;
    }

    public void setOpeningPosition(int position) {

    }
    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        lowerWall.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds();

        if (!isClosingType) {
            lowerWall.y -= Constants.WALL_GAP_SPEED * delta;
            rect.y += Constants.WALL_GAP_SPEED * delta;
            if (rect.y < imageProvider.getScreenHeight()
                    || lowerWall.y + lowerWall.height > 0) {
                soundProvider.playBuzzSound();
            }
        } else {
            if (rect.y > lowerWall.y + lowerWall.height) {
                lowerWall.y += Constants.WALL_GAP_SPEED * delta;
                rect.y -= Constants.WALL_GAP_SPEED * delta;
                soundProvider.playBuzzSound();
            }
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

    public void fireSound(){
        soundProvider.playfirewooshSound();
    }
}