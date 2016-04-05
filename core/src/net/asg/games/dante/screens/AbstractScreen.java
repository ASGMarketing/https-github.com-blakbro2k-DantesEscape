package net.asg.games.dante.screens;

import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Blakbro2k
 *         <p/>
 *         <code>AbstractScreen</code> implements <code>Screen</code> and <code>InputProcessor</code>
 *         from libgdx API.  This class has all methods implemented. All other screens will extend this class
 */
public class AbstractScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    protected OrthographicCamera camera;

    protected DantesEscapeGame game;

    protected ImageProvider imageProvider;

    protected SoundProvider soundProvider;

    protected ShapeRenderer debugRenderer;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
    }

}
