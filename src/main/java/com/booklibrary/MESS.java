package com.booklibrary;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class MESS implements ActionListener{
    private JLabel Jdepbookname;
    private JLabel Jdepbookid,DET;
    protected JTextField date,depbookname,depbookid;
    protected JButton ok;
    protected JFrame frame;


    /**
     * @param BOOKNAme
     * @param BoookID
     */
    public MESS(String BOOKNAme, String BoookID , String dat){
        int  y =(Toolkit.getDefaultToolkit().getScreenSize().height-getHeight())/2;
        int  x =(Toolkit.getDefaultToolkit().getScreenSize().width-getWidth())/2;
        int x1=560;
        int y1=350;

        java.awt.Font Font1=new Font("Georgia", Font.ROMAN_BASELINE, 22);
        Font log=new Font("Algerian", Font.PLAIN, 25);
        Font fillFont=new Font("Italic", Font.ITALIC, 20);
        // Font fil=new Font("Italic", Font.ITALIC, 17);  
           frame = new JFrame();


            frame.setBounds((x-(x1/2)),(y-(y1/2)),x1,y1);
            frame.setResizable(false);
            java.awt.Container  c= frame.getContentPane();
            ImageIcon imageIcon=new ImageIcon("images.jpg");
            frame.setIconImage(imageIcon.getImage());
            frame.setTitle("Message!!!!!.....");
            c.setLayout(null);
        
        ImageIcon ico=new ImageIcon("warn.png");
        Image ima=ico.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH);
        ImageIcon in=new ImageIcon(ima);
        JLabel lab=new JLabel(in);
        lab.setBounds(10,0, 80, 70);

        JLabel us1=new JLabel("Books Alrready Issues !!");
        us1.setBounds(120, 10, 1800, 60);
        us1.setForeground(Color.decode("#A25EF2"));
        us1.setFont(log);



        DET = new JLabel("DATE :");
        DET.setBounds(160-88,160-80, 250, 40);
        DET.setFont(Font1);
        DET.setForeground(Color.decode("#EA3680"));
        
        Jdepbookname = new JLabel("Book Name:");
        Jdepbookname.setBounds(108-88,210-80, 250, 40);
        Jdepbookname.setFont(Font1);
        Jdepbookname.setForeground(Color.decode("#EA3680"));
        Jdepbookid = new JLabel("Book Id:");
        Jdepbookid.setBounds(145-88, 260-80, 250, 40);
        Jdepbookid.setFont(Font1);
        Jdepbookid.setForeground(Color.decode("#EA3680"));

        date= new   JTextField();
        date.setBounds(150,160-80, 350, 37);
        date.setFont(fillFont);
        date.setEditable(false);

        depbookname= new   JTextField();
        depbookname.setBounds(150,210-80, 350, 37);
        depbookname.setFont(fillFont);
        depbookname.setEditable(false);
        depbookid= new   JTextField();
        depbookid.setBounds(150,260-80, 350, 37);
        depbookid.setFont(fillFont);
        depbookid.setEditable(false);

       

        ok=new JButton("Ok..");
        ok.setBounds(240,320-80, 90, 45);
        ok.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 20));
        ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ok.addActionListener(this);
        date.setText(dat);
        depbookname.setText(BOOKNAme);
        depbookid.setText(BoookID);

            c.add(DET);
            c.add(Jdepbookname);
            c.add(Jdepbookid);
            c.add(date);
            c.add(depbookname);
            c.add(depbookid);
            c.add(ok);
            c.add(lab);
            c.add(us1);

            frame.setVisible(true);

    }

    private int getWidth() {
        return 0;
    }

    private int getHeight() {
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //    System.out.println("Helleo");
        frame.setVisible(false);
    }
}