package com.booklibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Depo extends JPanel implements ActionListener {
    protected static Font fil;
    /**
     * 
     */
    final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
    final String DBuser = "usxqljshik8op7dd";
    final String DBpass = "ViDjCizs55WoYAMXOmeW";

    protected JFrame frame;
    private JButton submit, get;
    protected JComboBox<String> depsem;
    protected JTextField deproll, depname, depbookname, depbookid;
    protected JLabel Jdepname, Jdepbookname, Jdepbookid, restrict;
    private String S_name = "", Book_name = "", Book_id = "";
    private String E_ID, S_nam;
    private int check;
    private Progress pei;
    private JInternalFrame c;

    int X1 = 300;
    int Y1 = 250;

    public Depo() {
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
        setBounds(550, 0, x, y);
        setBackground(Color.decode("#C8BFE7"));
        setLayout(null);

        c = new JInternalFrame("Deposite Books", false, true, false, true);
        c.setBounds(0, 0, x - 550, y);
        c.setLayout(new FlowLayout());
        c.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        ImageIcon dep = new ImageIcon("img/depo.jpg");
        c.setFrameIcon(dep);
        c.setVisible(true);
        initFrame(c);
        add(c);
        c.setLayout(null);

        Font Font1 = new Font("Georgia", Font.ROMAN_BASELINE, 22);
        Font log = new Font("Algerian", Font.PLAIN, 70);
        Font fillFont = new Font("Italic", Font.ITALIC, 20);
        Font fil = new Font("Italic", Font.ITALIC, 17);

        JLabel dephead = new JLabel("Books Deposite");
        dephead.setBounds(330, 100, 1800, 60);
        dephead.setForeground(Color.decode("#A25EF2"));
        dephead.setFont(log);

        ImageIcon Icon1 = new ImageIcon("dep.jpg");
        Image im = Icon1.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        ImageIcon Icon2 = new ImageIcon(im);
        JLabel ico = new JLabel(Icon2);
        ico.setBounds(65, 0, 250, 250);

        // -----------------------text field--------------------------------------
        String ar[] = { "SEM_1", "SEM_2", "SEM_3", "SEM_4", "SEM_5", "SEM_6" };
        depsem = new JComboBox<>(ar);
        depsem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        depsem.setBounds(X1 + 370, Y1 + 60, 120, 40);
        depsem.setFont(fillFont);

        deproll = new JTextField();
        deproll.setBounds(X1 + 370, Y1 + 110, 120, 40);
        deproll.setFont(fillFont);

        depname = new JTextField();
        depname.setBounds(X1 + 240, Y1 + 180, 350, 37);
        depname.setFont(fillFont);
        depname.setEditable(false);
        depbookname = new JTextField();
        depbookname.setBounds(X1 + 240, Y1 + 230, 350, 37);
        depbookname.setFont(fillFont);
        depbookname.setEditable(false);
        depbookid = new JTextField();
        depbookid.setBounds(X1 + 240, Y1 + 280, 350, 37);
        depbookid.setFont(fillFont);
        depbookid.setEditable(false);

        // --------------------------lebel-----------------------------------------
        JLabel Jdepsem = new JLabel("Select Semster :");
        Jdepsem.setBounds(X1 + 210, Y1 + 60, 250, 40);
        Jdepsem.setFont(Font1);
        Jdepsem.setForeground(Color.decode("#409AE0"));

        JLabel Jdeproll = new JLabel("Roll No :");
        Jdeproll.setBounds(X1 + 280, Y1 + 110, 250, 40);
        Jdeproll.setFont(Font1);
        Jdeproll.setForeground(Color.decode("#409AE0"));

        Jdepname = new JLabel("Student Name:");
        Jdepname.setBounds(X1 + 78, Y1 + 180, 250, 40);
        Jdepname.setFont(Font1);
        Jdepname.setForeground(Color.decode("#EA3680"));
        Jdepbookname = new JLabel("Book Name:");
        Jdepbookname.setBounds(X1 + 108, Y1 + 230, 250, 40);
        Jdepbookname.setFont(Font1);
        Jdepbookname.setForeground(Color.decode("#EA3680"));
        Jdepbookid = new JLabel("Book Id:");
        Jdepbookid.setBounds(X1 + 145, Y1 + 280, 250, 40);
        Jdepbookid.setFont(Font1);
        Jdepbookid.setForeground(Color.decode("#EA3680"));

        // ----------------------------
        submit = new JButton("Submit");
        submit.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 20));
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.setBounds(X1 + 300, Y1 + 320, 170, 45);

        get = new JButton("Get Details");
        get.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        get.setBounds(X1 + 500, Y1 + 110, 120, 40);
        get.setFont(fil);
        submit.addActionListener(this);
        get.addActionListener(this);

        c.add(dephead);
        c.add(ico);
        c.add(depsem);
        c.add(Jdepsem);
        c.add(Jdeproll);
        c.add(deproll);
        c.add(depname);
        c.add(depbookname);
        c.add(depbookid);
        c.add(Jdepname);
        c.add(Jdepbookname);
        c.add(Jdepbookid);
        c.add(submit);
        c.add(get);

        restrict = new JLabel("");
        restrict.setFont(new Font("arial", Font.BOLD, 15));
        restrict.setForeground(Color.RED);
        c.add(restrict);
        restrict.setBounds(X1 + 370, Y1 + 155, 300, 20);
        restrict.setVisible(false);


