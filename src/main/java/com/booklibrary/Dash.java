package com.booklibrary;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Dash extends JPanel {
    JFileChooser fileChooser;
    static JButton pro;
    BufferedImage bufImg = null;
    InputStream dif;
    Progress pei;
   private File fil;
    static  ImageIcon proFile;
    static int flag=0;
    final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
    final String DBuser = "usxqljshik8op7dd";
    final String DBpass = "ViDjCizs55WoYAMXOmeW";

    ImageIcon idnk;

    public Dash() {
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
        setBounds(550, 0, x, y);
        setBackground(Color.decode("#C8BFE7"));
        setLayout(null);
        JLabel label = new JLabel("Welcome,");
        label.setBounds(200, 250, 300, 100);
        label.setForeground(Color.decode("#851947"));
        label.setFont(new Font("Algerian", Font.PLAIN, 50));
        add(label);

        JLabel label1 = new JLabel(Login_Signup.User_name.toUpperCase());
        label1.setBounds(400, 310, 300, 100);
        label1.setForeground(Color.decode("#0024FF"));
        label1.setFont(new Font("Arial", Font.PLAIN, 30));
        add(label1);

        JButton Ab = new JButton("About Us");
        Ab.setBounds(400, 600, 140, 30);
        Ab.setFont(new Font("Arial", Font.BOLD, 20));
        add(Ab);
        JTextArea tArea = new JTextArea();
        tArea.setBounds(200, 400, 700, 350);
        tArea.setText(
                " If you are referring to a specific library, such as a public or university library, it would be best to visit their official website or contact them directly for information about their services and history."
                        +
                        " \n\n\t\tIf \"library\" refers to a software library or code repository, I would need more context or a specific name to provide relevant information.");
        tArea.setFont(new Font("arial", Font.BOLD, 20));
        tArea.setLineWrap(true);
        tArea.setEditable(false);
        add(tArea);
        tArea.setWrapStyleWord(true);
        tArea.setBackground(Color.decode("#C8BFE7"));
        Ab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // pei = new Progress();
        // pei.pro.setBounds(500, 100, 330, 25);
        // add(pei.pro);
        // repaint();
        // validate();

        ImageIcon log = new ImageIcon("logout.png");
        Image logo = log.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING);
        ImageIcon logou = new ImageIcon(logo);
        JButton logout = new JButton("Logout", logou);
        logout.setBounds(1103, 230, 100, 30);
        logout.setFont(new Font("Arial", Font.BOLD, 15));
        add(logout);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Sel = JOptionPane.showConfirmDialog(null, "Are You Logout", "Message", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (Sel == 0) {

                    Login_Signup.bk.removeAll();
                    Login_Signup.bk.add(Login_Signup.heading);
                    Login_Signup.bk.add(Login_Signup.jPanel);
                    Login_Signup.bk.repaint();
                    Login_Signup.bk.revalidate();
                }

            }

        });

        // ImageIcon pr=new ImageIcon("bk.jpg");
        pro = new JButton();
        pro.setBounds(1100, 100, 100, 100);
        add(pro);
        pro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Onle jpg", "jpg");
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Onle png", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter1);
        pro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selelected = fileChooser.showOpenDialog(null);
                if (selelected == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String Filname = file.getAbsolutePath();
                     fil = new File(Filname);
                    if((fil.length()/1024) <30){
                    

                 pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {

        try {
            FileInputStream inputStream = new FileInputStream(fil);
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(url, DBuser, DBpass);
            
            final String qR = "update USER_S set IMAGES = ? where Full_name = '" + Login_Signup.User_name
                    + "'";
            PreparedStatement pst = con.prepareStatement(qR);
            pst.setBinaryStream(1, inputStream, inputStream.available());
            pst.executeUpdate();

            idnk = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
            Image pro_file = idnk.getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
             proFile = new ImageIcon(pro_file);
            pro.setIcon(proFile);
            flag=1;
            try {
                pei.t.stop();
                pei.pro.setIndeterminate(false);
                pei.pro.setVisible(false);
            } catch (Exception e) {
                
            }
            JOptionPane.showMessageDialog(null, "Update Succesfully.");
              revalidate();
              repaint();
            con.close();

        } catch (Exception ee) {
            try {
                pei.t.stop();
                pei.pro.setIndeterminate(false);
                pei.pro.setVisible(false);
              } catch (Exception e) {
  
              }
              revalidate();
              repaint();
            JOptionPane.showMessageDialog(null, ee);
        }

      }

    }).start();





                    }else{
                        JOptionPane.showMessageDialog(null, "Less Than 30 Kb");
                    }

                    

                    // try{
                    // Class.forName("oracle.jdbc.OracleDriver");
                    // Connection cone =
                    // DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bittu","Bittu");
                    // final String Query="Select Images from user_s where
                    // full_name='"+Login_Signup.User_name+"'";
                    // PreparedStatement preparedStatement = cone.prepareStatement(Query);
                    // // preparedStatement.setString(1, currentuser.getText().trim());
                    // ResultSet rs = preparedStatement.executeQuery();
                    // while (rs.next()) {
                    // InputStream in = rs.getBinaryStream("IMAGES");
                    // bufImg = ImageIO.read(in);
                    // }
                    // ImageIcon inin=new ImageIcon();
                    // Image innk=inin.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    // ImageIcon set=new ImageIcon(innk);
                    // pro.setIcon(set);

                    // }catch(Exception js){
                    // js.getStackTrace();
                    // }
                } else {
                    JOptionPane.showMessageDialog(null, "Photo not choosen");
                }
            }

        });

    }

}
