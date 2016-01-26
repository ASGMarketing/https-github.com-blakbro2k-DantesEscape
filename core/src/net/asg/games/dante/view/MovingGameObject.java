package net.asg.games.dante.view;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import net.asg.games.dante.Constants;
import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.screens.GameScreenState.LevelState;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Blakbro2k This class defines the moving object on the screen of the
 *         game.
 * 
 *         It requires an image provider to handle the textures. It returns an
 *         array of texture regions if the object has animations
 */
public class MovingGameObject {

	protected int moveSpeed = Constants.OBJECT_MOVE_SPEED;

	protected float animationPeriod = Constants.DEFAULT_ANIMATION_PERIOD;

	protected int width;

	protected int height;

	protected Rectangle rect;

	protected Rectangle hitboxes;

	protected int frame = 0;

	protected float time = 0;

	protected boolean isHitboxActive;

	protected TextureRegion[] textureRegions;

	protected ImageProvider imageProvider;

	protected SoundManager soundManager;

	public boolean isCollided = false;

	protected boolean isSoundTriggered = false;

	protected MovingGameObjectState state;

	protected Rectangle getPosition() {
		return rect;
	}	

	public MovingGameObject(ImageProvider imageProvider,
			TextureRegion[] textureRegions, SoundManager soundManager,
			int width, int height, boolean isHitboxActive, MovingGameObjectState state) {

		this.imageProvider = imageProvider;
		this.soundManager = soundManager;
		this.textureRegions = textureRegions;
		this.isHitboxActive = isHitboxActive;
		this.height = height;
		this.width = width;

		rect = new Rectangle();
		rect.width = width;
		rect.height = height;

		this.rect.x = Constants.MAX_WIDTH;
		this.rect.y = 0;

		this.state = state;
		state.setPosX((int) rect.x);
		state.setPosY((int) rect.y);
		state.setCollided(isCollided);
		state.setHitboxActive(isHitboxActive);
		state.setSoundTriggered(isSoundTriggered);
	}

	public void moveLeft(float delta, float speedBonus) {
		rect.x -= moveSpeed * delta * speedBonus;
		state.setPosY((int) rect.y);
		time += delta;
		if (time > animationPeriod) {
			time -= animationPeriod;
			frame += 1;
			if (frame >= textureRegions.length) {
				frame = 0;
			}
		}
	}

	public boolean isLeftOfScreen() {
		return rect.x + width < 0;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(textureRegions[frame], rect.x, rect.y);
	}

	public void drawDebug(ShapeRenderer debugRenderer){
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
	}

	public boolean isOverlapping(Rectangle otherRect) {
		return rect.overlaps(otherRect);
	}

	public boolean isHitboxActive() {
		return isHitboxActive;
	}

	public void setHitboxActive(boolean bool) {
		isHitboxActive = bool;
		state.setHitboxActive(bool);
	}

	public void setMoveSpeed(int moveSpeed){
		this.moveSpeed = moveSpeed;
	}

	public void setAnimationSpeed(float f){
		this.animationPeriod = f;
	}

	public LevelState doCollision(float delta){
		if(isHitboxActive){
			if(!isSoundTriggered){
				soundManager.playDeathSound();
				isSoundTriggered = true;
				state.setSoundTriggered(isSoundTriggered);
			}
			return LevelState.FIREBALLHIT;
		}
		return LevelState.NOLCLIP;
	}

	public MovingGameObjectState getState() {
		return state;
	}
}
