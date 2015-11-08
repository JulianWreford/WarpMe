
package quizcommon;
/**
 *
 * @author Julian Wreford
 */
public class QuizElement 
{
   public enum Type 
   {
        Text,
        Image,
        Sound
    }
   public Type ElementType;
   public String Value;
   // Constructor
   public QuizElement(Type type, String value) 
   {
        ElementType = type;
        Value = value;
    }
   
   public static QuizElement buildQuizElement()
   {
	   return null;
   }
}
