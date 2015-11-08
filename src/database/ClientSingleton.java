package database;

import java.net.UnknownHostException;
import java.util.Collections;


import com.mongodb.*;
/**
 * Singleton class provide single mongo client reference across our application.
 * 
 * @author Julian Wreford
 */
public class ClientSingleton 
{
	// Client instance singleton as volatile (modified by different threads &
	// not cached locally: all reads and writes go straight to "main memory")
	// Possibly remove volatile keyword as it's an unnecessary slow-down,
	// Synchronised flushes cache anyway.
	private volatile static ClientSingleton uniqueInstance;
	// The MongoClient class is designed to be thread safe and shared among threads. 
    // We create only 1 instance for our given database cluster and use it across
    // our application.
	private MongoClient mongoClient;
	private MongoClientOptions options;
	private MongoCredential credential;
	
	private final String password = "afml17jzjd4fbbr200bzofu0e3mxwq0i";
	private final String host = "54.213.93.154";
	private final int port = 38180;

	/**
	 * 
	 */
	private ClientSingleton() 
	{
		// Setup client credentials for DB connection (user, db name & password)
		credential =  MongoCredential.createCredential("aic66mn7zcx1ms1y", "QuizitTVMongoDB", password.toCharArray());
		
		// Setup client options
        // Don't to leave socketTimeout and connectTimeout at defaults (eg infinite) if connection hangs 
        // for some reason will get stuck forever!
        // Set number of milliseconds the driver will wait before a connection attempt is aborted, for connections 
		// made through a Platform-as-a-Serivce (PaaS) it is advised to have a higher timeout (e.g. 25 seconds)
        // Set number of milliseconds the driver will wait for a response from the server for all types of 
		// requests (queries, writes, commands, authentication, etc.).
		// ConnectionsPerHost
		// ThreadsAllowedToBlockForConnectionMultiplier maximum number of threads that may be waiting 
		// for a connection to become available from the pool.
        options = MongoClientOptions.builder()
        		.connectTimeout(25000)
        		.socketTimeout(60000)
        		.connectionsPerHost(100)
        		.threadsAllowedToBlockForConnectionMultiplier(5)
                .build();
        try 
        {
        	System.out.println("Initialising MongoDB connection...");
        	// Maybe propagate the exception upwards <-----------------------------------------------------------------
        	// Create client (server address(host,port), credential, options)
			mongoClient = new MongoClient(new ServerAddress(host, port), 
					Collections.singletonList(credential),
					options);
			System.out.println("Initialisation to MongoDB Success...");
		} 
        catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Double checked dispatch method to initialise our client singleton class
	 * 
	 */
	public static ClientSingleton getInstance()
	{
		if(uniqueInstance == null)
		{
			synchronized (ClientSingleton.class)
			{
				if(uniqueInstance == null)
				{
					uniqueInstance = new ClientSingleton();
				}
			}
		}
		return uniqueInstance;
	}
	
	/**
	 * @return our mongo client
	 */
	public MongoClient getClient() {
		return mongoClient;
	}

}
