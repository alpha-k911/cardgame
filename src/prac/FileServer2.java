package prac;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;

/**
 *
 * @author abc
 */
public class FileServer2 {
    public static void main(String[] args) {
        ServerSocket server;
   DataInputStream din;
   BufferedInputStream bis;
    File file;
	DataOutputStream dout;
  
        try{
       server= new ServerSocket(5000);                
          System.out.println("Waiting for file request"); 
		//	String filename="D:\\Java\\eval2.txt";
			String filename="D:\\Java\\";
         Socket socket= server.accept();
         dout= new DataOutputStream(socket.getOutputStream());
		 din= new DataInputStream(socket.getInputStream());
            System.out.println("Connection Established");
         String fname= din.readUTF();
			filename=filename+fname;
             try {
			file= new File(filename);
			int len=(int)file.length();
        byte b[]= new byte[len];
            bis=new BufferedInputStream(new FileInputStream(file));
			bis.read(b,0,len);
			dout.write(b,0,len);
			dout.flush();
			//bis.close();
        } catch (FileNotFoundException ex) {
                System.out.println("caught");
                    }
			
       }
       catch(Exception e ){
           System.out.println("Caught");
       }
    }
}
