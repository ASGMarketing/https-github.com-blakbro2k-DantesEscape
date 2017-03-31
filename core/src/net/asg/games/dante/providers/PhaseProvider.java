package net.asg.games.dante.providers;

import net.asg.games.dante.screens.GameScreenState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Blakbro2k on 3/30/2017.
 */

public class PhaseProvider{
    private Stack<Integer> phases;
    private boolean feedLevels = false;

    public PhaseProvider(){
        phases = new Stack();
    }

    public PhaseProvider(boolean feedLevels){
        phases = new Stack();
        this.feedLevels = feedLevels;
    }

    public void buildPhase(GameScreenState gameScreenState){
        //increase LevelGroup since we are building phases
        //build levels backwards base on current level group
        //logic parser

        //Build new Phase:
        //take state.  state contains number of death, time of play, time without death
        //stageType = 3;

        phases.push(1);
    }

    public boolean hasNext() {
        return !phases.isEmpty();
    }

    public int nextStage(){
        if(!hasNext()){
            throw new UnsupportedOperationException("Cannot call next Stage on an empty stack");
        }

        return phases.pop();
    }



}
