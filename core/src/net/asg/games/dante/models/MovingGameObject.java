package net.asg.games.dante.models;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.states.GameScreenState.LevelState;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * @author Blakbro2k This class defines the moving object on the screen of the
 *         game.
 *
 *         It requires an image provider to handle the textures. It returns an
 *         array of texture regions if the object has animations
 */
public abstract class MovingGameObject implements Poolable {
    public static final int GAME_OBJECT_X = 0;
    public static final int GAME_OBJECT_Y = 1;
    public static final int GAME_OBJECT_WIDTH = 2;
    public static final int GAME_OBJECT_HEIGHT = 3;

    protected float animationPeriod = Constants.DEFAULT_ANIMATION_PERIOD;
    protected float time = 0;

    protected Rectangle rect;
    protected Rectangle hitboxBounds;
    protected TextureRegion[] textureRegions;
    protected ImageProvider imageProvider;
    protected SoundProvider soundProvider;
    protected MovingGameObjectState state;

    protected boolean isCollided = false;
    protected boolean isSoundTriggered = false;
    protected boolean isHitboxActive;
    protected int offSetX;
    protected int offSetY;
    protected int frame = 0;
    protected int width;
    protected int height;
    protected int moveSpeed = Constants.OBJECT_MOVE_SPEED;

    protected MovingGameObject(ImageProvider imageProvider,
                             TextureRegion[] textureRegions,
                             SoundProvider soundProvider,
                             MovingGameObjectState state,
                             int[] hitBoxConfig) {
        this.imageProvider = imageProvider;
        this.soundProvider = soundProvider;
        this.textureRegions = textureRegions;
        this.state = state;
        initialize(hitBoxConfig);
    }

    public abstract void initialize(int[] hitBoxConfig);

    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds();
        nextAnimationFrame(delta);
        setStatefulPosition();
    }

    public void nextAnimationFrame(float delta){
        time += delta;
        if (time > animationPeriod) {
            time -= animationPeriod;
            frame += 1;
            if (frame >= textureRegions.length) {
                frame = 0;
            }
        }
    }

    public void setStatefulPosition(){
        state.setPosX((int) rect.x);
        state.setPosY((int) rect.y);
        state.setHitPosX((int) hitboxBounds.x);
        state.setHitPosY((int) hitboxBounds.y);
        state.setCollided(isCollided);
        state.setHitboxActive(isHitboxActive);
        state.setSoundTriggered(isSoundTriggered);
    }

    public boolean isLeftOfScreen() {
        return rect.x + rect.width < 0;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureRegions[frame], rect.x, rect.y);
    }

    public void drawDebug(ShapeRenderer debugRenderer) {
        debugRenderer.set(ShapeType.Line);
        debugRenderer.setColor(Color.GREEN);
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        if(isHitboxActive) {
            debugRenderer.set(ShapeType.Filled);
            debugRenderer.setColor(Color.RED);
            debugRenderer.rect(hitboxBounds.x, hitboxBounds.y, hitboxBounds.width, hitboxBounds.height);
        }
    }

    public void setHitboxBounds(){
        hitboxBounds.x = rect.x + offSetX;
        hitboxBounds.y = rect.y + offSetY;
    }

    public void setHitboxActive(boolean bool) {
        isHitboxActive = bool;
        state.setHitboxActive(bool);
    }

    public boolean isOverlapping(Rectangle otherRect) {
        return hitboxBounds.overlaps(otherRect);
    }
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setAnimationSpeed(float f) {
        this.animationPeriod = f;
    }

    public LevelState doCollision(float delta) {
        if (isHitboxActive) {
            if (!isSoundTriggered) {
                soundProvider.playDeathSound();
                isSoundTriggered = true;
                state.setSoundTriggered(isSoundTriggered);
            }
            return LevelState.FIREBALLHIT;
        }
        return LevelState.NOLCLIP;
    }

    /**
     * Given a {@code Rectangle} sets the width in accordance with the
     * @param rect
     * @param size
     */
    public void setRectSize(Rectangle rect, int[] size){
        if(size[GAME_OBJECT_WIDTH] != -1){
            rect.width = size[GAME_OBJECT_WIDTH];
        } else {
            rect.width = this.rect.width;
        }

        if(size[GAME_OBJECT_HEIGHT] != -1){
            rect.height = size[GAME_OBJECT_HEIGHT];
        } else {
            rect.height = this.rect.height;
        }
    }
    public MovingGameObjectState getState() {
        return state;
    }

    public Rectangle getPosition() {
        return rect;
    }

    public boolean isCollided(){
        return isCollided;
    }

    public void setCollided(boolean isCollided){
        this.isCollided = isCollided;
    }

    @Override
    public abstract void reset();

    public abstract void fireSound();

    public void setObjectPosition(int x, int y){
        this.rect.x = x;
        this.rect.y = y;
        keepOnScreen();
    }

    private void keepOnScreen() {
        int screenHeight = imageProvider.getScreenHeight();

        if (rect.y < 0) {
            rect.y = 0;
        }

        if (rect.y + height > screenHeight) {
            rect.y = screenHeight - height;
        }
        setHitboxBounds();
    }
}
