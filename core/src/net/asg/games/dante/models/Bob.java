package net.asg.games.dante.models;

import net.asg.games.dante.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Bob {

    private int SPEED = Constants.BOB_MOVE_SPEED;

    private int width;

    private int height;

    private int screenWidth;

    private int screenHeight;

    private Rectangle bounds;

    private Rectangle hitboxBounds;

    private int offSetX;

    private int offSetY;

    public Bob(int posX, int posY, int height, int width,
               int offSetX, int offSetY, int hitHeight, int hitWidth) {
        bounds = new Rectangle();
        hitboxBounds = new Rectangle();

        this.screenWidth = Constants.GAME_WIDTH;
        this.screenHeight = Constants.GAME_HEIGHT;
        this.width = width;
        this.height = height;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        bounds.width = width;
        bounds.height = height;
        //Gdx.app.log(this.toString(), "X: " + posX + ", Y: " + posY);

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

        hitboxBounds.width = hitWidth;
        hitboxBounds.height = hitHeight;
        hitboxBounds.x = bounds.x + offSetX;
        hitboxBounds.y = bounds.y + offSetY;
    }

    public void setPositionX(float x) {
        if (x < 0) {
            bounds.x = (screenWidth / 16);
            hitboxBounds.x = bounds.x + offSetX;
        } else {
            bounds.x = x;
            hitboxBounds.x = bounds.x + offSetX;
        }

    }

    public void setPositionY(float y) {
        if (y < 0) {
            bounds.y = (screenHeight / 2) - (bounds.height / 2);
            hitboxBounds.y = bounds.y + offSetY;
        } else {
            bounds.y = y;
            hitboxBounds.y = bounds.y + offSetY;
        }
    }

    public Rectangle getPosition() {
        return bounds;
    }

    public Rectangle getHitbox() {
        return hitboxBounds;
    }

    private void keepOnScreen() {
        if (bounds.y < 0) {
            bounds.y = 0;
            hitboxBounds.y = bounds.y + offSetY;
        } else if (bounds.y + height > screenHeight) {
            bounds.y = screenHeight - height;
            hitboxBounds.y = bounds.y + offSetY;
        }
        if (bounds.x < 0) {
            bounds.x = 0;
            hitboxBounds.x = bounds.x + offSetX;
        } else if (bounds.x + width > screenWidth) {
            bounds.x = screenWidth - width;
            hitboxBounds.x = bounds.x + offSetX;
        }
    }

    public void moveX(float speedRatio, float delta) {
        bounds.x += speedRatio * SPEED * delta;
        hitboxBounds.x = bounds.x + offSetX;
        keepOnScreen();
    }

    public void moveY(float speedRatio, float delta) {
        bounds.y += speedRatio * SPEED * delta;
        hitboxBounds.y = bounds.y + offSetY;
        keepOnScreen();
    }

    public void setBounds(int width, int height) {
        bounds.setSize(width, height);
    }
}
