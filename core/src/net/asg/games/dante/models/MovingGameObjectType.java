/**
 *
 */
package net.asg.games.dante.models;

/**
 * @author Blakbro2k
 */
public enum MovingGameObjectType {
    Fireball(0),
    RockWall(1),
    LavaWall(2),
    GoalWall(3),
    SlidingRockWall(4),
    Missle(5),
    FastRockWall(6),
    FastLavaWall(7);

    protected final int value;

    public int getValue() {
        return value;
    }

    MovingGameObjectType(int value) {
        this.value = value;
    }

    public static MovingGameObjectType fromValue(int value) {
        switch (value) {
            case 0:
                return Fireball;
            case 1:
                return RockWall;
            case 2:
                return LavaWall;
            case 3:
                return GoalWall;
            case 4:
                return SlidingRockWall;
            case 5:
                return Missle;
            case 6:
                return FastRockWall;
            case 7:
                return FastLavaWall;
        }
        throw new ArrayIndexOutOfBoundsException("invalid MovingObjectType in MovingObject class: " + value);
    }
}

