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

/**
 * @author Blakbro2k
 */
public class Goal extends MovingGameObject {
    private int velocityX = Constants.GOAL_OBJECT_X_VELOCITY;

    public Goal(ImageProvider imageProvider,
                TextureRegion[] textureRegions,
                SoundProvider soundProvider,
                MovingGameObjectState state,
                int[] hitBoxConfig) {
        super(imageProvider, textureRegions, soundProvider, state, hitBoxConfig);
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
        setHitboxBounds();

        setStatefulPosition();
        velocityX -= 1;
        return LevelState.GOALHIT;
    }

    public void fireSound(){
        soundProvider.playflameBurstSound();
    }
}
