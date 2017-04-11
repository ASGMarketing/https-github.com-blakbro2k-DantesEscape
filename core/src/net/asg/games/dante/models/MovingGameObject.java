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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

/**
 * @author Blakbro2k This class defines the moving object on the screen of the
 *         game.
 *
 *         It requires an image provider to handle the textures. It returns an
 *         array of texture regions if the object has animations
 */
public class MovingGameObject implements Pool.Poolable{
    protected int moveSpeed = Constants.OBJECT_MOVE_SPEED;
    protected float animationPeriod = Constants.DEFAULT_ANIMATION_PERIOD;
    protected int width;
    protected int height;
    protected Rectangle rect;
    protected Rectangle hitboxBounds;
    protected int frame = 0;
    protected float time = 0;
    protected boolean isHitboxActive;
    protected TextureRegion[] textureRegions;
    protected ImageProvider imageProvider;
    protected SoundProvider soundProvider;
    public boolean isCollided = false;
    protected boolean isSoundTriggered = false;
    protected MovingGameObjectState state;
    protected int offSetX;
    protected int offSetY;


    public MovingGameObject(ImageProvider imageProvider,
                            TextureRegion[] textureRegions, SoundProvider soundProvider,
                            int width, int height, boolean isHitboxActive, net.asg.games.dante.states.MovingGameObjectState state,
                            int[] hitBoxConfig) {

        this.imageProvider = imageProvider;
        this.soundProvider = soundProvider;
        this.textureRegions = textureRegions;
        this.isHitboxActive = isHitboxActive;
        this.height = height;
        this.width = width;

        rect = new Rectangle();
        rect.width = width;
        rect.height = height;

        this.rect.x = imageProvider.getScreenWidth();
        this.rect.y = 0;

        this.state = state;

        hitboxBounds = new Rectangle();

        this.offSetX = hitBoxConfig[0];
        this.offSetY = hitBoxConfig[1];

        setRectSize(hitboxBounds, hitBoxConfig);
        setHitboxBounds(rect);

        state.setPosX((int) rect.x);
        state.setPosY((int) rect.y);
        state.setHitPosX((int) hitboxBounds.x);
        state.setHitPosY((int) hitboxBounds.y);
        state.setCollided(isCollided);
        state.setHitboxActive(isHitboxActive);
        state.setSoundTriggered(isSoundTriggered);
    }

    public void moveLeft(float delta, float speedBonus) {
        rect.x -= moveSpeed * delta * speedBonus;
        setHitboxBounds(rect);

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

    public void setStatefulPosition(){
        state.setPosY((int) rect.y);
        state.setPosX((int) rect.x);
    }

    public boolean isLeftOfScreen() {
        return rect.x + width < 0;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureRegions[frame], rect.x, rect.y);
    }

    public void drawDebug(ShapeRenderer debugRenderer) {
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
    }

    public void drawHitbox(ShapeRenderer debugRenderer) {
        debugRenderer.rect(hitboxBounds.x, hitboxBounds.y, hitboxBounds.width, hitboxBounds.height);
    }

    public void setHitboxBounds(Rectangle rect){
        hitboxBounds.x = rect.x + offSetX;
        hitboxBounds.y = rect.y + offSetY;
    }

    public boolean isOverlapping(Rectangle otherRect) {
        return hitboxBounds.overlaps(otherRect);
    }

    public void setHitboxActive(boolean bool) {
        isHitboxActive = bool;
        state.setHitboxActive(bool);
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

    public void setRectSize(Rectangle rect, int[] size){
        if(size[2] != -1){
            rect.width = size[2];
        } else {
            rect.width = this.width;
        }

        if(size[3] != -1){
            rect.height = size[3];
        } else {
            rect.height = this.height;
        }
    }
    public MovingGameObjectState getState() {
        return state;
    }

    @Override
    public void reset() {

    }
}
