package net.asg.games.dante.screens;

import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.images.ImageProvider;
import net.asg.games.dante.sound.SoundManager;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Blakbro2k
 *
 *	<code>CommonScreen</code> implements <code>Screen</code> and <code>InputProcessor</code>
 *	from libgdx API.  This class has all methods implemented. All other screens will extend this class
 */
public class CommonScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;
    
    protected OrthographicCamera camera;
    
    protected DantesEscapeGame game;
    
    protected ImageProvider imageProvider;
    
    protected SoundManager soundManager;
	
	protected ShapeRenderer debugRenderer;
		   
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
			if (batch != null) {
				batch.dispose();
			}
	}

}
