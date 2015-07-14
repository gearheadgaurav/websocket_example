/**
 * 
 */
package org.websocket.sample;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author gaurav
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/WebSocket/echo
 * Where "localhost" is the address of the host,
 * "WebSocket" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/echo") 
public class EchoServer {
	/**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection"); 
//        try {
//            //session.getBasicRemote().sendText("Connection Established");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
    	int[] amountList = {1000, 5000, 6000, 3000, 1000, 4000, 2000};
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
        	for(int amount: amountList){
        		session.getBasicRemote().sendText(String.valueOf(amount));
        	}

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
    }
}
