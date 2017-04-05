/**
 *
 */
package net.asg.games.dante.models;

import net.asg.games.dante.providers.LevelProvider;

/**
 * @author Blakbro2k
 */
public enum MovingGameObjectType {
    Fireball(LevelProvider.FIREBALL_LEVEL_VALUE),
    RockWall(LevelProvider.ROCK_LEVEL_VALUE),
    LavaWall(LevelProvider.LAVA_LEVEL_VALUE),
    GoalWall(LevelProvider.GOAL_LEVEL_VALUE),
    SlidingRockWall(LevelProvider.SLIDE_ROCK_LEVEL_VALUE),
    Missile(LevelProvider.MISSILE_LEVEL_VALUE),
    FastRockWall(LevelProvider.FAST_ROCK_LEVEL_VALUE),
    FastLavaWall(LevelProvider.FAST_LAVA_LEVEL_VALUE),
    EasyRockWall(LevelProvider.EASY_ROCK_LEVEL_VALUE);

    protected final int value;

    public int getValue() {
        return value;
    }

    MovingGameObjectType(int value) {
        this.value = value;
    }

    public static MovingGameObjectType fromValue(int value) {
        switch (value) {
            case LevelProvider.FIREBALL_LEVEL_VALUE:
                return Fireball;
            case LevelProvider.ROCK_LEVEL_VALUE:
                return RockWall;
            case LevelProvider.LAVA_LEVEL_VALUE:
                return LavaWall;
            case LevelProvider.GOAL_LEVEL_VALUE:
                return GoalWall;
            case LevelProvider.SLIDE_ROCK_LEVEL_VALUE:
                return SlidingRockWall;
            case LevelProvider.MISSILE_LEVEL_VALUE:
                return Missile;
            case LevelProvider.FAST_ROCK_LEVEL_VALUE:
                return FastRockWall;
            case LevelProvider.FAST_LAVA_LEVEL_VALUE:
                return FastLavaWall;
            case LevelProvider.EASY_ROCK_LEVEL_VALUE:
                return EasyRockWall;
        }
        throw new ArrayIndexOutOfBoundsException("invalid MovingObjectType in MovingObject class: " + value);
    }
}

