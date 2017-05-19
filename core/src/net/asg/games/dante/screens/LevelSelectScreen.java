package net.asg.games.dante.screens;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.models.Button;

/**
 * @author Blakbro2k
 */
public class LevelSelectScreen extends AbstractScreen {

    private Texture backgroundImage;

    public LevelSelectScreen(DantesEscapeGame game) {
        super();
        this.game = game;
    }

    @Override
    public void show() {
        imageProvider = game.getImageProvider();
        //backgroundImage = imageProvider.getBackgroundFire();
        //TextureRegion buttonBg = imageProvider.getButton();

        Button[] buttons = new Button[2];
        //buttons[0] = new Button(buttonBg, imageProvider.getLevel());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, imageProvider.getScreenWidth(), imageProvider.getScreenHeight());
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;

    }


}
