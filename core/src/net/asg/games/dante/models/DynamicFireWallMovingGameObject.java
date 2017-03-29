package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class DynamicFireWallMovingGameObject extends MovingGameObject {
    protected Rectangle lowerWall;

    private int position;
    private boolean isClosingType;
    protected int moveSpeed = Constants.WALL_OBJECT_MOVE_SPEED;

    public DynamicFireWallMovingGameObject(ImageProvider imageProvider,
                                           TextureRegion[] textureRegions, SoundProvider soundProvider,
                                           int width, int height, boolean isHitboxActive, MovingGameObjectState state,
                                           int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, width, height,
                isHitboxActive, state, hitBoxConfig);

        isClosingType = MathUtils.random(0, 1) == 0;

        //isClosingType = true;
        position = MathUtils.random(1, 4) * 50;

        //position = 5 * 50;

        this.rect = new Rectangle();
        this.rect.width = width;
        this.rect.height = height;

        this.lowerWall = new Rectangle();
        this.lowerWall.width = width;
        this.lowerWall.height = height;

        this.rect.x = this.imageProvider.getScreenWidth();
        this.rect.y = Constants.WALL_BASE_OFFSET - position;

        this.lowerWall.x = this.imageProvider.getScreenWidth();
        this.lowerWall.y = Constants.WALL_BASE_OFFSET - rect.height - position;

        //this.setAnimationSpeed(0.2f);

        setWallAsClosingType(isClosingType);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureRegions[frame], rect.x, rect.y);
        batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
    }

    public void drawDebug(ShapeRenderer debugRenderer) {
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        debugRenderer.rect(lowerWall.x, lowerWall.y, lowerWall.width,
                lowerWall.height);
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
    }

    public boolean isOverlapping(Rectangle otherRect) {
        return rect.overlaps(otherRect) || lowerWall.overlaps(otherRect);
    }
}
