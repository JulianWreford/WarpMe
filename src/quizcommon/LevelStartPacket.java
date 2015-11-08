
package quizcommon;

import java.util.ArrayList;

/**
 *
 * @author Julian Wreford
 */
public class LevelStartPacket 
{
   public Level currentLevel;
   public QuestionPacket firstQuestion;

    public LevelStartPacket(Level currentLevel, ArrayList<String> usersList, QuestionPacket firstQuestion)
    {
        this.currentLevel = currentLevel;
        this.firstQuestion = firstQuestion;
    }
   
    public Level getCurrentLevel() 
    {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) 
    {
        this.currentLevel = currentLevel;
    }

    public QuestionPacket getFirstQuestion() 
    {
        return firstQuestion;
    }

    public void setFirstQuestion(QuestionPacket firstQuestion) 
    {
        this.firstQuestion = firstQuestion;
    }
  
}
