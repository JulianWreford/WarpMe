
package quizserver;

import QuizUtils.Utils;

//import com.mongodb.MongoClient;
import com.shephertz.app42.server.AppWarpServer;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Starts our AppWarp Apache Tomcat server
 * 
 * @author Julian Wreford
 */
public class QuizServer 
{
    
    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) 
    {
		
		
        // There are three main components in here namely Server, Zone and Room.
    	System.out.println("Starting server...");
    	// Find system paths for reading packaged files
        String appconfigPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "AppConfig.json";
        // Start server and passes it new instance of QuizServerAdapter and path to AppConfig.JSON file
		boolean started = AppWarpServer.start(new QuizServerAdaptor(), appconfigPath);
        System.out.println("AppWarp server instance created...");
        
        // Here we need to establish database connection
        // query database and return JSON files and store
        // them in an array for broadcasting to clients
        try 
        {
        	System.out.println("Reading JSON Levels files...");
        	// Read the level information from text file and store data a JSON object in lJson
            JSONObject lJson = Utils.ReadJsonFile("Levels.txt");
            // Store above JSON object as a level array in static Utils variable LevelJson
            Utils.LevelJson = lJson.getJSONArray("Levels"); // Levels = JSON key
            // Store above JSON object 
            Utils.delayInBetweenTheLevels = lJson.getInt("delayInBetweenTheLevels"); // delayInBetweenTheLevels = key
            
            // Read questions and answers and store them in Utils variables
            for (Integer quiztype = 0; quiztype < Utils.TotalQuizTypes; quiztype++) 
            {
                Utils.QuestionArrayPerLevel.add(new ArrayList<JSONArray>());
                Utils.AnswerArrayPerLevel.add(new ArrayList<JSONArray>());
                for (Integer i = 0; i < Utils.LevelJson.length(); i++) 
                {
                    JSONObject json = Utils.ReadJsonFile("Questions_" +quiztype.toString()+ i.toString() + ".txt");
                    System.out.println("Questions_" +quiztype.toString()+ i.toString() + ".txt");
                    Utils.QuestionArrayPerLevel.get(quiztype).add(json.getJSONArray("Questions"));
                    Utils.AnswerArrayPerLevel.get(quiztype).add(json.getJSONArray("Answers"));
                }
            }
            System.out.println("Finished reading JSON Levels files...");
            System.out.println("Waiting for client connections...");
        } 
        catch (Exception e) 
        {
        	System.out.println("Exception: " + e.getMessage());
        }
    }
}
