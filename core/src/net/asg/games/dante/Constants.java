package net.asg.games.dante;

public class Constants {

	public static final boolean COPY_IMAGES = true;
	public static final boolean DEBUG = false;
	public static final boolean NO_CLIP_MODE_OFF = true;
	
	public static final int MAX_WIDTH = 1024;
	public static final int MAX_HEIGHT = 1024;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 480;
	public static final int STARTING_SPAWNTIME = 2200;

	//view constants
	public static final int BOB_MOVE_SPEED = 510;
	public static final int BOB_SPRITE_TOP_TRIM = 20;
	public static final int OBJECT_MOVE_SPEED = 550;
	public static final int GOAL_OBJECT_MOVE_SPEED = 800;
	public static final int WALL_OBJECT_MOVE_SPEED = 750;
	public static final int GOAL_OBJECT_X_VELOCITY = 1500;
	public static final int ROUND_TIME_DURATION = 21000;
	public static final int FIREBALL_SPAWN_TIME = 360;
	public static final int DYNAMIC_FIREWALL_SPAWN_TIME = 1360;
	public static final int FIREWALL_SPAWN_TIME = 1100;
	
	public static final int WALL_GAP_SPEED = 280;
	public static final int NORMAL_WALL_GAP_SIZE = 100;
	public static final int MEDIUM_WALL_GAP_SIZE = 150;
	public static final int LARGE_WALL_GAP_SIZE = 200;
	public static final int WALL_POSITION_ONE = 0;
	public static final int WALL_POSITION_TWO = 50;
	public static final int WALL_POSITION_THREE = 150;
	public static final int WALL_POSITION_FOUR = 200;
	public static final int WALL_POSITION_FIVE = 250;
	public static final int WALL_POSITION_SIX = 300;
	public static final int WALL_BASE_OFFSET = 450;

	public static final float DEFAULT_ANIMATION_PERIOD = 0.10f;
	public static final float DEFAULT_GAME_SPEED = 1.0f;
	public static final float BACKGROUND_SPEED = 0.7f;
	public static final float FOREGROUND_SPEED = 1.7f;
	public static final float FIREBALL_SPEED = 0.7f;
	
	public static final String GAME_TITLE = "Jerry's Escape";
	public static final String ASSETS_FOLDER_NAME = "assets";
	public static final String IMAGES_FOLDER_NAME = "images";
	public static final String GAME_FOLDER_NAME = "games";
	public static final String TEXT_IMAGES_FOLDER_NAME = "text-images";
	public static final String STATE_DATA_FILE = "data/gamestate-v2.json";
}
