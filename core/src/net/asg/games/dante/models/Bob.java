package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bob {
    private int width;

    private int height;

    private int screenWidth;

    private int screenHeight;

    protected int frame = 0;

    protected float time = 0;

    protected TextureRegion[] textureRegions;

    private Rectangle bounds;

    private Rectangle hitboxBounds;
    // TODO: 3/2/2016 Make an array of hitboxes

    private int offSetX;

    private int offSetY;

    public Bob(int posX, int posY, int height, int width,
               int offSetX, int offSetY, int hitHeight, int hitWidth) {
        // TODO: 3/2/2016 pass in an array of hitboxes
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
        textureRegions = new TextureRegion[30];

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

    public Rectangle getHitboxes() {
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

    public void moveX(float pixels, float delta) {
        bounds.x += pixels * Constants.BOB_MOVE_SPEED * delta;
        hitboxBounds.x = bounds.x + offSetX;
        keepOnScreen();
    }

    public void moveY(float pixels, float delta) {
        bounds.y += pixels * Constants.BOB_MOVE_SPEED * delta;
        hitboxBounds.y = bounds.y + offSetY;
        keepOnScreen();
    }

    public TextureRegion getBobFrame(float delta, float speedBonus){
        time += delta * speedBonus;
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
        textureRegions[0] = imageProvider.getBob(1);
        textureRegions[1] = imageProvider.getBob(2);
        textureRegions[2] = imageProvider.getBob(3);
        textureRegions[3] = imageProvider.getBob(4);
        textureRegions[4] = imageProvider.getBob(5);
        textureRegions[5] = imageProvider.getBob(6);
        textureRegions[6] = imageProvider.getBob(7);
        textureRegions[7] = imageProvider.getBob(8);
        textureRegions[8] = imageProvider.getBob(9);
        textureRegions[9] = imageProvider.getBob(10);
        textureRegions[10] = imageProvider.getBob(11);
        textureRegions[11] = imageProvider.getBob(12);
        textureRegions[12] = imageProvider.getBob(13);
        textureRegions[13] = imageProvider.getBob(14);
        textureRegions[14] = imageProvider.getBob(15);
        textureRegions[15] = imageProvider.getBob(16);
        textureRegions[16] = imageProvider.getBob(17);
        textureRegions[17] = imageProvider.getBob(18);
        textureRegions[18] = imageProvider.getBob(19);
        textureRegions[19] = imageProvider.getBob(20);
        textureRegions[20] = imageProvider.getBob(21);
        textureRegions[21] = imageProvider.getBob(22);
        textureRegions[22] = imageProvider.getBob(23);
        textureRegions[23] = imageProvider.getBob(24);
        textureRegions[24] = imageProvider.getBob(25);
        textureRegions[25] = imageProvider.getBob(26);
        textureRegions[26] = imageProvider.getBob(27);
        textureRegions[27] = imageProvider.getBob(28);
        textureRegions[28] = imageProvider.getBob(29);
        textureRegions[29] = imageProvider.getBob(30);
    }
}
