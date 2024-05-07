package com.booklibrary;

import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.time.LocalDate;
import java.lang.Thread;

public class Issues extends JPanel implements ActionListener, FocusListener {
    protected static final Font Font1 = null;
    protected static Font fillFont;
    protected JButton register;
    protected JFrame frame;
    private Progress pei;
    final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
    final String DBuser = "usxqljshik8op7dd";
    final String DBpass = "ViDjCizs55WoYAMXOmeW";

    protected JLabel em, mob, User, lsem, lRol, bookName, bookId, restrict1, restrict2, restrict3;
    protected JTextField tem, tmob, tusr, Rol, bookname, bookid;
    protected String col = "#EA3680";
    protected String setname = "", setmob = "", setemail = "";
    protected String Bookval;
    protected String Bookvalu, BookNAME, BookID;
    protected int check;
    protected String Depo, dat;
    JInternalFrame c;
    private JComboBox<String> com;
    /**
    * 
    */
    int Sizex = 350;
    int Sizey = 100;

    public Issues() {
        // iuessername=Login_Signup.User_name;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
        setBounds(550, 0, x, y);
        setBackground(Color.decode("#C8BFE7"));
        setLayout(null);

        c = new JInternalFrame("Issues Books", false, true, false, true);
        c.setBounds(0, 0, x - 550, y);
        c.setLayout(new FlowLayout());
        c.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        ImageIcon IS = new ImageIcon("img/isses.png");
        c.setFrameIcon(IS);
        c.setVisible(true);
        initFrame(c);
        add(c);
        c.setLayout(null);

        Font Font1 = new Font("Georgia", Font.ROMAN_BASELINE, 20);
        Font log = new Font("Algerian", Font.PLAIN, 60);
        Font fillFont = new Font("Italic", Font.ITALIC, 20);
        // Font fil = new Font("Italic", Font.ITALIC, 17);

        ImageIcon Icon1 = new ImageIcon("issu.jpg");
        Image im = Icon1.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        ImageIcon Icon2 = new ImageIcon(im);
        JLabel ico = new JLabel(Icon2);
        ico.setBounds(30, 15, 250, 250);
        JLabel us1 = new JLabel("Books Issues");
        us1.setBounds(450, 40, 1800, 60);
        us1.setForeground(Color.decode("#A25EF2"));
        us1.setFont(log);
        // -----------------------------------------------------------------
        lsem = new JLabel("Select Semster :");
        lsem.setBounds(Sizex + 120, Sizey + 60, 250, 40);
        lsem.setFont(Font1);
        lsem.setForeground(Color.decode("#409AE0"));

        lRol = new JLabel("Enter the Roll :");
        lRol.setBounds(Sizex + 120, Sizey + 110, 250, 40);
        lRol.setFont(Font1);
        lRol.setForeground(Color.decode("#409AE0"));

        em = new JLabel("Student Name:");
        em.setBounds(Sizex + 28, Sizey + 180, 250, 40);
        em.setFont(Font1);
        em.setForeground(Color.decode(col));
        mob = new JLabel("E-mail Id:");
        mob.setBounds(Sizex + 78, Sizey + 230, 250, 40);
        mob.setFont(Font1);
        mob.setForeground(Color.decode(col));
        User = new JLabel("Mobile no-:");
        User.setBounds(Sizex + 65, Sizey + 280, 250, 40);
        User.setFont(Font1);
        User.setForeground(Color.decode(col));

        bookName = new JLabel("Book Name:");
        bookName.setBounds(Sizex + 65, Sizey + 400, 250, 40);
        bookName.setFont(Font1);
        bookName.setForeground(Color.decode(col));
        // bookName.setForeground(Color.decode("#409AE0"));
        
        bookId = new JLabel("Enter Book Id:");
        bookId.setBounds(Sizex + 145, Sizey + 330, 150, 40);
        bookId.setFont(Font1);
        bookId.setForeground(Color.decode("#409AE0"));
        // -----------------------------------------------------------------
        String ar[] = { "SEM_1", "SEM_2", "SEM_3", "SEM_4", "SEM_5", "SEM_6" };
        com = new JComboBox<>(ar);
        com.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        com.setBounds(Sizex + 290, Sizey + 60, 120, 40);
        com.setFont(fillFont);

        Rol = new JTextField();
        Rol.setBounds(Sizex + 290, Sizey + 110, 120, 40);
        Rol.setFont(fillFont);

        tem = new JTextField();
        tem.setBounds(Sizex + 190, Sizey + 180, 350, 37);
        tem.setFont(fillFont);
        tem.setEditable(false);
        tmob = new JTextField();
        tmob.setBounds(Sizex + 190, Sizey + 230, 350, 37);
        tmob.setFont(fillFont);
        tmob.setEditable(false);
        tusr = new JTextField();
        tusr.setBounds(Sizex + 190, Sizey + 280, 350, 37);
        tusr.setFont(fillFont);
        tusr.setEditable(false);
        // -------------------------------------------------------------------
        bookname = new JTextField();
        bookname.setBounds(Sizex + 190, Sizey + 400, 350, 40);
        bookname.setFont(fillFont);
        bookname.setEditable(false);
        
        


        bookid = new JTextField();
        bookid.setBounds(Sizex + 290, Sizey + 330, 150, 40);
        bookid.setFont(fillFont);
        bookid.setEditable(false);

        // -----------------------------------------------------------------
        // ImageIcon regis=new ImageIcon("Register.png");
        // Image Sc=regis.getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
        // ImageIcon regist1=new ImageIcon(Sc);
        register = new JButton("Submit");
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        register.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 20));
        register.setBounds(Sizex + 200, Sizey + 480, 170, 50);

        // get = new JButton("Get Details");
        // get.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // get.setBounds(Sizex + 440, Sizey + 110, 120, 40);
        // get.setFont(fil);

        // ImageIcon Lo=new ImageIcon("SIGUPLOGo.jpg");
        // Image Sc2=Lo.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        // ImageIcon Lo1=new ImageIcon(Sc2);
        // Logo=new JLabel(Lo1);
        // Logo.setBounds(85, 20, 120, 120);

        

        c.add(us1);

        c.add(em);
        c.add(mob);
        c.add(User);
        c.add(bookId);
        c.add(bookName);

        c.add(tem);
        c.add(tmob);
        c.add(tusr);
        // c.add(get);
        c.add(register);
        // c.add(Logo);
        c.add(ico);
        c.add(com);
        c.add(lsem);
        c.add(Rol);
        c.add(lRol);
        c.add(bookname);
        c.add(bookid);

        register.addActionListener(this);
        register.setEnabled(false);
        // get.addActionListener(this);
        Rol.addFocusListener(this);

        restrict1 = new JLabel("");
        restrict1.setFont(new Font("arial", Font.BOLD, 15));
        restrict1.setForeground(Color.RED);
        c.add(restrict1);
        restrict1.setVisible(false);
        restrict1.setBounds(Sizex + 290, Sizey + 149, 300, 20);

        restrict2 = new JLabel("");
        restrict2.setFont(new Font("arial", Font.BOLD, 15));
        restrict2.setForeground(Color.RED);
        c.add(restrict2);
        restrict2.setVisible(false);

        restrict3 = new JLabel("");
        restrict3.setFont(new Font("arial", Font.BOLD, 15));
        restrict3.setForeground(Color.RED);
        c.add(restrict3);
        restrict3.setVisible(false);
        restrict3.setBounds(Sizex + 290, Sizey + 375, 300, 20);
     

        Rol.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                bookid.setText(null);
                bookname.setText(null);
                restrict1.setVisible(false);
                tem.setText(null);
                tmob.setText(null);
                tusr.setText(null);
                setname = "";
                setmob = "";
                setemail = "";
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });


        bookid.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c)) {
                    e.consume();
                    bookid.setEditable(true);
                    restrict3.setVisible(false);
                    if (Character.isLetter(c)) {
                        bookid.setEditable(true);
                        restrict3.setText("Input only Numeric (0-9)");
                        restrict3.setVisible(true);
                    }
                } else {
                    restrict3.setVisible(false);
                    if (bookid.getText().length() == 10) {
                        bookid.setEditable(false);
                        restrict3.setText("Input Only 10 digit number");
                        restrict3.setVisible(true);
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

        Rol.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {

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






        bookid.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict3.setVisible(false);
                bookid.setText(null);
                bookname.setText(null);
                register.setEnabled(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
               
                if(bookid.getText().equals("") ){
                }else{

            
              
            pei = new Progress();
                pei.pro.setBounds(480, 100, 330, 25);
                c.add(pei.pro);
                repaint();
                validate();

            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Connection con2 = DriverManager.getConnection(url, DBuser, DBpass);
                       
                        
                        final java.lang.String Qry = "select * from Books_Infor where Book_Id=" + bookid.getText();
                       
                        
                        Statement statement = con2.createStatement();
                        ResultSet rs = statement.executeQuery(Qry);
                        if (rs.next()) {
                     
                        String a=rs.getString("column_name_check");  
                        int a1=Integer.parseInt(a);
                        
                        if(a1==1){
                            bookname.setText(rs.getString("Book_Name"));
                                register.setEnabled(true);
                        }else{
                              try {
                                    pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }
                            JOptionPane.showMessageDialog(com, "This Book Already issues", "Message!..",JOptionPane.INFORMATION_MESSAGE);
                        }
                        } else {
                            try {
                                        pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }
                            JOptionPane.showMessageDialog(com, "Wrong Book Id", "Message!..",
                                    JOptionPane.WARNING_MESSAGE);
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



            }

        });

        bookname.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict2.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });

    }

    private void initFrame(JInternalFrame jInternalFrame) {
        // initFrame(jInternalFrame);
    }

    public void actionPerformed(ActionEvent e) {
        {
            if (bookname.getText().equals("") && bookid.getText().equals("")) {
                restrict2.setText("Book Name Required*");
                restrict2.setBounds(Sizex + 190, Sizey + 445, 300, 20);
                restrict2.setVisible(true);
                
                restrict3.setText("Book ID Required*");
                
                restrict3.setVisible(true);
            } else if (bookname.getText().equals("")) {
                restrict2.setText("Book Name Required*");
                restrict2.setBounds(Sizex + 190, Sizey + 445, 300, 20);
                restrict2.setVisible(true);
            } else if (bookid.getText().equals("")) {
                restrict3.setText("Book ID Required*");
                restrict3.setVisible(true);
            } else if (setname.equals("") && setmob.equals("") && setemail.equals("")) {
                // JOptionPane.showMessageDialog(null, "Student Detail Not Found", "Message!..",
                // JOptionPane.INFORMATION_MESSAGE);
            } else {
                pei = new Progress();
                pei.pro.setBounds(480, 100, 330, 25);
                c.add(pei.pro);
                repaint();
                validate();
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        // try{
                        // Class.forName("oracle.jdbc.OracleDriver");
                        // Connection con =
                        // DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bittu","Bittu");
                        // final String QY="select USNAME from ADUSERS where USER_ID='"+iuessername+"'";
                        // Statement st=con.createStatement();
                        // ResultSet r=st.executeQuery(QY);
                        // while(r.next()){
                        // issuesName=r.getString("USNAME");
                        // }
                        // con.close();
                        // // System.out.println(issuesName);
                        // }catch(Exception ty){}
                        {

                            LocalDate str = LocalDate.now();

                            try {
                                // Class.forName("oracle.jdbc.OracleDriver");
                                Connection con = DriverManager.getConnection(url, DBuser, DBpass);
                                // final String Queru="SELECT STUDENT_NAME,MOB,EMAIL FROM "+(String)
                                // com.getSelectedItem()+" WHERE ROLL_NO="+Rol.getText();
                                final String Queru = "SELECT Checked FROM  BOOKS where SEM='"
                                        + (String) com.getSelectedItem()
                                        + "' and roll=" + Rol.getText();
                                Statement statemen = con.createStatement();
                                ResultSet result = statemen.executeQuery(Queru);
                                while (result.next()) {
                                    check = result.getInt("checked");
                                }
                                con.close();
                                if (check == 1) {

                                    try {
                                        // System.out.println("HELlo");
                                        // Class.forName("oracle.jdbc.OracleDriver");
                                        Connection conect = DriverManager.getConnection(url, DBuser, DBpass);
                                        final String Query = "select BOOK_NAME,BOOK_NO,ISSUES from BOOKS where SEM='"
                                                + (String) com.getSelectedItem() + "' and ROLL=" + Rol.getText() + "";
                                        // System.out.println(Query);
                                        Statement statement = conect.createStatement();
                                        ResultSet resul = statement.executeQuery(Query);
                                        while (resul.next()) {
                                            dat = resul.getString("ISSUES");
                                            BookNAME = resul.getString("BOOK_NAME");
                                            BookID = resul.getString("BOOK_NO");
                                        }
                                        conect.close();
                                    } catch (Exception ejh) {
                                    }

                                    String[] t = dat.split(" ");
                                    String s = t[0];
                                    try {
                                        pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }
                                    new MESS(BookNAME, BookID, s);
                                    // c.add(new NOTIssues(
                                } else {

                                    try {

                                        Connection co = DriverManager.getConnection(url, DBuser, DBpass);
                                        final String Qry = "insert into BOOKS(Roll,Student_name,SEM,Issues,book_name,Book_NO,Issuer_name,CHECKED) values("
                                                + Rol.getText() + ",'" + setname + "','"
                                                + (String) com.getSelectedItem()
                                                + "','" + str + "','" + bookname.getText() + "','" + bookid.getText()
                                                + "','"
                                                + Login_Signup.User_name + "',1)";
                                        PreparedStatement statement = co.prepareStatement(Qry);
                                        // System.out.println(Qry);
                                           String q1 ="UPDATE Books_Infor set column_name_check=0 WHERE Book_Id="+bookid.getText();
                                                  
                                        statement.executeUpdate(q1);
                                        statement.executeUpdate();
                                        String message = "Dear,\n \t" + setname
                                                + " book issues Successfully : \n \t\t\t Book Name :"
                                                + bookname.getText()
                                                + "\n \t\t\t Book Id :" + bookid.getText();
                                        try {
                                            pei.t.stop();
                                            pei.pro.setIndeterminate(false);
                                            pei.pro.setVisible(false);
                                        } catch (Exception e) {

                                        }
                                        JOptionPane.showMessageDialog(com, "Issue Suceessfully");
                                    
                                        
                                        
                                        com.setSelectedIndex(0);
                                        tem.setText(null);
                                        tmob.setText(null);
                                        tusr.setText(null);
                                        Rol.setText(null);
                                        bookid.setText(null);
                                        bookname.setText(null);
                                        new Thread(new email_id(message, "Books Library : Issues", setemail, "bytecoading@gmail.com")).start();
                                        
                                        setname = "";
                                        setmob = "";
                                        setemail = "";
                                        
                                        co.close();

                                    } catch (Exception en) {
                                        JOptionPane.showMessageDialog(com, en);
                                    }
                                }

                                con.close();
                            } catch (Exception eh) {

                            }
                        }
                    }

                }).start();
            }
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        Rol.setText(null);
        bookid.setEditable(false);
        bookid.setText(null);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (Rol.getText().equals("")) {
            restrict1.setText("Roll Required*");
          
            restrict1.setVisible(true);
        } else {

            pei = new Progress();
            pei.pro.setBounds(480, 100, 330, 25);
            c.add(pei.pro);
            repaint();
            validate();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        tem.setText(null);
                        tusr.setText(null);
                        tmob.setText(null);
                        // ALTER USER BITTU IDENTIFIED BY Bittu ACCOUNT UNLOCK;
                        // Class.forName("oracle.jdbc.OracleDriver");
                        Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                        Statement statement = conn.createStatement();
                        // String Qury="select * from books WHERE SEM='"+(String)
                        // com.getSelectedItem()+"' and ROLL="+Rol.getText()+"";
                        String Qury = "select * from " + (String) com.getSelectedItem() + " WHERE ROLL_NO="
                                + Rol.getText()
                                + "";
                        // System.out.println(com.getSelectedItem());

                        // System.out.println(Rol.getText());

                        ResultSet rs = statement.executeQuery(Qury);
                        if (rs.next()) {
                            {

                                try {
                                    // Class.forName("oracle.jdbc.OracleDriver");
                                    Connection con = DriverManager.getConnection(url, DBuser, DBpass);
                                    final String Queru = "SELECT STUDENT_NAME,MOB,EMAIL FROM "
                                            + (String) com.getSelectedItem() + " WHERE ROLL_NO=" + Rol.getText();
                                    Statement statemen = con.createStatement();
                                    ResultSet result = statemen.executeQuery(Queru);
                                    while (result.next()) {
                                        setname = result.getString("STUDENT_NAME");
                                        setmob = result.getString("MOB");
                                        setemail = result.getString("EMAIl");
                                    }
                                    try {
                                        pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }
                                    tem.setText(setname);
                                    tmob.setText(setemail);
                                    tusr.setText(setmob);
                                    bookid.setEditable(true);
                                    con.close();
                                } catch (Exception et) {

                                }
                            }
                        } else {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            int Sel = JOptionPane.showConfirmDialog(com, "Student Detail's are not found!!!..\nAre You Registration new Student?", "Message", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                        if (Sel == 0) {
                            Login_Signup.bk.removeAll();
                            Login_Signup.bk.add(new MENU());
                            Login_Signup.bk.add(new Student_Registation());
                            Login_Signup.bk.repaint();
                            Login_Signup.bk.revalidate();
                        }
                            // JOptionPane.showMessageDialog(null, "Detail not found");
                            setname = "";
                            setmob = "";
                            setemail = "";
                        }

                        conn.close();
                    } catch (Exception e1) {
                    }
                }

            }).start();

        }
    }

}
// Improve _______________________________________________________________
// class NOTIssues extends JInternalFrame{
// public NOTIssues()
// {
// setTitle("Not issues");
// setResizable(false);
// setClosable(true);
// setMaximizable(false);
// setIconifiable(false);
// setBounds(585, 290, 750, 500);
// setVisible(true);
// setLayout(new FlowLayout());
// setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
// setBackground(Color.black);

// setVisible(true);
// setLayout(null);

// JLabel kl=new JLabel("Jknjm");

// }

// }