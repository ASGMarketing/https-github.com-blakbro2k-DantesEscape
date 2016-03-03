package net.asg.games.dante;

import net.asg.games.dante.StateManager.StateBundle;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.screens.GameScreen;
import net.asg.games.dante.screens.GameScreenState;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.screens.MainMenuScreen;
import net.asg.games.dante.screens.SettingsScreen;
import net.asg.games.dante.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

public class DantesEscapeGame extends Game {
    private ImageProvider imageProvider;
    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;
    private SettingsScreen settingsMenuScreen;

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
        settingsMenuScreen = new SettingsScreen(this);
        mainMenuScreen = new MainMenuScreen(this);

        StateBundle stateBundle = stateManager.retrieveState();

        //levelScreen = new LevelSelectScreen(this);
        setScreen(new SplashScreen(this));

        final long splash_start_time = TimeUtils.millis();

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        long splash_elapsed_time = TimeUtils.millis() - splash_start_time;
                        if (splash_elapsed_time < Constants.SPLASH_MINIMUM_MILLISECONDS) {
                            Timer.schedule(
                                    new Timer.Task() {
                                        @Override
                                        public void run() {
                                            DantesEscapeGame.this.gotoMainMenuScreen();
                                        }
                                    }, (float) (Constants.SPLASH_MINIMUM_MILLISECONDS - splash_elapsed_time) / 1000);
                        } else {
                            DantesEscapeGame.this.gotoMainMenuScreen();
                        }
                    }
                });
    }

    public void render() {
        super.render();
        fpsLog.log();
    }

    public void endGame(GameScreenState gameScreenState){
        gameScreenState.gameReset();
        soundProvider.stopAllSounds();
        soundProvider.resetSoundBoard();
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

    public void gotoMainMenuScreen() {
        setScreen(new MainMenuScreen(this));
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
        mainMenuScreen.dispose();
        settingsMenuScreen.dispose();
        //levelScreen.dispose();
        gameScreen.dispose();
    }

    public void startGame(GameScreenState gameScreenState) {
            gameScreenState.gameReset();
            gameScreenState.isPaused = false;
            soundProvider.resetSoundBoard();
    }
}
