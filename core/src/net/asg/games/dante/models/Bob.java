package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Bob {
    private int width;
    private int height;
    private int screenWidth;
    private int screenHeight;
    private int frame = 0;
    private float time = 0;
    private TextureRegion[] textureRegions;
    private Rectangle bounds;
    private Rectangle hitBounds;
    private int offSetX;
    private int offSetY;

    public Bob(int posX, int posY, int width, int height, int[] hitboxConfig) {
        bounds = new Rectangle();
        hitBounds = new Rectangle();

        this.screenWidth = Constants.GAME_WIDTH;
        this.screenHeight = Constants.GAME_HEIGHT;
        this.width = width;
        this.height = height;
        this.offSetX = hitboxConfig[MovingGameObject.GAME_OBJECT_X];
        this.offSetY = hitboxConfig[MovingGameObject.GAME_OBJECT_Y];
        bounds.width = width;
        bounds.height = height;
        textureRegions = new TextureRegion[Constants.BOB_TOTAL_ANIMATION_FRAMES];

        if (posX < 0) {
            bounds.x = (screenWidth / 16);
        } else {
            bounds.x = posX;
        }

        if (posY < 0) {
            bounds.y = (screenHeight / 2) - (bounds.height / 2);
        } else {
            bounds.y = posY;
        }

        hitBounds.width = hitboxConfig[MovingGameObject.GAME_OBJECT_WIDTH];
        hitBounds.height = hitboxConfig[MovingGameObject.GAME_OBJECT_HEIGHT];
        hitBounds.x = bounds.x + offSetX;
        hitBounds.y = bounds.y + offSetY;

    }

    public void setPositionX(float x) {
        if (x < 0) {
            bounds.x = (screenWidth / 16);
            hitBounds.x = bounds.x + offSetX;
        } else {
            bounds.x = x;
            hitBounds.x = bounds.x + offSetX;
        }

    }

    public void setPositionY(float y) {
        if (y < 0) {
            bounds.y = (screenHeight / 2) - (bounds.height / 2);
            hitBounds.y = bounds.y + offSetY;
        } else {
            bounds.y = y;
            hitBounds.y = bounds.y + offSetY;
        }
    }

    public Rectangle getPosition() {
        return bounds;
    }

    public Rectangle getHitboxes() {
        return hitBounds;
    }

    private void keepOnScreen() {
        if (bounds.y < 0) {
            bounds.y = 0;
            hitBounds.y = bounds.y + offSetY;
        } else if (bounds.y + height > screenHeight) {
            bounds.y = screenHeight - height;
            hitBounds.y = bounds.y + offSetY;
        }
        if (bounds.x < 0) {
            bounds.x = 0;
            hitBounds.x = bounds.x + offSetX;
        } else if (bounds.x + width > screenWidth) {
            bounds.x = screenWidth - width;
            hitBounds.x = bounds.x + offSetX;
        }
    }

    public void moveX(float pixels, float delta) {
        bounds.x += pixels * Constants.BOB_MOVE_SPEED * delta;
        hitBounds.x = bounds.x + offSetX;
        keepOnScreen();
    }

    public void moveY(float pixels, float delta) {
        bounds.y += pixels * Constants.BOB_MOVE_SPEED * delta;
        hitBounds.y = bounds.y + offSetY;
        keepOnScreen();
    }

    public TextureRegion getBobFrame(float delta, float speedBonus){
        time += delta * 1;
        if (time > Constants.DEFAULT_ANIMATION_PERIOD) {
            time -= Constants.DEFAULT_ANIMATION_PERIOD;
            frame += 1;
            if (frame >= textureRegions.length) {
                frame = 0;
            }
        }
        return textureRegions[frame];
    }

    public void setBobAnimation(ImageProvider imageProvider) {
        imageProvider.setAnimations(textureRegions, ImageProvider.BOB_ID);
    }

    public void drawDebug(ShapeRenderer debugRenderer) {
        debugRenderer.set(ShapeType.Line);
        debugRenderer.setColor(Color.GREEN);
        debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        debugRenderer.set(ShapeType.Filled);
        debugRenderer.setColor(Color.RED);
        debugRenderer.rect(hitBounds.x, hitBounds.y, hitBounds.width, hitBounds.height);
    }
}
