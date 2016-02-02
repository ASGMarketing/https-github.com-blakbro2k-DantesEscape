/**
 * 
 */
package net.asg.games.dante.view;

/**
 * @author Blakbro2k
 *
 */
public enum MovingGameObjectType {
    	Fireball(0),
	    FireWall(1),
	    DynamicWall(2),
	    GoalWall(3);
	    
	    private final int value;
	    
	    public int getValue() {
	        return value;
	    }
	    
	    private MovingGameObjectType(int value) {
	        this.value = value;
	    }
	    
	    public static MovingGameObjectType fromValue(int value) {
	        switch(value) {
	            case 0:
	                return Fireball;
	            case 1:
	                return FireWall;
	            case 2:
	                return DynamicWall;
	            case 3:
	                return GoalWall;                
	        }
	        throw new ArrayIndexOutOfBoundsException("invalid MovingObjectType in MovingObject class: " + value);    
	    }
	}

