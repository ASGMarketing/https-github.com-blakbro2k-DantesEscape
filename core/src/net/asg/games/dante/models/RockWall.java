package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

public class RockWall extends MovingGameObject {
    public static final int HARD_GAP_SIZE = 100;
    public static final int NORMAL_GAP_SIZE = 150;
    public static final int EASY_GAP_SIZE = 200;
    public static final int WALL_POSITION_ONE = 0;
    public static final int WALL_POSITION_TWO = 50;
    public static final int WALL_POSITION_THREE = 150;
    public static final int WALL_POSITION_FOUR = 200;
    public static final int WALL_POSITION_FIVE = 250;
    public static final int WALL_POSITION_SIX = 300;
    public static final int WALL_BASE_OFFSET = 450;

    protected Rectangle lowerWall;
    protected Rectangle lowerHitboxBounds;

    protected int position;
    protected int holeSize;
    protected int moveSpeed = Constants.WALL_OBJECT_MOVE_SPEED;

    public RockWall(ImageProvider imageProvider,
                    TextureRegion[] textureRegions,
                    SoundProvider soundProvider,
                    MovingGameObjectState state,
                    int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);

        lowerWall = new Rectangle();
        lowerHitboxBounds = new Rectangle();

        int rectConfig[] = {0,0,textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight()};

        setRectSize(rect, rectConfig);
        setRectSize(lowerWall, rectConfig);
        setRectSize(hitboxBounds, hitBoxConfig);
        setRectSize(lowerHitboxBounds, hitBoxConfig);
        setHitboxBounds();

        this.setAnimationSpeed(0.1f);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureRegions[frame], rect.x, rect.y, rect.width, rect.height);
        batch.draw(textureRegions[frame], lowerWall.x, lowerWall.y);
    }

    public void drawDebug(ShapeRenderer debugRenderer) {
        debugRenderer.set(ShapeType.Line);
        debugRenderer.setColor(Color.GREEN);
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        debugRenderer.rect(lowerWall.x, lowerWall.y, lowerWall.width, lowerWall.height);
        drawHitbox(debugRenderer);
    }

    public void drawHitbox(ShapeRenderer debugRenderer) {
        debugRenderer.set(ShapeType.Filled);
        debugRenderer.setColor(Color.RED);
        debugRenderer.rect(hitboxBounds.x, hitboxBounds.y, hitboxBounds.width, hitboxBounds.height);
        debugRenderer.rect(lowerHitboxBounds.x, lowerHitboxBounds.y, lowerHitboxBounds.width, lowerHitboxBounds.height);
    }

    public void setHitboxBounds() {
        hitboxBounds.x = rect.x + offSetX;
        hitboxBounds.y = rect.y + offSetY;
        lowerHitboxBounds.x = lowerWall.x + offSetX;
        lowerHitboxBounds.y = lowerWall.y + offSetY;
    }

    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        lowerWall.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds();

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

    public boolean isOverlapping(Rectangle otherRect) {
        return hitboxBounds.overlaps(otherRect) || lowerHitboxBounds.overlaps(otherRect);
    }

    public void setObjectPosition(int x, int y){
        this.rect.x = this.imageProvider.getScreenWidth();
        this.rect.y = WALL_BASE_OFFSET - position;

        this.lowerWall.x = this.imageProvider.getScreenWidth();
        this.lowerWall.y = WALL_BASE_OFFSET - rect.height - holeSize - position;

        rect.x = lowerWall.x = x;
        rect.y = g;
        lowerWall.y = y;
        setHitboxBounds();
    }

    public void setHolePosition(int position){
        this.position = position;
    }

    public void setHoleSize(int holeSize){
        this.holeSize = holeSize;
    }
    public String toString() {
        return "RockWall: \n" +
                "Upper Wall: " + rect + "\n" +
                "Lower Wall: " + lowerWall + "\n" +
                "position: " + position + "\n" +
                "holeSize: " + holeSize + "\n" +
                "moveSpeed: " + moveSpeed + "\n";
    }
}
