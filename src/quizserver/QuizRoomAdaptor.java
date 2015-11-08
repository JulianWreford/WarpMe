
package quizserver;

import QuizUtils.Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.shephertz.app42.server.domain.Room;
import com.shephertz.app42.server.idomain.BaseRoomAdaptor;
import com.shephertz.app42.server.idomain.HandlingResult;
import com.shephertz.app42.server.idomain.IRoom;
import com.shephertz.app42.server.idomain.IUser;
import com.shephertz.app42.server.idomain.IZone;
import com.sun.codemodel.JCodeModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import jsonPojo.QuestionEntry;

import org.apache.mina.core.buffer.IoBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import quizcommon.QuizAnswer;
import quizcommon.QuizRequestCode;
import quizcommon.QuizResponseCode;
import quizcommon.QuizType;
import quizcommon.UserStatus;

import com.mongodb.*;
import com.mongodb.util.JSON;

import database.ClientSingleton;
/**
 * BaseRoomAdaptor handles the room specific methods. It handles all the interactions taking place 
 * between the users in a room. It also contains handleTimerTick() similar to BaseZoneAdapter.
 * 
 * @author Julian Wreford
 */
public class QuizRoomAdaptor extends BaseRoomAdaptor 
{
	// Stuff for reading in JSON files
	private static String folder = System.getProperty("user.dir") + System.getProperty("file.separator") + "Resource" + System.getProperty("file.separator");
    
	private IZone izone;
    private IRoom gameRoom;
    ArrayList<UserStatus> UserStatusList = new ArrayList<UserStatus>();
    public static ArrayList<JSONArray> QuestionArrayofAttempedLevel = new ArrayList<JSONArray>();
    public static ArrayList<JSONArray> AnswerArrayofAttempedLevel = new ArrayList<JSONArray>();
    private int QuizTimerCount = 0;
    private int GameCurrentLevel = 0, GameCurrentQuestion = -1;
    private byte GAME_STATUS;
    private byte QUIZ_TYPE = QuizType.POLITICS;
    private int StartQuizFlag = 0;
    private boolean HasAnyUserLeftTheRoom=false;
    
    private DB mongoDatabase;
    private DBCollection mongoColl;
    private DBCollection mongoColl1;
    private DBObject dbObject;
    
    private Timer dbTimer;
    
    private ArrayList<JSONObject> ourQuestionsArray;
    private ArrayList<JSONObject> ourResultsArray;
    private QuestionEntry questionEntry;
    private JSONArray jsonResultsArray;
    // For timing our db connection and question creation
    private long start;
    private long end;
    
