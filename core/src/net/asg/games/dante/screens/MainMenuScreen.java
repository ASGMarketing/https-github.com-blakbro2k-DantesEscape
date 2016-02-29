package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.asg.games.dante.DantesEscapeGame;

public class MainMenuScreen extends AbstractScreen {
    //private Button[] buttons;
    //private Button helpButton;

    //private boolean soundOn;
    private Skin skin;
    private Stage stage;
    private Table table;


    public MainMenuScreen(DantesEscapeGame game) {
        // TODO Auto-generated constructor stub
        super();
        this.game = game;

        //soundOn = State.isSoundOn();
        //game.getSoundManager().setSoundOn(soundOn);
    }

    @Override
    public void show() {
        //super.show();
        imageProvider = game.getImageProvider();

        batch = new SpriteBatch();

        skin = imageProvider.getDefaultButtonSkin();
        //skin = imageProvider.getDefaultUIskin();

        //Gdx.app.log("Skin",skin.toString());
        stage = new Stage(new ScreenViewport(new OrthographicCamera(imageProvider.getScreenWidth(),
                imageProvider.getScreenHeight())));

        table = new Table();
        //table.debug();      // Turn on all debug lines (table, cell, and widget).
        //table.defaults().expand().fill().padBottom(4f);
        //table.align(Align.center | Align.top);
        table.setPosition(imageProvider.getScreenWidth() / 2,imageProvider.getScreenHeight() / 2);
        //table.setX(400f);
        //table.setY(imageProvider.getScreenHeight() / 2);
        //table.setPosition(0, imageProvider.getScreenHeight());

        stage.addActor(table);

        final ImageButton playGameButton = new ImageButton(imageProvider.getPlayButtonStyle());
        final ImageButton quitGameButton = new ImageButton(imageProvider.getExitButtonStyle());
        final ImageButton settingsGameButton = new ImageButton(imageProvider.getConfigButtonStyle());

        table.add(playGameButton).colspan(2);
        table.row();
        table.add(settingsGameButton);
        table.add(quitGameButton);

       // playGameButton.setWidth(200f);
        //playGameButton.setHeight(100f);
        //playGameButton.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

        //quitGameButton.setWidth(200f);
        //quitGameButton.setHeight(100f);
        //quitGameButton.setPosition(playGameButton.getX(), playGameButton.getY() + 200f);

        //settingsGameButton.setWidth(200f);
        //settingsGameButton.setHeight(100f);
        //settingsGameButton.setPosition(playGameButton.getX(), playGameButton.getY() + 200f);

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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        stage.draw();
        batch.end();
    }
}
