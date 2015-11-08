
package quizserver;

import QuizUtils.Utils;
import com.restfb.types.User;
import com.shephertz.app42.server.idomain.BaseZoneAdaptor;
import com.shephertz.app42.server.idomain.HandlingResult;
import com.shephertz.app42.server.idomain.IRoom;
import com.shephertz.app42.server.idomain.IUser;
import com.shephertz.app42.server.idomain.IZone;

//import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.mina.core.buffer.IoBuffer;
import org.json.JSONArray;
import org.json.JSONObject;
import quizcommon.QuizResponseCode;
//import com.mongodb.*;

/**
 * BaseZoneAdaptor handles all the zone specific requests like creation and deletion of rooms, etc. 
 * It also creates a MongoDB Client instance that is shares across the VM.
 * It also handles the authorisation requests. Whenever a client connects to a server,
 * handleAddUserRequest() is invoked. There is a special method named handleTimerTick() 
 * which is repeatedly called after a predefined time period (defined in AppConfig.json). 
 * 
 * @author Julian Wreford
 */
public class QuizZoneAdaptor extends BaseZoneAdaptor 
{

    IZone zone;

    public QuizZoneAdaptor(IZone zone) 
    {
        this.zone = zone;

    }

    /** 
     * Invoked when a user is removed from the list of users of the zone. 
     * This happens when the client disconnects from AppWarp. 
     *  
     * @param user the user being removed. 
     */ 
    @Override 
    public void onUserRemoved(IUser user)
    {
        System.out.println("QuizZoneAdaptor onUserRemoved "+user.getName());
        IRoom room = user.getLocation();
        if(room!=null)
        {
            QuizRoomAdaptor adaptor = (QuizRoomAdaptor) room.getAdaptor();
            adaptor.onUserLeaveRequest(user);
        }
    }
    
    /** 
     * Invoked when an admin room is deleted from the dashboard. 
     *  
     * @param room the room being removed 
     */  
    @Override
    public void onAdminRoomDeleted(IRoom room) 
    {
    
    }
    
    /** 
     * Invoked when a room is added from the admin dashboard. This is also invoked when the  
     * server is started and the previously created admin rooms are added to the zone. 
     *  
     * @param room the room being added. 
     */ 
    @Override
    public void onAdminRoomAdded(IRoom room) 
    {
        System.out.println("Room Created " + room.getName() + " with ID " + room.getId());
    }

    /** 
     * Invoked when a create room request is received from the client. 
     *  
     * By default this will result in a success response sent back to the user, 
     * the room will be added to the list of rooms and a room created notification 
     * will be sent to all the subscribers of the lobby. 
     *  
     * @param user the user who has sent the request. Null if received from admin dashboard 
     * @param room the room being created 
     * @param result use this to override the default behavior 
     */
    @Override
    public void handleCreateRoomRequest(IUser user, IRoom room, HandlingResult result) 
    {
        System.out.println("Room Created " + room.getName() + " " + user.getName() + " with ID " + room.getId());
        room.setAdaptor(new QuizRoomAdaptor(this.zone, room));
    }

    /** 
     * Invoked when a private chat request is received from an online user 
     * for another user who is also online. By default the server will send 
     * a success response back to the sender and a private chat notification  
     * to the receiver. 
     *  
     * @param sender 
     * @param toUser 
     * @param result  
     */ 
    @Override
    public void handlePrivateChatRequest(IUser sender, IUser toUser, HandlingResult result)
    {
        System.out.println("Sending Message " + sender.getName() + " to " + toUser.getName());
    }

    /** 
     * Invoked when a delete room request is received from the client. 
     *  
     * By default this will result in a success response sent back to the user, 
     * the room will be removed from the list of rooms and a room deleted notification 
     * will be sent to all the subscribers of the lobby. 
     *  
     * @param user the user who has sent the request. Null if received from admin dashboard 
     * @param room the room being created 
     * @param result use this to override the default behaviour 
     */
    @Override
    public void handleDeleteRoomRequest(IUser user, IRoom room, HandlingResult result) 
    {
    }

