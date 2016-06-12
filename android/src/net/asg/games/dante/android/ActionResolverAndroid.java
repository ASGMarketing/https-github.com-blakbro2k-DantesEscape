package net.asg.games.dante.android;

import android.content.Context;

import com.badlogic.gdx.Gdx;

import net.asg.games.dante.ActionResolver;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by joshuabernitt on 6/12/16.
 */
public class ActionResolverAndroid implements ActionResolver {
    Context context;

    public ActionResolverAndroid(Context context) {
        this.context = context;
    }

    public void backButton() {
        System.out.println("Hello World from the Back button");
        Gdx.app.debug("MyBackButton", "Hello world from the Back Button");
//        Gdx.app.exit();
    }
}
