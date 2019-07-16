package prac;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author abc
 */
public class Server1{
  public static void main(String args[])
  {
   ServerSocket server;
   boolean stop=true;
   Scanner sc;
   try{
       server= new ServerSocket(5000);
       sc= new Scanner(System.in);
         while(stop){
         
          System.out.println("Waiting for the client request");
          
         Socket socket= server.accept();
         System.out.println("Connection Established");
         InetAddress inet= socket.getInetAddress();
         int port= socket.getPort();
         DataInputStream din= new DataInputStream(socket.getInputStream());
         	 String line="";
         	while(!(line.equals("end"))){
         		line= din.readUTF();
         		System.out.println(line);  
         	
         	}        
         }
       }
       catch(Exception e ){
       
       }
  }
}