    /** 
     * Invoked when connect request is received from a client. 
     *  
     * By default the user will be added to the zone and if user with the  
     * same name exists that user will be disconnected. 
     *  
     * @param user the user who has sent the connect request. 
     * @param authData the authdata passed in the connect request 
     * @param result use this to override the default behaviour 
     */ 
    @Override
    public void handleAddUserRequest(IUser user, String authData, HandlingResult result) 
    { 
    	System.out.println("In handleAddUserRequest()"+ result);
    	// Below method of finding user fb friend in zone is totally 
    	// slow and I will need to re-write
    	try 
    	{
            /*If user had joined using his facebook account this function will make
             *list of friends those are playing this game in current zone
             */
            System.out.println("Add user request " + user.getName());
            if (!authData.equals("")) 
            {
                user.setCustomData(authData);
                try 
                {
                    JSONObject statObj = new JSONObject(user.getCustomData());
                    // Get list of user fb friends
                    List fbFriends = Utils.getFacebookFriends(statObj.getString("AccessToken"));
                    // Create a list of IUsers for fb friends
                    ArrayList<IUser> FBFriendsInMyZone = new ArrayList<IUser>();
                    // Create a list of users in this zone
                    Iterator zoneLstIterator = this.zone.getUsers().iterator();
                    // Loop for each user in this zone
                    while (zoneLstIterator.hasNext()) 
                    {
                    	// Create a IUser object for each user in zone
                        IUser zoneUser = (IUser) zoneLstIterator.next();
                        // Get user fb id
                        String fbId = new JSONObject(zoneUser.getCustomData()).getString("FacebookId");
                        // Create an iterator for user fb friends from user fb firend list
                        Iterator lstIterator = fbFriends.listIterator();
                        // Loop for each user fb friend
                        while (lstIterator.hasNext()) 
                        {
                        	// Check if the current zone user is user fb friend as we loop through
                            User fbuser = (User) lstIterator.next();
                            if (fbId.equals(fbuser.getId())) 
                            {
                            	// It is user fb friend so add them to our friend list
                                FBFriendsInMyZone.add(zoneUser);
                            }
                        }
                    }
                    // Create a JSON user array
                    JSONArray usersArray = new JSONArray();
                    // Loop for each user friend we found in zone
                    for (int i = 0; i < FBFriendsInMyZone.size(); i++) 
                    {
                        JSONObject objPayload = new JSONObject();
                        // Add user fb friend name to JSONObject
                        objPayload.put("username", FBFriendsInMyZone.get(i).getName());
                        // Add user fb friend custom data and fb id to JSONObject
                        objPayload.put("facebookId", new JSONObject(FBFriendsInMyZone.get(i).getCustomData()).getString("FacebookId"));
                        // Add each user fb friend JSONObject to our JSONArray
                        usersArray.put(objPayload);
                    }
                    // Create byte array of user fb friend list
                    int Length = 40 + usersArray.toString().getBytes().length;
                    IoBuffer buf = IoBuffer.allocate(Length);
                    buf.setAutoExpand(true);
                    buf.put(QuizResponseCode.FBFRIENDLIST);
                    buf.putInt(usersArray.toString().getBytes().length);
                    buf.put(usersArray.toString().getBytes());
                    // Broadcast user fb friend list byte array to client
                    user.SendUpdatePeersNotification(buf.array(), false);
                } 
                catch (Exception e) 
                {
                }
            }
        } 
    	catch (Exception e) 
    	{
        }
    }
    
    /** 
     * Invoked when a recover request is sent by a client with in the allowance 
     * time set while first connecting. 
     *  
     * By default the user session will be successfully resumed and a response 
     * will be sent back to the user. 
     *  
     * @param user the user who is attempting to recover 
     * @param authData the authData sent by the client 
     * @param result use this to override the default behavior  
     */  
    public void handleResumeUserRequest(IUser user, String authData, HandlingResult result)
    {
    	
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
    public void onTimerTick(long time) 
    {
    	
    }
    
    /** 
     * Invoked when a user is paused. This happens when a client's connection 
     * breaks without receiving a disconnect request and the client has set  
     * a recovery allowance period. 
     *  
     * @param user  
     */  
    public void onUserPaused(IUser user) 
    {
    	
    }
}