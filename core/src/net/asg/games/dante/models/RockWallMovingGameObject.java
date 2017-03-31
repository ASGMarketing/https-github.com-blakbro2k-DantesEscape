package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class RockWallMovingGameObject extends MovingGameObject {
    protected Rectangle lowerWall;
    protected Rectangle lowerHitboxBounds;

    protected int position;
    protected int holeSize;
    public boolean isMobile = false;
    protected int isMovingDown = 0;
    protected int moveSpeed = Constants.WALL_OBJECT_MOVE_SPEED;

    public RockWallMovingGameObject(ImageProvider imageProvider,
                                    TextureRegion[] textureRegions, SoundProvider soundProvider,
                                    int width, int height, boolean isHitboxActive, net.asg.games.dante.states.MovingGameObjectState state,
                                    int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, width, height,
                isHitboxActive, state, hitBoxConfig);

        //this.rect = new Rectangle();
        this.rect.width = width;
        this.rect.height = height;

        position = MathUtils.random(0, 6) * 50;
        holeSize = Constants.MEDIUM_WALL_GAP_SIZE;

        this.lowerWall = new Rectangle();
        this.lowerWall.width = width;
        this.lowerWall.height = height;

        this.rect.x = this.imageProvider.getScreenWidth();
        this.rect.y = Constants.WALL_BASE_OFFSET - position;

        this.lowerWall.x = this.imageProvider.getScreenWidth();
        this.lowerWall.y = Constants.WALL_BASE_OFFSET - rect.height - holeSize - position;

        lowerHitboxBounds = new Rectangle();

        setRectSize(lowerHitboxBounds, hitBoxConfig);
        setHitboxBounds(rect);
        setLowerHitboxBounds(lowerWall);

        this.setAnimationSpeed(0.1f);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureRegions[frame], rect.x, rect.y,rect.width,rect.height);
        batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
    }

    public void drawDebug(ShapeRenderer debugRenderer) {
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        debugRenderer.rect(lowerWall.x, lowerWall.y, lowerWall.width, lowerWall.height);
    }

    public void drawHitbox(ShapeRenderer debugRenderer) {
        debugRenderer.rect(hitboxBounds.x, hitboxBounds.y, hitboxBounds.width, hitboxBounds.height);
        debugRenderer.rect(lowerHitboxBounds.x, lowerHitboxBounds.y, lowerHitboxBounds.width, lowerHitboxBounds.height);
   }

    public void setLowerHitboxBounds(Rectangle rect){
        lowerHitboxBounds.x = lowerWall.x + offSetX;
        lowerHitboxBounds.y = lowerWall.y + offSetY;
    }

    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        lowerWall.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds(rect);
        setLowerHitboxBounds(lowerWall);

        if (isMobile) {
            switch (isMovingDown) {
                case 0:
                    rect.y += Constants.WALL_GAP_SPEED * delta;
                    lowerWall.y += Constants.WALL_GAP_SPEED * delta;
                    if (rect.y + 50 > this.imageProvider.getScreenHeight())
                        isMovingDown = 1;
                    break;
                case 1:
                    rect.y -= Constants.WALL_GAP_SPEED * delta;
                    lowerWall.y -= Constants.WALL_GAP_SPEED * delta;
                    if (lowerWall.y + lowerWall.height - 50 < 0)
                        isMovingDown = 0;
                    break;
            }
            state.setPosX((int) rect.x);
            state.setPosY((int) rect.y);
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
        return hitboxBounds.overlaps(otherRect) || lowerHitboxBounds.overlaps(otherRect);
    }
}
