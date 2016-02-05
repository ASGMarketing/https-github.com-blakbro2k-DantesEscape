package net.asg.games.dante;

import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.screens.GameScreen;
import net.asg.games.dante.screens.GameScreenState;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.screens.MainMenuScreen;
import net.asg.games.dante.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.Timer;

public class DantesEscapeGame extends Game {
    private ImageProvider imageProvider;
    private GameScreen gameScreen;
    //private GameState gameState;

    //private TextResources textResources;
    private SoundProvider soundProvider;
    public boolean isDebugOn = false;
    //private FPSLogger fpsLogger;
    private StateManager stateManager;

    private FPSLogger fpsLog;

    public DantesEscapeGame() {
        this.isDebugOn = false;
    }

    public DantesEscapeGame(boolean isDebugOn) {
        this.isDebugOn = isDebugOn;
    }

    @Override
    public void create() {
        imageProvider = new ImageProvider();
        imageProvider.load();

        soundProvider = new SoundProvider();
        soundProvider.load();

        fpsLog = new FPSLogger();
        fpsLog.log();

        gameScreen = new GameScreen(this, null);

        stateManager = new StateManager();
        //menuScreen = new MenuScreen(this);
        //levelScreen = new LevelSelectScreen(this);
        setScreen(new SplashScreen(this));

        final long splash_start_time = System.currentTimeMillis();

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
                        if (splash_elapsed_time < Constants.SPLASH_MINIMUM_MILLIS) {
                            Timer.schedule(
                                    new Timer.Task() {
                                        @Override
                                        public void run() {
                                            DantesEscapeGame.this.gotoGameScreen();
                                        }
                                    }, (float) (Constants.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
                        } else {
                            DantesEscapeGame.this.gotoGameScreen();
                        }
                    }
                });
    }

    public void render() {
        super.render();
        fpsLog.log();
    }

    public void gotoGameScreen() {
        setScreen(new GameScreen(this, null));
    }

    public ImageProvider getImageProvider() {
        return imageProvider;
    }

    public SoundProvider getSoundProvider() {
        return soundProvider;
    }

    public void showHelp() {
        //setScreen(new HelpScreen(this));
    }

    public void gotoMenuScreen() {
        //setScreen(new MenuScreen(this));
    }

    //public GameState getGameState() {
    //    return gameState;
    //}

    //public TextResources getTextResources() {
    //	return textResources;
    //}

    public void persist(GameScreenState gameScreenState) {
        stateManager.persist(gameScreenState);
    }

    @Override
    public void dispose() {
        imageProvider.dispose();
        soundProvider.dispose();

        //menuScreen.dispose();
        //levelScreen.dispose();
        gameScreen.dispose();
    }
}
