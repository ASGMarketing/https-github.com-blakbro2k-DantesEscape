package net.asg.games.dante.models;

import com.badlogic.gdx.utils.Pool;

import net.asg.games.dante.DantesEscapeGame;

/**
 * Created by Blakbro2k on 4/11/2017.
 */

public class MovingGameObjectPool {
    MovingGameObjectFactory movingGameObjectFactory;

    public MovingGameObjectPool(DantesEscapeGame game){
        movingGameObjectFactory = new MovingGameObjectFactory(game.getImageProvider(), game.getSoundProvider());
    }

    public final Pool<Missile> EasyMisslePool = new Pool<Missile>() {
        @Override
        protected Missile newObject() {
            return movingGameObjectFactory.getEasyLaunchMissle();
        }
    };

    public final Pool<Missile> HardMisslePool = new Pool<Missile>() {
        @Override
        protected Missile newObject() {
            return movingGameObjectFactory.getEasyLaunchMissle();
        }
    };
}
