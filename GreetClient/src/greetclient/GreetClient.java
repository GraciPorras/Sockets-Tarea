/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetclient;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Graciela Porras
 */
public class GreetClient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public String sendMessage(String msg) throws IOException {
        
        out.println(msg);
        System.out.println(msg);
        String resp = in.readLine();
        return resp;
    }
 
    public void stopConnection() {
        try {
           
            in.close();
            out.close();
            clientSocket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() {
        try {
            
            GreetClient client = new GreetClient();
            client.startConnection("127.0.0.1", 6666);
            String response = client.sendMessage("hello server");
            System.out.println(response);
            //assertEquals("hello client", response);
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void givenClient_whenServerEchosMessage_thenCorrect_Continua() {
        try {
            
            GreetClient client = new GreetClient();
            client.startConnection("127.0.0.1", 6666);
            
            String resp1 = client.sendMessage("hello");
            System.out.println(resp1);
            
            String resp2 = client.sendMessage("world");
            System.out.println(resp2);
            
            String resp3 = client.sendMessage("!");
            System.out.println(resp3);
            
            String resp4 = client.sendMessage(".");
            System.out.println(resp4);
                      
        
            /*assertEquals("hello", resp1);
            assertEquals("world", resp2);
            assertEquals("!", resp3);
            assertEquals("good bye", resp4);*/
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void givenClient1_whenServerResponds_thenCorrect() {
        try {
            GreetClient client1 = new GreetClient();
            client1.startConnection("127.0.0.1", 6666);
            
            String msg1 = client1.sendMessage("hello");
            System.out.println(msg1);
            String msg2 = client1.sendMessage("world");
            System.out.println(msg2);
            String terminate = client1.sendMessage(".");
            System.out.println("Servidor responde: "+terminate+"\n");
            
            /*assertEquals(msg1, "hello");
            assertEquals(msg2, "world");
            assertEquals(terminate, "bye");*/
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
}
 

public void givenClient2_whenServerResponds_thenCorrect() {
        try {
            GreetClient client2 = new GreetClient();
            client2.startConnection("127.0.0.1", 6666);
            
            String msg1 = client2.sendMessage("hello");
            System.out.println(msg1);
            
            String msg2 = client2.sendMessage("world");
            System.out.println(msg2);
            
            String terminate = client2.sendMessage(".");
            System.out.println("Servidor responde: "+terminate+"\n");
            
            /*assertEquals(msg1, "hello");
            assertEquals(msg2, "world");
            assertEquals(terminate, "bye");*/
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public static void main(String[] args) {
        
        GreetClient client=new GreetClient();
        //COMUNICAR CLIENTE-SERVIDOR Y CONECTAR CONTINUAMENTE
        //client.givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect();
        //client.givenClient_whenServerEchosMessage_thenCorrect_Continua();
        
        //CONECTAR CON MULTIUSUARIOS
        client.givenClient1_whenServerResponds_thenCorrect();
        client.givenClient2_whenServerResponds_thenCorrect();
    }
}

