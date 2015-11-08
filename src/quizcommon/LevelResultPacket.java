
package quizcommon;

import java.util.ArrayList;

/**
 *
 * @author Julian Wreford
 */
public class LevelResultPacket 
{
    public ArrayList<LevelResult> UsersResults;
    public Level CurrentLevel;

    public LevelResultPacket(ArrayList<LevelResult> UsersResults, Level CurrentLevel) 
    {
        this.UsersResults = UsersResults;
        this.CurrentLevel = CurrentLevel;
    }

    public ArrayList<LevelResult> getUsersResults() 
    {
        return UsersResults;
    }

    public void setUsersResults(ArrayList<LevelResult> UsersResults) 
    {
        this.UsersResults = UsersResults;
    }

    public Level getCurrentLevel() 
    {
        return CurrentLevel;
    }

    public void setCurrentLevel(Level CurrentLevel) 
    {
        this.CurrentLevel = CurrentLevel;
    }
}
