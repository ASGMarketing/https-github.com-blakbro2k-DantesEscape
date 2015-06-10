
package net.asg.games.dante.desktop;

import net.asg.games.dante.Constants;
import net.asg.games.dante.DantesEscapeGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

/**
 * @author Blakbro2k
 *
 * Main Desktop file for libgdx. Loads the application in a desktop window
 * with configuration.  It creates a new <code>LwjglApplication</code> by
 * passing a new <code>DantesEscapeGame</code>.
 */
public class DesktopLauncher {
	public static void main(String[] args) {

		boolean copyImages = Constants.COPY_IMAGES;
		boolean debugOn = Constants.DEBUG;

		//Use TexturePacker to pack image files

		if (args.length == 1 && args[0].equals("texturepacker")) {
			// Create two run configurations
			// 1. For texture packing. Pass 'texturepacker' as argument and use desktop/src
			//    as working directory
			// 2. For playing game with android/assets as working directory
			Settings settings = new Settings();
			settings.maxWidth = Constants.MAX_WIDTH;
			settings.maxHeight = Constants.MAX_HEIGHT;
			TexturePacker.process(settings, Constants.IMAGES_FOLDER_NAME, Constants.ASSETS_FOLDER_NAME, Constants.GAME_FOLDER_NAME);
			TexturePacker.process(settings, Constants.TEXT_IMAGES_FOLDER_NAME, Constants.ASSETS_FOLDER_NAME, Constants.TEXT_IMAGES_FOLDER_NAME);
		}

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Constants.GAME_TITLE;
		//cfg.useGL30 = true;
		cfg.width = Constants.GAME_WIDTH;
		cfg.height = Constants.GAME_HEIGHT;

		new LwjglApplication(new DantesEscapeGame(debugOn), cfg);
	}
}