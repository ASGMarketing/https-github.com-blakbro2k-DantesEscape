package net.asg.games.dante;

import net.asg.games.dante.screens.AbstractScreen;

import java.util.List;

/**
 * Created by joshuabernitt on 6/12/16.
 * Expended Handlers capability to show ads and get high scores 4/5/2017
 */
public interface ActionResolver {
    /**
     * Interface for back button
     */
    void backButton(DantesEscapeGame game);

    void showBannerAd();

    void hideBannerAd();

    void showOrLoadInterstital(boolean show);

    void share();

    void showScores();

    void getHighScoreName();

    void getDebugSetting();
}
