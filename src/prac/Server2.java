package prac;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package server1;

/**
 *
 * @author abc
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abc
 */
public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket server= new ServerSocket(5000);
			while(true){
				System.out.println("Server is waiting for the request");
                Socket socket= server.accept();
                            System.out.println(socket);
				System.out.println("Connection established");
				new MyThread(socket);
                                new WriteThread(socket);
			}
			
        } catch (IOException ex) {
            Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


class MyThread extends Thread{
	Socket socket;
	MyThread(Socket s){
		socket=s;
		start();
	}
	public void run(){
		try{
			DataInputStream din= new DataInputStream(socket.getInputStream());
              String data="";
			  while(!(data.equals("stop"))){
				  data= din.readUTF();
              System.out.println(data);
			  }
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}
}


class WriteThread extends Thread{
    Socket socket;
    Scanner sc;
    DataOutputStream dout;
    public WriteThread(Socket s){
        socket=s;
        sc= new Scanner(System.in);
        try {
            dout= new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Caught");
        }
        start();
    }
    public void run(){
        String msg="";
        while(!(msg.equals("stop"))){
            System.out.println("Enter data");
            msg=sc.nextLine();
            try {
                dout.writeUTF(msg);
            } catch (IOException ex) {
                System.out.println("Caught");
                try {
                    dout.close();
                } catch (IOException ex1) {
                    System.out.println("caught");
                }
            }
        }
        try {
            dout.close();
        } catch (IOException ex) {
            System.out.println("caught");
        }
    }
}