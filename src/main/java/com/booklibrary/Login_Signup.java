package com.booklibrary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

class Login_Signup extends JFrame implements ActionListener {
    boolean MOBCheck;
    boolean FIRCheck;
    boolean LASCheck;
    boolean FOREmailCheck;
    boolean SGNEmailCheck;
    boolean LOGEmailCheck;
    NameVal NameValid = new NameVal();
    static JPanel heading;
    private JPanel Signup,signup;
    static JPanel jPanel;
    private JPanel Forgot;
    static JLabel bk;
    static String officeotp;
    private JLabel restrict, restrict1, restric, ForgotOTPres, tpassrestrict, signOTp, signName, sigName, signMob,offi,off,
            signOTP;
    private JTextField user, ForText, trepass, ForOtp, TextFirst, TextLast, SinEmail, TextOtp, SinText,ofTextOtp;
    private JPasswordField pass, tpass;
    private JButton sumit, forgen, singup, show, show1, SignOtp, SignUp, log, SendOtp, ForComf;
    Font Font1;
    private String OTP, OTPSTR;
    ImageIcon image2;
    static String User_name;
    BufferedImage bufImg = null;
    InputStream dif;
    static ImageIcon set;
    public Object progressBar;
    private JCheckBox checkBox1,checkBox2,checkBox3;
    final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
    final String DBuser = "usxqljshik8op7dd";
    final String DBpass = "ViDjCizs55WoYAMXOmeW";
    Progress pei;
     EmailValid em = new EmailValid();

    Login_Signup() {
        // EmailValid Emvalid = new EmailValid();
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) - 70;
        int x = Toolkit.getDefaultToolkit().getScreenSize().width - getWidth();
        setTitle("Book library");
        ImageIcon imageIcon = new ImageIcon("img/book_lib.png");
        setIconImage(imageIcon.getImage());
        // Header---------
        ImageIcon op = new ImageIcon("img/opt.png");
        Image im1 = op.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        image2 = new ImageIcon(im1);
        setResizable(true);
        Font1 = new Font("Georgia", Font.ROMAN_BASELINE, 20); // label id

