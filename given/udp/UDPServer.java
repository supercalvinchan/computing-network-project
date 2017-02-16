/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import common.MessageInfo;

public class UDPServer {

	private DatagramSocket recvSoc;
	private int totalMessages = -1;
	private int[] receivedMessages;
	private boolean checkofreceived;

	private void run() {
		int	pacSize;//fine
		byte[]	pacData;//fine
		DatagramPacket 	pac;//fine

		// TO-DO: Receive the messages and process them by calling processMessage(...).
		//        Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever

		System.out.println("Ready to recieve messages...");
		boolean close = false;
		while(!close){

			pacSize = 5000;
			pacData = new byte[5000];
		// setup package
			pac = new DatagramPacket(pacData, pacSize);

			try{
				//        Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever
        recvSoc.setSoTimeout(10000);
				recvSoc.receive(pac);
						String d = new String (pac.getData()).trim();
						  processMessage(d);
			}
			catch (IOException e){
				System.out.println("Error IO exception receiving packet.");
				System.out.println("Could be due to timeout.");
				System.out.println("Now closing server...");
				System.exit(-1);
			}


		}

	}


              public void processMessage(String data) {

		MessageInfo msg = null;

		// TO-DO: Use the data to construct a new MessageInfo object



		try {

			msg = new MessageInfo(data);

		}

		catch (Exception e) {
			System.out.println("Error: IO exception creating ObjectInputStream.");
		}
		// try to implement class not found

		// TO-DO: On receipt of first message, initialise the receive buffer

		if(receivedMessages==null){
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
		}

		// TO-DO: Log receipt of the message

		receivedMessages[msg.messageNum] = 1;

		// TO-DO: If this is the last expected message, then identify
		//        any missing messages

		if (msg.messageNum + 1 == msg.totalMessages) {
			boolean close = true;

			String s = "Lost packet numbers: ";
			int count = 0;
			for (int i = 0; i < totalMessages; i++) {
				if (receivedMessages[i] != 1) {
					count++;
					s = s + " " + (i+1) + ", ";
				}
			}

			if (count == 0) s = s + "None";

			System.out.println("Of " + msg.totalMessages + ", " + (msg.totalMessages - count) + " received successfully");
			System.out.println("Of " + msg.totalMessages + ", " + count + " failed");

		}

	}


	public UDPServer(int rp) {
		// TO-DO: Initialise UDP socket for receiving data
		try {

			recvSoc = new DatagramSocket(rp);
		}
		catch (SocketException e) {
			System.out.println("Error: Could not create socket on port " + rp);
			System.exit(-1);
		}

		// Make it so the server can run.

		//close = false;
	}

	public static void main(String args[]) {
		int	recvPort;

		// Get the parameters from command line
		if (args.length < 1) {
			System.err.println("Arguments required: recv port");
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[0]);

		// TO-DO: Construct Server object and start it by calling run().

		UDPServer udpsrv = new UDPServer(recvPort);
		try {
			udpsrv.run();
		} catch (Exception e) {}

	}

}
