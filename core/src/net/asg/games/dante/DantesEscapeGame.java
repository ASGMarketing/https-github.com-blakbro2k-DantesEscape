package net.asg.games.dante;

import net.asg.games.dante.models.MovingGameObjectPool;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.LevelProvider;
import net.asg.games.dante.screens.GameScreen;
import net.asg.games.dante.states.GameScreenState;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.screens.MainMenuScreen;
import net.asg.games.dante.screens.SettingsScreen;
import net.asg.games.dante.screens.SplashScreen;
import net.asg.games.dante.states.StateManager;
import net.asg.games.dante.states.StateManager.StateBundle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

public class DantesEscapeGame extends Game {
    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private LevelProvider levelProvider;
    private ActionResolver actionResolver;

    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;
    private SettingsScreen settingsMenuScreen;

    //private GameState gameState;
    //private TextResources textResources;
    public boolean isDebugOn = true;
    private StateManager stateManager;
    private FPSLogger fpsLog;


    public DantesEscapeGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    @Override
    public void create() {
        imageProvider = new ImageProvider();
        imageProvider.load();
        soundProvider = new SoundProvider();
        soundProvider.load();
        levelProvider = new LevelProvider();

        fpsLog = new FPSLogger();
        fpsLog.log();

        stateManager = new StateManager();

        gameScreen = new GameScreen(this, null);
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

    public void gotoGameScreen() {
        setScreen(new GameScreen(this, null));
    }
    
    public void gotoMainMenuScreen() {
        setScreen(new MainMenuScreen(this));
    }
    
    public void gotoSettingsScreen() {
        setScreen(new SettingsScreen(this));
    }


    public ImageProvider getImageProvider() {
        return imageProvider;
    }

    public SoundProvider getSoundProvider() {
        return soundProvider;
    }

    public LevelProvider getLevelProvider() {
        return levelProvider;
    }

    public ActionResolver getActionResolver() {
        return actionResolver;
    }

    //public void showHelp() {
        //setScreen(new HelpScreen(this));
    //}

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
        gameScreen.dispose();
    }

    public void startGame(GameScreenState gameScreenState) {
            gameScreenState.gameReset();
            gameScreenState.isPaused = false;
            soundProvider.resetSoundBoard();
    }


    public void endGame(GameScreenState gameScreenState){
        gameScreenState.gameReset();
        soundProvider.stopAllSounds();
        soundProvider.resetSoundBoard();
    }

    public void exitApp(int errorCode) {
        Gdx.app.exit();
    }
}
