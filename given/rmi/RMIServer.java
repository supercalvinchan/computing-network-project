/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import java.rmi.AccessException;



import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;

	public RMIServer() throws RemoteException {
	super();
                 }

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// TO-DO: On receipt of first message, initialise the receive buffer
                    if (receivedMessages == null) 
                       {
			totalMessages = msg.totalMessages;
                        receivedMessages = new int[msg.totalMessages];
		        }
                // TO-DO: Log receipt of the message
                   receivedMessages[msg.messageNum] = 1;
		// TO-DO: If this is the last expected message, then identify
		//        any missing messages

	           if(msg.messageNum == totalMessages - 1)
                   {
                       System.out.println("last expected message, then id any missing messages");
                   }
}


            public static void main(String[] args) {
             // TO-DO: Initialise Security Manager
                 if(System.getSecurityManager() == null)
                {
                    System.setSecurityManager(new SecurityManager()); 
                }
		// TO-DO: Instantiate the server class
               
		// TO-DO: Bind to RMI registry
                 try{
               RMIServerI server = new RMIServer();
               rebindServer("RMIServerI", server);       
                 }catch(Exception e)
              {
                
                e.printStackTrace();
                System.exit(-1);
             }
	}

	protected static void rebindServer(String serverURL, RMIServerI server) {
         try{
                LocateRegistry.createRegistry(2000);
                       Naming.rebind(serverURL,server);

           }catch(RemoteException e)
              {
                System.out.println("can not bind becasue of initializing");
                e.printStackTrace();
                System.exit(-1);
             }catch(MalformedURLException e)
              {
                System.out.println("can not bind because of malformed URL");
                e.printStackTrace();
                System.exit(-1);
             }
		// TO-DO:
		// Start / find the registry (hint use LocateRegistry.createRegistry(...)
		// If we *know* the registry is running we could skip this (eg run rmiregistry in the start script)

		// TO-DO:
		// Now rebind the server to the registry (rebind replaces any existing servers bound to the serverURL)
		// Note - Registry.rebind (as returned by createRegistry / getRegistry) does something similar but
		// expects different things from the URL field.
	}
}
