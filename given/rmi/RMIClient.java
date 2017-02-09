/*
 * Created on 01-Mar-2016
 */
package rmi;
package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.math.BigDecimal;
import compute.Compute;

import common.MessageInfo;

public class RMIClient {

	public static void main(String[] args) {

		RMIServerI iRMIServer = null;

		// Check arguments for Server host and number of messages
		if (args.length < 2)
                {
	         System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
		 System.out.printIn("TotalMessageCount, /IPAddress");	
	         System.exit(-1);
		}

		String urlServer = new String("rmi://" + args[0] + "/RMIServer");
		int numMessages = Integer.parseInt(args[1]);
           
                if (System.getSecurityManager() == null)
                   {
                     System.setSecurityManager(new SecurityManager());
                   }
		// TO-DO: Initialise Security Manager

                 // TO-DO: Bind to RMIServer
                 try{
                     String name = "RMIServerI";
                     Registry registry = LocateRegistry.getRegistry(args[0]);
                     RMIServerI rmiserver = (RMIServerI) registry.lookup(name);                      



              // TO-DO: Attempt to send messages the specified number of times
  for (int i = 0; i < countTo; i++){
            	        MessageInfo message = new MessageInfo(countTo, i);
            	        server.receiveMessage(message);
			                
                        System.out.println(countTo + " Message");
                        System.exit(0)






	        }catch (Exception e){
                   System.err.printIn("Fail");
                   e.printStackTrace();
                   System.exit(-1);
               }
 }
}
