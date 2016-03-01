package net.asg.games.dante.models;

import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.layout.Layout;
import net.asg.games.dante.layout.LayoutItem;
import net.asg.games.dante.layout.LayoutRow;
import net.asg.games.dante.layout.Margin;
import net.asg.games.dante.layout.SimpleLayoutManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class GameOverMessage {

    private Texture board;

    private TextureRegion gameOverLabel;

    private TextureRegion startLabel;

    private int boardX;

    private int boardY;

    private LayoutItem startLayoutItem;

    private LayoutItem youLoseLayoutItem;

    private SimpleLayoutManager simpleLayoutManager;

    public GameOverMessage(ImageProvider imageProvider, int score) {
        this.board = imageProvider.getGameOverBoard();

        gameOverLabel = imageProvider.getYouLoseLabel();

        startLabel = imageProvider.getStartLabel();

        int screenWidth = imageProvider.getScreenWidth();
        int screenHeight = imageProvider.getScreenHeight();

        boardX = (screenWidth - board.getWidth()) / 2;
        boardY = (screenHeight - board.getHeight()) / 2;

        int boardTopPadding = 50;
        int boardBottomPadding = 150;

        simpleLayoutManager = new SimpleLayoutManager(board.getWidth(),
                board.getHeight(), boardX, boardY);

        youLoseLayoutItem = new LayoutItem(gameOverLabel);
        simpleLayoutManager.add(youLoseLayoutItem,
                Layout.CENTER_HORIZONTAL | Layout.ALIGN_BOTTOM,
                new Margin(0, 0, 0, boardTopPadding));

/*        startLayoutItem = new LayoutItem(startLabel);
        simpleLayoutManager.add(startLayoutItem,
                Layout.CENTER_HORIZONTAL | Layout.ALIGN_BOTTOM,
                new Margin(0, 0, 0, boardBottomPadding));*/
    }

    public void draw(SpriteBatch batch) {
        batch.draw(board, boardX, boardY);
        simpleLayoutManager.draw(batch);
    }

    public boolean isStartPressed(Vector3 touchPos) {
        return startLayoutItem.isPressed(touchPos);
    }
}