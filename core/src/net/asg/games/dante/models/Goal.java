/**
 *
 */
package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.states.GameScreenState.LevelState;
import net.asg.games.dante.providers.SoundProvider;
import net.asg.games.dante.states.MovingGameObjectState;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Blakbro2k
 */
public class Goal extends MovingGameObject {
    private int velocityX;

    public Goal(ImageProvider imageProvider,
                TextureRegion[] textureRegions,
                SoundProvider soundProvider,
                MovingGameObjectState state,
                int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);
    }

    public void initialize(int[] hitBoxConfig){
        this.offSetX = hitBoxConfig[GAME_OBJECT_X];
        this.offSetY = hitBoxConfig[GAME_OBJECT_Y];

        rect = new Rectangle();
        hitboxBounds = new Rectangle();

        int rectConfig[] = {0,0,textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight()};

        setRectSize(rect, rectConfig);
        setRectSize(hitboxBounds, hitBoxConfig);
        reset();
        setHitboxActive(true);
        setStatefulPosition();
    }

    public void moveLeft(float delta, float speedBonus) {
        int moveSpeed = Constants.GOAL_OBJECT_MOVE_SPEED;
        rect.x -= moveSpeed * delta;
        setHitboxBounds();

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

    public LevelState doCollision(float delta) {
        if (!isSoundTriggered) {
            soundProvider.playGoalHitSound();
            state.setSoundTriggered(isSoundTriggered = true);
        }

        rect.y -= Constants.GOAL_HIT_SPEED * delta;
        rect.x += velocityX * delta;
        velocityX -= 30;
        return LevelState.GOALHIT;
    }

    public void reset() {
        time = 0;
        isCollided = false;
        isSoundTriggered = false;
        isHitboxActive = true;
        frame = 0;
        velocityX = Constants.GOAL_OBJECT_X_VELOCITY;
        setHitboxActive(false);
        setObjectPosition(imageProvider.getScreenWidth(), 0);
    }

    public void fireSound(){
        soundProvider.playfirewooshSound();
    }
}
