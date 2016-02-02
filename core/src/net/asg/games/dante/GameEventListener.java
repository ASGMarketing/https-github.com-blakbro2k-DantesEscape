package net.asg.games.dante;

import java.util.List;
import net.asg.games.dante.HighScoreManager.HighScore;

/**
 * Created by Blakbro2k on 2/2/2016.
 */
public interface GameEventListener {

    void showAds(boolean show);

    void getHighScoreName();

    void showScores(List<HighScore> highScores);
}
