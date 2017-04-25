package net.asg.games.dante.models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import net.asg.games.dante.Constants;
import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.LevelProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.GameScreenState;

import java.util.Iterator;

/**
 * Created by Blakbro2k on 3/25/2017.
 */

public class GameWorld {
    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private LevelProvider levelProvider;
    private MovingGameObjectPool gameObjectsPool;

    private Texture pauseScreen;

    private Sprite backgroundSprite;

    private Sprite foregroundSprite;

    private Sprite middleGroundSprite;

    private Bob bob;

    private Button levelResetButton;

    private Button homeButton;

    private float bgScrollTimer;

    private float mgScrollTimer;

    private float fgScrollTimer;

    private MovingGameObjectFactory movingGameObjectFactory;

    private Array<MovingGameObject> movingObjects;


    private String scoreName;

    private BitmapFont bitmapFontName;

    public GameWorld (DantesEscapeGame game){
        imageProvider = game.getImageProvider();
        soundProvider = game.getSoundProvider();
        levelProvider = game.getLevelProvider();
        movingGameObjectFactory = new MovingGameObjectFactory(game);

        initialize();
    }

    private void setUpBackground(){

    }

    public void initialize() {
        backgroundSprite = imageProvider.getBackgroundSprite();
        foregroundSprite = imageProvider.getForegroundSprite();
        middleGroundSprite = imageProvider.getMiddlegroundSprite();

        //Our Hero's Sprite
        TextureRegion bobTexture = imageProvider.getBob(1);

        //Our Hero object
        bob = new Bob(-1, -1, bobTexture.getRegionWidth(), bobTexture.getRegionHeight(), Constants.BOB_HITBOX);
        bob.setBobAnimation(imageProvider);

        movingObjects = new Array<MovingGameObject>();

        scoreName = Constants.STARTING_SCORE_LABEL;
        bitmapFontName = new BitmapFont();

        pauseScreen = imageProvider.getPauseScreen();

        homeButton = new Button(imageProvider.getHomeButton());
        homeButton.setPos(imageProvider.getScreenWidth() / 2 + 20, imageProvider.getScreenHeight() / 2 - 60);

        levelResetButton = new Button(imageProvider.getResetButton());
        levelResetButton.setPos(imageProvider.getScreenWidth() / 2 - 90, imageProvider.getScreenHeight() / 2 - 60);

        soundProvider.setSound(true);
        soundProvider.playBgSound();
    }

    public void toggleBGMusic(){

    }

    public Bob getBob(){
        return bob;
    }

    public Iterator<MovingGameObject> getMovingObjects(){
        return movingObjects.iterator();
    }

    public void updateWorld(long runTime, float delta, GameScreenState gameScreenState){
        updateBackground(gameScreenState, delta);

        updateScore(gameScreenState, delta);

        spawnNextObject(runTime, gameScreenState);

        /*
         * Using Iterator, we update all objects on screen to move, and discard
	     * all objects off screen All hit detection happens here also
		 */
        Iterator<MovingGameObject> iter = movingObjects.iterator();
        while (iter.hasNext()) {
            MovingGameObject fo = iter.next();

            fo.moveLeft(delta, gameScreenState.gameSpeed);

            if (fo.isLeftOfScreen()) {
                iter.remove();
            }

            if (fo.isOverlapping(bob.getHitboxes())) {
                fo.isCollided = true;
            }
        }
    }

    private void updateBackground(GameScreenState gameScreenState, float delta){
        //Move background
        if (bgScrollTimer > 1.0f)
            bgScrollTimer = 0.0f;

        if (mgScrollTimer > 1.0f)
            mgScrollTimer = 0.0f;

        if (fgScrollTimer > 1.0f)
            fgScrollTimer = 0.0f;

        backgroundSprite.setU(bgScrollTimer);
        backgroundSprite.setU2(bgScrollTimer + 1);

        middleGroundSprite.setU(mgScrollTimer);
        middleGroundSprite.setU2(mgScrollTimer + 1);

        foregroundSprite.setU(fgScrollTimer);
        foregroundSprite.setU2(fgScrollTimer + 1);

        bgScrollTimer += delta * gameScreenState.getBackgroundSpeed();
        mgScrollTimer += delta * gameScreenState.getMiddlegroundSpeed();
        fgScrollTimer += delta * gameScreenState.getForegroundSpeed();
    }

    private void updateScore(GameScreenState gameScreenState, float delta) {
        gameScreenState.score += gameScreenState.standardMovingBonus * delta;
    }

    private void spawnNextObject(long runTime, GameScreenState gameScreenState){
        if (runTime - gameScreenState.lastGameObjTime > gameScreenState.spawnTime) {
            movingObjects.add(levelProvider.getNextObject(movingGameObjectFactory, gameScreenState));
        }
    }
}
