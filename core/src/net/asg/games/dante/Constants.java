package net.asg.games.dante;

import net.asg.games.dante.models.HitboxConfiguration;

public class Constants {
    public static final boolean DEBUG = false;
    public static final boolean NO_CLIP_MODE_OFF = true;

    public static final int MAX_WIDTH = 1024;
    public static final int MAX_HEIGHT = 1024;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 480;
    public static final int STARTING_SPAWNTIME = 2200;

    //view constants
    public static final int BOB_MOVE_SPEED = 510;

    public static final int[] BOB_HITBOX = {0, 12, 70, 48};
    public static final int[] FIREBALL_HITBOX = {0,8, 50, 45};
    public static final int[] ROCKWALL_HITBOX = {0, 0, 16, -1};
    public static final int[] GOAL_HITBOX = {0, 0, 16, 800};

    public static final int OBJECT_MOVE_SPEED = 550;
    public static final int GOAL_OBJECT_MOVE_SPEED = 800;
    public static final int WALL_OBJECT_MOVE_SPEED = 750;
    public static final int GOAL_OBJECT_X_VELOCITY = 1500;
    public static final int ROUND_TIME_DURATION = 16000;
    public static final int FIREBALL_SPAWN_TIME = 360;
    public static final int DYNAMIC_FIREWALL_SPAWN_TIME = 1360;
    public static final int FIREWALL_SPAWN_TIME = 1100;

    public static final int WALL_GAP_SPEED = 280;

    public static final int HIGH_SCORES_COUNT = 5;
    public static final int FIREBALL_SPEED = 820;


    public static final float DEFAULT_ANIMATION_PERIOD = 0.10f;
    public static final float DEFAULT_GAME_SPEED = 1.0f;
    public static final float BACKGROUND_SPEED = 0.7f;
    public static final float MIDGROUND_SPEED = 1.1f;
    public static final float FOREGROUND_SPEED = 2.0f;

    public static final String GAME_TITLE = "Jerry's Escape";
    public static final String SOURCE_ASSETS_FOLDER_PATH = "../../desktop/";
    public static final String TARGET_ASSETS_FOLDER_PATH = "../assets";
    public static final String IMAGES_FOLDER_NAME = "images";
    public static final String GAME_ATLAS_NAME = "games";
    public static final String TEXT_IMAGES_FOLDER_NAME = "text-images";
    public static final String TEXT_IMAGES_ATLAS_NAME = "text-images";
    public static final String STATE_DATA_FILE = "data/gamestate-v2.json";
    public static final String SCORES_DATA_FILE = "data/scores-v1.json";
    public static final String BUTTONS_IMAGES_FOLDER_NAME = "buttons";
    public static final String BUTTONS_IMAGES_ATLAS_NAME = "buttons";

    public static final long SPLASH_MINIMUM_MILLISECONDS = 2500L;

    public static final int BOB_TOTAL_ANIMATION_FRAMES = 30;
    public static final int FIREBALL_TOTAL_ANIMATION_FRAMES = 9;
    public static final int FIREWALL_TOTAL_ANIMATION_FRAMES = 5;
    public static final int DYNAMIC_FIREWALL_TOTAL_ANIMATION_FRAMES = 3;
    public static final int GOAL_HIT_SPEED = 1300;
}
