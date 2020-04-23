/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Graciela Porras
 */
public class EchoMultiServer {
    private ServerSocket serverSocket;
    
    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    public void start(int port) {
       
        try {
            serverSocket = new ServerSocket(port);
            int cont=0;
            
            while (true){
                new EchoClientHandler(serverSocket.accept()).start();
                
                cont++;
                System.out.println("Cliente  "+cont);
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
