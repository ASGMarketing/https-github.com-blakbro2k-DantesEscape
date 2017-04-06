/**
 *
 */
package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.states.GameScreenState.LevelState;
import net.asg.games.dante.providers.SoundProvider;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Blakbro2k
 */
public class Goal extends MovingGameObject {
    private int velocityX = Constants.GOAL_OBJECT_X_VELOCITY;
    // private int animationPattern[];

    public Goal(ImageProvider imageProvider, TextureRegion[] textureRegions,
                SoundProvider soundProvider, int width, int height, boolean isHitboxActive,
                net.asg.games.dante.states.MovingGameObjectState state, int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, width, height,
                isHitboxActive, state, hitBoxConfig);
    }

    public void moveLeft(float delta, float speedBonus) {
        int moveSpeed = Constants.GOAL_OBJECT_MOVE_SPEED;
        rect.x -= moveSpeed * delta;
        setHitboxBounds(rect);

        state.setPosX((int) rect.x);
        time += delta;
        if (time > animationPeriod) {
            time -= animationPeriod;
            frame += 1;
            if (frame >= textureRegions.length) {
                frame = 0;
            }
        }
    }

    public LevelState doCollision(float delta) {
        if (!isSoundTriggered) {
            soundProvider.playGoalHitSound();
            state.setSoundTriggered(isSoundTriggered = true);
        }

        rect.y -= Constants.GOAL_HIT_SPEED * delta;
        rect.x += velocityX * delta;
        setHitboxBounds(rect);

        state.setPosX((int) rect.x);
        state.setPosX((int) rect.y);
        velocityX -= 1;
        return LevelState.GOALHIT;
    }
}
