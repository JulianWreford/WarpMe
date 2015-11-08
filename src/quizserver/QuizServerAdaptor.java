
package quizserver;

import com.shephertz.app42.server.idomain.BaseServerAdaptor;
import com.shephertz.app42.server.idomain.IZone;

import database.ClientSingleton;

/**
 * Handles the creation and deletion of zones
 * 
 * @author Julian Wreford
 */
public class QuizServerAdaptor extends BaseServerAdaptor
{
	
	/** 
	* Invoked when a new zone is added through the admin dashboard.  
	* This will also be invoked when the server instance is started  
	* and loads zones created in earlier launches (read from HSQLDB) 
	*  
	* @param zone the zone being added 
	*/  
    @Override
    public void onZoneCreated(IZone zone)
    {   
    	System.out.println("Starting server adapter...");
        System.out.println("Zone Created " + zone.getName() + " with key " + zone.getAppKey());
        zone.setAdaptor(new QuizZoneAdaptor(zone));
    }
    
    
    /** 
    * Invoked when a zone is deleted from the admin dashboard 
    *  
    * @param zone the zone being deleted 
    */  
    public void onZoneDeleted(IZone zone)  
    {
    	// Close our connection to MongoDB when zone is deleted
    	System.out.println("Closing MongoDB client connection...");
    	ClientSingleton.getInstance().getClient().close();
    	System.out.println("Closing MongoDB client success...");
    }
}
