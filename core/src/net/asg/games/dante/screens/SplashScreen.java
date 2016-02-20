package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.asg.games.dante.DantesEscapeGame;

/**
 * Created by Blakbro2k on 2/4/2016.
 */
public class SplashScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Texture splashScreenTexture;

    public SplashScreen(DantesEscapeGame game) {
        super();
        batch = new SpriteBatch();
        splashScreenTexture = game.getImageProvider().getSplashScreen();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(splashScreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        splashScreenTexture.dispose();
        batch.dispose();
    }
}
