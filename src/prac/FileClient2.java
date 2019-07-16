package prac;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package clients;

import java.io.DataInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abc
 */
class FileClient2{
    String line="";
    Socket socket;
   
   DataInputStream din=null;
   DataOutputStream dout=null;
   ObjectOutputStream outdata=null;
   Scanner sc;
   
   public static void main(String args[]){
	   new FileClient2().getFile();
   }
   
public void getFile()
{   
   
   try{
	sc= new Scanner(System.in);
	System.out.println("Enter the file name");
	String fname= sc.nextLine();
   	socket= new Socket("192.168.43.128",5000);
   	System.out.println("Connected");
	dout= new DataOutputStream(socket.getOutputStream());
	dout.writeUTF(fname);
	dout.flush();
   	din= new DataInputStream(socket.getInputStream());    	
   	FileOutputStream fout;
	BufferedOutputStream bos=null;
	byte b[]= new byte[1024];
	  try{
		  fout= new FileOutputStream("eval2.txt");
		  bos= new BufferedOutputStream(fout);
		  int rcount;
		  //int off=0;
		  int i=0;
		  do{
			  rcount=din.read(b,0,1020);
			  System.out.println(rcount);
			  bos.write(b,0,rcount);			
		  }while(rcount>0);
		  
	/*	  do{
			  rcount=din.read(b,0,1020);
			  System.out.println(rcount);
			  bos.write(b,0,rcount);	
				i++;
		  }while(i<2);  */
	//	  rcount=din.read(b,0,1020);   // gives the exception
			  System.out.println(rcount);
		//  int rcount= din.read(b,0,1020);
		//  bos.write(b,0,rcount);
		  bos.close();
	  }catch(Exception e2){
		   System.out.println("caught");
		   bos.close();
	  }
   
   	System.out.println("Client closed the chat and connection");   	
   	
   	}catch(Exception e){
   	  e.printStackTrace();
   	}
   	
    
   
	
   }
  }