deproll.addKeyListener(new KeyListener() {

    @Override
    public void keyTyped(KeyEvent e) {
       
        char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume();
            deproll.setEditable(true);
            restrict.setVisible(false);
            if (Character.isLetter(c)) {
                deproll.setEditable(true);
                restrict.setText("Input only Numeric (0-9)");
                restrict.setVisible(true);
            }
        } else {
            restrict.setVisible(false);
            if (deproll.getText().length() == 10) {
                deproll.setEditable(false);
                restrict.setText("Input Only 10 digit number");
                restrict.setVisible(true);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }
    
});
        



        deproll.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                S_name = "";
                Book_name = "";
                Book_id = "";
                restrict.setVisible(false);
                depname.setText(null);
                depbookname.setText(null);
                depbookid.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });
    }

    private void initFrame(JInternalFrame c) {
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (get.equals(e.getSource())) {
            if (deproll.getText().equals("")) {
                restrict.setText("Roll Required*");
                
                restrict.setVisible(true);
            } else {
                pei = new Progress();
                pei.pro.setBounds(480, 200, 330, 25);
                c.add(pei.pro);
                repaint();
                validate();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            // Class.forName("oracle.jdbc.OracleDriver");
                            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                            final String Query = "select STUDENT_NAME,BOOK_NAME,BOOK_NO,checked from BOOKS where SEM='"
                                    + (String) depsem.getSelectedItem() + "' and ROLL=" + deproll.getText() + "";
                            Statement statement = conn.createStatement();
                            ResultSet result = statement.executeQuery(Query);
                            // System.out.println("conn");
                            while (result.next()) {
                                check = result.getInt("checked");
                                S_name = result.getString("STUDENT_NAME");
                                Book_name = result.getString("BOOK_NAME");
                                Book_id = result.getString("BOOK_NO");
                            }
                            if (check == 0) {
                                S_name = "";
                                Book_name = "";
                                Book_id = "";
                                try {
                                    pei.t.stop();
                                    pei.pro.setIndeterminate(false);
                                    pei.pro.setVisible(false);
                                } catch (Exception e) {

                                }
                             
                                int Sel = JOptionPane.showConfirmDialog(null, "No book Issues!!!..\nAre You Issues Books", "Message", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                                if (Sel == 0) {

                                    Login_Signup.bk.removeAll();
                                    Login_Signup.bk.add(new MENU());
                                    Login_Signup.bk.add(new Issues());
                                    Login_Signup.bk.repaint();
                                    Login_Signup.bk.revalidate();
                                }
                                
                            } else {
                                depname.setText(S_name);
                                depbookname.setText(Book_name);
                                depbookid.setText(Book_id);
                                try {
                                    pei.t.stop();
                                    pei.pro.setIndeterminate(false);
                                    pei.pro.setVisible(false);
                                } catch (Exception e) {

                                }
                                conn.close();
                            }

                        } catch (Exception et) {

                        }
                        // _------------------------------------------------------------
                        try {
                            Class.forName("oracle.jdbc.OracleDriver");
                            Connection co = DriverManager.getConnection(url, DBuser, DBpass);

                            final String q = "select EMAIL,STUDENT_NAME from "+ (String) depsem.getSelectedItem() + " where ROLL_NO=" + deproll.getText() + "";
                           
                            Statement statement = co.createStatement();
                            ResultSet resu = statement.executeQuery(q);
                            if (resu.next()) {
                                // check=result.getInt("checked");
                                E_ID = resu.getString("EMAIL");
                                S_nam = resu.getString("STUDENT_NAME");
                            }
                     
                            co.close();
                        } catch (Exception wed) {
                        }
                    }

                }).start();
            }
        } else {

            // try{
            // Class.forName("oracle.jdbc.OracleDriver");
            // Connection con =
            // DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bittu","Bittu");
            // final String QY="select USNAME from ADUSERS where USER_ID='"+DEPONAme+"'";
            // Statement st=con.createStatement();
            // ResultSet r=st.executeQuery(QY);
            // while(r.next()){
            // depoName=r.getString("USNAME");
            // }
            // con.close();
            // // System.out.println(depoName);
            // }catch(Exception ty){}
            if (S_name.equals("") && Book_name.equals("") && Book_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Student Detail Not Found", "Message!..",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                pei = new Progress();
                pei.pro.setBounds(480, 200, 330, 25);
                c.add(pei.pro);
                repaint();
                validate();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            LocalDate str = LocalDate.now();
                            Class.forName("oracle.jdbc.OracleDriver");
                            Connection con = DriverManager.getConnection(url, DBuser, DBpass);
                            final String Query = "update BOOKS set DEPOSITE='" + str + "',DEPOSITE_NAME='"
                                    + Login_Signup.User_name
                                    + "',checked=0 where roll=" + deproll.getText() + " and sem='"
                                    + (String) depsem.getSelectedItem() + "'";
                            // System.out.println(Query);
                            String q1 ="UPDATE Books_Infor set column_name_check=1 WHERE Book_Id="+Book_id;
                                        
                            Statement statemen = con.createStatement();
                            statemen.executeUpdate(q1);
                            statemen.executeUpdate(Query);
                          
                           
                            String message = "DEAR,\n\t" + S_nam
                                    + "\n\t\tBOOK DEPOSITE SUCCESSFULLY.\n\t\t\t BOOK NAME : "
                                    + Book_name + "\n\t\t\t BOOK ID :" + Book_id;
                          
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(null, "Book Deposite Successfully");
                            S_name = "";
                            Book_name = "";
                            Book_id = "";
                            depsem.setSelectedIndex(0);
                            deproll.setText(null);
                            depname.setText(null);
                            depbookname.setText(null);
                            depbookid.setText(null);
                            System.out.println(E_ID);
                            new Thread(new email_id(message, "Books Library : Deposite", E_ID, "bytecoading@gmail.com")).start();
                            frame.setVisible(false);
                            new Menu();
                            con.close();
                        } catch (Exception rtyu) {
                        }
                    }
                }).start();
            }
        }

    }

}
