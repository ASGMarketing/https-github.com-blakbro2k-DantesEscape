package net.asg.games.dante.desktop;

import net.asg.games.dante.ActionResolver;
import net.asg.games.dante.DantesEscapeGame;

/**
 * Created by Blakbro2k on 4/18/2017.
 */

public class DesktopLauncherResolver implements ActionResolver {
    @Override
    public void backButton(DantesEscapeGame game) {
        System.out.println("backButton Resolver called.");
        game.exitApp(0);
    }

    @Override
    public void showBannerAd() {
        System.out.println("showBannerAd Resolver called.");
    }

    @Override
    public void hideBannerAd() {
        System.out.println("hideBannerAd Resolver called.");
    }

    @Override
    public void showOrLoadInterstital() {
        System.out.println("showOrLoadInterstital Resolver called.");
    }

    @Override
    public void share() {
        System.out.println("share Resolver called.");
    }

    @Override
    public void showScores() {
        System.out.println("showScores Resolver called.");
    }

    @Override
    public void getHighScoreName() {
        System.out.println("getHighScoreName Resolver called.");
    }

    @Override
    public void getDebugSetting() {
        System.out.println("getDebugSetting Resolver called.");
    }
}
