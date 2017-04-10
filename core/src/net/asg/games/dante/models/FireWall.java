package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

public class FireWall extends RockWall {
    private boolean isClosingType;

    public FireWall(ImageProvider imageProvider,
                    TextureRegion[] textureRegions, SoundProvider soundProvider,
                    int width, int height, boolean isHitboxActive, MovingGameObjectState state,
                    int[] hitBoxConfig, int position, int holeSize) {
        super(imageProvider, textureRegions, soundProvider, width, height,
                isHitboxActive, state, hitBoxConfig, position, holeSize);

        isClosingType = MathUtils.random(0, 1) == 0;

        //isClosingType = true;
        position = MathUtils.random(1, 4) * 50;

        //this.rect = new Rectangle();
        this.rect.width = width;
        this.rect.height = height;

        this.lowerWall = new Rectangle();
        this.lowerWall.width = width;
        this.lowerWall.height = height;

        this.rect.x = this.imageProvider.getScreenWidth();
        this.rect.y = WALL_BASE_OFFSET - position;

        this.lowerWall.x = this.imageProvider.getScreenWidth();
        this.lowerWall.y = WALL_BASE_OFFSET - rect.height - position;

        //this.setAnimationSpeed(0.2f);
        setRectSize(lowerHitboxBounds, hitBoxConfig);
        setHitboxBounds(rect);
        setLowerHitboxBounds(lowerWall);
        setWallAsClosingType(isClosingType);
        this.setAnimationSpeed(0.11f);
    }

    public void setWallAsClosingType(boolean wallType) {
        this.isClosingType = wallType;
        if (isClosingType) {
            this.rect.y = imageProvider.getScreenHeight() - position;
            this.lowerWall.y = 0 - height - position;
        }
    }


    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        lowerWall.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds(rect);
        setLowerHitboxBounds(lowerWall);

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
}