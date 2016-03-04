package net.asg.games.dante.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import net.asg.games.dante.Constants;
import net.asg.games.dante.DantesEscapeGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new DantesEscapeGame();
        }
}