        // Frame ----
        setExtendedState(JFrame.NORMAL);
        setSize(x, y);
        setResizable(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon backgeo = new ImageIcon("img/bk.jpg");
        Image Ima_back = backgeo.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(Ima_back);
        bk = new JLabel("", background, JLabel.CENTER);
        bk.setBounds(0, 0, 1920, 1080);
        add(bk);

        heading = new JPanel();
        heading.setBackground(Color.decode("#A7ECFF"));
        heading.setBounds(350, 160, 650, 700);
        heading.setLayout(null);

        jPanel = new JPanel();
        jPanel.setBounds(1000, 160, 550, 700);

        bk.add(jPanel);
        bk.add(heading);
        // -----------------------------------------------------
        jPanel.setLayout(null);
        JLabel wel = new JLabel("Welcome,");
        wel.setBounds(50, 130, 200, 100);
        heading.add(wel);
        wel.setForeground(Color.decode("#851947"));
        wel.setFont(new Font("Algerian", Font.PLAIN, 40));

        JLabel lib = new JLabel("To Libaray!");
        lib.setBounds(150, 190, 200, 100);
        heading.add(lib);
        lib.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        lib.setForeground(Color.decode("#579145"));

        JTextArea tArea = new JTextArea();
        tArea.setBounds(80, 290, 500, 200);
        tArea.setText("How can I assist you today?" +
                " Whether you're looking for a book recommendation, need help " +
                " with research, or have any other questions,"
                + "feel free to ask. Enjoy your time in the library!");
        tArea.setFont(new Font("arial", Font.BOLD, 20));
        tArea.setLineWrap(true);
        tArea.setEditable(false);
        tArea.setWrapStyleWord(true);
        heading.add(tArea);
        tArea.setBackground(Color.decode("#A7ECFF"));

        // ---------------login page-------------

        JLabel labellog = new JLabel("Login");
        labellog.setBounds(230, 100, 350, 50);
        labellog.setFont(Font1);
        jPanel.add(labellog);
        labellog.setFont(new Font("Book Antiqua", Font.BOLD, 40));

        JLabel uer = new JLabel("Email_Id");
        uer.setBounds(100, 210, 350, 50);
        uer.setFont(Font1);
        jPanel.add(uer);
        user = new JTextField(50);
        user.setBounds(100, 250, 350, 50);
        jPanel.add(user);
        user.setFont(new Font("arial", Font.ITALIC, 20));
        JLabel pas = new JLabel("Password ");
        pas.setBounds(100, 330, 350, 50);
        pas.setFont(Font1);
        jPanel.add(pas);
        pass = new JPasswordField(50);
        pass.setBounds(100, 370, 350, 50);
        pass.setFont(new Font("arial", Font.ITALIC, 20));
        jPanel.add(pass);

        forgen = new JButton("Forgot password?");
        forgen.setBounds(300, 425, 140, 25);
        forgen.setFont(new Font("arial", Font.BOLD, 12));
        jPanel.add(forgen);

        sumit = new JButton("Login");
        sumit.setBounds(200, 500, 150, 50);
        jPanel.add(sumit);
        sumit.setFont(new Font("arial", Font.BOLD, 25));

        JLabel lsig = new JLabel("Need An Account");
        lsig.setBounds(150, 600, 180, 25);
        jPanel.add(lsig);
        lsig.setFont(new Font("Book Antiqua", Font.BOLD, 20));

        singup = new JButton("Signup");
        singup.setBounds(315, 600, 80, 25);
        singup.setFont(new Font("arial", Font.BOLD, 13));
        jPanel.add(singup);
        // ----------------------------------------------
        ImageIcon unsho = new ImageIcon("img/unshow.jpg");
        ImageIcon sho = new ImageIcon("img/show.png");
        Image unsiz = unsho.getImage().getScaledInstance(30, 25, Image.SCALE_AREA_AVERAGING);
        Image siz = sho.getImage().getScaledInstance(30, 20, Image.SCALE_AREA_AVERAGING);
        ImageIcon unIm = new ImageIcon(unsiz);
        ImageIcon Im = new ImageIcon(siz);

        show = new JButton(Im);
        show.setBounds(455, 380, 30, 25);
        show.setFont(new Font("arial", Font.BOLD, 13));
        jPanel.add(show);

        show1 = new JButton(unIm);
        show1.setBounds(455, 380, 30, 25);
        show1.setFont(new Font("arial", Font.BOLD, 13));
        jPanel.add(show1);

        show.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        show1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        singup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sumit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        show.addActionListener(this);
        show1.addActionListener(this);
        singup.addActionListener(this);
        forgen.addActionListener(this);
        sumit.addActionListener(this);

        user.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

                restrict.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(user.getText().equals("")){

                }else{
            if(em.isValid(user.getText())==true){
                LOGEmailCheck=true;
            }else{
                restrict.setText("Invalid Email Id");
                restrict.setBounds(110, 300, 300, 20);
                restrict.setVisible(true);
                LOGEmailCheck=false;

            }

            }
                 }
        });
        pass.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                restrict1.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                    
            }

        });

        restrict = new JLabel("");
        restrict.setFont(new Font("arial", Font.BOLD, 15));
        restrict.setForeground(Color.RED);
        jPanel.add(restrict);
        restrict.setVisible(false);

        restrict1 = new JLabel("");
        restrict1.setFont(new Font("arial", Font.BOLD, 15));
        restrict1.setForeground(Color.RED);
        jPanel.add(restrict1);
        restrict1.setVisible(false);
        // user.setText("bittu50520@gmail.com");
        // pass.setText("Bittu000kr");

        // setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (sumit.equals(e.getSource())) {
            if (user.getText().equals("") && pass.getText().toString().equals("")) {
                restrict.setText("Email_Id Required*");
                restrict.setBounds(110, 300, 300, 20);
                restrict.setVisible(true);
                restrict1.setText("Password Required*");
                restrict1.setBounds(110, 420, 300, 20);
                restrict1.setVisible(true);

            } else if (user.getText().equals("")) {
                restrict.setText("Email_Id Required*");
                restrict.setBounds(110, 300, 300, 20);
                restrict.setVisible(true);
            } else {
                if (pass.getText().toString().equals("")) {
                    restrict1.setText("Password Required*");
                    restrict1.setBounds(110, 420, 300, 20);
                    restrict1.setVisible(true);
                }else if(LOGEmailCheck==false){
                }
                
                else {
                    user.setEditable(false);
                    pass.setEditable(false);
                    pei = new Progress();
                    pei.pro.setBounds(120, 460, 330, 25);
                    jPanel.add(pei.pro);
                    jPanel.repaint();
                    jPanel.validate();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                // Class.forName("oracle.jdbc.OracleDriver");
                                final Connection connection = DriverManager.getConnection(url, DBuser, DBpass);
                                Statement statement = connection.createStatement();
                                String Qury = "select * from USER_S  WHERE email_id='" + user.getText() + "'";
                                // String Qury="select * from user_s WHERE email_id='bittubabu2993@gmail.com'";
                                ResultSet rs = statement.executeQuery(Qury);
                                if (rs.next()) {
                                    User_name = rs.getString("Full_NAME");
                                    try {
                                        // Class.forName("oracle.jdbc.OracleDriver");
                                        final Connection connectio = DriverManager
                                                .getConnection(url, DBuser, DBpass);
                                        Statement statemen = connectio.createStatement();
                                        String Qur = "select * from USER_S  WHERE PASS_WORD= '"
                                                + pass.getText().toString()
                                                + "'";
                                        // String Qur="select * from user_s WHERE PASS_WORD='byte'";
                                        ResultSet rs1 = statemen.executeQuery(Qur);
                                        if (rs1.next()) {
                                            // Class.forName("oracle.jdbc.OracleDriver");
                                            final Connection connecti = DriverManager
                                                    .getConnection(url, DBuser, DBpass);
                                            Statement stateme = connecti.createStatement();
                                            String Qu = "select * from USER_S  WHERE email_id='" + user.getText()
                                                    + "' and "
                                                    + " PASS_WORD= '" + pass.getText().toString() + "'";
                                            ResultSet s1 = stateme.executeQuery(Qu);
                                            if (s1.next()) {
                                                connecti.close();

                                                try {
                                                    Class.forName("oracle.jdbc.OracleDriver");
                                                    Connection cone = DriverManager.getConnection(url, DBuser, DBpass);
                                                    final String Query = "Select Images from USER_S where full_name='"
                                                            + User_name + "'";
                                                    PreparedStatement preparedStatement = cone.prepareStatement(Query);
                                                    // preparedStatement.setString(1, currentuser.getText().trim());
                                                    ResultSet rs2 = preparedStatement.executeQuery();
                                                    while (rs2.next()) {
                                                        InputStream in = rs.getBinaryStream("IMAGES");
                                                        bufImg = ImageIO.read(in);
                                                    }
                                                    cone.close();
                                                } catch (Exception js) {
                                                    js.getStackTrace();
                                                }

                                                bk.removeAll();
                                                bk.add(new MENU());
                                                bk.add(new Dash());
                                                MENU.home.setEnabled(false);
                                                bk.repaint();
                                                bk.revalidate();
                                                try {
                                                    ImageIcon inin = new ImageIcon(bufImg);
                                                    Image innk = inin.getImage().getScaledInstance(100, 100,
                                                            Image.SCALE_SMOOTH);
                                                       set = new ImageIcon(innk);
                                                    Dash.pro.setIcon(set);
                                                } catch (Exception e) {
                                                    // TODO: handle exception
                                                }
                                                try {
                                                    pei.t.stop();
                                                    pei.pro.setIndeterminate(false);
                                                    pei.pro.setVisible(false);
                                                } catch (Exception e) {

                                                }
                                            } else {
                                                pass.setEditable(true);
                                                restrict1.setText("Password Wrong");
                                                restrict1.setBounds(110, 420, 300, 20);
                                                restrict1.setVisible(true);
                                                try {
                                                    pei.t.stop();
                                                    pei.pro.setIndeterminate(false);
                                                    pei.pro.setVisible(false);
                                                } catch (Exception e) {

                                                }
                                            }

                                        } else {
                                            pass.setEditable(true);
                                            restrict1.setText("Wrong Password");
                                            restrict1.setBounds(110, 420, 300, 20);
                                            restrict1.setVisible(true);

                                            try {
                                                pei.t.stop();
                                                pei.pro.setIndeterminate(false);
                                                pei.pro.setVisible(false);
                                            } catch (Exception e) {

                                            }
                                        }
                                        // connection.close();
                                    } catch (Exception Sql) {
                                    }
                                } else {
                                    // pro.frame.dispose();
                                    user.setEditable(true);
                                    pass.setEditable(true);
                                    restrict.setText("Not Registered");
                                    restrict.setBounds(110, 300, 300, 20);
                                    restrict.setVisible(true);

                                    try {
                                        pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }

                                }
                                connection.close();
                            } catch (Exception Sql) {

                            }

                        }

                    }).start();

                }
            }
        } else if (show.equals(e.getSource())) {
            pass.setEchoChar((char) 0);
            show.setVisible(false);
            show1.setVisible(true);

        } else if (show1.equals(e.getSource())) {
            pass.setEchoChar('*');
            show1.setVisible(false);
            show.setVisible(true);

        }

        else if (forgen.equals(e.getSource())) {
            // ----------------------------------------------------------------------------------------------
            bk.removeAll();
            Forgot = new JPanel();
            Forgot.setBounds(1000, 160, 550, 700);
            bk.add(Forgot);
            bk.add(heading);
            Forgot.setLayout(null);
            JLabel SigLo = new JLabel("Remember password");
            SigLo.setBounds(130, 630, 200, 25);
            Forgot.add(SigLo);
            SigLo.setFont(new Font("Book Antiqua", Font.BOLD, 18));

            log = new JButton("Log In");
            log.setBounds(320, 630, 80, 25);
            log.setFont(new Font("arial", Font.BOLD, 13));
            Forgot.add(log);
            log.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JLabel labelfor = new JLabel("Forgot Password");
            labelfor.setBounds(140, 70, 350, 50);
            labelfor.setFont(Font1);
            Forgot.add(labelfor);
            labelfor.setFont(new Font("Book Antiqua", Font.BOLD, 40));

            JLabel ForEmail = new JLabel("Email_Id");
            ForEmail.setBounds(100, 160, 350, 50);
            ForEmail.setFont(Font1);
            Forgot.add(ForEmail);
            ForText = new JTextField(50);
            ForText.setBounds(100, 200, 350, 50);
            Forgot.add(ForText);
            ForText.setFont(new Font("arial", Font.ITALIC, 20));

            JLabel SinOt = new JLabel(image2);
            SinOt.setBounds(120, 280, 40, 40);
            Forgot.add(SinOt);

            ForOtp = new JTextField();
            ForOtp.setBounds(170, 280, 140, 50);
            ForOtp.setFont(new Font("arial", Font.ITALIC, 20));
            Forgot.add(ForOtp);
            ForOtp.setEditable(false);

            SendOtp = new JButton("Send Otp");
            SendOtp.setBounds(330, 295, 130, 25);
            SendOtp.setFont(new Font("arial", Font.BOLD, 13));
            Forgot.add(SendOtp);

            JLabel lPass = new JLabel("Enter Password");
            lPass.setBounds(100, 340, 350, 50);
            lPass.setFont(Font1);
            Forgot.add(lPass);

            tpass = new JPasswordField(50);
            tpass.setBounds(100, 380, 350, 50);
            Forgot.add(tpass);
            tpass.setFont(new Font("arial", Font.ITALIC, 20));

            JLabel Lrepass = new JLabel("Renter Password");
            Lrepass.setBounds(100, 420, 350, 50);
            Lrepass.setFont(Font1);
            Forgot.add(Lrepass);

            trepass = new JTextField(50);
            trepass.setBounds(100, 460, 350, 50);
            Forgot.add(trepass);
            trepass.setFont(new Font("arial", Font.ITALIC, 20));

            ForComf = new JButton("Submit");
            ForComf.setBounds(200, 540, 150, 50);
            Forgot.add(ForComf);
            ForComf.setFont(new Font("arial", Font.BOLD, 25));

            restric = new JLabel();
            restric.setFont(new Font("arial", Font.BOLD, 15));
            restric.setForeground(Color.RED);
            Forgot.add(restric);

            ForgotOTPres = new JLabel();
            ForgotOTPres.setFont(new Font("arial", Font.BOLD, 15));
            ForgotOTPres.setForeground(Color.RED);
            Forgot.add(ForgotOTPres);

            tpassrestrict = new JLabel();
            tpassrestrict.setFont(new Font("arial", Font.BOLD, 15));
            tpassrestrict.setForeground(Color.RED);
            Forgot.add(tpassrestrict);

            restric.setVisible(false);

            log.addActionListener(this);
            Cursor.getDefaultCursor();
            SendOtp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            ForComf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Forgot.repaint();
            Forgot.revalidate();

            ForText.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    // SendOtp.setText("send OTP");
                    // OTP = "";
                    // ForText.setText(null);
                    restric.setVisible(false);
                    // ForOtp.setEditable(false);
                    // ForOtp.setText(null);
                }

                @Override
                public void focusLost(FocusEvent e) {
                        if(em.isValid(ForText.getText())){
                            // System.out.println("Valoid");
                            FOREmailCheck=true;
                        }else{
                            restric.setText("Invalid Email Id");
                            restric.setBounds(110, 250, 300, 20);
                            restric.setVisible(true);
                            FOREmailCheck=false;
                        }
                        System.out.println(FOREmailCheck);
                }

            });

            ForOtp.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    ForgotOTPres.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {

                }

            });

            tpass.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    tpassrestrict.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                }

            });
            trepass.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    tpassrestrict.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                }

            });

            log.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    bk.removeAll();
                    bk.add(jPanel);
                    bk.add(heading);
                    jPanel.repaint();
                    jPanel.revalidate();
                }

            });

            SendOtp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (ForText.getText().equals("")) {
                        restric.setText("Email_Id Required*");
                        restric.setBounds(110, 250, 300, 20);
                        restric.setVisible(true);
                    }else if(false==FOREmailCheck){

                    }
                    else {


                        
                        ForText.setEditable(false);
                        pei = new Progress();
                        pei.pro.setBounds(120, 130, 330, 25);
                        Forgot.add(pei.pro);
                        repaint();
                        validate();
                        new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    // ALTER USER BITTU IDENTIFIED BY Bittu ACCOUNT UNLOCK;
                                    // Class.forName("oracle.jdbc.OracleDriver");
                                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                                    Statement statement = conn.createStatement();
                                    String Qury = "select * from USER_S WHERE EMAIL_ID='" + ForText.getText() + "'";
                                    ResultSet rs = statement.executeQuery(Qury);
                                    if (rs.next()) {
                                        OTP = GEN_OTP.generateOTP();
                                        String message = "Dear, \n \t user \n \t\t Your Otp Is : " + OTP;
                                        String subject = "Books Library : Password Forgot";
                                        String from = "bytecoading@gmail.com";
                                        new Thread(new email_id(message, subject, ForText.getText(), from)).start();
                                        try {
                                            pei.t.stop();
                                            pei.pro.setIndeterminate(false);
                                            pei.pro.setVisible(false);
                                        } catch (Exception e) {

                                        }
                                        ForOtp.setEditable(true);
                                        // JOptionPane.showMessageDialog(null,
                                        //         "Otp Sended :" + ForText.getText());
                                        restric.setText("OTP Sended");
                                        restric.setForeground(Color.GREEN);
                                        restric.setBounds(350, 250, 300, 20);
                                        restric.setVisible(true);
                                        SendOtp.setText("Resend OTP");
                                        // ForText.setEditable(true);

                                    } else {
                                        try {
                                            pei.t.stop();
                                            pei.pro.setIndeterminate(false);
                                            pei.pro.setVisible(false);
                                        } catch (Exception e) {

                                        }
                                        restric.setText("Not Registered");
                                        restric.setBounds(110, 250, 300, 20);
                                        restric.setVisible(true);
                                        ForText.setEditable(true);

                                    }
                                    conn.close();
                                } catch (Exception e1) {
                                    JOptionPane.showMessageDialog(null, e1);
                                }
                            }

                        }).start();

                    }
                }

            });

            ForComf.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (ForText.getText().equals("") && ForOtp.getText().equals("")
                            && tpass.getText().toString().equals("") && trepass.getText().equals("")) {
                        restric.setText("Email_Id Required*");
                        restric.setBounds(110, 250, 300, 20);
                        restric.setVisible(true);
                        ForgotOTPres.setText("OTP Required*");
                        ForgotOTPres.setBounds(180, 330, 300, 20);
                        ForgotOTPres.setVisible(true);

                        tpassrestrict.setText("Password Required*");
                        tpassrestrict.setBounds(110, 510, 300, 20);
                        tpassrestrict.setVisible(true);
                    } else if (ForOtp.getText().equals("") && tpass.getText().toString().equals("")
                            && trepass.getText().equals("")) {
                        ForgotOTPres.setText("OTP Required*");
                        ForgotOTPres.setBounds(180, 330, 300, 20);
                        ForgotOTPres.setVisible(true);

                        tpassrestrict.setText("Password Required*");
                        tpassrestrict.setBounds(110, 510, 300, 20);
                        tpassrestrict.setVisible(true);

                    } else if (tpass.getText().toString().equals("") && trepass.getText().equals("")) {
                        tpassrestrict.setText("Password Required*");
                        tpassrestrict.setBounds(110, 510, 300, 20);
                        tpassrestrict.setVisible(true);

                    } else if (ForText.getText().equals("") && ForOtp.getText().equals("")) {
                        restric.setText("Email_Id Required*");
                        restric.setBounds(110, 250, 300, 20);
                        restric.setVisible(true);
                        ForgotOTPres.setText("OTP Required*");
                        ForgotOTPres.setBounds(180, 330, 300, 20);
                        ForgotOTPres.setVisible(true);
                    } else if (ForText.getText().equals("")) {
                        restric.setText("Email_Id Required*");
                        restric.setBounds(110, 250, 300, 20);
                        restric.setVisible(true);
                    } else if (ForOtp.getText().equals("")) {
                        ForgotOTPres.setText("OTP Required*");
                        ForgotOTPres.setBounds(180, 330, 300, 20);
                        ForgotOTPres.setVisible(true);
                    } else if (tpass.getText().toString().equals("")) {
                        tpassrestrict.setText("Password Required*");
                        tpassrestrict.setBounds(110, 510, 300, 20);
                        tpassrestrict.setVisible(true);
                    } else if (trepass.getText().equals("")) {
                        tpassrestrict.setText("Reenter Password Required*");
                        tpassrestrict.setBounds(110, 510, 300, 20);
                        tpassrestrict.setVisible(true);
                    } else if (OTP.equals(ForOtp.getText())) {
                        if (tpass.getText().toString().length() >= 8) {

                            if (tpass.getText().toString().equals(trepass.getText())) {
                                pei = new Progress();
                                pei.pro.setBounds(120, 130, 330, 25);
                                Forgot.add(pei.pro);
                                repaint();
                                validate();

                                ForOtp.setEditable(false);
                                tpass.setEditable(false);
                                trepass.setEditable(false);

                                new Thread(new Runnable() {

                                    @Override
                                    public void run() {

                                        try {
                                            // Class.forName("oracle.jdbc.OracleDriver");

                                            Connection con = DriverManager.getConnection(url, DBuser, DBpass);
                                            final String Queru = "UPDATE USER_S SET pass_word='"
                                                    + trepass.getText().toString()
                                                    + "' WHERE Email_ID='" + ForText.getText() + "'";
                                            Statement sy = con.createStatement();
                                            sy.executeUpdate(Queru);
                                            try {
                                                pei.t.stop();
                                                pei.pro.setIndeterminate(false);
                                                pei.pro.setVisible(false);
                                            } catch (Exception e) {

                                            }
                                            JOptionPane.showMessageDialog(null, "Password Changed Sucessfully");
                                            String message = "Dear, \n \t user \n \t\t Your Password is Changed Succesfull";
                                            String subject = "Books Library : Password ";
                                            String from = "bytecoading@gmail.com";
                                            bk.removeAll();
                                            bk.add(jPanel);
                                            bk.add(heading);
                                            jPanel.repaint();
                                            jPanel.revalidate();
                                            new Thread(new email_id(message, subject, ForText.getText(), from)).start();
                                            con.close();
                                        } catch (Exception rtr) {
                                            try {
                                                pei.t.stop();
                                                pei.pro.setIndeterminate(false);
                                                pei.pro.setVisible(false);
                                            } catch (Exception e) {

                                            }
                                            System.out.println(rtr);
                                        }
                                    }

                                }).start();

                            } else {
                                try {
                                    pei.t.stop();
                                    pei.pro.setIndeterminate(false);
                                    pei.pro.setVisible(false);
                                } catch (Exception ew) {

                                }
                                tpassrestrict.setText("Password Not Match");
                                tpassrestrict.setBounds(110, 510, 300, 20);
                                tpassrestrict.setVisible(true);
                            }
                        } else {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception ew) {

                            }
                            tpassrestrict.setText("Must Be Password 8 Digit");
                            tpassrestrict.setBounds(110, 510, 300, 20);
                            tpassrestrict.setVisible(true);
                        }
                    } else {
                        try {
                            pei.t.stop();
                            pei.pro.setIndeterminate(false);
                            pei.pro.setVisible(false);
                        } catch (Exception eww) {

                        }
                        ForgotOTPres.setText("OTP Invalid");
                        ForgotOTPres.setBounds(180, 330, 300, 20);
                        ForgotOTPres.setVisible(true);
                    }

                }

            });

        }

        else if (singup.equals(e.getSource())) {

            bk.removeAll();
            Signup = new JPanel();
            Signup.setBounds(1000, 160, 550, 700);
            bk.add(Signup);
            Signup.setLayout(null);

            JLabel labellog = new JLabel("Sign Up");
            labellog.setBounds(200, 40, 350, 50);
            labellog.setFont(Font1);
            Signup.add(labellog);
            labellog.setFont(new Font("Book Antiqua", Font.BOLD, 40));

            JLabel SigFirst = new JLabel("First Name");
            SigFirst.setBounds(80, 110, 350, 50);
            SigFirst.setFont(Font1);
            Signup.add(SigFirst);

            TextFirst = new JTextField();
            TextFirst.setBounds(75, 150, 200, 50);
            TextFirst.setFont(new Font("arial", Font.ITALIC, 20));
            Signup.add(TextFirst);

            JLabel SigLast = new JLabel("Last Name");
            SigLast.setBounds(300, 110, 350, 50);
            SigLast.setFont(Font1);
            Signup.add(SigLast);

            TextLast = new JTextField();
            TextLast.setBounds(295, 150, 200, 50);
            TextLast.setFont(new Font("arial", Font.ITALIC, 20));
            Signup.add(TextLast);

            JLabel SigEmail = new JLabel("Email_Id");
            SigEmail.setBounds(80, 210, 350, 50);
            SigEmail.setFont(Font1);
            Signup.add(SigEmail);

            SinEmail = new JTextField();
            SinEmail.setBounds(75, 250, 420, 50);
            SinEmail.setFont(new Font("arial", Font.ITALIC, 20));
            Signup.add(SinEmail);

            JLabel SigMob = new JLabel("Mobile No");
            SigMob.setBounds(80, 310, 350, 50);
            SigMob.setFont(Font1);
            Signup.add(SigMob);

            JTextField Sin91 = new JTextField(" +91");
            Sin91.setEditable(false);
            Sin91.setBounds(75, 350, 50, 50);
            Sin91.setFont(new Font("arial", Font.ITALIC, 20));
            Signup.add(Sin91);

            SinText = new JTextField();
            SinText.setBounds(125, 350, 370, 50);
            SinText.setFont(new Font("arial", Font.ITALIC, 20));
            Signup.add(SinText);

            JLabel SinOtp = new JLabel(image2);
            SinOtp.setBounds(120, 450, 40, 40);
            Signup.add(SinOtp);

            TextOtp = new JTextField();
            TextOtp.setBounds(170, 450, 140, 50);
            TextOtp.setFont(new Font("arial", Font.ITALIC, 20));
            Signup.add(TextOtp);
            TextOtp.setEditable(false);

            SignOtp = new JButton("Send OTP");
            SignOtp.setBounds(330, 465, 130, 25);
            SignOtp.setFont(new Font("arial", Font.BOLD, 13));
            Signup.add(SignOtp);

            SignUp = new JButton("SignUp");
            SignUp.setBounds(200, 540, 150, 50);
            Signup.add(SignUp);
            SignUp.setFont(new Font("arial", Font.BOLD, 25));

            JLabel SigLog = new JLabel("Already An Account");
            SigLog.setBounds(130, 630, 200, 25);
            Signup.add(SigLog);
            SigLog.setFont(new Font("Book Antiqua", Font.BOLD, 20));

            log = new JButton("Log In");
            log.setBounds(320, 630, 80, 25);
            log.setFont(new Font("arial", Font.BOLD, 13));
            Signup.add(log);

            bk.add(Signup);
            bk.add(heading);
            Signup.repaint();
            Signup.revalidate();

            signOTp = new JLabel("");
            signOTp.setFont(new Font("arial", Font.BOLD, 15));
            signOTp.setForeground(Color.RED);
            Signup.add(signOTp);
            signOTp.setVisible(false);

            signName = new JLabel("");
            signName.setFont(new Font("arial", Font.BOLD, 15));
            signName.setForeground(Color.RED);
            Signup.add(signName);
            signName.setVisible(false);

            sigName = new JLabel("");
            sigName.setFont(new Font("arial", Font.BOLD, 15));
            sigName.setForeground(Color.RED);
            Signup.add(sigName);
            sigName.setVisible(false);

            signMob = new JLabel("");
            signMob.setFont(new Font("arial", Font.BOLD, 15));
            signMob.setForeground(Color.RED);
            Signup.add(signMob);
            signMob.setVisible(false);

            signOTP = new JLabel("");
            signOTP.setFont(new Font("arial", Font.BOLD, 15));
            signOTP.setForeground(Color.RED);
            Signup.add(signOTP);
            signOTP.setVisible(false);

            log.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            SignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            SinOtp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            TextFirst.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    signName.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    TextFirst.setText(TextFirst.getText().toUpperCase());
                    if (TextFirst.getText().length() < 3) {
                        signName.setText("Invalid First Name");
                        signName.setBounds(80, 190, 300, 40);
                        signName.setVisible(true);
                    } else {
                        String stryg = TextFirst.getText();
                        FIRCheck = NameValid.FirsNAME(stryg);
                        if (FIRCheck == false) {
                            signName.setText("Invalid First Name");
                            signName.setBounds(80, 190, 300, 40);
                            signName.setVisible(true);
                        }
                    }

                }

            });
            TextLast.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {

                }

                @Override
                public void focusLost(FocusEvent e) {
                    TextLast.setText(TextLast.getText().toUpperCase());
                    if (TextLast.getText().length() < 3) {
                        sigName.setText("Invalid last Name");
                        sigName.setBounds(300, 190, 300, 40);
                        sigName.setVisible(true);
                    } else {
                        String lastSTR = TextLast.getText();
                        LASCheck = NameValid.LastNAME(lastSTR);
                        if (LASCheck == false) {
                            sigName.setText("Invalid last Name");
                            sigName.setBounds(300, 190, 300, 40);
                            sigName.setVisible(true);
                        }

                    }

                }

            });
            SinEmail.addFocusListener(new FocusListener() {

              @Override
                public void focusGained(FocusEvent e) {
                    signOTp.setVisible(false);
                    // signOTP.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if(SinEmail.getText().equals("")){

                    }else{
                    if(em.isValid(SinEmail.getText())){
                        SGNEmailCheck=true;

                    }else{
                        SGNEmailCheck=false;
                        signOTp.setText("invalid Email Id");
                                        signOTp.setBounds(80, 290, 300, 40);
                                        signOTp.setVisible(true);
                                        SinEmail.setEditable(true);
                    }
                }}


            });

            
            SinText.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    signMob.setVisible(false);
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
                        signMob.setBounds(80, 390, 300, 40);
                        signMob.setVisible(true);
                    } else if (mb1 == 9 || mb1 == 8 || mb1 == 7 || mb1 == 6) {
                        MOBCheck = true;
                        if(SinText.getText().length()==10){
                            signMob.setVisible(false);
                        }
                    } else {
                        MOBCheck = false;
                        signMob.setText("Invalid Mobile Number");
                        signMob.setBounds(80, 390, 300, 40);
                        signMob.setVisible(true);
                    }
                }
                         }
            });
            TextOtp.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    signOTP.setText(null);
                }

                @Override
                public void focusLost(FocusEvent e) {

                }

            });

            TextFirst.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                        TextFirst.setEditable(true);
                        sigName.setVisible(false);
                    } else {
                        ke.consume();
                        sigName.setText("Input Chracter (ABC..)");
                        sigName.setBounds(80, 190, 300, 40);
                        sigName.setVisible(true);
                        TextFirst.setEditable(false);

                    }

                }
            });

            TextLast.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {

                    char c = ke.getKeyChar();
                    if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                        TextLast.setEditable(true);
                        sigName.setVisible(false);
                    } else {
                        ke.consume();
                        sigName.setText("Input Chracter (ABC..)");
                        sigName.setBounds(300, 190, 300, 40);
                        sigName.setVisible(true);
                        TextLast.setEditable(false);

                    }

                }
            });

            SinText.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {

                    char c = ke.getKeyChar();
                    if (!Character.isDigit(c)) {
                        ke.consume();
                        SinText.setEditable(true);
                        signMob.setVisible(false);
                        if (Character.isLetter(c)) {
                            SinText.setEditable(true);
                            signMob.setText("Input only Numeric (0-9)");
                            signMob.setBounds(80, 390, 300, 40);
                            signMob.setVisible(true);
                        }
                    } else {
                        signMob.setVisible(false);
                        if (SinText.getText().length() == 10) {
                            SinText.setEditable(false);
                            signMob.setText("Input Only 10 digit number");
                            signMob.setBounds(80, 390, 300, 40);
                            signMob.setVisible(true);
                        }
                    }
                }
            });

            SignOtp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (SinEmail.getText().equals("")) {
                        signOTp.setText("Email Required*");
                        signOTp.setBounds(80, 290, 300, 40);
                        signOTp.setVisible(true);
                    } else {
                        if (SGNEmailCheck == true) {

                            SignOtp.setEnabled(false);

                            pei = new Progress();
                            pei.pro.setBounds(120, 95, 330, 25);
                            Signup.add(pei.pro);
                            repaint();
                            validate();
                            SinEmail.setEditable(false);
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    OTPSTR = GEN_OTP.generateOTP();
                                    String message = "Dear, \n \t Your Otp Is: " + OTPSTR;
                                    String subject = "Books Library : OTP ";
                                    String from = "bytecoading@gmail.com";
                                    new Thread(new email_id(message, subject, SinEmail.getText(), from)).start();
                                    try {
                                        pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }
                                    if (true == email_id.EamiMess) {
                                        TextOtp.setEditable(true);
                                        signOTp.setText("OTP Sended");
                                        signOTp.setForeground(Color.GREEN);
                                        signOTp.setBounds(400, 290, 300, 40);
                                        signOTp.setVisible(true);
                                        SinEmail.setEditable(false);
                                        SignOtp.setEnabled(true);
                                        SignOtp.setText("Resend OTP");
                                        ;

                                    } else {
                                        signOTp.setText("Invalid Email Id");
                                        signOTp.setBounds(80, 290, 300, 40);
                                        signOTp.setVisible(true);
                                        SinEmail.setEditable(true);
                                        // SignOtp.setEnabled(true);

                                    }
                                }

                            }).start();

                        } 
                    }
                }
            });
            SignUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                                







                    if (TextFirst.getText().equals("") && SinEmail.getText().equals("")
                            && SinText.getText().equals("")) {
                        signName.setText("Name Required*");
                        signName.setBounds(80, 190, 300, 40);
                        signName.setVisible(true);

                        signOTp.setText("Email_Id Required*");
                        signOTp.setBounds(80, 290, 300, 40);
                        signOTp.setVisible(true);

                        signMob.setText("Mobile Required*");
                        signMob.setBounds(80, 390, 300, 40);
                        signMob.setVisible(true);
                    } else if (SinEmail.getText().equals("") && SinText.getText().equals("")) {
                        signOTp.setText("Email_Id Required*");
                        signOTp.setBounds(80, 290, 300, 40);
                        signOTp.setVisible(true);

                        signMob.setText("Mobile Required*");
                        signMob.setBounds(80, 390, 300, 40);
                        signMob.setVisible(true);
                    } else if (TextFirst.getText().equals("")) {
                        signName.setText("Name Required*");
                        signName.setBounds(80, 190, 300, 40);
                        signName.setVisible(true);
                    } else if (SinText.getText().equals("")) {
                        signMob.setText("Mobile Required*");
                        signMob.setBounds(80, 390, 300, 40);
                        signMob.setVisible(true);
                    } else if (SinEmail.getText().equals("")) {
                        signOTp.setText("Email_Id Required*");
                        signOTp.setBounds(80, 290, 300, 40);
                        signOTp.setVisible(true);
                    } else if (MOBCheck == false) {

                    } else if (FIRCheck == false) {

                    } else if (LASCheck == false) {

                    } else if (TextOtp.getText().equals("")) {
                        signOTP.setText("OTP Required*");
                        signOTP.setBounds(180, 490, 300, 40);
                        signOTP.setVisible(true);
                        signMob.setVisible(false);
                    }

                    else if (OTPSTR.equals(TextOtp.getText())) {
                        TextFirst.setEditable(false);
                        TextLast.setEditable(false);
                        SinText.setEditable(false);
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                            
                                officeotp = GEN_OTP.generateOTP();
                                String message = "Dear sir, \n \t New Candidate register in BCA Library \n \t\t Name:-"+TextFirst.getText() + " " + TextLast.getText()+"   \n\t\t Mob:-"+SinText.getText()+" \n\t\t Email:-"+SinEmail.getText()+"\n\n\n\t\t\t OTP is: " + officeotp;
                                String subject = "Books Library : OTP ";
                                String from = "bytecoading@gmail.com";
                                new Thread(new email_id(message, subject,"bittubabu2993@gmail.com", from)).start();
                            }
                            
                        }).start();
                                bk.removeAll();
           signup = new JPanel();
            signup.setBounds(1000, 160, 550, 700);
            bk.add(signup);
            bk.add(heading);
            signup.setLayout(null);
            bk.repaint();
            bk.revalidate();

            off = new JLabel("OTP Sended");
            off.setFont(new Font("arial", Font.BOLD, 15));
            off.setForeground(Color.GREEN);
            signup.add(off);
            off.setBounds(360, 260, 300, 40);
                        
            JLabel label1=new JLabel("BCA Office Authentiction OTP");
            label1.setBounds(120, 130, 500, 100);
            label1.setForeground(Color.black);
            label1.setFont( new Font("Georgia", Font.ROMAN_BASELINE, 25));
            signup.add(label1);

            ofTextOtp = new JTextField();
            ofTextOtp.setBounds(170, 225, 140, 50);
            ofTextOtp.setFont(new Font("arial", Font.ITALIC, 20));
            signup.add(ofTextOtp);
            
            JButton ofSignOtp = new JButton("Resend Otp");
            ofSignOtp.setBounds(330, 240, 130, 25);
            ofSignOtp.setFont(new Font("arial", Font.BOLD, 13));
            signup.add(ofSignOtp);
            
          JButton  ofSignUp = new JButton("SignUp");
          ofSignUp.setBounds(200,570, 150, 50);
          ofSignUp.setFont(new Font("arial", Font.BOLD, 25));
          signup.add(ofSignUp);
          
          JLabel term=new JLabel("(✓) Terms and Conditions:");
          term.setBounds(20, 290, 300, 50);
          signup.add(term);
          term.setFont(new Font("italic", Font.ITALIC, 25));
          
        JTextArea tArea = new JTextArea();
        tArea.setBounds(80, 340, 450, 200);
        tArea.setText("1. Conduct not conducive to the proper use of the library is forbidden.\n" + //
                "\n" + //
                "2. Noise, disturbance or unruly behaviour is forbidden in any part of the library.\n" + //
                "\n" + //
                "3. Smoking, food and drinks are not allowed in the library.");
        tArea.setFont(new Font("italic", Font.ITALIC, 18));
        tArea.setLineWrap(true);
        tArea.setEditable(false);
        tArea.setWrapStyleWord(true);
        signup.add(tArea);

         checkBox1 =new JCheckBox();
        checkBox1.setBounds(30, 350, 40, 40);
        signup.add(checkBox1);
        
         checkBox2= new JCheckBox();
        checkBox2.setBounds(30, 420, 40, 40);
        signup.add(checkBox2);
         checkBox3 =new JCheckBox();
        checkBox3.setBounds(30, 480, 40, 40);
        signup.add(checkBox3);

        checkBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            ofSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            ofSignOtp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            ImageIcon backgeo = new ImageIcon("img/logo.jpg");
        Image Ima_back = backgeo.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(Ima_back);
        log.setBackground(Color.black);
        // log.setForeground(Color.black);
        JLabel log = new JLabel(background);
        log.setBounds(20, 20, 500 , 140);
       signup.add(log);


            offi = new JLabel("");
            offi.setFont(new Font("arial", Font.BOLD, 15));
            offi.setForeground(Color.RED);
            signup.add(offi);
            offi.setVisible(false);

                        ofSignOtp.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new Thread(new Runnable() {
                                    
                                    @Override
                                    public void run() {
                                off.setVisible(false);
                            
                                officeotp = GEN_OTP.generateOTP();
                                String message = "Dear sir, \n \t New Candidate register in BCA Library \n \t\t Name:-"+TextFirst.getText() + " " + TextLast.getText()+"   \n\t\t Mob:-"+SinText.getText()+" \n\t\t Email:-"+SinEmail.getText()+"\n\n\n\t\t\t OTP is: " + officeotp;
                                String subject = "Books Library : OTP ";
                                String from = "bytecoading@gmail.com";
                                new Thread(new email_id(message, subject,"bittubabu2993@gmail.com", from)).start();
                                off.setVisible(true);
                            }
                            
                        }).start();
                            }
                            

                        });
