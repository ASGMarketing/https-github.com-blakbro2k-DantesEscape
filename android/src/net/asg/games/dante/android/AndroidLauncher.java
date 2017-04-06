package net.asg.games.dante.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import net.asg.games.dante.ActionResolver;
import net.asg.games.dante.Constants;
import net.asg.games.dante.DantesEscapeGame;
import net.asg.games.dante.GameEventListener;
import net.asg.games.dante.HighScoreManager;

import java.util.List;

public class AndroidLauncher extends AndroidApplication {
	ActionResolverAndroid actionResolverAndroid;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set context of action
		actionResolverAndroid = new ActionResolverAndroid(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new DantesEscapeGame(actionResolverAndroid), config);

		/*


		        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the libgdx View
        View gameView = initializeForView(new DantesEscapeGame(Constants.DEBUG), false);

        // Create and setup the AdMob view
        AdView adView = new AdView(this, AdSize.BANNER, "xxxxxxxx"); // Put in your secret key here
        adView.loadAd(new AdRequest());

        // Add the libgdx view
        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams =
        	new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
        			RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(adView, adParams);

        // Hook it all up
        setContentView(layout);
		 */
	}
}
