package net.asg.games.dante.providers;

import net.asg.games.dante.models.MovingGameObjectType;
import net.asg.games.dante.states.GameScreenState;

import java.util.Stack;

/**
 * Created by Blakbro2k on 3/30/2017.
 */

public class PhaseProvider{
    private Stack<Integer> phases;
    private boolean feedLevels = false;

    public PhaseProvider(){
        phases = new Stack<Integer>();
    }

    public PhaseProvider(boolean feedLevels){
        phases = new Stack<Integer>();
        this.feedLevels = feedLevels;
    }

    public void buildPhase(GameScreenState gameScreenState){
        gameScreenState.levelGroup++;

        //increase LevelGroup since we are building phases
        //build levels backwards base on current level group
        //logic parser

        //Build new Phase:
        //take state.  state contains number of death, time of play, time without death
        //stageType = 3;

        /*
        LG: 1 | 1 Phase    |   ordered Rockwall
        LG: 2 | 2 Phases   |   ordered Rockwall, Fireballs
        LG: 3 | 3 Phases   |   random Rockwall, Fireballs, sliding walls
        LG: 4 | 3 Phases   |   rockwall, fireballs, sliding walls, lava walls
        LG: 5 | 3 Phases   |   rockwall, fireballs, sliding walls, lava walls
        LG: 6 | 4 Phases   |   rockwall, fireballs, sliding walls, lava walls, missles
        LG: 7 | 4 Phases   |   rockwall, firewalls, sliding walls, lava walls, missles, circular object
         */
//System.out.println(gameScreenState.levelGroup);
        if(!feedLevels) {
            switch (gameScreenState.levelGroup) {
                case 1:
                    phases.push(MovingGameObjectType.EasyRockWall.getValue());
                    break;
                case 2:
                    phases.push(MovingGameObjectType.RockWall.getValue());
                    phases.push(MovingGameObjectType.Fireball.getValue());
                    break;
                case 3:
                    phaseRandomizer(-1,
                            MovingGameObjectType.Fireball.getValue(),
                            MovingGameObjectType.RockWall.getValue());
                    phases.push(MovingGameObjectType.SlidingRockWall.getValue());
                    break;
                case 4:
                    phaseRandomizer(-1,
                            MovingGameObjectType.Fireball.getValue(),
                            MovingGameObjectType.RockWall.getValue(),
                            MovingGameObjectType.SlidingRockWall.getValue());
                    phases.push(MovingGameObjectType.LavaWall.getValue());
                    break;
                case 5:
                    phaseRandomizer(-1,
                            MovingGameObjectType.SlidingRockWall.getValue(),
                            MovingGameObjectType.RockWall.getValue(),
                            MovingGameObjectType.LavaWall.getValue());
                    phases.push(MovingGameObjectType.EasyMissile.getValue());

                case 6:
                case 7:
                default:
                    phaseRandomizer(4,
                            MovingGameObjectType.Fireball.getValue(),
                            MovingGameObjectType.RockWall.getValue(),
                            MovingGameObjectType.SlidingRockWall.getValue(),
                            MovingGameObjectType.LavaWall.getValue(),
                            MovingGameObjectType.EasyMissile.getValue());
                    break;
            }
        } else {
            throw new UnsupportedOperationException("Feeding levels is not supported");
        }
    }

    public boolean hasNext() {
        return !phases.isEmpty();
    }

    public int nextStage() {
        if(!hasNext()){
            throw new UnsupportedOperationException("Cannot call next Stage on an empty stack");
        }
        return phases.pop();
    }

    public void flush(){
        phases.removeAllElements();
    }

    public void phaseRandomizer(int max, int... phaseType){
        if(phaseType.length < 0){
            throw new UnsupportedOperationException("PhaseType cannot be less than 0");
        }

        int currentIndex = phaseType.length, temporaryValue, randomIndex;

        while (0 != currentIndex){
            randomIndex = (int) Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            temporaryValue = phaseType[currentIndex];
            phaseType[currentIndex] = phaseType[randomIndex];
            phaseType[randomIndex] = temporaryValue;
        }

        int maxIndex;
        if(max > 0 && max < phaseType.length){
            maxIndex = max;
        } else {
            maxIndex = phaseType.length;
        }

        for(int i = 0; i < maxIndex; i++){
            phases.push(phaseType[i]);
        }
    }
}
