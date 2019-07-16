/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

import com.sun.org.apache.xerces.internal.impl.xs.identity.ValueStore;
import java.awt.Color;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Nandishwar
 */

public class client1 extends javax.swing.JFrame {

    /**
     * Creates new form client
     */
    public static ImageIcon convrt(ImageIcon imageIcon){ 
        Image im = imageIcon.getImage();
        Image newim = im.getScaledInstance(180,253, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newim);
        return imageIcon;
    }
    static String Control = "0000_0";
    static String data = "LLLL";
    static String str = "";
    ArrayList<Integer> values = new ArrayList<Integer>();
    public client1() {
       
        initComponents();
        values.add(0);
        values.add(0);
        values.add(0);
        values.add(0);
        jLabel6.setBackground(Color.RED);
          ArrayList<JLabel> labels = new ArrayList<JLabel>();
          labels.add(jLabel1);
          labels.add(jLabel2);
          labels.add(jLabel3);
          labels.add(jLabel4);
          for(JLabel j:labels){
              double r = Math.random();
                int y = (int) (r*10);
                y = y % 2;
                if (y == 0) {
                  j.setIcon(new ImageIcon(getClass().getResource("r1.png")));
                }else{
                  j.setIcon(new ImageIcon(getClass().getResource("b1.png")));
                }
          }
          ArrayList<JButton> buttons = new ArrayList<JButton>();
          buttons.add(jButton1);
          buttons.add(jButton2);
          buttons.add(jButton3);
          buttons.add(jButton4);
          for(JButton b:buttons){
              b.setEnabled(false);
          }
          
        try {
            System.out.println("trying to connect");
            Socket socket= new Socket("127.0.0.1", 4444);
//        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
//        Control = din.readUTF(); 
//            ReadThread rt = new ReadThread(socket);
//            Writethread wt = new Writethread(socket);
            DataInputStream dinm = new DataInputStream(socket.getInputStream());
            DataOutputStream doutm = new DataOutputStream(socket.getOutputStream());
            Control = dinm.readUTF();
            System.out.println(""+Control);
            if (Control.charAt(7) == '0') {
                jLabel6.setBackground(Color.red);
            }
            int p = 1;
            while (p == 1) {                
                str = dinm.readUTF();
                System.out.println(""+str);
//                char[] Chars = r.toCharArray();
//                Chars[5] = '1';/
//                setButtons(Control);
//                jLabel6.setBackground(Color.red);
//                Control = String.valueOf(Chars);
                if (str.equals("1")){
                    jLabel6.setBackground(Color.GREEN);
//                    setStatus(1);
                    setButtons(Control);
                }else{
//                    setStatus(0);
                    jLabel6.setBackground(Color.RED);   
                }
//                String data = "LLLW";
                setButtons(Control);
                double r = Math.random();
                int y = (int) (r*10);
                y = y % 4;
                char[] myNameChars = data.toCharArray();
                myNameChars[y] = 'W';
                data = String.valueOf(myNameChars);
                System.out.println(""+data);
                p = 0;
            }
            
            
        } catch (IOException ex) {
//            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Socket error");
            System.out.println("");
        }
       
        
    }

    private void setButtons(String c) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c.charAt(7) == '1') {
            if (c.charAt(2) == '0') {
                jButton1.setEnabled(true);
            }else{
                jButton1.setEnabled(false);
            }
            if (c.charAt(3) == '0') {
                jButton2.setEnabled(true);
            }else{
                jButton2.setEnabled(false);
            }
            if (c.charAt(4) == '0') {
                jButton3.setEnabled(true);
            }else{
                jButton3.setEnabled(false);
            }
            if (c.charAt(5) == '0') {
                jButton4.setEnabled(true);
            }else{
                jButton4.setEnabled(false);
            }
        }else{
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton4.setEnabled(false);
        }
    }
    class Writethread extends Thread{
        Socket socket;
//        DataOutputStream dout;

        Writethread(Socket s) {
            socket = s;
            //                this.dout = new DataOutputStream(socket.getOutputStream());
//                System.out.println("");
            start();
        }

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            try {
//                System.out.println("");
                while (true) {    
//                    Scanner sc =new Scanner(System.in);
//                    String line="";
//                    line = sc.nextLine();
//                    dout.writeUTF(line);
                }
            } catch (Exception e) {
            }
        }
        
    }
    class ReadThread extends Thread{
        Socket socket;
        DataInputStream din;
        String r = "";
        ReadThread(Socket s) {
            socket = s;
            try {
                this.din = new DataInputStream(socket.getInputStream());
                start();
            } catch (IOException ex) {
//                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            try {
                System.out.println("Thread Initialised");
                Control = din.readUTF();
                while (true) {                    
                    r = din.readUTF();
                    System.out.println(""+r);
                }
                
            } catch (IOException ex) {
//                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
           
        }
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setMaximumSize(new java.awt.Dimension(200, 400));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 400));
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 400));

        jLabel3.setMaximumSize(new java.awt.Dimension(200, 400));
        jLabel3.setMinimumSize(new java.awt.Dimension(200, 400));
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 400));

        jLabel2.setMaximumSize(new java.awt.Dimension(200, 400));
        jLabel2.setMinimumSize(new java.awt.Dimension(200, 400));
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 400));

        jLabel4.setMaximumSize(new java.awt.Dimension(200, 400));
        jLabel4.setMinimumSize(new java.awt.Dimension(200, 400));
        jLabel4.setPreferredSize(new java.awt.Dimension(200, 400));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel5.setText("Results");

        jButton1.setText("Flip");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Flip");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Flip");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Flip");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jButton1)
                .addGap(187, 187, 187)
                .addComponent(jButton2)
                .addGap(184, 184, 184)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        char[] Chars = Control.toCharArray();
        Chars[3] = '1';
        Chars[7] = '0';
        Control = String.valueOf(Chars);
        setButtons(Control);
        jLabel6.setBackground(Color.red);
        
        if (data.charAt(1) == 'W') {
            jLabel2.setIcon(new ImageIcon(getClass().getResource("w1.png")));
        }
        else{
            jLabel2.setIcon(new ImageIcon(getClass().getResource("j1.png")));
        }
//        done = 1;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        char[] Chars = Control.toCharArray();
        Chars[4] = '1';
        Chars[7] = '0';
        Control = String.valueOf(Chars);
        setButtons(Control);
        jLabel6.setBackground(Color.red);
        
        if (data.charAt(2) == 'W') {
            jLabel3.setIcon(new ImageIcon(getClass().getResource("w1.png")));
        }
        else{
            jLabel3.setIcon(new ImageIcon(getClass().getResource("j1.png")));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        char[] Chars = Control.toCharArray();
        Chars[2] = '1';
        Chars[7] = '0';
        Control = String.valueOf(Chars);
        setButtons(Control);
        
        jLabel6.setBackground(Color.red);
        
        if (data.charAt(0) == 'W') {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("w1.png")));
        }
        else{
            jLabel1.setIcon(new ImageIcon(getClass().getResource("j1.png")));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        char[] Chars = Control.toCharArray();
        Chars[5] = '1';
        Chars[7] = '0';
        Control = String.valueOf(Chars);
        setButtons(Control);
        
        jLabel6.setBackground(Color.red);
        
        if (data.charAt(3) == 'W') {
            jLabel4.setIcon(new ImageIcon(getClass().getResource("w1.png")));
        }
        else{
            jLabel4.setIcon(new ImageIcon(getClass().getResource("j1.png")));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
