package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.asg.games.dante.DantesEscapeGame;

public class MainMenuScreen extends AbstractScreen {
    private Stage stage;

    public MainMenuScreen(DantesEscapeGame game) {
        // TODO Auto-generated constructor stub
        super();
        this.game = game;
    }

    @Override
    public void show() {
        //super.show();
        imageProvider = game.getImageProvider();

        batch = new SpriteBatch();

        stage = new Stage(new StretchViewport(imageProvider.getScreenWidth(),
                imageProvider.getScreenHeight()));

        Table table = new Table();
        Table heading = new Table();

        table.setPosition(imageProvider.getScreenWidth() / 2, imageProvider.getScreenHeight() / 4);
        heading.setPosition(imageProvider.getScreenWidth() / 2, imageProvider.getScreenHeight() - 100);

        Label.LabelStyle headingStyle = new Label.LabelStyle(imageProvider.getRavieFont(), Color.RED);
        Label headingLabel = new Label("DANTE'S ESCAPE",headingStyle);
        heading.add(headingLabel);

        stage.addActor(heading);
        stage.addActor(table);

        final ImageButton playGameButton = new ImageButton(imageProvider.getPlayButtonStyle());
        final ImageButton quitGameButton = new ImageButton(imageProvider.getExitButtonStyle());
        final ImageButton settingsGameButton = new ImageButton(imageProvider.getConfigButtonStyle());

        table.add(playGameButton).colspan(2);
        table.row();
        table.add(settingsGameButton);
        table.add(quitGameButton);

        playGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gotoGameScreen();
            }
        });

        quitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // stage.addActor(quitGameButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //camera.update();
        //batch.setProjectionMatrix(camera.combined);

        batch.begin();
        stage.draw();
        batch.end();
    }
}