    /**
     * @param izone
     * @param room
     */
    //@SuppressWarnings("deprecation")
	public QuizRoomAdaptor(IZone izone, IRoom room) 
    {
        this.izone = izone;
        this.gameRoom = room;
        
		System.out.println("Getting database...");
        mongoDatabase = ClientSingleton.getInstance().getClient().getDB("QuizitTVMongoDB");
        System.out.println("Getting database success...");
        
        System.out.println("Getting mongo collection...");
        mongoColl = mongoDatabase.getCollection("EN_Collection.GOT");
        
        //mongoColl = mongoDatabase.getCollection("EN_Collection.");
        //System.out.println("Getting collection success...");
        
        /*List<DBObject> indexes = mongoColl.getIndexInfo();
        for(DBObject i : indexes)
        {
        	System.out.println("Index information: " + i.toString());//<---- DISPLAY INDEX INFORMATION
        }*/
        //mongoColl.drop();//   <-------------------------------------- DROP COLLECTION
        //System.out.println("Getting first document...");
        //System.out.println("Number of documents in collection: " + mongoColl.count()); // <---- COUNTS DOCUMENTS IN COLLECTION
        //start = System.currentTimeMillis();
        //BasicDBObject index = new BasicDBObject("season.questions.questionEntry.metaTags",1);
        //mongoColl.createIndex(index); // <----------------------------- CREATE INDEX (slightly faster than no index)
        //mongoColl.ensureIndex(index);   // <------------------------- ENSURE INDEX
        //mongoColl.dropIndex(index);     // <------------------------- DROP INDEX
        
        /*DBCursor cursor = mongoColl1.find(); // <--------------------- REMOVES/COPIES ALL DOCUMENTS IN COLLECTION
        try
        {
    		while (cursor.hasNext()) 
    		{
    			//mongoColl.remove(cursor.next());
    			mongoColl.insert(cursor.next());
    	}finally{
    		cursor.close();
    	}*/
    		
    	
    	//System.out.println("Number of documents in collection: " + mongoColl.count()); // <---- COUNTS DOCUMENTS IN COLLECTION
    	
        /*DBCursor cursorDoc = mongoColl.find();  // <---------------- DISPLAY DOCUMENTS
        while (cursorDoc.hasNext()) {  
         System.out.println(cursorDoc.next());  
        }*/
       
        //System.out.println(dbObject);
        //System.out.println("Getting document success...");
        /*dbObject = mongoColl. findOne();        // <---------------- DISPLAY DOCUMENTS
        String s = JSON.serialize(dbObject);
        JSONObject json = null;
        try 
        {
			 json = new JSONObject(s);
			 //System.out.println(json.toString());
			 System.out.println(json.toString(4));
		} 
        catch (JSONException e1) 
        {
			e1.printStackTrace();
		}*/
        
        // Use this to insert our files into mongo db
        //insertJsonFiles();//  <------------------------------------ INSERT DOCUMENT

        
        //System.out.println("Getting all collections..."); // <----------DISPLAYS ALL COLLECTIONS
        //Set<String> tables = mongoDatabase.getCollectionNames();
        //for(String coll : tables)
        //{
        //	System.out.println("Collection Name: " + coll);
        //}
        //System.out.println("Getting all collections success...");
        
        //System.out.println("Before call to queryMetaTags");
        // Timer to test how long it takes to produce our 10 questions
        //start = System.currentTimeMillis();
        // Now we try and query our db
        //queryMetaTags("Relationships");//  <------------------------ QUERY COLLECTION
        //pullOut10Questions(); // <----------------------------- GET 10 QUESTIONS
        //pojoOurQuestions(); // <------------------------------- POJO OUR QUESTIONS
        //System.out.println("After call to queryMetaTags"); 
        //end = System.currentTimeMillis();
    	//double elapsedTime = ((end-start)/1000.0);
    	//System.out.println("Mongodb and question creation time: " + elapsedTime);
    	
    	
        GAME_STATUS = QuizConstants.STOPPED;
        try 
        {
            if (((Room) this.gameRoom).getProperties().get("QuizTopic").toString().equalsIgnoreCase("Politics")) 
            {
                QUIZ_TYPE = QuizType.POLITICS;
            } else {
                QUIZ_TYPE = QuizType.CRICKET;
            }
        } 
        catch (Exception e) 
        {
        }
    }

    
    // Query our collection documents metaTag elements for a matching string
    // @SuppressWarnings("deprecation")
	public void queryMetaTags(String query)
    {
		// Query to search all documents in current collection
        List<String> continentList = Arrays.asList(new String[]{query});
        DBObject matchFields = new 
           BasicDBObject("season.questions.questionEntry.metaTags", 
          new BasicDBObject("$in", continentList));
        DBObject groupFields = new BasicDBObject( "_id", "$_id").append("questions", 
           new BasicDBObject("$push","$season.questions"));
        //DBObject unwindshow = new BasicDBObject("$unwind","$show");
        DBObject unwindsea = new BasicDBObject("$unwind", "$season");
        DBObject unwindepi = new BasicDBObject("$unwind", "$season.questions");
        DBObject match = new BasicDBObject("$match", matchFields);
        DBObject group = new BasicDBObject("$group", groupFields); 
        
        ArrayList<DBObject> pipeline = new ArrayList<>();
        pipeline.add(unwindsea);
        pipeline.add(unwindepi);
        pipeline.add(match);
        pipeline.add(group);
        
        @SuppressWarnings("deprecation")
		AggregationOutput output = 
	    	mongoColl.aggregate(pipeline);
        //CommandResult output = (CommandResult)
        //mongoColl.aggregate(pipeline,new BasicDBObject("explain",true));
        //mongoColl.explainAggregate(unwindsea,unwindepi,match,group);
        
        String jsonString = null;
        JSONObject jsonObject = null;
        jsonResultsArray = null;
        ourResultsArray = new ArrayList<JSONObject>();
        
        // Loop for each document in our collection
        for (DBObject result : output.results())  
        {      	
            try 
            {
				// Parse our results so we can add them to an ArrayList
				jsonString = JSON.serialize(result);           	 
				jsonObject = new JSONObject(jsonString);
				jsonResultsArray = jsonObject.getJSONArray("questions");
				
				// Put each of our returned questionEntry elements into an ArrayList
				for (int i = 0; i < jsonResultsArray.length(); i++)
				{
					System.out.println("jsonResultsArray element (" + i + "): " + jsonResultsArray.getJSONObject(i).toString());
					ourResultsArray.add(jsonResultsArray.getJSONObject(i));			
				}
    		} 
            catch (JSONException e1) 
            {
    			e1.printStackTrace();
    		}
        }   
    }

