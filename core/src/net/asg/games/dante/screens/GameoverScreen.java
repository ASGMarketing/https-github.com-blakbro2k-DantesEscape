package net.asg.games.dante.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameoverScreen extends AbstractScreen {


    private Texture gameoverImage;

    public void show() {
        imageProvider = game.getImageProvider();

        gameoverImage = imageProvider.getGameOverBoard();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, imageProvider.getScreenWidth(),
                imageProvider.getScreenHeight());

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(gameoverImage, 0, 0);
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    //backgroundImage = imageProvider.getBackground();
}
