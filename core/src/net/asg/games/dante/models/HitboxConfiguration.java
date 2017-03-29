package net.asg.games.dante.models;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Blakbro2k on 3/27/2017.
 */

public class HitboxConfiguration {
    private int[] hitboxConfiguration;

    public HitboxConfiguration (int... ints){
        if(ints.length == 4) {
            this.hitboxConfiguration = ints;
        } else {
            throw new RuntimeException("Hitbox configuration must have an index size of 4");
        }
    }

    public int getOffSetX(){
        return hitboxConfiguration[0];
    }

    public int getOffSetY(){
        return hitboxConfiguration[1];
    }

    public int getHitboxWidth(){
        return hitboxConfiguration[2];
    }

    public int getHitboxHeight(){
        return hitboxConfiguration[3];
    }
}
