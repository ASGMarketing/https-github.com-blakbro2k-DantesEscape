package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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

        Table table = new Table();
        table.setPosition(imageProvider.getScreenWidth() / 2, imageProvider.getScreenHeight() / 4);

        Label.LabelStyle headingStyle = new Label.LabelStyle(imageProvider.getRavieFont(), Color.RED);
        Label headingLabel = new Label("DANTE'S ESCAPE",headingStyle);

        final TextButton backGameButton = new TextButton("Back",imageProvider.getDefaultUIskin());
        final Label musicLabel = new Label("Music",imageProvider.getDefaultUIskin());
        final Label soundFXlabel = new Label("Sound Effects",imageProvider.getDefaultUIskin());
        final TextButton googleGameButton = new TextButton("Sign In",imageProvider.getDefaultUIskin());
        final TextButton creditsGameButton = new TextButton("Credits",imageProvider.getDefaultUIskin());

        // TODO: 3/4/2016 back buttong with settings title
        // TODO: 3/4/2016 music slider
        // TODO: 3/4/2016 sound effects slider
        // TODO: 3/4/2016 Google Sign in
        // TODO: 3/4/2016 credits

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
