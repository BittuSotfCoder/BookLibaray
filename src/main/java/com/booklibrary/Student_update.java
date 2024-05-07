package com.booklibrary;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Student_update extends JPanel implements ActionListener {
    protected JLabel lsem, lRol, restrict1,restrict2,signMob;
    protected JTextField Rol;
    private JRadioButton Female, Male;
    private JTextArea addr, Decs;
    private JDateChooser cal;
    private Date sqlDate;
    private String fn2 = "";
    private String fTn2 = "";
    final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
    final String DBuser = "usxqljshik8op7dd";
    final String DBpass = "ViDjCizs55WoYAMXOmeW";
    EmailValid em = new EmailValid();
    private String FinalGen;
    Font Font1, Font2;
    static boolean LOGEmailCheck,MOBCheck;
    JInternalFrame c;
    private String S_name, F_name, Gend;
    private JTextField TextFirst, TextLast, SinEmail, SinText, F_TextFirst, F_TextLast;
    /**
     *
     */
    private JButton getDetails, Register_Now;
    private JComboBox<String> com;

    Progress pei;

    public Student_update() {
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
        setBounds(550, 0, x, y);
        setBackground(Color.decode("#C8BFE7"));
        setLayout(null);

        c = new JInternalFrame("Student's Update", false, true, false, true);
        c.setBounds(0, 0, x - 550, y);
        c.setLayout(new FlowLayout());
        c.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        ImageIcon up = new ImageIcon("img/update.jpg");
        c.setFrameIcon(up);
        c.setVisible(true);
        initFrame(c);
        add(c);
        c.setLayout(null);

        // Font Font1=new Font("Georgia", Font.ROMAN_BASELINE, 20);
        Font log = new Font("Algerian", Font.PLAIN, 40);
        Font fillFont = new Font("Italic", Font.ITALIC, 20);
        Font1 = new Font("Georgia", Font.ROMAN_BASELINE, 20); // label id
        Font2 = new Font("Georgia", Font.ROMAN_BASELINE, 25); // label id

        JLabel us1 = new JLabel("Student's Update");
        us1.setBounds(450, 40, 1800, 60);
        us1.setFont(log);
        // -----------------------------------------------------------------
        lsem = new JLabel("Select Semester :");
        lsem.setBounds(200, 160, 250, 40);
        // lsem.setForeground(Color.decode("#409AE0"));
        lsem.setFont(Font2);
        lRol = new JLabel("Enter Roll No:");
        lRol.setBounds(700, 160, 250, 40);
        lRol.setFont(Font2);

        // -----------------------------------------------------------------
        String ar[] = { "SEM_1", "SEM_2", "SEM_3", "SEM_4", "SEM_5", "SEM_6" };
        com = new JComboBox<>(ar);
        com.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        com.setBounds(430, 160, 120, 50);
        com.setFont(Font2);

        Rol = new JTextField();
        Rol.setBounds(890, 160, 120, 50);
        Rol.setFont(fillFont);

        JLabel SigFi = new JLabel("Student Name :");
        SigFi.setBounds(150, 270, 200, 50);
        SigFi.setFont(Font2);
        c.add(SigFi);

        JLabel SigFirst = new JLabel("First Name");
        SigFirst.setBounds(380, 230, 350, 50);
        SigFirst.setFont(Font1);
        c.add(SigFirst);

        TextFirst = new JTextField();
        TextFirst.setBounds(375, 270, 300, 50);
        TextFirst.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(TextFirst);

        JLabel SigLast = new JLabel("Last Name");
        SigLast.setBounds(800, 230, 350, 50);
        SigLast.setFont(Font1);
        c.add(SigLast);

        TextLast = new JTextField();
        TextLast.setBounds(795, 270, 300, 50);
        TextLast.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(TextLast);

        JLabel Fath_SigFi = new JLabel("Father's Name :");
        Fath_SigFi.setBounds(150, 370, 200, 50);
        Fath_SigFi.setFont(Font2);
        c.add(Fath_SigFi);

        JLabel F_SigFirst = new JLabel("First Name");
        F_SigFirst.setBounds(380, 330, 350, 50);
        F_SigFirst.setFont(Font1);
        c.add(F_SigFirst);

        F_TextFirst = new JTextField();
        F_TextFirst.setBounds(375, 370, 300, 50);
        F_TextFirst.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(F_TextFirst);

        JLabel F_SigLast = new JLabel("Last Name");
        F_SigLast.setBounds(800, 330, 350, 50);
        F_SigLast.setFont(Font1);
        c.add(F_SigLast);

        F_TextLast = new JTextField();
        F_TextLast.setBounds(795, 370, 300, 50);
        F_TextLast.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(F_TextLast);

        JLabel Gen_SigFi = new JLabel("Gender :");
        Gen_SigFi.setBounds(230, 465, 350, 50);
        Gen_SigFi.setFont(Font2);
        c.add(Gen_SigFi);

        Male = new JRadioButton("Male");
        Male.setBounds(380, 465, 100, 50);
        Male.setFont(Font1);
        c.add(Male);

        Female = new JRadioButton("Female");
        Female.setBounds(480, 465, 100, 50);
        Female.setFont(Font1);
        c.add(Female);

        JLabel D_O_B = new JLabel("Date Of Birth :");
        D_O_B.setBounds(700, 465, 350, 50);
        D_O_B.setFont(Font2);
        c.add(D_O_B);

        cal = new JDateChooser();
        cal.setBounds(900, 465, 200, 50);
        c.add(cal);
        cal.setDateFormatString("dd-MM-yyyy");
        

        ButtonGroup gender = new ButtonGroup();
        gender.add(Male);
        gender.add(Female);
        Male.setSelected(true);

        JLabel SigEmail = new JLabel("Email_Id");
        SigEmail.setBounds(170, 530, 350, 50);
        SigEmail.setFont(Font1);
        c.add(SigEmail);

        SinEmail = new JTextField();
        SinEmail.setBounds(165, 570, 420, 50);
        SinEmail.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(SinEmail);

        JLabel SigMob = new JLabel("Mobile No");
        SigMob.setBounds(680, 530, 350, 50);
        SigMob.setFont(Font1);
        c.add(SigMob);

        JTextField Sin91 = new JTextField(" +91");
        Sin91.setEditable(false);
        Sin91.setBounds(675, 570, 50, 50);
        Sin91.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(Sin91);

        SinText = new JTextField();
        SinText.setBounds(725, 570, 370, 50);
        SinText.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(SinText);

        JLabel addres = new JLabel("Address:");
        addres.setBounds(180, 640, 150, 50);
        c.add(addres);
        addres.setFont(Font2);

        addr = new JTextArea();
        addr.setBounds(300, 640, 280, 100);
        c.add(addr);
        addr.setFont(new Font("arial", Font.ITALIC, 20));
        addr.setLineWrap(true);

        JLabel decs = new JLabel("Description:");
        decs.setBounds(680, 640, 150, 50);
        c.add(decs);
        decs.setFont(Font2);

        Decs = new JTextArea();
        Decs.setBounds(830, 640, 280, 100);
        Decs.setFont(new Font("arial", Font.ITALIC, 20));
        c.add(Decs);
        Decs.setLineWrap(true);

        Register_Now = new JButton("Save Now");
        Register_Now.setBounds(550, 800, 200, 50);
        Register_Now.setFont(new Font("arial", Font.BOLD, 25));
        c.add(Register_Now);
        getDetails = new JButton("GetDetails");
        getDetails.setBounds(1050, 165, 100, 40);
        getDetails.setFont(new Font("arial", Font.BOLD, 13));
        c.add(getDetails);
        c.add(us1);
        c.add(com);
        c.add(lsem);
        c.add(Rol);
        c.add(lRol);

        Register_Now.addActionListener(this);
        getDetails.addActionListener(this);
        Male.addActionListener(this);
        Female.addActionListener(this);
        getDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Register_Now.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        restrict1 = new JLabel("");
        restrict1.setFont(new Font("arial", Font.BOLD, 15));
        restrict1.setForeground(Color.RED);
        c.add(restrict1);
        restrict1.setVisible(false);

        restrict2 = new JLabel("");
        restrict2.setFont(new Font("arial", Font.BOLD, 15));
        restrict2.setForeground(Color.RED);
        c.add(restrict2);
        restrict2.setVisible(false);

        signMob = new JLabel("");
        signMob.setFont(new Font("arial", Font.BOLD, 15));
        signMob.setForeground(Color.RED);
        c.add(signMob);
        signMob.setVisible(false);
        signMob.setBounds(725, 620, 300, 20);


        Register_Now.setEnabled(false);
        // // -----------------------------------------------------------------

        Rol.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                restrict1.setBounds(890, 210, 300, 20);
                
                 char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                        Rol.setEditable(true);
                        restrict1.setVisible(false);
                        if (Character.isLetter(c)) {
                            Rol.setEditable(true);
                            restrict1.setText("Input only Numeric (0-9)");
                            restrict1.setVisible(true);
                        }
                    } else {
                        restrict1.setVisible(false);
                        if (Rol.getText().length() == 10) {
                            Rol.setEditable(false);
                            restrict1.setText("Input Only 10 digit number");
                            restrict1.setVisible(true);
                        }
                    }
            }

            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
              
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
            }
            
        });

        TextFirst.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                restrict1.setBounds(375, 318, 300, 20);
                char c = e.getKeyChar();
                if (Character.isLetter(c)  || Character.isISOControl(c)) {
                    TextFirst.setEditable(true);
                    restrict1.setVisible(false);
                   } else {
                       e.consume();
                       restrict1.setText("Input Chracter (ABC..)");
                           restrict1.setVisible(true);
                           TextFirst.setEditable(false);
       
                       }
                   }
       
                   @Override
                   public void keyPressed(java.awt.event.KeyEvent e) {
                
                   }
       
                   @Override
                   public void keyReleased(java.awt.event.KeyEvent e) {
                 
                   }
               });

        TextLast.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                    restrict1.setBounds(799, 318, 300, 20);
                    char c = e.getKeyChar();
                    if (Character.isLetter(c)|| Character.isISOControl(c)) {
                        TextLast.setEditable(true);
                        restrict1.setVisible(false);
                       } else {
                           e.consume();
                           restrict1.setText("Input Chracter (ABC..)");
                               restrict1.setVisible(true);
                               TextLast.setEditable(false);
           
                           }
                       }
           
                       @Override
                       public void keyPressed(java.awt.event.KeyEvent e) {
                    
                       }
           
                       @Override
                       public void keyReleased(java.awt.event.KeyEvent e) {
                     
                       }
                   });

     F_TextFirst.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(java.awt.event.KeyEvent e) {
                        restrict1.setBounds(375, 425, 300, 20);;
                        char c = e.getKeyChar();
                        if (Character.isLetter(c)  || Character.isISOControl(c)) {
                            F_TextFirst.setEditable(true);
                            restrict1.setVisible(false);
                           } else {
                               e.consume();
                               restrict1.setText("Input Chracter (ABC..)");
                                   restrict1.setVisible(true);
                                   F_TextFirst.setEditable(false);
                
                               }
                           }
                
                           @Override
                           public void keyPressed(java.awt.event.KeyEvent e) {
                        
                           }
                
                           @Override
                           public void keyReleased(java.awt.event.KeyEvent e) {
                         
                           }
                       });
                
     F_TextLast.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(java.awt.event.KeyEvent e) {
                        restrict1.setBounds(795, 425, 300, 20);;
                        char c = e.getKeyChar();
                        if (Character.isLetter(c)  || Character.isISOControl(c)) {
                            F_TextLast.setEditable(true);
                            restrict1.setVisible(false);
                           } else {
                               e.consume();
                               restrict1.setText("Input Chracter (ABC..)");
                                   restrict1.setVisible(true);
                                   F_TextLast.setEditable(false);
                
                               }
                           }
                
                           @Override
                           public void keyPressed(java.awt.event.KeyEvent e) {
                        
                           }
                
                           @Override
                           public void keyReleased(java.awt.event.KeyEvent e) {
                         
                           }
                       });

    SinText.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(java.awt.event.KeyEvent e) {
                            signMob.setBounds(725, 620, 300, 20);
                              char c = e.getKeyChar();
                                if (!Character.isDigit(c)) {
                                    e.consume();
                                    SinText.setEditable(true);
                                    signMob.setVisible(false);
                                    if (Character.isLetter(c)) {
                                        SinText.setEditable(true);
                                        signMob.setText("Input only Numeric (0-9)");
                                        signMob.setVisible(true);
                                    }
                                } else {
                                    signMob.setVisible(false);
                                    if (SinText.getText().length() == 10) {
                                        SinText.setEditable(false);
                                        signMob.setText("Input Only 10 digit number");
                                  
                                        signMob.setVisible(true);
                                    }
                                }
                        }
                    
                        @Override
                        public void keyPressed(java.awt.event.KeyEvent e) {
                     
                        }
                    
                        @Override
                        public void keyReleased(java.awt.event.KeyEvent e) {
                       
                        }
                        
                    });























        Rol.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                signMob.setVisible(false);
                restrict2.setVisible(false);
                restrict1.setVisible(false);
                getDetails.setEnabled(true);
                Rol.setText(null);
                Register_Now.setEnabled(false);
                TextFirst.setText(null);
                TextLast.setText(null);
                F_TextFirst.setText(null);
                F_TextLast.setText(null);
                Male.setSelected(true);
                cal.setDate(null);
                SinEmail.setText(null);
                SinText.setText(null);
                addr.setText(null);
                Decs.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });

        TextFirst.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict1.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });

        F_TextFirst.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict1.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });

        cal.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict1.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });

        SinEmail.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict2.setVisible(false);
                restrict1.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(SinEmail.getText().equals("")){

                }else{
            if(em.isValid(SinEmail.getText())==true){
                LOGEmailCheck=true;
            }else{
                restrict2.setText("Invalid Email Id");
                restrict2.setBounds(165, 620, 300, 20);
                restrict2.setVisible(true);
                LOGEmailCheck=false;

            }

            }

            }

        });

        SinText.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                signMob.setVisible(false);
                restrict1.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if(SinText.getText().equals("")){

                }
                else{
                int digi = 10 - SinText.getText().length();
                String Mobcheck = SinText.getText();
                char c = Mobcheck.charAt(0);
                String t = String.valueOf(c);
                int mb1 = Integer.parseInt(t);
                if (SinText.getText().length() < 10) {
                    MOBCheck = false;
                    signMob.setText("Please Enter " + Integer.toString(digi) + " More Digit");
                    
                    signMob.setVisible(true);
                } else if (mb1 == 9 || mb1 == 8 || mb1 == 7 || mb1 == 6) {
                    MOBCheck = true;
                    if(SinText.getText().length()==10){
                        signMob.setVisible(false);
                    }
                } else {
                    MOBCheck = false;
                    signMob.setText("Invalid Mobile Number");
                    signMob.setVisible(true);
                }
            }
                     }
        });


    }

    private void initFrame(JInternalFrame jInternalFrame) {
        // initFrame(jInternalFrame);
    }

    @Override
    public void actionPerformed(ActionEvent ee) {

        if (getDetails.equals(ee.getSource())) {

            if(Rol.getText().equals("")){
                        restrict1.setText("Roll Required*");
                        restrict1.setBounds(890, 210, 300, 20);
                        restrict1.setVisible(true);

            }
            else{
            signMob.setVisible(false);
                restrict2.setVisible(false);
                restrict1.setVisible(false);
               
                TextFirst.setText(null);
                TextLast.setText(null);
                F_TextFirst.setText(null);
                F_TextLast.setText(null);
                Male.setSelected(true);
                cal.setDate(null);
                SinEmail.setText(null);
                SinText.setText(null);
                addr.setText(null);
                Decs.setText(null);

            pei = new Progress();
            pei.pro.setBounds(470, 100, 330, 25);
            c.add(pei.pro);
            repaint();
            validate();

            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Connection con2 = DriverManager.getConnection(url, DBuser, DBpass);
                        final String Qry = "select * from " + com.getSelectedItem() + " where Roll_no=" + Rol.getText();
                        Statement statement = con2.createStatement();
                        ResultSet rs = statement.executeQuery(Qry);
                        if (rs.next()) {
                            S_name = rs.getString("STUDENT_NAME");
                            F_name = rs.getString("FATHER_NAME");
                            Gend = rs.getString("GENDER");
                            if (Gend.equals("Male")) {
                                Male.setSelected(true);
                            } else {
                                Female.setSelected(true);
                            }
                            sqlDate = rs.getDate("D_O_B");
                            cal.setDate(sqlDate);
                            SinEmail.setText(rs.getString("EMAIL"));
                            SinText.setText(rs.getString("MOB"));
                            addr.setText(rs.getString("ADDRESS"));
                            Decs.setText(rs.getString("DECS"));

                            String[] F_Nam = S_name.split(" ");
                            String[] Fath_Name = F_name.split(" ");
                            String fn = F_Nam[0];
                            TextFirst.setText(fn);
                            String fTn = Fath_Name[0];
                            F_TextFirst.setText(fTn);
                            try {
                                fTn2 = Fath_Name[1];
                                F_TextLast.setText(fTn2);
                                fn2 = F_Nam[1];
                                TextLast.setText(fn2);
                            } catch (Exception enw) {
                            } finally {
                                getDetails.setEnabled(false);
                                Register_Now.setEnabled(true);
                                fn2 = F_Nam[1];
                                TextLast.setText(fn2);
                                fTn2 = Fath_Name[1];
                                F_TextLast.setText(fTn2);

                            }
                        } else {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(com, "Detail Not Found", "Message!..",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        con2.close();
                    } catch (Exception jkhjg) {
                        jkhjg.getStackTrace();
                    }
                    try {
                        pei.t.stop();
                        pei.pro.setIndeterminate(false);
                        pei.pro.setVisible(false);
                    } catch (Exception e) {

                    }
                }

            }).start();
        }




        } else if (Register_Now.equals(ee.getSource())) {

           
                    if (Rol.getText().equals("")) {
                        restrict1.setText("Roll Required*");
                        restrict1.setBounds(890, 210, 300, 20);
                        restrict1.setVisible(true);
                    } else if (TextFirst.getText().equals("")) {

                        restrict1.setText("Name Requried*");
                        restrict1.setBounds(375, 318, 300, 20);
                        restrict1.setVisible(true);
                    }

                    else if (F_TextFirst.getText().equals("")) {
                        restrict1.setText("Father's Name Requried*");
                        restrict1.setBounds(375, 425, 300, 20);
                        restrict1.setVisible(true);

                    }

                    else if (cal.getDate() == null) {

                        restrict1.setText("Date Of Birth Requried*");
                        restrict1.setBounds(900, 520, 300, 20);
                        restrict1.setVisible(true);
                    } else if (SinEmail.getText().equals("")) {
                        restrict1.setText("Eamil_Id Requried*");
                        restrict1.setBounds(165, 620, 300, 20);
                        restrict1.setVisible(true);
                    } else if (SinText.getText().equals("")) {
                        signMob.setText(null);
                        signMob.setText("Mobile No. Requried*");
                        // signMob.setBounds(725, 620, 300, 20);
                        signMob.setVisible(true);
                    } 
                    else if(LOGEmailCheck==false ){
                    
                    
                    } else if(MOBCheck==false ){
         

                    }
                    else {
                        pei = new Progress();
                        pei.pro.setBounds(470, 100, 330, 25);
                        c.add(pei.pro);
                        repaint();
                        validate();
                         new Thread(new Runnable() {

                @Override
                public void run() {
                        try {
                            if (Male.isSelected()) {

                                FinalGen = "Male";

                            } else {
                                FinalGen = "Female";
                            }
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String d = simpleDateFormat.format(cal.getDate());

                            Connection con3 = DriverManager.getConnection(url, DBuser, DBpass);
                            final String quwer = "update " + com.getSelectedItem() + " SET STUDENT_NAME='"
                                    + TextFirst.getText()
                                    + " " + TextLast.getText() + "', FATHER_NAME='" + F_TextFirst.getText() + " "
                                    + F_TextLast.getText() + "', GENDER='" + FinalGen + "', D_O_B='" + d + "', MOB='"
                                    + SinText.getText()
                                    + "', EMAIL='" + SinEmail.getText() + "', ADDRESS='" + addr.getText() + "', DECS='"
                                    + Decs.getText() + "' WHERe ROLL_NO=" + Rol.getText();

                            Statement statemen = con3.createStatement();
                            statemen.executeUpdate(quwer);
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(com, "Update Suceesfully", "Message!..",
                                    JOptionPane.INFORMATION_MESSAGE);
                            com.setSelectedIndex(0);
                            Rol.setText(null);
                            TextFirst.setText(null);
                            TextLast.setText(null);
                            F_TextFirst.setText(null);
                            F_TextLast.setText(null);
                            Male.setSelected(true);
                            cal.setDate(null);
                            SinEmail.setText(null);
                            SinText.setText(null);
                            addr.setText(null);
                            Decs.setText(null);
                            con3.close();
                        } catch (SQLException e1) {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(com, e1, "Message!..", JOptionPane.ERROR_MESSAGE);

                        }
                    }
    
                }).start();
                    }
        }

    }
}