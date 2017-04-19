package net.asg.games.dante.screens;

import java.util.Iterator;

import net.asg.games.dante.Constants;
import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.models.GameWorld;
import net.asg.games.dante.models.MovingGameObjectPool;
import net.asg.games.dante.providers.LevelProvider;
import net.asg.games.dante.models.Bob;
import net.asg.games.dante.models.Button;
import net.asg.games.dante.models.GameOverMessage;
import net.asg.games.dante.models.MovingGameObject;
import net.asg.games.dante.models.MovingGameObjectFactory;
import net.asg.games.dante.states.MovingGameObjectState;
import net.asg.games.dante.states.GameScreenState;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * @author Blakbro2k
 *         <p/>
 *         The main screen for the game. This screen is where the main game is
 *         played. All sprites and game objects are drawn here. This screen
 *         extends <code>AbstractScreen</code> to minimize having to write
 *         unimplemented methods.
 */
public class GameScreen extends AbstractScreen {

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
    private LevelProvider levelProvider;
    private GameScreenState gameScreenState;
    private String scoreName;
    private BitmapFont bitmapFontName;
    private Texture pauseScreen;
    private GameOverMessage gameOverMessage;
    private GameWorld world;
    private ParticleEffect effect;
    private boolean isAdShown = false;


    public GameScreen(DantesEscapeGame game, GameScreenState state) {
        if (state != null) {
            gameScreenState = state;
        } else {
            gameScreenState = new GameScreenState();
        }
        game.startGame(gameScreenState);
        this.game = game;
    }

    public void show() {
        //Create our Game world
        world = new GameWorld(game);
        imageProvider = game.getImageProvider();
        soundProvider = game.getSoundProvider();
        soundProvider.setSound(true);
        soundProvider.playBgSound();

        backgroundSprite = imageProvider.getBackgroundSprite();
        foregroundSprite = imageProvider.getForegroundSprite();
        middleGroundSprite = imageProvider.getMiddlegroundSprite();

        debugRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, imageProvider.getScreenWidth(), imageProvider.getScreenHeight());

        batch = new SpriteBatch();

        movingGameObjectFactory = new MovingGameObjectFactory(imageProvider, soundProvider);

        TextureRegion bobTexture = imageProvider.getBob(1);

        bob = new Bob(-1, -1, bobTexture.getRegionWidth(), bobTexture.getRegionHeight(), Constants.BOB_HITBOX);
        bob.setBobAnimation(imageProvider);

        //effect = new ParticleEffect();
        //effect.load(Gdx.files.internal("jetfuel.p"), Gdx.files.internal(""));
        //effect.getEmitters().first().setPosition(bob.getPosition().x, bob.getPosition().y - 40);
        //effect.start();

        movingObjects = new Array<MovingGameObject>();
        scoreName = "score: 0";
        bitmapFontName = new BitmapFont();

        pauseScreen = imageProvider.getPauseScreen();

        homeButton = new Button(imageProvider.getHomeButton());
        homeButton.setPos(imageProvider.getScreenWidth() / 2 + 20, imageProvider.getScreenHeight() / 2 - 60);

        levelResetButton = new Button(imageProvider.getResetButton());
        levelResetButton.setPos(imageProvider.getScreenWidth() / 2 - 90, imageProvider.getScreenHeight() / 2 - 60);

        levelProvider = new LevelProvider();

        Gdx.input.setInputProcessor(this);
        //Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
        //set up the background and forground scroll properties

        //Gdx.app.log("GameScreen", gameScreenState.toString());
        //this is where the world is rendered. Renderer.render(world)
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

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        //System.out.println(effect);

        //effect.update(delta);
        batch.begin();

        // Draw the Background
        backgroundSprite.draw(batch);
        // Draw the middleground
        middleGroundSprite.draw(batch);
        // Draw the foreground
        foregroundSprite.draw(batch);

        scoreName = "score: " + gameScreenState.score;
        bitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        imageProvider.getDefaultFont().draw(batch, scoreName, 10, imageProvider.getScreenHeight() - 15);

        //effect.setPosition(bob.getPosition().x, bob.getPosition().y);
        //effect.draw(batch);
        batch.draw(bob.getBobFrame(delta,gameScreenState.gameSpeed), bob.getPosition().x, bob.getPosition().y);

        for (MovingGameObject movingObject : movingObjects) {
            movingObject.draw(batch);

            if (movingObject.isCollided) {
                levelProvider.doLevelTransition(movingObject.doCollision(delta), gameScreenState);
            }
        }

        if (gameScreenState.isDead) {
            if (gameOverMessage == null) {
                gameOverMessage = new GameOverMessage(imageProvider, gameScreenState.score);
                game.getActionResolver().showBannerAd();
            }

            gameOverMessage.draw(batch);
            levelResetButton.draw(batch);
            homeButton.draw(batch);
        }

        if (gameScreenState.isPaused) {
            batch.draw(pauseScreen, 0, 0);
        }

        batch.end();

        //if(effect.isComplete()){
        //    effect.reset();
       // }

