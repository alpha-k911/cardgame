/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.www.content.audio.wav;

/**
 *
 * @author Nandishwar
 */
public class server {
    public static void main(String[] args) {
        int i = 0;
        int n = 2;
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(0);
        values.add(0);
        values.add(0);
        values.add(0);
        String name="";
        try {
            ServerSocket server = new ServerSocket(5000);
//            MyThread t[] = new MyThread[n];
            String Control[] = new String[n];
            Socket socket[] = new Socket[n];
            DataOutputStream dout[] = new DataOutputStream[n];
            DataInputStream din[] = new DataInputStream[n];
            while(i!=n){
                System.out.println("Server is waiting for Client "+i);
                socket[i] = server.accept();
                name = "C"+i;
//                t[i] = new MyThread(socket[i]);
//                t[i].setName(name);
                Control[i] = new String();
                Control[i] = "0";
                dout[i] = new DataOutputStream(socket[i].getOutputStream());
                dout[i].writeUTF(Control[i]);
                din[i] = new DataInputStream(socket[i].getInputStream());
//                t[i].begin();
                i++;
            }
            String r = "";
            int w = 0;
            i=0;
            int v;
            String p = "";
            int done = 0;
            while (done == 0) {  
                int a =0 ;
                r = "1";
                dout[i].writeUTF(r);
                //r = 1 as we are sending control as "1" to client i
                System.out.println("Sent for "+i);
                p = din[i].readUTF();
                //p=1 if he wins
                if (p.equals("1")) {
                done = 1;// stopping the loop to declare winner
                w = i;
                    v = values.get(i);
                    v++;
                    values.set(i, v);
                }
                System.out.println("read from "+i+": "+p);
                try {                    
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (done == 0) {
                    v = values.get(i);
                    v++;
                    values.set(i, v);
                    i = i + 1;
                    i = i % n;
                }
            }
            if (done == 1) {
                for (int j = 0; j < n; j++) {
                    r = "2";
                    dout[j].writeUTF(r);
                    try {                    
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                String results = "No. of Turns:";
                
                for(i = 0 ;i<n ; i++){
                    results = results + "\nC"+i+" :"+values.get(i);
                }
                results = results + "\nWinner = "+"C"+w;
                for (int j = 0; j < n; j++){
                    dout[j].writeUTF(results);
                    try {                    
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
        } catch (IOException ex) {
            System.out.println("Can't listen on port 4444");
        }
    }
}

