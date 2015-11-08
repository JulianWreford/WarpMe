
package quizcommon;

import com.shephertz.app42.server.idomain.IUser;
import java.util.ArrayList;

/**
 *
 * @author Julian Wreford
 */
public class UserStatus 
{

    private IUser User;
    private int UserCurrentLevel;
    private int ScoreTillLastLevel;
    private ArrayList<Integer> userScorePerLevel;   
    private ArrayList<ArrayList<QuizAnswer>> AnswersPerLevel;

   
    public UserStatus(IUser User, int UserCurrentLevel, int ScoreTillLastLevel, ArrayList<Integer> userScorePerLevel,ArrayList<ArrayList<QuizAnswer>> AnswersPerLevel) 
    {
        this.User = User;
        this.UserCurrentLevel = UserCurrentLevel;
        this.ScoreTillLastLevel = ScoreTillLastLevel;
        this.userScorePerLevel = userScorePerLevel;
        this.AnswersPerLevel = AnswersPerLevel;
    }
    
    
    public int getScoreTillLastLevel() 
    {
    	System.out.println("UserStatus getScoreTillLastLevel: " + ScoreTillLastLevel);
        return ScoreTillLastLevel;
    }

    public void setScoreTillLastLevel(int ScoreTillLastLevel) 
    {
    	System.out.println("UserStatus setScoreTillLastLevel: " + ScoreTillLastLevel);
        this.ScoreTillLastLevel = ScoreTillLastLevel;
    }
   
    public int getUserCurrentLevel() 
    {
    	System.out.println("UserStatus getUserCurrentLevel: " + UserCurrentLevel);
        return UserCurrentLevel;
    }

    public void setUserCurrentLevel(int UserCurrentLevel) 
    {
    	System.out.println("UserStatus setUserCurrentLevel: " + UserCurrentLevel);
        this.UserCurrentLevel = UserCurrentLevel;
    }

    public IUser getUser() 
    {
    	System.out.println("UserStatus getUser: " + User.toString());
        return User;
    }

    public void setUser(IUser User) 
    {
    	System.out.println("UserStatus setUser: " + User.toString());
        this.User = User;
    }

    public ArrayList<ArrayList<QuizAnswer>> getAnswersPerLevel() 
    {
    	System.out.println("UserStatus getAnswersPerLevel: ");
        return AnswersPerLevel;
    }

    public void setAnswersPerLevel(ArrayList<ArrayList<QuizAnswer>> AnswersPerLevel) 
    {
    	System.out.println("UserStatus setAnswersPerLevel");
        this.AnswersPerLevel = AnswersPerLevel;
    }

    public ArrayList<Integer> getUserScorePerLevel() 
    {
    	System.out.println("UserStatus getUserScorePerLevel");
        return userScorePerLevel;
    }

    public void setUserScorePerLevel(ArrayList<Integer> userScorePerLevel) 
    {
    	System.out.println("UserStatus setUserScorePerLevel");
        this.userScorePerLevel = userScorePerLevel;
    }
}
