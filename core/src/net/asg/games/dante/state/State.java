package net.asg.games.dante.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Blakbro2k on 1/26/2016.
 */
public class State {
        private static final String preferencesName = "dante-game-preferences";

        public static boolean isSoundOn() {
            Preferences prefs = Gdx.app.getPreferences(preferencesName);
            return prefs.getBoolean("SoundOn");
        }

        public static void setSoundOn(boolean on) {
            Preferences prefs = Gdx.app.getPreferences(preferencesName);
            prefs.putBoolean("SoundOn", on);
            prefs.flush();
        }

        public static boolean isLevelUnlocked(int level) {
            if (level == 0) {
                return true;
            }
            Preferences prefs = Gdx.app.getPreferences(preferencesName);
            return prefs.getBoolean("Level" + (level + 1));
        }

        public static void unLockLevel(int level) {
            Preferences prefs = Gdx.app.getPreferences(preferencesName);
            prefs.putBoolean("Level" + (level + 1), true);
            prefs.flush();
        }

}
