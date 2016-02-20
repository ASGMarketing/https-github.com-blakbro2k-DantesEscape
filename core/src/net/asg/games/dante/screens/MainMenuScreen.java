package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.games.dante.DantesEscapeGame;

public class MainMenuScreen extends AbstractScreen {
    //private Button[] buttons;
    //private Button helpButton;

    //private boolean soundOn;
    private Skin skin;
    private Stage stage;


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

        batch = new SpriteBatch();

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        //Gdx.app.log("Skin",skin.toString());
        stage = new Stage();

        final TextButton newGameButton = new TextButton("New Game", skin, "default");
        final TextButton quitGameButton = new TextButton("Quit App", skin, "default");

        newGameButton.setWidth(200f);
        newGameButton.setHeight(200f);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

        quitGameButton.setWidth(200f);
        quitGameButton.setHeight(200f);
        quitGameButton.setPosition(newGameButton.getX(), newGameButton.getY() + 400f);

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gotoGameScreen();
            }
        });

        quitGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        stage.addActor(newGameButton);
        stage.addActor(quitGameButton);

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
