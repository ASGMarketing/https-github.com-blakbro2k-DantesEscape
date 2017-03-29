package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.asg.games.dante.DantesEscapeGame;

/**
 * Created by Blakbro2k on 2/4/2016.
 */
public class SplashScreen extends AbstractScreen {
    private Stage stage;
    private SpriteBatch batch;
    private Texture splashScreenTexture;

    public SplashScreen(DantesEscapeGame game) {
        super();
        this.game = game;
    }

    public void show(){
        imageProvider = game.getImageProvider();

        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(imageProvider.getScreenWidth(),imageProvider.getScreenHeight()));

        // Load the splash image
        splashScreenTexture = game.getImageProvider().getSplashScreen();
        splashScreenTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Image backgroundImage = new Image(splashScreenTexture);
        backgroundImage.setFillParent(true);
        backgroundImage.setScaling(Scaling.fillY);

        // Add splash image to the screen
        stage.addActor(backgroundImage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        stage.draw();
        //batch.draw(splashScreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        splashScreenTexture.dispose();
        batch.dispose();
    }
}