        //Draw Debug Hitboxes and sprite boxes
        if (game.isDebugOn) {

            debugRenderer.setProjectionMatrix(camera.combined);

            debugRenderer.begin(ShapeType.Line);
            debugRenderer.setColor(Color.GREEN);

            //ShapeRenderer hitBoxRenderer = new ShapeRenderer();
            //hitBoxRenderer.begin(ShapeType.Filled);
           // hitBoxRenderer.setColor(1, 0, 0, Color.alpha(0.5f));

            //bob.draw

            debugRenderer.rect(bob.getPosition().x, bob.getPosition().y,
                    bob.getPosition().width, bob.getPosition().height);

            for (MovingGameObject movingObject : movingObjects) {
                movingObject.drawDebug(debugRenderer);
               // movingObject.drawHitbox(hitBoxRenderer);
            }
            debugRenderer.end();

            //Gdx.gl.glEnable(GL30.GL_BLEND);
            //Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
            //hitBoxRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeType.Filled);
            debugRenderer.setColor(1, 0, 0, Color.alpha(0.5f));

            debugRenderer.rect(bob.getHitboxes().x, bob.getHitboxes().y,
                    bob.getHitboxes().width, bob.getHitboxes().height);

            for (MovingGameObject movingObject : movingObjects) {
                movingObject.drawHitbox(debugRenderer);
            }
            debugRenderer.end();
        }

        processInput(delta);

        if (gameScreenState.isPaused || gameScreenState.isDead) {
            return;
        }

        //world.updateWorld(TimeUtils.millis(), delta, gameScreenState);

        /*
        new render:

        update World
        render World
         process input

         */

        //This is where the world would be updated
        //world.update(delta);
        //world.updateWorld(delta, TimeUtils.millis(), gameScreenState);

        bgScrollTimer += delta * gameScreenState.getBackgroundSpeed();
        mgScrollTimer += delta * gameScreenState.getMiddlegroundSpeed();
        fgScrollTimer += delta * gameScreenState.getForegroundSpeed();

        gameScreenState.score += gameScreenState.standardMovingBonus * delta;

        if (TimeUtils.millis() - gameScreenState.lastGameObjTime > gameScreenState.spawnTime) {
            movingObjects.add(levelProvider.getNextObject(movingGameObjectFactory, gameScreenState));
        }

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

    private void processInput(float delta) {
        if (gameScreenState.isLevelStarted && !gameScreenState.isPaused && !gameScreenState.isDead) {
            if (Gdx.input.isKeyPressed(Keys.UP)) {
                bob.moveY(1, delta);
            }
            if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                bob.moveY(-1, delta);
            }
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                bob.moveX(-1, delta);
            }
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                bob.moveX(1, delta);
            }
        }
    }

    @Override
    public void pause() {
        if (!gameScreenState.isPaused && gameScreenState.isLevelStarted) {
            soundProvider.setBgMusicOff();
            gameScreenState.isPaused = true;

            long pausedTime = TimeUtils.millis();

            gameScreenState.lastGameObjTime -= pausedTime;
            gameScreenState.roundEndTime -= pausedTime;

            gameScreenState.movingObjectStates = new Array<MovingGameObjectState>();
            for (MovingGameObject fo : movingObjects) {
                gameScreenState.movingObjectStates.add(fo.getState());
            }
            gameScreenState.bobX = (int) bob.getPosition().x;
            gameScreenState.bobY = (int) bob.getPosition().y;
        }
        game.persist(gameScreenState);
    }

    @Override
    public void resume() {
        if (gameScreenState.isPaused) {
            soundProvider.setBgMusicOn();
            gameScreenState.isPaused = false;
            long now = TimeUtils.millis();
            gameScreenState.lastGameObjTime += now;
            gameScreenState.roundEndTime += now;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
            game.getActionResolver().backButton(game);
        }

        if (keycode == Keys.P) {
            if (gameScreenState.isPaused) {
                //soundManager.setPauseMusicOff();
                gameScreenState.isPaused = false;
                resume();
            } else {
                //soundManager.setPauseMusicOn();
                gameScreenState.isPaused = true;
                pause();
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (gameScreenState.isPaused && !gameScreenState.isDead)
            resume();

        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (gameScreenState.isLevelStarted && !gameScreenState.isPaused && !gameScreenState.isDead) {
            float delta = Gdx.graphics.getDeltaTime();

            Vector3 touchPos = new Vector3();
            touchPos.set(screenX, screenY, 0);
            camera.unproject(touchPos);

            if (bob.getPosition().x < touchPos.x)
                bob.moveX(1, delta);
            if (bob.getPosition().x > touchPos.x)
                bob.moveX(-1, delta);
            if (bob.getPosition().y < touchPos.y)
                bob.moveY(1, delta);
            if (bob.getPosition().y > touchPos.y)
                bob.moveY(-1, delta);
        }
        if (gameScreenState.isDead) {
            gameScreenState.totalDeaths += 1;
            levelProvider.flushPhases();

            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (homeButton.isPressed(touchPos)) {
                movingObjects.clear();
                game.endGame(gameScreenState);
                //Gdx.app.exit();
                game.gotoMainMenuScreen();
            }

            if (levelResetButton.isPressed(touchPos)) {
                gameScreenState.gameReset();
                movingObjects.clear();
                bob.setPositionX(gameScreenState.bobX);
                bob.setPositionY(gameScreenState.bobY);
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (gameScreenState.isLevelStarted && !gameScreenState.isPaused && !gameScreenState.isDead) {

            float delta = Gdx.graphics.getDeltaTime();

            Vector3 touchPos = new Vector3();
            touchPos.set(screenX, screenY, 0);
            camera.unproject(touchPos);

            if (bob.getPosition().x < touchPos.x)
                bob.moveX(1, delta);
            if (bob.getPosition().x > touchPos.x)
                bob.moveX(-1, delta);
            if (bob.getPosition().y < touchPos.y)
                bob.moveY(1, delta);
            if (bob.getPosition().y > touchPos.y)
                bob.moveY(-1, delta);
        }
        return true;
    }
}
