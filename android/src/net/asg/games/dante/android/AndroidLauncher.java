package net.asg.games.dante.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import net.asg.games.dante.ActionResolver;
import net.asg.games.dante.DantesEscapeGame;

public class AndroidLauncher extends AndroidApplication {
	ActionResolverAndroid actionResolverAndroid;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set context of action
		actionResolverAndroid = new ActionResolverAndroid(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new DantesEscapeGame(actionResolverAndroid), config);
	}
}
