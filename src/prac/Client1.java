package prac;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author abc
 */
class Client1{
public static void main(String args[])
{
   Socket socket;
   DataInputStream din;
   DataOutputStream dout;
   ObjectOutputStream outdata;
   Scanner sc;
   try{
        System.out.println("Before connection");
   	socket= new Socket("192.168.43.128",5000);
   	System.out.println("Connected");
        System.out.println(socket.isBound());
        System.out.println(socket.isConnected());
   	sc= new Scanner(System.in);
   	dout= new DataOutputStream(socket.getOutputStream());
   	String line="";
   		do{
   			System.out.println("Enter some text");   	
		   	line= sc.nextLine();   	
		   	dout.writeUTF(line);
		   	dout.flush();
   		}while(!(line.equals("end")));
   	
   	dout.close();
   	socket.close();
   	}catch(Exception e){
   	  e.printStackTrace();
   	}
   	
   }
  }
   	
