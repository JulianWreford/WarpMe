
package quizcommon;
/**
 *
 * @author Julian Wreford
 */
public class QuizAnswer 
{
    public int QuestionId;
    public int Answer;

    public int getQuestionId() 
    {
        return QuestionId;
    }

    public void setQuestionId(int QuestionId) 
    {
        this.QuestionId = QuestionId;
    }

    public int getAnswer() 
    {
        return Answer;
    }

    public void setAnswer(int Answer) 
    {
        this.Answer = Answer;
    }

    public QuizAnswer(int QuestionId, int Answer) 
    {
        this.QuestionId = QuestionId;
        this.Answer = Answer;
    }
}
