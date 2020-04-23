/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetserver;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Graciela Porras
 */

public class GreetServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void start(int port) {
     try {
         
         serverSocket = new ServerSocket(port);
         clientSocket = serverSocket.accept();
         
         out = new PrintWriter(clientSocket.getOutputStream(), true);
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         String greeting = in.readLine();
          
         if ("hello server".equals(greeting)) {
             out.println("hello client");
         }
         else {
             out.println("unrecognised greeting");
         }
     } catch (IOException ex) {
         Logger.getLogger(GreetServer.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void startContinua(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                out.println(inputLine);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GreetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void stop() {
     try {
         in.close();
         out.close();
         clientSocket.close();
         serverSocket.close();
     } catch (IOException ex) {
         Logger.getLogger(GreetServer.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    public static void main(String[] args) {
        
        //COMUNICAR CLIENTE-SERVIDOR Y CONECTAR CONTINUAMENTE
        //GreetServer server=new GreetServer();
        //server.start(6666);
        //server.startContinua(6666);
        
        //CONECTAR CON MULTIUSUARIOS
        EchoMultiServer serverMulti=new EchoMultiServer();
        serverMulti.start(6666);

    }
    
}
