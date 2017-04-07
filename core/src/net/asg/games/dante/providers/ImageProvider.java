package net.asg.games.dante.providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import net.asg.games.dante.Constants;

public class ImageProvider {

    private TextureAtlas atlas;
    private TextureAtlas textAtlas;
    private TextureAtlas buttonsAtlas;
    private Texture background;
    private Texture middleground;
    private Texture foreground;
	private Texture splashScreen;
	private Skin defaultUISkin;
    private Skin defaultButtonSkin;
    private Texture animatedBackground;
    private Texture animatedForeground;
    private Texture animatedMiddleground;
    private Texture pauseScreen;
    private Sprite backGroundSprite;
    private Sprite foreGroundSprite;
    private Sprite middleGroundSprite;
    private Texture gameover;
    private BitmapFont defaultFont;
    private BitmapFont ravieFont;

    public enum ObjectType{
        FIREBALL, BOB, DFIREWALL, FIREWALL, GOVER, GOAL
    }

    public ImageProvider() {
    }

    public void setAnimations(TextureRegion[] textureRegions, ImageProvider imageProvider, ObjectType objectType){
        for(int f = 0; f < textureRegions.length; f++){
            switch(objectType){
                case BOB:
                    textureRegions[f] = imageProvider.getBob(f + 1);
                    break;
                case FIREBALL:
                    textureRegions[f] = imageProvider.getFireBall(f + 1);
                    break;
                case DFIREWALL:
                    textureRegions[f] = imageProvider.getFireWall(f + 1);
                    break;
                case FIREWALL:
                    textureRegions[f] = imageProvider.getFireWall(f + 1);
                    break;
                case GOVER:
                    textureRegions[f] = imageProvider.getFireBall(f + 1);
                    break;
                default:
                    break;
            }
        }
    }

