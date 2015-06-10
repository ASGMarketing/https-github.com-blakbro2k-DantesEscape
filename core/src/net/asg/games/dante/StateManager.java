package net.asg.games.dante;



import net.asg.games.dante.models.GameLevelState;
import net.asg.games.dante.screens.GameScreenState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class StateManager {
	public static class StateBundle {
		public GameScreenState gameScreenState;
		//public GameLevelState GameLevelState;
	}
	
	public void persist(GameScreenState gameScreenState/*, GameLevelState gameLevelState*/) {
        FileHandle stateDataFile = Gdx.files.local(Constants.STATE_DATA_FILE);
        
        StateBundle stBundle = new StateBundle();
        stBundle.gameScreenState = gameScreenState;
        //stBundle.GameLevelState = gameLevelState;
        
        Json json = new Json();
        String state = json.toJson(stBundle);
        stateDataFile.writeString(state, false);
        
        //Gdx.app.log("GameScreen", state);
	}
	
	public StateBundle retrieveState() {
		FileHandle stateDataFile = Gdx.files.local(Constants.STATE_DATA_FILE);
		if( stateDataFile.exists() ) {
			Json json = new Json();
			try {
				String stateStr = stateDataFile.readString();
                return json.fromJson(StateBundle.class, stateStr );
            } catch( Exception e ) {
                Gdx.app.error( "StateManager", 
                		"Unable to parse existing game screen state data file", e );
            }
		}
		return null;
	}
	
}