ofTextOtp.addFocusListener(new FocusListener() {

    @Override
    public void focusGained(FocusEvent e) {
        offi.setVisible(false);
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
    
});
    checkBox1.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
            offi.setVisible(false);
        }

        @Override
        public void focusLost(FocusEvent e) {
         
        }
        
    });
  checkBox2.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
            offi.setVisible(false);
        }

        @Override
        public void focusLost(FocusEvent e) {
         
        }
        
    });
  checkBox3.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
            offi.setVisible(false);
        }

        @Override
        public void focusLost(FocusEvent e) {
         
        }
        
    });


       ofSignUp.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(checkBox1.isSelected()&&checkBox2.isSelected()&&checkBox3.isSelected()){
              
                if(officeotp.equals(ofTextOtp.getText())){
                




                        
                        pei = new Progress();
                        pei.pro.setBounds(100, 5, 330, 25);
                        signup.add(pei.pro);
                        repaint();
                        validate();
                        new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    FileInputStream inputStream = new FileInputStream("img/p.jpeg");
                                    Connection con2 = DriverManager.getConnection(url, DBuser, DBpass);
                                    final String sql = "insert into USER_S (Full_name,Email_ID,MOB,pass_word,images)values(?,?,?,?,?)";
                                    PreparedStatement statement = con2.prepareStatement(sql);
                                    String Ps = Passk.Password();
                                    statement.setString(1, TextFirst.getText() + " " + TextLast.getText());
                                    statement.setString(2, SinEmail.getText());
                                    statement.setDouble(3, Double.parseDouble(SinText.getText()));
                                    statement.setString(4, Ps);
                                    statement.setBinaryStream(5, inputStream, inputStream.available());
                                    int row = statement.executeUpdate();
                                    if (row > 0) {
                                        try {
                                            pei.t.stop();
                                            pei.pro.setIndeterminate(false);
                                            pei.pro.setVisible(false);
                                        } catch (Exception e) {

                                        }
                                        JOptionPane.showMessageDialog(null,
                                                "Register Succesfully!..\nPassword send Your email Id!..");
                                        bk.removeAll();
                                        bk.add(jPanel);
                                        bk.add(heading);
                                        jPanel.repaint();
                                        jPanel.revalidate();
                                        String message = "Dear, \n \t user \n \t\t Your Password Is : " + Ps;
                                        String subject = "Books Library  : Password ";
                                        String from = "bytecoading@gmail.com";

                                        new Thread(new email_id(message, subject, SinEmail.getText(), from)).start();
                                    } else {
                                    }
                                    con2.close();
                                } catch (Exception et) {
                                    try {
                                        pei.t.stop();
                                        pei.pro.setIndeterminate(false);
                                        pei.pro.setVisible(false);
                                    } catch (Exception e) {

                                    }
                                    JOptionPane.showMessageDialog(null, "User Alredy SingUp", "Message",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    bk.removeAll();
                                    bk.add(jPanel);
                                    bk.add(heading);
                                    jPanel.repaint();
                                    jPanel.revalidate();
                                }
                            }

                        }).start();
                }else{
                offi.setText("OTP Not Verified");
                offi.setBounds(180, 270, 300, 40);
                offi.setVisible(true);
                }
            }
            else{
                offi.setText("Check Terms and Conditions");
                offi.setBounds(30, 540, 300, 40);
                offi.setVisible(true);
            }

            
        }
        
       });


















                    } else {
                        signOTP.setText("invalid OTP");
                        signOTP.setBounds(180, 490, 300, 40);
                        signOTP.setVisible(true);
                    }
                }

            });

            log.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bk.removeAll();
                    bk.add(jPanel);
                    bk.add(heading);
                    jPanel.repaint();
                    jPanel.revalidate();

                }

            });
        }

    }

}
