package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import net.asg.games.dante.providers.LevelProvider;
import net.asg.games.dante.screens.GameScreenState;

/**
 * Created by Blakbro2k on 3/25/2017.
 */

public class World {
    private Sprite backgroundSprite;

    private Sprite foregroundSprite;

    private Sprite middleGroundSprite;

    private Bob bob;

    private Button levelResetButton;

    private Button homeButton;

    private float bgScrollTimer;

    private float mgScrollTimer;

    private float fgScrollTimer;

    private MovingGameObjectFactory movingGameObjectFactory;

    private Array<MovingGameObject> movingObjects;

    private LevelProvider levelProvider;

    private String scoreName;

    private BitmapFont bitmapFontName;

    public void initialize(){

    }
}
