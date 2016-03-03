package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.asg.games.dante.DantesEscapeGame;

/**
 * Created by Blakbro2k on 3/1/2016.
 */
public class SettingsScreen extends AbstractScreen {

    private Stage stage;

    public SettingsScreen(DantesEscapeGame game) {
        super();
        this.game = game;
    }

    @Override
    public void show() {

        imageProvider = game.getImageProvider();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(imageProvider.getScreenWidth(),
                imageProvider.getScreenHeight()));


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        stage.draw();
        batch.end();
    }

}
