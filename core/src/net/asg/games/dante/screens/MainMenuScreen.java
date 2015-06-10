package net.asg.games.dante.screens;

import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.images.ImageProvider;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class MainMenuScreen implements Screen, InputProcessor {
	
	private DantesEscapeGame game;
	
	private OrthographicCamera camera;
	
	private ImageProvider imageProvider;
	
    private Button [] buttons;
    private Button helpButton;
	
	public MainMenuScreen(DantesEscapeGame game) {
		// TODO Auto-generated constructor stub
    	super();
    	this.game = game;
	}

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
		
        buttons = new Button [2];
        //buttons[0] = new Button(helpButton, null);
        //buttons[0] = new Button(buttonBg, imageProvider.getStart());
        //buttons[1] = new Button(buttonBg, imageProvider.getKids());
        //buttons[2] = new Button(buttonBg, imageProvider.getScores());

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
		// TODO Auto-generated method stub

	}

}
