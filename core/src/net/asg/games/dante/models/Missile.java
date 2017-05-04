package net.asg.games.dante.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;

/**
 * Created by Blakbro2k on 4/8/2017.
 */

public class Missile extends MovingGameObject {
    private float targetX;
    private boolean isDefunct;
    private boolean isFalling;

    public Missile(ImageProvider imageProvider,
                   TextureRegion[] textureRegions,
                   SoundProvider soundProvider,
                   MovingGameObjectState state,
                   int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);

    }

    public void initialize(int[] hitBoxConfig){
        this.offSetX = hitBoxConfig[GAME_OBJECT_X];
        this.offSetY = hitBoxConfig[GAME_OBJECT_Y];

        setLaunching();
        this.isDefunct = false;
        flippedTextures(false);

        rect = new Rectangle();
        hitboxBounds = new Rectangle();

        int rectConfig[] = {0,0,textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight()};

        setRectSize(rect, rectConfig);
        setRectSize(hitboxBounds, hitBoxConfig);
        setHitboxActive(true);
        reset();
        setStatefulPosition();
        setMoveSpeed(Constants.FIREBALL_SPEED);
        imageProvider.setAnimations(textureRegions, ImageProvider.FIREBALL_ID);
    }

    public void setActive(){
        isHitboxActive = true;
    }

    public void setInactive(){
        isHitboxActive = false;
    }

    public void setFalling(){
        isFalling = true;
        flippedTextures(true);
        setTopOfScreen();
    }

    public void setTargetX(float x){
        targetX = x;
    }

    public void setLaunching(){
        isFalling = false;
        flippedTextures(false);
        setBottomOfScreen();
    }

    public void setTopOfScreen(){
        rect.setY(imageProvider.getScreenHeight()-rect.height);
    }

    public void setBottomOfScreen(){
        rect.setY(0);
    }

    private void flippedTextures(boolean isFlipped){
        for(int i  = 0; i < textureRegions.length; i++){
            if(isFlipped){
                if(!textureRegions[i].isFlipY()) {
                  textureRegions[i].flip(false, true);
                }
            } else  {
                if(textureRegions[i].isFlipY()) {
                    textureRegions[i].flip(false, true);
                }
            }
        }
    }

    public void moveLeft(float delta, float speedBonus) {
        if(rect.x > targetX || isDefunct) {
            rect.x -= moveSpeed * delta * speedBonus;
            setHitboxBounds();
        }

        if(rect.x <= targetX){
            if(!isDefunct) {
                if (!isFalling) {
                    if (rect.y < imageProvider.getScreenHeight()) {
                        rect.y += moveSpeed * delta * speedBonus;
                        setHitboxBounds();
                    }

                    if (rect.y >= imageProvider.getScreenHeight()) {
                        isDefunct = true;
                    }
                } else {
                    if (rect.y > 0 - rect.height) {
                        rect.y -= moveSpeed * delta * speedBonus;
                        setHitboxBounds();
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

    public void reset() {
        time = 0;
        isCollided = false;
        isSoundTriggered = false;
        frame = 0;
        setHitboxActive(false);
        setObjectPosition(imageProvider.getScreenWidth(), 0);
    }

    public void fireSound(){

    }
}
