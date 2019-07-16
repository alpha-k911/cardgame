/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

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

public class client extends javax.swing.JFrame {

    /**
     * Creates new form client
     */
    
    private void change(){
        send = 1;
        System.out.println("send: "+send);
    }
    static String Control = "";
    static String data = "LLLL";
    static int send = 0;
    static int val = 0;
    static int active = 1;
    ArrayList<Integer> values = new ArrayList<Integer>();// values are for flipped or not
    public client() {
        initComponents();
        values.add(0);
        values.add(0);
        values.add(0);
        values.add(0);
        jLabel6.setBackground(Color.RED);
          ArrayList<JLabel> labels = new ArrayList<JLabel>();
          jTextArea1.setVisible(false); 
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
            Socket socket= new Socket("192.168.137.46", 4444);
            DataInputStream dinm = new DataInputStream(socket.getInputStream());
            Control = dinm.readUTF();
            System.out.println(""+Control);
            if (Control.equals("0")) {
                jLabel6.setBackground(Color.red);
            }
            Writethread wt = new Writethread(socket);
            Th t = new Th(socket);
            double r = Math.random();
                int y = (int) (r*10);
                y = y % 4;
                char[] myNameChars = data.toCharArray();
                myNameChars[y] = 'W';
                data = String.valueOf(myNameChars);
//                System.out.println(""+data);
            
        } catch (IOException ex) {
//            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Socket error");
            System.out.println("");
        }
       
        
    }

    private void setButtons(String c) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (c.equals("1")) {
            if (values.get(0) == 0) {
                jButton1.setEnabled(true);
            }else{
                jButton1.setEnabled(false);
            }
            if (values.get(1) == 0) {
                jButton2.setEnabled(true);
            }else{
                jButton2.setEnabled(false);
            }
            if (values.get(2) == 0) {
                jButton3.setEnabled(true);
            }else{
                jButton3.setEnabled(false);
            }
            if (values.get(3) == 0) {
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
        DataOutputStream dout;
//        DataOutputStream dout;

        Writethread(Socket s) {
            socket = s;
            try {
                //                this.dout = new DataOutputStream(socket.getOutputStream());
//                System.out.println("");
                dout = new DataOutputStream(s.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
            start();
        }

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            try {
                int g = 0;
                System.out.println("writer thread is running");
                while (active == 1) {
                    if (g == 0) {
                        System.out.println("entered loop");
                        g=1;
                    }
                    try {
                        Thread.sleep(30);
                    } catch (Exception e) {
                    }
                    while (send == 1) { 
                       if (g == 1) {
                        System.out.println("entered send loop");
//                        g=1;
                        } 
                        dout.writeUTF(val+"");
                        send = 0;
                    }
                    
                }
            } catch (Exception e) {
            }
            
        }
        
    }
    class Th extends Thread{
        Socket socket;
        public Th(Socket s) {
            this.socket = s;
            start();
        }

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            DataInputStream dinm = null;
            try {
                dinm = new DataInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
            int p = 1;
            //p is flag for status of game either done or still going on 
            while (p == 1) {
                try {
                    Control = dinm.readUTF();
                } catch (IOException ex) {
//                    Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex);
                        p = 0;
                }
                System.out.println(""+Control);
                if (Control.equals("2")) {
                    p = 0;
                    active = 0;
                }
                if (Control.equals("1")){
                    jLabel6.setBackground(Color.GREEN);
//                    setButtons(Control);
                }else{
                    jLabel6.setBackground(Color.RED);   
                }
                setButtons(Control);
                
//                p = 0;
            }
            if (p == 0) {
                String results = null;
                try {
                    results = dinm.readUTF();
                } catch (IOException ex) {
                    Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTextArea1.setText(results);
                jTextArea1.setVisible(true);
                jTextArea1.setEnabled(false);
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel5.setText("Results");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(178, 178, 178)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton1)
                        .addGap(187, 187, 187)
                        .addComponent(jButton2)
                        .addGap(184, 184, 184)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(74, 74, 74))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(5, 5, 5)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Can you Win???");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        values.set(1, 1);
        Control = "0";
        setButtons(Control);
        jLabel6.setBackground(Color.red);
        if (data.charAt(1) == 'W') {
            jLabel2.setIcon(new ImageIcon(getClass().getResource("w1.png")));
            val = 1;
//            System.out.println(""+val);
            send = 1;
            System.out.println("val: "+val);
        }
        else{
            jLabel2.setIcon(new ImageIcon(getClass().getResource("j1.png")));
            val = 0;
            System.out.println("val: "+val);
            send = 1;
        }
        change();
        send = 1;
        System.out.println("send: "+send);
//        done = 1;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
//        Control = String.valueOf(Chars);
        values.set(2, 1);
        Control = "0";
        setButtons(Control);
        jLabel6.setBackground(Color.red);
        
        if (data.charAt(2) == 'W') {
            jLabel3.setIcon(new ImageIcon(getClass().getResource("w1.png")));
            val = 1;
        }
        else{
            jLabel3.setIcon(new ImageIcon(getClass().getResource("j1.png")));
            val = 0;
        }
        change();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        values.set(0, 1);
        Control = "0";
        setButtons(Control);
        jLabel6.setBackground(Color.red);
        if (data.charAt(0) == 'W') {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("w1.png")));
            val = 1;
        }
        else{
            jLabel1.setIcon(new ImageIcon(getClass().getResource("j1.png")));
            val = 0;
        }
        send = 1;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        values.set(3, 1);
        Control = "0";
        setButtons(Control);
        jLabel6.setBackground(Color.red);
        if (data.charAt(3) == 'W') {
            jLabel4.setIcon(new ImageIcon(getClass().getResource("w1.png")));
            val = 1;
        }
        else{
            jLabel4.setIcon(new ImageIcon(getClass().getResource("j1.png")));
            val = 0;
        }
        send = 1;
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
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client().setVisible(true);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
