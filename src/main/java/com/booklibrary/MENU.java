package com.booklibrary;

import static com.booklibrary.Login_Signup.bk;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MENU extends JPanel {

    final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
    final String DBuser = "usxqljshik8op7dd";
    final String DBpass = "ViDjCizs55WoYAMXOmeW";
    private Progress pei;
   
   static JButton home, issue, deposite, student_reg, student_update, book_iformation, student_information;
    BufferedImage bufImg = null;
    InputStream dif;

    public MENU() {
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());

        setBounds(0, 0, 550, y);
        setBackground(Color.decode("#9495F0"));
        setLayout(null);

        ImageIcon ho = new ImageIcon("img/home.jpg");
        Image h = ho.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        ImageIcon hom = new ImageIcon(h);
        home = new JButton("   \tHome\t", hom);
        home.setBounds(140, 150, 250, 60);
        home.setFont(new Font("arial", Font.BOLD, 13));
        add(home);

        ImageIcon IS = new ImageIcon("img/isses.png");
        Image is = IS.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon isu = new ImageIcon(is);
        issue = new JButton("Issues  Books", isu);
        issue.setBounds(140, 250, 250, 60);
        issue.setFont(new Font("arial", Font.BOLD, 13));
        add(issue);

        ImageIcon dep = new ImageIcon("img/depo.jpg");
        Image depo = dep.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon depos = new ImageIcon(depo);
        deposite = new JButton("Deposite Books", depos);
        deposite.setBounds(140, 350, 250, 60);
        deposite.setFont(new Font("arial", Font.BOLD, 13));
        add(deposite);

        ImageIcon re = new ImageIcon("img/regis.jpg");
        Image reg = re.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon regis = new ImageIcon(reg);
        student_reg = new JButton("Student's Registration", regis);
        student_reg.setBounds(140, 450, 250, 60);
        student_reg.setFont(new Font("arial", Font.BOLD, 13));
        add(student_reg);

        ImageIcon up = new ImageIcon("img/update.jpg");
        Image upd = up.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon upda = new ImageIcon(upd);
        student_update = new JButton("Student's Update", upda);
        student_update.setBounds(140, 550, 250, 60);
        student_update.setFont(new Font("arial", Font.BOLD, 13));
        add(student_update);

        ImageIcon inf = new ImageIcon("img/rec.png");
        Image info = inf.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon infor = new ImageIcon(info);
        book_iformation = new JButton("Books Information", infor);
        book_iformation.setBounds(140, 650, 250, 60);
        book_iformation.setFont(new Font("arial", Font.BOLD, 13));
        add(book_iformation);

        ImageIcon stud = new ImageIcon("img/stud.png");
        Image studen = stud.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon student = new ImageIcon(studen);
        student_information = new JButton("Information", student);
        student_information.setBounds(140, 750, 250, 60);
        student_information.setFont(new Font("arial", Font.BOLD, 13));
        add(student_information);

        home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        issue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deposite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        student_reg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        student_update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        book_iformation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        student_information.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        



        home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                
                Dash d1=new Dash();
                bk.removeAll();
                bk.add(new MENU());
                bk.add(d1);
                if(Dash.flag==0){
                    Dash.pro.setIcon(Login_Signup.set);
                }else{
                    Dash.pro.setIcon(Dash.proFile);
                }
                  home.setEnabled(false);
                issue.setEnabled(true);
                deposite.setEnabled(true);
                student_reg.setEnabled(true);
                student_update.setEnabled(true);
                book_iformation.setEnabled(true);
                student_information.setEnabled(true);
                bk.repaint();
                bk.revalidate();
                
                // pei = new Progress();
                // pei.pro.setBounds(500, 100, 330, 25);
                // // Dash.add(pei.pro);
                // d1.add(pei.pro);
                // repaint();
                // validate();

        //         new Thread(new Runnable() {

        //             @Override
        //             public void run() {
        //         try {
        //             Class.forName("oracle.jdbc.OracleDriver");
        //             Connection cone = DriverManager.getConnection(url, DBuser, DBpass);
        //             final String Query = "Select Images from USER_S where full_name='" + Login_Signup.User_name + "'";
        //             PreparedStatement preparedStatement = cone.prepareStatement(Query);
        //             // preparedStatement.setString(1, currentuser.getText().trim());
        //             ResultSet rs = preparedStatement.executeQuery();
        //             while (rs.next()) {
        //                 InputStream in = rs.getBinaryStream("IMAGES");
        //                 bufImg = ImageIO.read(in);
        //                 ImageIcon inin = new ImageIcon(bufImg);
        //                 Image innk = inin.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //                 ImageIcon set = new ImageIcon(innk);
        //                 try {
        //                     pei.t.stop();
        //                     pei.pro.setIndeterminate(false);
        //                     pei.pro.setVisible(false);
        //                   } catch (Exception e) {
      
        //                   }
        //                 Dash.pro.setIcon(set);

        //             }
        //             cone.close();
        //         } catch (Exception js) {
        //               try {
        //                     pei.t.stop();
        //                     pei.pro.setIndeterminate(false);
        //                     pei.pro.setVisible(false);
        //                   } catch (Exception e) {
      
        //                   }
        //             js.getStackTrace();
        //         }
        //     }
            
        // }).start();

            }

        });
        issue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bk.removeAll();
                bk.add(new MENU());
                bk.add(new Issues());
                // home, issue, deposite, student_reg, student_update, book_iformation, student_information;
                home.setEnabled(true);
                issue.setEnabled(false);
                deposite.setEnabled(true);
                student_reg.setEnabled(true);
                student_update.setEnabled(true);
                book_iformation.setEnabled(true);
                student_information.setEnabled(true);
                bk.repaint();
                bk.revalidate();
            }

        });
        deposite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bk.removeAll();
                bk.add(new MENU());
                bk.add(new Depo());
                  home.setEnabled(true);
                issue.setEnabled(true);
                deposite.setEnabled(false);
                student_reg.setEnabled(true);
                student_update.setEnabled(true);
                book_iformation.setEnabled(true);
                student_information.setEnabled(true);
                bk.repaint();
                bk.revalidate();
            }

        });
        student_reg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bk.removeAll();
                bk.add(new MENU());
                bk.add(new Student_Registation());
                  home.setEnabled(true);
                issue.setEnabled(true);
                deposite.setEnabled(true);
                student_reg.setEnabled(false);
                student_update.setEnabled(true);
                book_iformation.setEnabled(true);
                student_information.setEnabled(true);
                bk.repaint();
                bk.revalidate();

            }

        });
        student_update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bk.removeAll();
                bk.add(new MENU());
                bk.add(new Student_update());
                  home.setEnabled(true);
                issue.setEnabled(true);
                deposite.setEnabled(true);
                student_reg.setEnabled(true);
                student_update.setEnabled(false);
                book_iformation.setEnabled(true);
                student_information.setEnabled(true);
                bk.repaint();
                bk.revalidate();

            }
        });
        book_iformation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bk.removeAll();
                bk.add(new MENU());
                bk.add(new Books_information());
                  home.setEnabled(true);
                issue.setEnabled(true);
                deposite.setEnabled(true);
                student_reg.setEnabled(true);
                student_update.setEnabled(true);
                book_iformation.setEnabled(false);
                student_information.setEnabled(true);
                bk.repaint();
                bk.revalidate();
            }

        });
        student_information.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bk.removeAll();
                bk.add(new MENU());
                bk.add(new Students_information());
                  home.setEnabled(true);
                issue.setEnabled(true);
                deposite.setEnabled(true);
                student_reg.setEnabled(true);
                student_update.setEnabled(true);
                book_iformation.setEnabled(true);
                student_information.setEnabled(false);
                bk.repaint();
                bk.revalidate();
            }

        });

    }

    public class home {
    }
}