    public void load() {
        atlas = new TextureAtlas(Gdx.files.internal("games.atlas"));
        textAtlas = new TextureAtlas(Gdx.files.internal("text-images.atlas"));
        buttonsAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));

        //Textures
        background = new Texture(Gdx.files.internal("cave_bg.png"));
        middleground = new Texture(Gdx.files.internal("cave_mg.png"));
        foreground = new Texture(Gdx.files.internal("cave_fg.png"));
		splashScreen = new Texture(Gdx.files.internal("splash-screen.png"));
        pauseScreen = new Texture(Gdx.files.internal("paused.png"));
        gameover = new Texture(Gdx.files.internal("gameover.png"));
        animatedBackground = new Texture(Gdx.files.internal("cave_bg.png"));
        animatedBackground.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        animatedMiddleground = new Texture(Gdx.files.internal("cave_mg.png"));
        animatedMiddleground.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        animatedForeground = new Texture(Gdx.files.internal("cave_fg.png"));
        animatedForeground.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		//Sprites
        backGroundSprite = new Sprite(animatedBackground, 0, 0, this.getScreenWidth(), this.getScreenHeight());
        foreGroundSprite = new Sprite(animatedForeground, 0, 0, this.getScreenWidth(), this.getScreenHeight());
        middleGroundSprite = new Sprite(animatedMiddleground, 0, 0, this.getScreenWidth(), this.getScreenHeight());

		//Skins
        defaultUISkin = new Skin(Gdx.files.internal("data/uiskin.json"));
        buildButtonSkins();

        //Fonts
        defaultFont = new BitmapFont(Gdx.files.internal("data/default.fnt"), Gdx.files.internal("data/default.png"), false);
        ravieFont = new BitmapFont(Gdx.files.internal("fonts/ravie.fnt"), Gdx.files.internal("fonts/ravie.png"), false);

    }

    public void dispose() {
        atlas.dispose();
        textAtlas.dispose();
        buttonsAtlas.dispose();
        animatedBackground.dispose();
        animatedMiddleground.dispose();
        animatedForeground.dispose();
        background.dispose();
        middleground.dispose();
        foreground.dispose();
        gameover.dispose();
        pauseScreen.dispose();
		splashScreen.dispose();
        defaultUISkin.dispose();
        defaultButtonSkin.dispose();
        defaultFont.dispose();
        ravieFont.dispose();
    }

    public int getScreenWidth() {
        return Constants.GAME_WIDTH;
    }

    public int getScreenHeight() {
        return Constants.GAME_HEIGHT;
    }

    public Sprite getBackgroundSprite() {
        return backGroundSprite;
    }

    public Sprite getForegroundSprite() {
        return foreGroundSprite;
    }

    public Sprite getMiddlegroundSprite() {
        return middleGroundSprite;
    }

    public Texture getGameOverBoard() {
        return gameover;
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getForeground() {
        return foreground;
    }

	public Texture getSplashScreen() {
		return splashScreen;
	}

    public Texture getPauseScreen() {
        return pauseScreen;
    }

    public TextureRegion getFireBall(int frame) {
        return atlas.findRegion("Fireball", frame);
    }

    public TextureRegion getRockWall() {
        return atlas.findRegion("rockwall");
    }

    public TextureRegion getFireWall(int frame) {
        return atlas.findRegion("firewall", frame);
    }

    public TextureRegion getBob(int frame) {
        return atlas.findRegion("bob",frame);
    }

    public TextureRegion getGoal() {
        return atlas.findRegion("goalpole");
    }

    public TextureRegion getHomeButton() {
        return buttonsAtlas.findRegion("home");
    }

    public TextureRegion getResetButton() {
        return buttonsAtlas.findRegion("reset");
    }

    public BitmapFont getDefaultFont() { return defaultFont;}

    public BitmapFont getRavieFont() { return ravieFont;}

    public Skin getDefaultUIskin() { return defaultUISkin; }

    public Skin getDefaultButtonSkin() { return defaultButtonSkin; }

    private void buildButtonSkins (){
        defaultButtonSkin = new Skin();
        defaultButtonSkin.addRegions(buttonsAtlas);
    }

    public ImageButtonStyle getExitButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        tempButtonStyle.up = defaultButtonSkin.getDrawable("exit");  //Set image for not pressed button
        tempButtonStyle.down = defaultButtonSkin.getDrawable("exit_down");  //Set image for pressed
        tempButtonStyle.over = defaultButtonSkin.getDrawable("exit_over");  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getConfigButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        tempButtonStyle.up = defaultButtonSkin.getDrawable("config");  //Set image for not pressed button
        tempButtonStyle.down = defaultButtonSkin.getDrawable("config_down");  //Set image for pressed
        tempButtonStyle.over = defaultButtonSkin.getDrawable("config_over");  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getPlayButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        tempButtonStyle.up = defaultButtonSkin.getDrawable("play");  //Set image for not pressed button
        tempButtonStyle.down = defaultButtonSkin.getDrawable("play_down");  //Set image for pressed
        tempButtonStyle.over = defaultButtonSkin.getDrawable("play_over");  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getBackButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        tempButtonStyle.up = defaultButtonSkin.getDrawable("back");  //Set image for not pressed button
        tempButtonStyle.down = defaultButtonSkin.getDrawable("back_down");  //Set image for pressed
        tempButtonStyle.over = defaultButtonSkin.getDrawable("back_over");  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public TextureRegion getHelp() {
		return atlas.findRegion("Help");
	}
	
	public TextureRegion getButton() {
		return atlas.findRegion("button");
	}
	
	public TextureRegion getBack() {
		return atlas.findRegion("back");
	}
	
	public TextureRegion getGrey2() {
		return atlas.findRegion("number_two_grey");
	}
	
	public TextureRegion getGrey3() {
		return atlas.findRegion("number_three_grey");
	}
	
	public TextureRegion getNumber(int number) {
		return atlas.findRegion("number", number);
	}

	public TextureRegion getClockBase() {
		return atlas.findRegion("base");
	}

	public TextureRegion getSecond(int second) {
		return atlas.findRegion("second", second);
	}
	
	public TextureRegion getSecondRed(int second) {
		return atlas.findRegion("second_red", second);
	}

	public TextureRegion getPause() {
		return atlas.findRegion("player_pause");
	}
	
	public TextureRegion getSoundImage(boolean on) {
		if (on) {
			return atlas.findRegion("sound_on");
		}
		return atlas.findRegion("sound_off");
	}
	

	
	/*public int getFruitsCount() {
		return FruitType.fruitNames.length;
	}

	public TextureRegion getFruit(int fruitType) {
		return atlas.findRegion(FruitType.fruitNames[fruitType]);
	}
	
	public TextureRegion getFruitBig(int fruitType) {
		String name = FruitType.fruitNames[fruitType] + "2";
		return atlas.findRegion(name);
	}	
	
	public TextureRegion getBadAppleFrame(int frame) {
		return atlas.findRegion("bad_apple", frame);
	}
	
	public TextureRegion getStarFrame(int frame) {
		return atlas.findRegion("star", frame);
	}

	public TextureRegion getMinusSign() {
		return atlas.findRegion("minus");
	}
	
	public TextureRegion getRestart() {
		return atlas.findRegion("restart");
	}

	public TextureRegion getTimes2() {
		return atlas.findRegion("x2");
	}
	
*/


    /**
     * Text images
     */

    public TextureRegion getYouLoseLabel() {
        return textAtlas.findRegion("YouLose");
    }

    public TextureRegion getStartLabel() {
        return textAtlas.findRegion("Start");
    }
	
	/*
	public TextureRegion getLogo() {
		return textAtlas.findRegion("CatchTheFruits");
	}	
		
	public TextureRegion getKids() {
		return textAtlas.findRegion("Kids");
	}
	
	public TextureRegion getScores() {
		return textAtlas.findRegion("Scores");
	}
	
	public TextureRegion getUnlockedLabel() {
		return textAtlas.findRegion("Unlocked");
	}	
	
	public TextureRegion getLevel() {
		return textAtlas.findRegion("Level");
	}
	
	public TextureRegion getLevelGrey() {
		return textAtlas.findRegion("Level_grey");
	}
	
	public TextureRegion getSpringLabel() {
		return textAtlas.findRegion("SpringLabel");
	}
	
	public TextureRegion getSummerLabel() {
		return textAtlas.findRegion("SummerLabel");
	}
	
	public TextureRegion getAutumnLabel() {
		return textAtlas.findRegion("AutumnLabel");
	}
	
	public TextureRegion getWinterLabel() {
		return textAtlas.findRegion("WinterLabel");
	}	

	public TextureRegion getFruitsLabel() {
		return textAtlas.findRegion("fruits");
	}	

	public TextureRegion getTotalLabel() {
		return textAtlas.findRegion("Total");
	}	
	
	public TextureRegion getGoalLabel() {
		return textAtlas.findRegion("Goal");
	}
	
	public TextureRegion getPointsLabel() {
		return textAtlas.findRegion("Points");
	}	
		
	public TextureRegion getSuccessLabel() {
		return textAtlas.findRegion("Success");
	}
	*/
}