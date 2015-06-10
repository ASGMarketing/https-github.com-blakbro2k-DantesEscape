package net.asg.dante;

import net.asg.games.dante.DantesEscapeGame;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //cfg.useGL20 = false;
        
        initialize(new DantesEscapeGame(), cfg);
    }
}