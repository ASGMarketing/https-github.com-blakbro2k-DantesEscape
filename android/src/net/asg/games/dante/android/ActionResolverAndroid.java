package net.asg.games.dante.android;

import android.content.Context;

import com.badlogic.gdx.Gdx;

import net.asg.games.dante.ActionResolver;
import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.HighScoreManager;
import net.asg.games.dante.screens.AbstractScreen;
import net.asg.games.dante.screens.SettingsScreen;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by joshuabernitt on 6/12/16.
 */
public class ActionResolverAndroid implements ActionResolver {
    Context context;

    /**
     * Constructor to set context
     * @param context
     */
    public ActionResolverAndroid(Context context) {
        this.context = context;
    }

    /**
     * Implement interface back button
     */
    public void backButton(DantesEscapeGame game) {
        System.out.println("Hello GameWorld from the Back button");
        Gdx.app.debug("MyBackButton", "Hello world from the Back Button");
        game.gotoMainMenuScreen();
    }

    @Override
    public void showAds(boolean show) {

    }

    @Override
    public void showOrLoadInterstital() {

    }

    @Override
    public void getHighScoreName() {

    }

    @Override
    public void getDebugSetting() {

    }

    @Override
    public void showScores(List<HighScoreManager.HighScore> highScores) {

    }
}
