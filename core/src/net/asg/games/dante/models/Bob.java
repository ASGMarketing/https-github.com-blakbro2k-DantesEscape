package net.asg.games.dante.models;

import net.asg.games.dante.Constants;

import com.badlogic.gdx.math.Rectangle;

public class Bob {

	private int SPEED = Constants.BOB_MOVE_SPEED;

	private int width;

	private int height;

	private int screenWidth;

	private int screenHeight;

	private Rectangle bounds;

	private Rectangle[] hitboxes;

	public Bob(int posX, int posY, int height, int width) {
		bounds = new Rectangle();
        hitboxes = new Rectangle[0];

		this.screenWidth = Constants.MAX_WIDTH;
		this.screenHeight = Constants.MAX_HEIGHT;
		this.width = width;
		this.height = height;

		if (posX < 0) {
			bounds.x = screenWidth / 2 - width / 2;
		}

		else {
			bounds.x = posX;
		}

		if (posY < 0) {
			bounds.y = screenHeight / 2 - height / 2;
		}

		else {
			bounds.y = posX;
		}

		// bounds.x = 20;
		bounds.width = width;
		bounds.height = height;
	}

	public void setPositionX(float x) {
		bounds.x = x - width/2;

		keepOnScreen();
	}

	public void setPositionY(float y) {
		if (y < 0)
			y = screenHeight / 2;
		bounds.y = y - height/2;

		keepOnScreen();
	}

	public Rectangle getPosition() {
		return bounds;
	}

	private void keepOnScreen() {
		if (bounds.y < 0) {
			bounds.y = 0;
		}
		else if (bounds.y + height> screenHeight) {
			bounds.y = screenHeight - height;
		}
		if (bounds.x < 0) {
			bounds.x = 0;
		}
		else if (bounds.x + width > screenWidth) {
			bounds.x = screenWidth - width;
		}
	}

	public void moveX(float speedRatio, float delta) {
		bounds.x += speedRatio * SPEED * delta;

		keepOnScreen();
	}

	public void moveY(float speedRatio, float delta) {
		bounds.y += speedRatio * SPEED * delta;

		keepOnScreen();
	}

	public void setBounds(int width, int height){
		bounds.setSize(width, height);
	}
}