	public void pullOut10Questions()
	{
		// Array to hold 10 random numbers between 0 and our results total
		ArrayList<Integer> ourRandomNumbersList = generate10RandomNumbersInRange(ourResultsArray.size());
		// Array to hold our 10 random questions from our results
		ourQuestionsArray = new ArrayList<JSONObject>();
		
		// Loop through each of our results in array
		for (int i = 0; i < ourResultsArray.size(); i++)
		{
			// Loop through our array holding our 10 random numbers
			for(int j = 0; j < ourRandomNumbersList.size(); j++)
			{
				// If our results array index equals one of our 10 random numbers
				if(ourRandomNumbersList.get(j) == i)	
				{
					// Then add that result to our final questionElement array
					ourQuestionsArray.add(ourResultsArray.get(i));
					//try { // Remove later it's for print test to console<---------------------------
					//	System.out.println("Our QuestionEntry from mongo: " + ourResultsArray.get(i).getString("questionEntry"));
					//} catch (JSONException e) {
					//	e.printStackTrace();
					//}
				}
			}
		}
	}
	
	//public void pojoOurQuestions(ArrayList<JSONObject> questionsArray)
	public void pojoOurQuestions()
	{
		// Copy our questions from ourQuestionsArray into our pojo's 
		// Loop for each JSONObject in ourQuestionsArray
		for(int i = 0; i < ourQuestionsArray.size(); i++)
		{
			try 
			{
				JSONObject jsonObject = ourQuestionsArray.get(i);
				String s = jsonObject.getString("questionEntry");
				// Need to put our question entries into our Pojo's
				questionEntry = new ObjectMapper().readValue(s, QuestionEntry.class);
				
				//System.out.println("Our questionEntry from pojo: " + questionEntry.toString());
				
			} catch (JSONException e) 
			{
				e.printStackTrace();
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// Return 10 random numbers in range
	public ArrayList<Integer> generate10RandomNumbersInRange(int range)
	{
	    Random rand = new Random();
	    int e;
	    int i;
	    int g = 10;
	    // Store random numbers is HashSet
	    HashSet<Integer> randomNumbers = new HashSet<Integer>();

	    for (i = 0; i < g; i++) 
	    {
	        e = rand.nextInt(range);
	        randomNumbers.add(e);
	        // Keep adding numbers until we reach 10
	        if (randomNumbers.size() <= 10) 
	        {
	            if (randomNumbers.size() == 10) 
	                g = 10;
	            g++;
	            randomNumbers.add(e);
	        }
	    }
	    // Return our random numbers as an ArrayList
	    ArrayList<Integer> al = new ArrayList<Integer>();
	    Iterator<Integer> iter = randomNumbers.iterator();
	    while(iter.hasNext())
	    {
	    	al.add(iter.next());
	    }
	    return al;
	}
	
    public void insertJsonFiles()
    {
        try
        {
        	System.out.println("In start insertJsonFiles");
	        // Insert whole show as 1 document
	        mongoColl = mongoDatabase.getCollection("EN_Collection.ShowList_Collection"); 
	        String s = readInJsonFile("ShowArrayList.txt");
	        System.out.println("String retruned by readInJsonFile: " + s);
	        
	        // Method 1
	        DBObject dbo = (DBObject)JSON.parse(s);
	        WriteResult result = mongoColl.insert(dbo);
	        System.out.println("Write Result: " + result.toString());
	        
	        // Method 2
	        //Object jsonObj = s; 
	        //Object o = com.mongodb.util.JSON.parse(jsonObj.toString());
	        //DBObject dbObj = (DBObject) o;
	        //WriteResult result = mongoColl.insert(dbObj);
	        //System.out.println("Write Result: " + result);
	        
	        // Method 3
	        //JSONArray jsonArray = new JSONArray(s);
	        //JSONObject jsonObject = new JSONObject();
	        //jsonObject.put("GameofThrones", jsonArray);
	        //DBObject bson = (DBObject) JSON.parse(jsonObject.toString());
	        //WriteResult result = mongoColl.insert(bson);
	        //System.out.println("Write Result: " + result);
	        
	        // Insert seasons as separate documents
	        //mongoColl = mongoDatabase.getCollection("GOT1");
	        //DBObject dbo1 = (DBObject)JSON.parse(readInJsonFile("GOT1.txt"));
	        //mongoColl.insert(dbo1);
	        System.out.println("In end insertJsonFiles");
        }
        catch (Exception e)
        {
        	System.out.println("Exception inserting documents: " + e.getMessage());
        }
    }
    
    
    public String readInJsonFile(String fileName)
    {
        try 
        {
        	System.out.println("Start readInJsonFile");
        	// First method preserves line breaks but more memory
        	// Useful for printing to console
        	byte[] encoded = Files.readAllBytes(Paths.get(folder + fileName));
        	System.out.println("End readInJsonFile");
        	return new String(encoded, Charset.defaultCharset());
        	
        	// Second method is more efficient because input buffer for decoding doesn't need to contain entire file
        	//List<String> lines = Files.readAllLines(Paths.get(folder + fileName), Charset.defaultCharset());
        	//String listLines = "";
        	//for (String s : lines)
        	//{
        	//    listLines += s + "\t";
        	//}
        	//System.out.println("End readInJsonFile");
        	//return listLines;
        	
        } 
        catch (Exception e) 
        {
            System.out.println("Exception readInJasonFile: " + e);
            return null;
        }
    }
    
    /** 
     * Invoked when a user leaves the room 
     *  
     * @param user the user who is leaving the room 
     */ 
    @Override
    public void onUserLeaveRequest(IUser user) 
    {
        StartQuizFlag = 0;
        HasAnyUserLeftTheRoom=true;
        System.out.println("QuizRoomAdaptor onUserLeaveRequest " + user.getName() + " left room " + user.getLocation().getId());
        UserStatusList.clear();
    }

    /** 
     * Invoked when a join request is received by the room and the number of 
     * joined users is less than the maxUsers allowed. 
     *  
     * By default this will result in a success response sent back the user,  
     * the user will be added to the list of joined users of the room and 
     * a user joined room notification will be sent back to all the subscribed  
     * users of the room. 
     *  
     * @param user the user who has sent the request 
     * @param result use this to override the default behaviour 
     */
    @Override
    public void handleUserJoinRequest(IUser user, HandlingResult result) 
    {	
    	System.out.println("In handleUserJoinRequest()");
        System.out.println(user.getName() + " joined room Request");
        if (UserStatusList.size() < gameRoom.getMaxUsers()) 
        {
            System.out.println(user.getName() + " joined room Request Success");
            UserStatus newUser = new UserStatus(user, 0, 0, new ArrayList<Integer>(), new ArrayList<ArrayList<QuizAnswer>>());
            UserStatusList.add(newUser);
        }
    }

    /** 
     * Invoked when a update peers request is received from the client in the room. 
     *  
     * This will be either a STARTQUIZ or ANSWERPACKET
     * By default this will trigger a success response back to the client and  
     * will broadcast a notification update to all the subscribers of the room. 
     *  
     * @param sender the user who has sent the request 
     * @param update the byte array sent by the user 
     * @param result use this to override the default behaviour 
     */ 
    @Override
    public void handleUpdatePeersRequest(IUser sender, byte[] update, HandlingResult result) 
    {
    	System.out.println("In handleUpdatePeersRequest" + " sender = " + sender.getName());
        try 
        {
            result.sendNotification = false;
            int size;
            byte[] fbInvitation;
            String fbObject;
            JSONObject invObject;
            IoBuffer buf = IoBuffer.allocate(update.length, false);
            buf.setAutoExpand(true);
            buf.put(update, 0, update.length);
            buf.flip();
            buf.position(0);
            byte bt = buf.get();
            switch (bt) 
            {
                case QuizRequestCode.STARTQUIZ:
                    StartQuizFlag++;
                    System.out.println("Received Start Quiz Packet" + StartQuizFlag + "RoomId " + this.gameRoom.getId()
                    		+ " sender = " + sender.getName());
                    break;
                case QuizRequestCode.ANSWERPACKET:
                    System.out.println("Answer Received " + bt);
                    for (int i = 0; i < UserStatusList.size(); i++) 
                    {
                        if (UserStatusList.get(i).getUser().getName().equalsIgnoreCase(sender.getName())) 
                        {
                        	// ID of the last question asked on client
                            int queID = buf.getInt();
                            // int representing the answer response to previously asked question
                            int ans = buf.getInt();
                            // If this answer is for the game current question
                            if (UserStatusList.get(i).getAnswersPerLevel().size() == GameCurrentLevel) 
                            {
                            	System.out.println("ANSWERPACKET if statement & getAnswersPerLevel().size() == GameCurrentLevel: " + GameCurrentLevel);
                            	// Then add new QuizAnswer element to their answered questions array for this game
                                UserStatusList.get(i).getAnswersPerLevel().add(new ArrayList<QuizAnswer>());
                            }
                            // Get the total number of questions now answered in this game
                            int currentSize = UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).size();
                            
                            QuizAnswer userAnswer = new QuizAnswer(queID, ans);
                            if (currentSize == 0) 
                            {
                            	System.out.println("ANSWERPACKET if statement & currentSize == 0");
                                UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).add(userAnswer);
                                updateAndBroadCastScore(i, queID - 1, ans);
                            } 
                            else if (UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).get(currentSize - 1).QuestionId != queID) 
                            {
                            	System.out.println("ANSWERPACKET else if getAnswersPerLevel().get(GameCurrentLevel).get(currentSize - 1).QuestionId != queID - queID = " + queID);          
                                UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).add(userAnswer);
                                updateAndBroadCastScore(i, queID - 1, ans);
                            }
                        }
                    }
                    break;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /** 
     * Invoked every time the Game loop timer is fired. The frequency of the 
     * tick can be modified through the AppConfig.json file. Do NOT perform 
     * blocking operations in this callback. 
     *  
     * @param time the difference, measured in milliseconds, between 
     * the current time and midnight, January 1, 1970 UTC as returned 
     * by System.currentTimeMillis() 
     */
    // Original Levels.txt
    // "timePerQuestion": 12
    // "delayInBetweenTheLevels":"2"
    @Override
    public void onTimerTick(long time) 
    {
        /*
         * A game when room full
         * or we can say max users are equals to joined users
         * Once the game has started it will send question on every 10 seconds
         */
    	System.out.println("In onTimerTick() and QuizTimerCount = " + QuizTimerCount);
    	try 
    	{
    		// A player has left the game so delete room
	    	if (HasAnyUserLeftTheRoom) 
	        {
	            HasAnyUserLeftTheRoom=false;
	            System.out.println("Deleting Room " + this.gameRoom.getId() + " " + this.izone.deleteRoom(gameRoom.getId()));
	            gameRoom=null;
	            GAME_STATUS = QuizConstants.STOPPED;
	        }
	        if (gameRoom!=null&&GAME_STATUS == QuizConstants.STOPPED && gameRoom.getJoinedUsers().size() == gameRoom.getMaxUsers() && gameRoom.getMaxUsers() == StartQuizFlag) 
	        {
	        	// Start the game when we have 2 players
	        	System.out.println("In onTimerTick() second if statement");
	            GAME_STATUS = QuizConstants.RUNNING;
	            QuizTimerCount=Utils.LevelJson.getJSONObject(GameCurrentLevel).getInt("timePerQuestion") * 2-1;
	            StartQuizFlag = 0;
	        } 
	        else if (gameRoom!=null&&GAME_STATUS == QuizConstants.RUNNING) 
	        {
	        	// Both players are still in the game so determine which packet should be sent next
	            QuizTimerCount++;
	            System.out.println("In onTimerTick() else if statement");
                if (GameCurrentLevel < Utils.LevelJson.length()) 
                {
                    int timeCount = Utils.LevelJson.getJSONObject(GameCurrentLevel).getInt("timePerQuestion") * 2;
                    if (QuizTimerCount == timeCount) 
                    {
                    	System.out.println("In onTimerTick() else if statement and QuizTimer == TomerCount");
                        int totalQuestion = Utils.LevelJson.getJSONObject(GameCurrentLevel).getInt("totalQuestions");
                        if (GameCurrentQuestion != -1) 
                        {
                            AddDefaultAnswers();
                        }
                        if (GameCurrentQuestion < (totalQuestion - 1)) 
                        {
                            SendQuestion();
                        } 
                        else 
                        {
                            SendLevelEndPacket();
                        }
                    }
                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /** 
     * Invoked when a chat request is received from the client in the room. 
     *  
     * By default this will trigger a success response back to the client and  
     * will broadcast a notification message to all the subscribers of the room. 
     *  
     *  
     * @param sender the user who has sent the request 
     * @param message the message that was sent 
     * @param result use this to override the default behavior 
     */  
    public void handleChatRequest(IUser sender, String message, HandlingResult result) 
    {
    }
    

    private void AddDefaultAnswers() 
    {
        /* if user had not submmited the last question answer then this function 
         *adds -1 as user answer and broadcast the scores
         */
    	System.out.println("In AddDefaultAnswers()");
        for (int i = 0; i < UserStatusList.size(); i++) 
        {
            if (UserStatusList.get(i).getAnswersPerLevel().size() == GameCurrentLevel) 
            {
                UserStatusList.get(i).getAnswersPerLevel().add(new ArrayList<QuizAnswer>());
            }
            int currentSize = UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).size();
            QuizAnswer userAnswer = new QuizAnswer(GameCurrentQuestion, -1);
            if (currentSize == 0) 
            {
                UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).add(userAnswer);
                updateAndBroadCastScore(i, GameCurrentQuestion, -1);
            } 
            else if (UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).get(currentSize - 1).QuestionId != GameCurrentQuestion) 
            {
                UserStatusList.get(i).getAnswersPerLevel().get(GameCurrentLevel).add(userAnswer);
                updateAndBroadCastScore(i, GameCurrentQuestion, -1);
            }
        }
    }

    // reset timer count
    // increment current game question count by 1
    private void SendQuestion() 
    {
    	System.out.println("In SendQuestion()");
        QuizTimerCount = 0;
        GameCurrentQuestion++;
        byte[] questionPacket = getNextQuestionPacket();
        for (int i = 0; i < UserStatusList.size(); i++) 
        {
            UserStatus user = UserStatusList.get(i);
            System.out.println("Sending Question to User "+user.getUser().getName().toString());
            user.getUser().SendUpdatePeersNotification(questionPacket, true);
        }
    }

    
    private void SendLevelEndPacket() 
    {
    	System.out.println("In SendLevelEndPacket()");
        QuizTimerCount = (QuizTimerCount-Utils.delayInBetweenTheLevels*2);
        try 
        {
            JSONArray usersArray = new JSONArray();
            for (int i = 0; i < UserStatusList.size(); i++) 
            {
                JSONObject objPayload = new JSONObject();
                objPayload.put("user", UserStatusList.get(i).getUser().getName());
                objPayload.put("currentLevelscore", UserStatusList.get(i).getUserScorePerLevel().get(GameCurrentLevel));
                int totalScore = UserStatusList.get(i).getScoreTillLastLevel() + UserStatusList.get(i).getUserScorePerLevel().get(GameCurrentLevel);
                UserStatusList.get(i).setScoreTillLastLevel(totalScore);
                objPayload.put("totalScore", UserStatusList.get(i).getScoreTillLastLevel());
                usersArray.put(objPayload);
            }
            IoBuffer buf = IoBuffer.allocate(48 + usersArray.length());
            buf.setAutoExpand(true);
            if (GameCurrentLevel < Utils.LevelJson.length() - 1) 
            {
                buf.put(QuizResponseCode.LEVELRESULT);
            } 
            else 
            {
                buf.put(QuizResponseCode.FINALRESULT);
            }
            buf.putInt(GameCurrentLevel);
            buf.putInt(usersArray.toString().getBytes().length);
            buf.put(usersArray.toString().getBytes());
            for (int i = 0; i < UserStatusList.size(); i++) 
            {
                UserStatus user = UserStatusList.get(i);
                user.getUser().SendUpdatePeersNotification(buf.array(), true);
            }
            GameCurrentLevel++;
            GameCurrentQuestion = -1;
        } 
        catch (Exception e) 
        {
        }
    }

    
    private byte[] getNextQuestionPacket() 
    {
    	System.out.println("In getNextQuestionPacket()");
        try 
        {
            JSONObject question = Utils.getQuestionJsonObject(QUIZ_TYPE, GameCurrentLevel, GameCurrentQuestion);
            IoBuffer buf = IoBuffer.allocate(48 + question.length());
            buf.setAutoExpand(true);
            if (GameCurrentQuestion < 1) 
            {
                buf.put(QuizResponseCode.LEVELSTART);
                buf.putInt(GameCurrentLevel);
            } 
            else 
            {
                buf.put(QuizResponseCode.QUESTIONPACKET);
                JSONObject LastQuestionAnswer = Utils.getAnswerJsonObject(QUIZ_TYPE, GameCurrentLevel, GameCurrentQuestion - 1);
                int ans = LastQuestionAnswer.getInt("Answer");
                System.out.println("Last Question Answer " + ans);
                buf.putInt(ans);
            }
            buf.putInt(question.toString().getBytes().length);
            buf.put(question.toString().getBytes());
            System.out.println("Send Question Packet of " + buf.array().length);
            return buf.array();
        } 
        catch (Exception e) 
        {
            return null;
        }
    }

    private void updateAndBroadCastScore(int userIndex, int queID, int ans) 
    {
    	System.out.println("In updateAndBroadcastScore()");
        try 
        {
            System.out.println("Check answer and broadcast score");
            JSONObject LastQuestionAnswer = Utils.getAnswerJsonObject(QUIZ_TYPE, GameCurrentLevel, queID);
            int currentSize = UserStatusList.get(userIndex).getUserScorePerLevel().size();
            if (currentSize > GameCurrentLevel) 
            {
                int score = UserStatusList.get(userIndex).getUserScorePerLevel().remove(currentSize - 1);
                if (LastQuestionAnswer.getInt("Answer") == ans) 
                {
                    UserStatusList.get(userIndex).getUserScorePerLevel().add(score + 30);
                } 
                else if (ans == -1) 
                {
                    UserStatusList.get(userIndex).getUserScorePerLevel().add(score);
                } 
                else 
                {
                    UserStatusList.get(userIndex).getUserScorePerLevel().add(score - 10);
                }
            } 
            else 
            {
                //it is for 1st question of cuurentLevel
                if (LastQuestionAnswer.getInt("Answer") == ans) 
                {
                    UserStatusList.get(userIndex).getUserScorePerLevel().add(30);
                } 
                else if (ans == -1) 
                {
                    UserStatusList.get(userIndex).getUserScorePerLevel().add(0);
                } 
                else 
                {
                    UserStatusList.get(userIndex).getUserScorePerLevel().add(-10);
                }
            }
            JSONObject objPayload = new JSONObject();
            objPayload.put("user", UserStatusList.get(userIndex).getUser().getName());
            objPayload.put("currentLevelscore", UserStatusList.get(userIndex).getUserScorePerLevel().get(GameCurrentLevel));
            IoBuffer buf = IoBuffer.allocate(48 + objPayload.toString().getBytes().length);
            buf.setAutoExpand(true);
            buf.put(QuizResponseCode.UPDATESCORE);
            buf.putInt(objPayload.toString().getBytes().length);
            buf.put(objPayload.toString().getBytes());
            for (int i = 0; i < UserStatusList.size(); i++) 
            {
                UserStatus user = UserStatusList.get(i);
                user.getUser().SendUpdatePeersNotification(buf.array(), true);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    /** 
     * Invoked when an updateProperties request is received from the client.  
     * The server has already validated the request meets its criteria of  
     * property size and lock validation. If any of the server checks fail, then 
     * this will not be invoked and a error response will be sent back to the  
     * client. 
     *  
     * By default, the server will accept the request and modify the room's 
     * properties as in the request. It will then send a success response back to 
     * the sender and a user change properties notification will be sent to all 
     * the subscribed users of the room. 
     *  
     * @param user sender of the request 
     * @param addOrUpdateMap the <key, value> map of properties to be added or updated 
     * @param removeKeysArray the array of keys for properties to be removed 
     * @param result use this to override the default behavior 
     */  
    public void handlePropertyChangeRequest(IUser sender, HashMap<String, Object> addOrUpdateMap, ArrayList<String> removeKeysList, HandlingResult result) 
    {
    }
    
    /** 
     * Invoked when a lockProperties request is received from the client. 
     * The server has already validated the request meets its criteria of  
     * property size and lock validation. If any of the server checks fail, then 
     * this will not be invoked and a error response will be sent back to the  
     * client. 
     * 
     * By default, the server will accept the request and modify the room's 
     * properties as in the request. It will then send a success response back to 
     * the sender and a user change properties notification will be sent to all 
     * the subscribed users of the room. 
     *  
     * @param sender sender of the request 
     * @param locksToUpdate the <key, value> pairs to be updated and locked by the sender 
     * @param result use this to override the default behavior 
     */  
    public void handleLockPropertiesRequest(IUser sender, HashMap<String, Object> locksToUpdate, HandlingResult result) 
    {
    }
    
    /** 
     * Invoked when a lockProperties request is received from the client. 
     * The server has already validated the request meets its criteria of  
     * lock validation (i.e. the sender holds the locks its requesting to unlock).  
     * If the server checks fail, then this will not be invoked and a error response  
     * will be sent back to the client. 
     * 
     * By default, the server will accept the request and modify the room's 
     * lock table as in the request. It will then send a success response back to 
     * the sender and a user change properties notification will be sent to all 
     * the subscribed users of the room. 
     *  
     * @param sender sender of the request 
     * @param unlockKeysArray array of keys of the properties to be unlocked 
     */  
    public void onUnlockPropertiesRequest(IUser sender, ArrayList<String> unlockKeysList) 
    {
    }
    
    /** 
     * Invoked when a subscribe room request is received by the room. 
     *  
     * By default this will result in a success response sent back the user,  
     * the user will be added to the list of subscribed users of the room. 
     *  
     * @param sender sender of the request 
     * @param result use this to override the default behaviour 
     */  
    public void handleUserSubscribeRequest(IUser sender, HandlingResult result)  
    {
    	System.out.println("An  subscribe room request is received by the room" + gameRoom.getId());
    }
    
    /** 
     * Invoked when a user sends an unsubscribe request  
     *  
     * @param sender sender of the request 
     */  
    public void onUserUnsubscribeRequest(IUser sender)  
    {
    	System.out.println("An  unsubscribe room request is received by the room" + gameRoom.getId());
    }
    
}
