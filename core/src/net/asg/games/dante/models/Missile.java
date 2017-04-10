package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

/**
 * Created by Blakbro2k on 4/8/2017.
 */

public class Missile extends MovingGameObject {
    private final double targetX;
    private boolean isDefunct;
    private boolean isFalling;

    public Missile(ImageProvider imageProvider, TextureRegion[] textureRegions, SoundProvider soundProvider, int width, int height,
                   boolean isHitboxActive, MovingGameObjectState state, int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, width, height, isHitboxActive, state, hitBoxConfig);
        setLaunching();
        this.isDefunct = false;
        targetX = MathUtils.random(0, imageProvider.getScreenWidth());
    }

    public void setActive(){
        isHitboxActive = true;
    }

    public void setInactive(){
        isHitboxActive = false;
    }

    public void setFalling(){
        isFalling = true;
        updateTextures();
        setTopOfScreen();
    }

    public void setLaunching(){
        isFalling = false;
    }

    public void setTopOfScreen(){
        rect.setY(imageProvider.getScreenHeight()-rect.height);
    }

    private void updateTextures(){
        for(int i  = 0; i < textureRegions.length; i++){
            //if(flipped) {
                textureRegions[i].flip(false, true);
            //}
        }
    }

    public void moveLeft(float delta, float speedBonus) {
        if(rect.x > targetX || isDefunct) {
            rect.x -= moveSpeed * delta * speedBonus;
            setHitboxBounds(rect);
        }

        if(rect.x <= targetX){
            if(!isDefunct) {
                if (!isFalling) {
                    if (rect.y < imageProvider.getScreenHeight()) {
                        rect.y += moveSpeed * delta * speedBonus;
                        setHitboxBounds(rect);
                    }

                    if (rect.y >= imageProvider.getScreenHeight()) {
                        isDefunct = true;
                    }
                } else {
                    if (rect.y > 0 - rect.height) {
                        rect.y -= moveSpeed * delta * speedBonus;
                        setHitboxBounds(rect);
                    }

                    if (rect.y <= 0 - rect.height) {
                        isDefunct = true;
                    }
                }
            }
        }

        time += delta;
        if (time > animationPeriod) {
            time -= animationPeriod;
            frame += 1;
            if (frame >= textureRegions.length) {
                frame = 0;
            }
        }
        setStatefulPosition();
    }
}
