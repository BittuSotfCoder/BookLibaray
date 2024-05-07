package com.booklibrary;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

// import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public class Books_information extends JPanel {

  protected static Object jpanel;

  public Books_information() {
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
    setBounds(550, 0, x, y);
    setBackground(Color.decode("#C8BFE7"));
    setLayout(null);
    initFrame(new Book_infor());
    add(new Book_infor());

  }

  private void initFrame(JInternalFrame jInternalFrame) {
  }

}

class Book_infor extends JInternalFrame {
  static boolean resphoto=false;
 private FileInputStream inputStream12,inputStream;
    private JButton BookAdd,BookRead,BookUpload;
  private JButton GetDetails,Reset,Register_Now,BookDelete;
  private JLabel semlabel, fillabel, BookLabelId, BookLabelName, DoMore, doLess, OR,res1,res2,res3,
      restrictBookName, rsetBookId;
  private JComboBox<java.lang.String> SEM;
  private JPanel panel;
  File BooKPhoto;

  private JTextField TextBookId, TextBookName,TBookId,TBookpubli,TBookWriter,TBookGen;
  private JRadioButton OrderDate, OrderBook;
  Jpanel jpp;
  static String BookIdMatch;
  private BufferedImage bufImg = null;
  private JFileChooser fileChooser;
  private JCheckBox TBookNameCheck,TBookpubliCheck,TBookWriterCheck,TBookGenCheck;
     Connection con2,ConectionBookDetail ;
  private JLabel LBookID,LBookName,LBookPubl,LBookGen,LBookWriter,LBookPhoto;
  private JTextArea TBookName;
  JTable j;
  Font lfont;

  final java.lang.String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
  final java.lang.String DBuser = "usxqljshik8op7dd";
  final java.lang.String DBpass = "ViDjCizs55WoYAMXOmeW";
  Progress pei;

  public Book_infor() {
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
    setBounds(0, 0, x - 550, y);
    setLayout(new FlowLayout());
    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    setLayout(null);
    ImageIcon stud = new ImageIcon("img/stud.png");
    setFrameIcon(stud);
    setTitle("Infromation");

        panel = new JPanel();
        panel.setBounds(0, 140, 1400, 785);
        panel.setLayout(null);
        add(panel);

        // panel1 = new JPanel();
        // panel1.setBounds(0, 140, 1400, 785);
        // add(panel1);
        // panel1.setLayout(new BorderLayout());
   
    
    lfont = new Font("Georgia", Font.ROMAN_BASELINE, 20);

  
    fillabel = new JLabel("Filters:");
    fillabel.setBounds(60, 20, 200, 40);
    fillabel.setFont(lfont);
    add(fillabel);

    OrderDate = new JRadioButton("Books Informatoin");
    OrderDate.setBounds(70, 50,210, 40);
    OrderDate.setFont(lfont);
    add(OrderDate);

    OrderBook = new JRadioButton("Books Modify");
    OrderBook.setBounds(70, 90, 200, 40);
    OrderBook.setFont(lfont);
    add(OrderBook);

    ButtonGroup gender = new ButtonGroup();
    gender.add(OrderBook);
    gender.add(OrderDate);
    OrderDate.setSelected(true);

    semlabel = new JLabel("Books:");
    semlabel.setBounds(310, 15, 200, 40);
    semlabel.setFont(lfont);
    add(semlabel);
   java.lang. String Sem[] = { "Total Books", "Issues Books", "Not Issues"};
    SEM = new JComboBox<>(Sem);
    SEM.setBounds(320, 50, 150, 40);
    SEM.setFont(new Font("italic", Font.PLAIN, 18));
    add(SEM);
        int sizex=160;
        BookLabelId = new JLabel("Book Id : ");
        BookLabelId.setBounds(350+sizex, 15, 200, 40);
        BookLabelId.setFont(lfont);
        add(BookLabelId);
        TextBookId = new JTextField();
        TextBookId.setBounds(350+sizex, 50, 200, 40);
        add(TextBookId);
    


        DoMore = new JLabel("(Do More Use)");
        DoMore.setBounds(450+sizex, 18, 200, 40);
        DoMore.setFont(new Font("Arial", Font.ITALIC, 15));
        DoMore.setForeground(Color.decode("#2A3BFA"));
        add(DoMore);

        OR = new JLabel("(OR)");
        OR.setBounds(590+sizex, 50, 50, 40);
        add(OR);

        doLess = new JLabel("(Do Less Use)");
        doLess.setBounds(880+sizex, 18, 200, 40);
        doLess.setFont(new Font("Arial", Font.ITALIC, 15));
        doLess.setForeground(Color.decode("#2A3BFA"));
        add(doLess);

        BookLabelName = new JLabel("Book Name:");
        BookLabelName.setBounds(650+sizex, 15, 200, 40);
        BookLabelName.setFont(lfont);
        add(BookLabelName);
        TextBookName = new JTextField();
        TextBookName.setBounds(650+sizex, 50, 330, 40);
        add(TextBookName);


        rsetBookId = new JLabel();
        rsetBookId.setFont(new Font("arial", Font.BOLD, 15));
        rsetBookId.setForeground(Color.RED);
        add(rsetBookId);

        restrictBookName = new JLabel();
        restrictBookName.setFont(new Font("arial", Font.BOLD, 15));
        restrictBookName.setForeground(Color.RED);
        add(restrictBookName);


    {

     TextBookId.addFocusListener(new FocusListener() {

          @Override
          public void focusGained(FocusEvent e) {
            TextBookName.setEditable(false);
            rsetBookId.setVisible(false);
            restrictBookName.setVisible(false);
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (TextBookId.getText().equals("")) {
              TextBookName.setEditable(true);
            }

          }

        });

        TextBookName.addFocusListener(new FocusListener() {
    @Override
          public void focusGained(FocusEvent e) {
            TextBookId.setEditable(false);
            restrictBookName.setVisible(false);
            restrictBookName.setVisible(false);
        

          }

          @Override
          public void focusLost(FocusEvent e) {
            if (TextBookName.getText().equals("")) {
              TextBookId.setEditable(true);
            }
            
          }
        });

    }
    
    
    GetDetails = new JButton("GetDetails");
    GetDetails.setBounds(1050+sizex, 50, 120, 40);
    add(GetDetails);


    SEM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    OrderDate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    OrderBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    GetDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    OrderDate.setEnabled(false);
 
    GetDetails.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        panel.setLayout(new BorderLayout());
        if(SEM.getSelectedItem().equals("Issues Books")){


             pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {
// ---------------------auto inform------
        { 
      panel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          DefaultTableModel Model = new DefaultTableModel();
String[] columnNames = {"Book's images","Book's Id", "Book's Name", "Book Publication", "Book's Writer", "Books Generation" };
          Model.setColumnIdentifiers(columnNames);

          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(140);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "SELECT * FROM `Books_Infor` WHERE `column_name_check`=0";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            // Book_Id,Book_Name,Publications,Generation,Writer,Img
            while (rs.next()) {
            
             InputStream in = rs.getBinaryStream("Img");
              bufImg = ImageIO.read(in);
              Image image = bufImg;         
              ImageIcon icon =new ImageIcon(image); 
              Rol = Integer.toString(rs.getInt("Book_Id"));
              S_nmae = rs.getString("Book_Name");
              Semm = rs.getString("Publications");
              isue = rs.getString("Writer");
              BookNAME = rs.getString("Generation");
              Model.addRow(new Object[] {icon,Rol, S_nmae, Semm, isue,BookNAME});
            }
            panel.add(new JScrollPane(j));
            try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception e) {

            }
            revalidate();
            repaint();
            conn.close();
          } catch (Exception ikm) {
          }

        }

      }

    }).start();
          
        }else if(SEM.getSelectedItem().equals("Not Issues")){
         

    pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {
// ---------------------auto inform------
        { 

      panel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          DefaultTableModel Model = new DefaultTableModel();
String[] columnNames = {"Book's images","Book's Id", "Book's Name", "Book Publication", "Book's Writer", "Books Generation" };
          Model.setColumnIdentifiers(columnNames);

          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(140);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "SELECT * FROM `Books_Infor` WHERE `column_name_check`=1";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            // Book_Id,Book_Name,Publications,Generation,Writer,Img
            while (rs.next()) {
            
             InputStream in = rs.getBinaryStream("Img");
              bufImg = ImageIO.read(in);
              Image image = bufImg;         
              ImageIcon icon =new ImageIcon(image); 
              Rol = Integer.toString(rs.getInt("Book_Id"));
              S_nmae = rs.getString("Book_Name");
              Semm = rs.getString("Publications");
              isue = rs.getString("Writer");
              BookNAME = rs.getString("Generation");
              Model.addRow(new Object[] {icon,Rol, S_nmae, Semm, isue,BookNAME});
            }
            panel.add(new JScrollPane(j));
            try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception e) {

            }
            revalidate();
            repaint();
            conn.close();
          } catch (Exception ikm) {
          }

        }

      }

    }).start();

          

        }else if(SEM.getSelectedItem().equals("Total Books")){
    pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {
// ---------------------auto inform------
        { 
      panel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          DefaultTableModel Model = new DefaultTableModel();
String[] columnNames = {"Book's images","Book's Id", "Book's Name", "Book Publication", "Book's Writer", "Books Generation" };
          Model.setColumnIdentifiers(columnNames);

          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(140);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "select * from Books_Infor";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            // Book_Id,Book_Name,Publications,Generation,Writer,Img
            while (rs.next()) {
            
             InputStream in = rs.getBinaryStream("Img");
              bufImg = ImageIO.read(in);
              Image image = bufImg;         
              ImageIcon icon =new ImageIcon(image); 
              Rol = Integer.toString(rs.getInt("Book_Id"));
              S_nmae = rs.getString("Book_Name");
              Semm = rs.getString("Publications");
              isue = rs.getString("Writer");
              BookNAME = rs.getString("Generation");
              Model.addRow(new Object[] {icon,Rol, S_nmae, Semm, isue,BookNAME});
            }
            panel.add(new JScrollPane(j));
            try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception e) {

            }
            revalidate();
            repaint();
            conn.close();
          } catch (Exception ikm) {
          }

        }

      }

    }).start();
        }


      }
      
    });
















    









    OrderDate.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
              try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception eds) {

            }
            remove(BookAdd);
            remove(BookRead);
            panel.setBackground(Color.white);
            panel.removeAll();
            
    semlabel = new JLabel("Books:");
    semlabel.setBounds(310, 15, 200, 40);
    semlabel.setFont(lfont);
    add(semlabel);
   java.lang. String Sem[] = { "Total Books", "Issues Books", "Not Issues"};
    SEM = new JComboBox<>(Sem);
    SEM.setBounds(320, 50, 150, 40);
    SEM.setFont(new Font("italic", Font.PLAIN, 18));
    add(SEM);
    int sizex=150;
    BookLabelId = new JLabel("Book Id : ");
    BookLabelId.setBounds(350+sizex, 15, 200, 40);
    BookLabelId.setFont(lfont);
    add(BookLabelId);
    TextBookId = new JTextField();
    TextBookId.setBounds(350+sizex, 50, 200, 40);
    add(TextBookId);
    
    
    
    DoMore = new JLabel("(Do More Use)");
    DoMore.setBounds(450+sizex, 18, 200, 40);
    DoMore.setFont(new Font("Arial", Font.ITALIC, 15));
    DoMore.setForeground(Color.decode("#2A3BFA"));
    add(DoMore);
    
    OR = new JLabel("(OR)");
    OR.setBounds(590+sizex, 50, 50, 40);
    add(OR);

    doLess = new JLabel("(Do Less Use)");
    doLess.setBounds(880+sizex, 18, 200, 40);
    doLess.setFont(new Font("Arial", Font.ITALIC, 15));
    doLess.setForeground(Color.decode("#2A3BFA"));
        add(doLess);

        BookLabelName = new JLabel("Book Name:");
        BookLabelName.setBounds(650+sizex, 15, 200, 40);
        BookLabelName.setFont(lfont);
        add(BookLabelName);
        TextBookName = new JTextField();
        TextBookName.setBounds(650+sizex, 50, 330, 40);
        add(TextBookName);


      
        rsetBookId = new JLabel();
        rsetBookId.setFont(new Font("arial", Font.BOLD, 15));
        rsetBookId.setForeground(Color.RED);
        add(rsetBookId);
        
        restrictBookName = new JLabel();
        restrictBookName.setFont(new Font("arial", Font.BOLD, 15));
        restrictBookName.setForeground(Color.RED);
        add(restrictBookName);
        
        
        
        
        TextBookId.addFocusListener(new FocusListener() {
            
            @Override
            public void focusGained(FocusEvent e) {
                TextBookName.setEditable(false);
                rsetBookId.setVisible(false);
            restrictBookName.setVisible(false);
          }

          @Override
          public void focusLost(FocusEvent e) {
              if (TextBookId.getText().equals("")) {
                  TextBookName.setEditable(true);
                }
                
            }
            
        });
        GetDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SEM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TextBookName.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
        TextBookId.setEditable(false);
            restrictBookName.setVisible(false);
            restrictBookName.setVisible(false);
          }

          @Override
          public void focusLost(FocusEvent e) {
              if (TextBookName.getText().equals("")) {
                  TextBookId.setEditable(true);
            }
            
          }
        });

    GetDetails = new JButton("GetDetails");
    GetDetails.setBounds(1050+sizex, 50, 120, 40);
    add(GetDetails);



        revalidate();
        repaint();
        OrderDate.setEnabled(false);
        OrderBook.setEnabled(true);

GetDetails.addActionListener(new ActionListener() {

  @Override
  public void actionPerformed(ActionEvent e) {
        panel.setLayout(new BorderLayout());
        if(SEM.getSelectedItem().equals("Issues Books")){


             pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {
// ---------------------auto inform------
        { 
      panel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          DefaultTableModel Model = new DefaultTableModel();
String[] columnNames = {"Book's images","Book's Id", "Book's Name", "Book Publication", "Book's Writer", "Books Generation" };
          Model.setColumnIdentifiers(columnNames);

          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(140);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "SELECT * FROM `Books_Infor` WHERE `column_name_check`=0";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            // Book_Id,Book_Name,Publications,Generation,Writer,Img
            while (rs.next()) {
            
             InputStream in = rs.getBinaryStream("Img");
              bufImg = ImageIO.read(in);
              Image image = bufImg;         
              ImageIcon icon =new ImageIcon(image); 
              Rol = Integer.toString(rs.getInt("Book_Id"));
              S_nmae = rs.getString("Book_Name");
              Semm = rs.getString("Publications");
              isue = rs.getString("Writer");
              BookNAME = rs.getString("Generation");
              Model.addRow(new Object[] {icon,Rol, S_nmae, Semm, isue,BookNAME});
            }
            panel.add(new JScrollPane(j));
            try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception e) {

            }
            revalidate();
            repaint();
            conn.close();
          } catch (Exception ikm) {
          }

        }

      }

    }).start();
          
        }else if(SEM.getSelectedItem().equals("Not Issues")){
         

    pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {
// ---------------------auto inform------
        { 

      panel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          DefaultTableModel Model = new DefaultTableModel();
String[] columnNames = {"Book's images","Book's Id", "Book's Name", "Book Publication", "Book's Writer", "Books Generation" };
          Model.setColumnIdentifiers(columnNames);

          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(140);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "SELECT * FROM `Books_Infor` WHERE `column_name_check`=1";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            // Book_Id,Book_Name,Publications,Generation,Writer,Img
            while (rs.next()) {
            
             InputStream in = rs.getBinaryStream("Img");
              bufImg = ImageIO.read(in);
              Image image = bufImg;         
              ImageIcon icon =new ImageIcon(image); 
              Rol = Integer.toString(rs.getInt("Book_Id"));
              S_nmae = rs.getString("Book_Name");
              Semm = rs.getString("Publications");
              isue = rs.getString("Writer");
              BookNAME = rs.getString("Generation");
              Model.addRow(new Object[] {icon,Rol, S_nmae, Semm, isue,BookNAME});
            }
            panel.add(new JScrollPane(j));
            try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception e) {

            }
            revalidate();
            repaint();
            conn.close();
          } catch (Exception ikm) {
          }

        }

      }

    }).start();

          

        }else if(SEM.getSelectedItem().equals("Total Books")){
    pei = new Progress();
    pei.pro.setBounds(590, 100, 330, 25);
    add(pei.pro);
    repaint();
    validate();
    new Thread(new Runnable() {

      @Override
      public void run() {
// ---------------------auto inform------
        { 
      panel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          DefaultTableModel Model = new DefaultTableModel();
String[] columnNames = {"Book's images","Book's Id", "Book's Name", "Book Publication", "Book's Writer", "Books Generation" };
          Model.setColumnIdentifiers(columnNames);

          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(140);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "select * from Books_Infor";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            // Book_Id,Book_Name,Publications,Generation,Writer,Img
            while (rs.next()) {
            
             InputStream in = rs.getBinaryStream("Img");
              bufImg = ImageIO.read(in);
              Image image = bufImg;         
              ImageIcon icon =new ImageIcon(image); 
              Rol = Integer.toString(rs.getInt("Book_Id"));
              S_nmae = rs.getString("Book_Name");
              Semm = rs.getString("Publications");
              isue = rs.getString("Writer");
              BookNAME = rs.getString("Generation");
              Model.addRow(new Object[] {icon,Rol, S_nmae, Semm, isue,BookNAME});
            }
            panel.add(new JScrollPane(j));
            try {
              pei.t.stop();
              pei.pro.setIndeterminate(false);
              pei.pro.setVisible(false);
            } catch (Exception e) {

            }
            revalidate();
            repaint();
            conn.close();
          } catch (Exception ikm) {
          }

        }

      }

    }).start();
        }

  }
  
});






        }
        
    });



















    OrderBook.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {  
          try {
             pei.t.stop();
             pei.pro.setIndeterminate(false);
             pei.pro.setVisible(false);
           } catch (Exception esq) {
  
           }
        panel.setLayout(null);
        remove(SEM);      
        remove(semlabel); 
        remove(BookLabelId);
        remove(BookLabelName);
        remove(TextBookId);
        remove(TextBookName);
        remove(OR);
        remove(DoMore);
        remove(doLess);
        remove( GetDetails);
        remove(restrictBookName);
        remove(rsetBookId);
        panel.removeAll();
         panel.setBackground(Color.white);

        ImageIcon up = new ImageIcon("img/add.png");
        Image upd = up.getImage().getScaledInstance(60, 40, Image.SCALE_SMOOTH);
        ImageIcon upda = new ImageIcon(upd);
        BookAdd = new JButton("Book Add", upda);
        BookAdd.setBounds(320, 45, 180, 50);
        BookAdd.setFont(new Font("arial", Font.BOLD, 17));
        
        add(BookAdd);

            ImageIcon up1 = new ImageIcon("img/reading.png");
        Image upd1 = up1.getImage().getScaledInstance(60, 40, Image.SCALE_SMOOTH);
        ImageIcon upda1 = new ImageIcon(upd1);
        BookRead = new JButton("Book Edit", upda1);
        BookRead.setBounds(530, 45, 180, 50);
        BookRead.setFont(new Font("arial", Font.BOLD, 17));
        add(BookRead);
    
        BookAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BookRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
                    
        revalidate();
        repaint();
        OrderBook.setEnabled(false);
        OrderDate.setEnabled(true);








        BookAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.setBackground(Color.decode("#DBA8A9"));
               int Sizex = 350;
      // int Sizey = 100;
        LBookID = new JLabel("Enter Book ID:");
        LBookID.setBounds(Sizex, 70, 250, 40);
        LBookID.setFont(lfont);
        TBookId = new JTextField();
        TBookId.setBounds(Sizex + 190,70, 150, 37);
        TBookId.setFont(Issues.fillFont);
        panel.add(TBookId);
        panel.add(LBookID);


        LBookName = new JLabel("Enter Book Name:");
        LBookName.setBounds(Sizex, 130, 250, 40);
        LBookName.setFont(lfont);
        panel.add(LBookName);
        TBookName = new JTextArea();
        TBookName.setBounds(Sizex + 190,130, 350, 100);
        panel.add(TBookName);
        TBookName.setFont(new Font("arial", Font.ITALIC, 20));
        TBookName.setLineWrap(true);

        LBookPubl = new JLabel("Book Publication:");
        LBookPubl.setBounds(Sizex, 250, 250, 40);
        LBookPubl.setFont(lfont);
        panel.add(LBookPubl);
        TBookpubli = new JTextField();
        TBookpubli.setBounds(Sizex + 190,250, 350, 37);
        TBookpubli.setFont(Issues.fillFont);
        panel.add(TBookpubli);

        LBookWriter = new JLabel("Book WriterName:");
        LBookWriter.setBounds(Sizex, 310, 250, 40);
        LBookWriter.setFont(lfont);
        panel.add(LBookWriter);
        TBookWriter = new JTextField();
        TBookWriter.setBounds(Sizex + 190,310, 350, 37);
        TBookWriter.setFont(Issues.fillFont);
        panel.add(TBookWriter);

        LBookGen = new JLabel("Book Generation:");
        LBookGen.setBounds(Sizex, 370, 250, 40);
        LBookGen.setFont(lfont);
        panel.add(LBookGen);
        TBookGen = new JTextField();
        TBookGen.setBounds(Sizex + 190,370, 350, 37);
        TBookGen.setFont(Issues.fillFont);
        panel.add(TBookGen);
    
        LBookPhoto = new JLabel("Book Photo:");
        LBookPhoto.setBounds(Sizex, 430, 250, 40);
        LBookPhoto.setFont(lfont);
        panel.add(LBookPhoto);
    
          BookUpload =new JButton("click Upload");
          BookUpload.setBounds(Sizex+190, 430, 140, 200);
          panel.add(BookUpload);

        Register_Now = new JButton("Register Now");
        Register_Now.setBounds(700, 680, 200, 50);
        Register_Now.setFont(new Font("arial", Font.BOLD, 25));
        panel.add(Register_Now);

        Reset = new JButton("Reset");
        Reset.setBounds(400, 680, 200, 50);
        Reset.setFont(new Font("arial", Font.BOLD, 25));
        panel.add(Reset);


       res1 = new JLabel();
        res1.setBounds(Sizex+190, 95, 250, 40);
        res1.setFont(new Font("arial", Font.ITALIC, 12));
        panel.add(res1);
        res1.setForeground(Color.red);

               res2 = new JLabel();
        res2.setBounds(Sizex+190, 218, 250, 40);
        res2.setFont(new Font("arial", Font.ITALIC, 12));
        panel.add(res2);
        res2.setForeground(Color.red);

               res3 = new JLabel();
        res3.setBounds(Sizex+190, 620, 250, 40);
        res3.setFont(new Font("arial", Font.ITALIC, 12));
        panel.add(res3);
        res3.setForeground(Color.red);

        BookUpload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Reset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Register_Now.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      revalidate();
      repaint();

     
    TBookId.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    char c = ke.getKeyChar();
                    if (!Character.isDigit(c)) {
                        ke.consume();
                        TBookId.setEditable(true);
                        res1.setVisible(false);
                        if (Character.isLetter(c)) {
                            TBookId.setEditable(true);
                            res1.setText("Input only Numeric (0-9)");
                            res1.setVisible(true);
                        }
                    } else {
                        res1.setVisible(false);
                        if (TBookId.getText().length() == 10) {
                            TBookId.setEditable(false);
                            res1.setText("Input Only 10 digit number");
                            res1.setVisible(true);
                        }
                    }
                }
            });

      TBookId.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
     res1.setText(null);
        }

        @Override
        public void focusLost(FocusEvent e) {
    
        }
        
      });

      TBookName.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
        res2.setText(null);
        }

        @Override
        public void focusLost(FocusEvent e) {
   
        }
        
      });

      BookUpload.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
         res3.setText(null);
         fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only jpg", "jpg");
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Only png", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter1);

          int selelected = fileChooser.showOpenDialog(null);
                if (selelected == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    java.lang.String Filname = file.getAbsolutePath();
                   File BooKPhoto1 = new File(Filname);
              

                    if((BooKPhoto1.length()/1024) <30){
                      BooKPhoto=BooKPhoto1;
                      resphoto=true;
                     
                      ImageIcon   idnk = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                     Image pro_file = idnk.getImage().getScaledInstance(130, 190, Image.SCALE_AREA_AVERAGING);
                     ImageIcon proFile = new ImageIcon(pro_file);
                     BookUpload.setIcon(proFile);
                    }
                    else{
                      JOptionPane.showMessageDialog(null, "Less Than 30 Kb");
                    }
        }else{
          JOptionPane.showMessageDialog(null, "Photo not choosen");
        }

        }


        
      });
      
      


      Register_Now.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {

          if(TBookId.getText().equals("")&&TBookName.getText().equals("") && false==resphoto){
              res1.setText("Required Book Id*");
              res2.setText("Required Book Name*");
              res3.setText("Required Book Photo*");}
             else if(TBookId.getText().equals("")&&TBookName.getText().equals("")){
                res1.setText("Required Book Id*");
                res2.setText("Required Book Name*");
              }
              else if(TBookId.getText().equals("") && false==resphoto){
                res1.setText("Required Book Id*");
                res3.setText("Required Book Photo*");
              }
              else if(TBookName.getText().equals("") && false==resphoto){
                res2.setText("Required Book Name*");
                res3.setText("Required Book Photo*");
              }
              else if(TBookId.getText().equals("")){
                res1.setText("Required Book Id*");
              }
              else if(TBookName.getText().equals("")){
              res2.setText("Required Book Name*");
              
              }
              else if(false==resphoto){
                
              res3.setText("Required Book Photo*");
              }
          else{


                pei = new Progress();
                pei.pro.setBounds(550, 100, 330, 25);
                add(pei.pro);
                repaint();
                validate();
        new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                          
                          con2= DriverManager.getConnection(url, DBuser, DBpass);
                          final java.lang.String sql = "INSERT INTO Books_Infor (Book_Id,Book_Name,Publications,Generation,Writer,Img)VALUES(?,?,?,?,?,?)";
                         
                           inputStream = new FileInputStream(BooKPhoto);
                            PreparedStatement statement = con2.prepareStatement(sql);
                            statement.setInt(1, Integer.parseInt(TBookId.getText()));
                            statement.setString(2, TBookName.getText());
                            statement.setString(3,TBookpubli.getText());
                            statement.setString(4, TBookGen.getText());
                            statement.setString(5, TBookWriter.getText());
                             statement.setBinaryStream(6, inputStream, inputStream.available());
                            int row = statement.executeUpdate();
                            if (row > 0) {
                                try {
                                    pei.t.stop();
                                    pei.pro.setIndeterminate(false);
                                    pei.pro.setVisible(false);
                                } catch (Exception e) {

                                }
                                JOptionPane.showMessageDialog(null, "Register Succesfully...");
                                
                                TBookId.setText(null);
                                TBookName.setText(null);
                                TBookpubli.setText(null);
                                TBookGen.setText(null);
                                TBookWriter.setText(null);
                                BooKPhoto=null;
                                BookUpload.setIcon(null);
                                BookUpload.setText("click Upload");
                            }
                            con2.close();
                        } catch (Exception et) {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                                con2.close();
                            } catch (Exception e) {
                            }
                            JOptionPane.showMessageDialog(panel, "It's BookId Already Registered");

                        }
                    }

                }).start();
                  









              }
                }
                
              });

              Reset.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                      TBookId.setText(null);
                                      TBookName.setText(null);
                                      TBookpubli.setText(null);
                                      TBookGen.setText(null);
                                      TBookWriter.setText(null);
                                      BooKPhoto=null;
                                      BookUpload.setIcon(null);
                                      BookUpload.setText("click Upload");
                }
                
              });

            }
          
          
    
        });
          
        // =====================Edit Books-------------------------------
        BookRead.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            
                panel.setBackground(Color.decode("#30788F"));
               int Sizex = 350;
      // int Sizey = 100;
        LBookID = new JLabel("Enter Book ID:");
        LBookID.setBounds(Sizex, 70, 250, 40);
        LBookID.setFont(lfont);
        TBookId = new JTextField();
        TBookId.setBounds(Sizex + 190,70, 150, 37);
        TBookId.setFont(Issues.fillFont);
        panel.add(TBookId);
        panel.add(LBookID);

         res1 = new JLabel();
        res1.setBounds(Sizex+190, 95, 250, 40);
        res1.setFont(new Font("arial", Font.ITALIC, 12));
        panel.add(res1);
        res1.setForeground(Color.red);


        LBookName = new JLabel("Enter Book Name:");
        LBookName.setBounds(Sizex, 130, 250, 40);
        LBookName.setFont(lfont);
        panel.add(LBookName);
        TBookName = new JTextArea();
        TBookName.setBounds(Sizex + 190,130, 350, 100);
        panel.add(TBookName);
        TBookName.setFont(new Font("arial", Font.ITALIC, 20));
        TBookName.setLineWrap(true);

        LBookPubl = new JLabel("Book Publication:");
        LBookPubl.setBounds(Sizex, 250, 250, 40);
        LBookPubl.setFont(lfont);
        panel.add(LBookPubl);
        TBookpubli = new JTextField();
        TBookpubli.setBounds(Sizex + 190,250, 350, 37);
        TBookpubli.setFont(Issues.fillFont);
        panel.add(TBookpubli);

        LBookWriter = new JLabel("Book WriterName:");
        LBookWriter.setBounds(Sizex, 310, 250, 40);
        LBookWriter.setFont(lfont);
        panel.add(LBookWriter);
        TBookWriter = new JTextField();
        TBookWriter.setBounds(Sizex + 190,310, 350, 37);
        TBookWriter.setFont(Issues.fillFont);
        panel.add(TBookWriter);

        LBookGen = new JLabel("Book Generation:");
        LBookGen.setBounds(Sizex, 370, 250, 40);
        LBookGen.setFont(lfont);
        panel.add(LBookGen);
        TBookGen = new JTextField();
        TBookGen.setBounds(Sizex + 190,370, 350, 37);
        TBookGen.setFont(Issues.fillFont);
        panel.add(TBookGen);
    
        LBookPhoto = new JLabel("Book Photo:");
        LBookPhoto.setBounds(Sizex, 430, 250, 40);
        LBookPhoto.setFont(lfont);
        panel.add(LBookPhoto);
    
          BookUpload =new JButton("click Upload");
          BookUpload.setBounds(Sizex+190, 430, 130, 190);
          panel.add(BookUpload);

        Register_Now = new JButton("Update Now");
        Register_Now.setBounds(800, 680, 200, 50);
        Register_Now.setFont(new Font("arial", Font.BOLD, 25));
        panel.add(Register_Now);

        BookDelete = new JButton("Delete Now");
        BookDelete.setBounds(500, 680, 200, 50);
        BookDelete.setFont(new Font("arial", Font.BOLD, 25));
        panel.add(BookDelete);

        Reset = new JButton("GetDetails");
        Reset.setBounds(Sizex+400, 70, 120, 40);
        Reset.setFont(Depo.fil);
        panel.add(Reset);

                          TBookName.setEditable(false);
                          TBookpubli.setEditable(false);
                          TBookGen.setEditable(false);
                          TBookWriter.setEditable(false);

                          
                          
                          TBookNameCheck =new JCheckBox("  Edit for Check");
                          TBookNameCheck.setBounds(Sizex+300+250, 160, 145, 25);
                          panel.add(TBookNameCheck);
                          
                          TBookpubliCheck =new JCheckBox("  Edit for Check");
                          TBookpubliCheck.setBounds(Sizex+300+250, 255, 145, 25);
                          panel.add(TBookpubliCheck);
                          
                          TBookWriterCheck =new JCheckBox("  Edit for Check");
                          TBookWriterCheck.setBounds(Sizex+300+250, 315, 145, 25);
                          panel.add(TBookWriterCheck);

                          TBookGenCheck =new JCheckBox("  Edit for Check");
                          TBookGenCheck.setBounds(Sizex+300+250, 375, 145, 25);
                          panel.add(TBookGenCheck);
                          
                          // TBookPhotoCheck =new JCheckBox("  Edit for Check");
                          // TBookPhotoCheck.setBounds(Sizex+300+30, 505, 145, 25);
                          // panel.add(TBookPhotoCheck);
                          
                           TBookNameCheck.setVisible(false);
                          TBookpubliCheck.setVisible(false);
                          TBookGenCheck.setVisible(false);
                          TBookWriterCheck.setVisible(false);
                          
        BookUpload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Reset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Register_Now.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      revalidate();
      repaint();

                 TBookId.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {

                    char c = ke.getKeyChar();
                    if (!Character.isDigit(c)) {
                        ke.consume();
                        TBookId.setEditable(true);
                        res1.setVisible(false);
                        if (Character.isLetter(c)) {
                            TBookId.setEditable(true);
                            res1.setText("Input only Numeric (0-9)");
                            res1.setVisible(true);
                        }
                    } else {
                        res1.setVisible(false);
                        if (TBookId.getText().length() == 10) {
                            TBookId.setEditable(false);
                            res1.setText("Input Only 10 digit number");
                            res1.setVisible(true);
                        }
                    }
                }
            });

              TBookNameCheck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 TBookName.setEditable(true);
                 TBookNameCheck.setEnabled(false);
                }
                
              });

              TBookpubliCheck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 TBookpubli.setEditable(true);
                 TBookpubliCheck.setEnabled(false);
                }
                
              });

              TBookWriterCheck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 TBookWriter.setEditable(true);
                 TBookWriterCheck.setEnabled(false);
                }
                
              });

              TBookGenCheck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 TBookGen.setEditable(true);
                 TBookGenCheck.setEnabled(false);
                }
                
              });

              // TBookPhotoCheck.addActionListener(new ActionListener() {

              //   @Override
              //   public void actionPerformed(ActionEvent e) {
              //     BookUpload.setEnabled(false);
              //     TBookPhotoCheck.setEnabled(false);
              //   }
                
              // });

// --------------------------------------Action Event Button

              TBookNameCheck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 TBookName.setEditable(true);
                 TBookNameCheck.setEnabled(false);
                }
                
              });

              TBookNameCheck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                 TBookName.setEditable(true);
                 TBookNameCheck.setEnabled(false);
                }
                
              });

      TBookId.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
            ClearFied();

        }

        @Override
        public void focusLost(FocusEvent e) {
          // // TODO Auto-generated method stub
          // throw new UnsupportedOperationException("Unimplemented method 'focusLost'");
        }
        
      });


              Reset.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                  if(TBookId.getText().equals("")){
                    res1.setText("Required Book ID*");
                  }
                  else{
                  if(TBookId.getText().equals(BookIdMatch)){
                   JOptionPane.showMessageDialog(panel, "It's BookId Already Fetch");
                  }else{
                  TBookId.setEditable(false);
            pei = new Progress();
            pei.pro.setBounds(470, 100, 330, 25);
            add(pei.pro);
            repaint();
            validate();

            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Connection con2 = DriverManager.getConnection(url, DBuser, DBpass);
                       
                        
                        final java.lang.String Qry = "select * from Books_Infor where Book_Id=" + TBookId.getText();
                       
                        
                        Statement statement = con2.createStatement();
                        ResultSet rs = statement.executeQuery(Qry);
                        if (rs.next()) {
                          // Book_Id,Book_Name,Publications,Generation,Writer,Img
                          // java.lang.String  FindBookName = rs.getString("Book_Name");
                          // java.lang.String  FindBookPubli = rs.getString("Publications");
                          // java.lang.String  FindBookGen = rs.getString("Publications");
                          // java.lang.String  FindBookWriter = rs.getString("Publications");   

                          TBookName.setText(rs.getString("Book_Name"));
                          TBookpubli.setText(rs.getString("Publications"));
                          TBookGen.setText(rs.getString("Generation"));
                          TBookWriter.setText(rs.getString("Writer"));
                          BookIdMatch=TBookId.getText();
                          TBookNameCheck.setVisible(true);
                          TBookpubliCheck.setVisible(true);
                          TBookGenCheck.setVisible(true);
                          TBookWriterCheck.setVisible(true);
                          InputStream in = rs.getBinaryStream("Img");
                           bufImg = ImageIO.read(in);
                        ImageIcon inin = new ImageIcon(bufImg);
                        Image innk = inin.getImage().getScaledInstance(130, 190, Image.SCALE_SMOOTH);
                        ImageIcon set = new ImageIcon(innk);
                        BookUpload.setIcon(set);
                      
                        } else {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(panel, "Detail Not Found", "Message!..",
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
          }
                }
                
              });



              // ____--delte && Update Function


              // ---------update upload
            BookDelete.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                
              if(TBookName.getText().equals("")){
                JOptionPane.showMessageDialog(panel, "Please Find Book Detail");
              }else{
                 int Sel = JOptionPane.showConfirmDialog(panel, "Are You Sure Delete", "Message", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (Sel == 0) {
                  pei = new Progress();
            pei.pro.setBounds(470, 100, 330, 25);
            add(pei.pro);
            repaint();
            validate();

            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Connection con2 = DriverManager.getConnection(url, DBuser, DBpass);
                        
                        final java.lang.String Qry = "delete from Books_Infor where Book_Id=" + TBookId.getText();
                        PreparedStatement st= con2.prepareStatement(Qry);
                        st.executeUpdate();
                        // if (rs.next()) {
                          // Book_Id,Book_Name,Publications,Generation,Writer,Img
                          // java.lang.String  FindBookName = rs.getString("Book_Name");
                          // java.lang.String  FindBookPubli = rs.getString("Publications");
                          // java.lang.String  FindBookGen = rs.getString("Publications");
                          // java.lang.String  FindBookWriter = rs.getString("Publications");   
                          
                              try {
                                  pei.t.stop();
                                  pei.pro.setIndeterminate(false);
                                  pei.pro.setVisible(false);
                              } catch (Exception e) {
  
                              }
                          JOptionPane.showMessageDialog(panel, "1 Book Deleted");

                          ClearFied();

                          // TBookId.setEditable(true);
                          // TBookId.setText(null);
                          // TBookName.setText(null);
                          // TBookpubli.setText(null);
                          // TBookGen.setText(null);
                          // TBookWriter.setText(null);
                          // bufImg=null;
                          // BookUpload.setIcon(null);
                          // BookUpload.setText("click Upload");



                          //  res1.setText(null);
                          // } else {
                        //     JOptionPane.showMessageDialog(null, "Detail Not Found", "Message!..",
                        //             JOptionPane.INFORMATION_MESSAGE);
                        // }
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



              }
              
            });

              BookUpload.addActionListener(new ActionListener() {
              
                @Override
                public void actionPerformed(ActionEvent e) {
                
          fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only jpg", "jpg");
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Only png", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter1);

          int selelected = fileChooser.showOpenDialog(null);
                if (selelected == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    java.lang.String Filname = file.getAbsolutePath();
                   File BooKPhoto1 = new File(Filname);


                    if((BooKPhoto1.length()/1024) <30){
                      BooKPhoto=BooKPhoto1;
                      ImageIcon   idnk = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                     Image pro_file = idnk.getImage().getScaledInstance(130, 190, Image.SCALE_AREA_AVERAGING);
                     ImageIcon proFile = new ImageIcon(pro_file);
                     BookUpload.setIcon(proFile);
                      try {
                         inputStream12 = new FileInputStream(Filname);
                      } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                      }
                  
                  
                      
                    }
                    else{
                      JOptionPane.showMessageDialog(null, "Less Than 30 Kb");
                    }
        }else{
          JOptionPane.showMessageDialog(null, "Photo not choosen");
        }  

                
                }

              });

              Register_Now.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
              
                       if(TBookName.getText().equals("")){
                JOptionPane.showMessageDialog(panel, "Please Find Book Detail");
              }else{

                if(TBookGenCheck.isSelected()||TBookNameCheck.isSelected()||TBookWriterCheck.isSelected()||TBookpubliCheck.isSelected() || inputStream12!=null ){
                                          pei = new Progress();
                        pei.pro.setBounds(470, 100, 330, 25);
                        add(pei.pro);
                        repaint();
                        validate();
                         new Thread(new Runnable() {

                @Override
                public void run() {
                        try {
                    
                          // Book_Id,Book_Name,Publications,Generation,Writer,Img

                            Connection con3 = DriverManager.getConnection(url, DBuser, DBpass);
                            if(TBookGenCheck.isSelected()||TBookNameCheck.isSelected()||TBookWriterCheck.isSelected()||TBookpubliCheck.isSelected()){
                             
                              final String quwer = "update Books_Infor SET Book_Name='"
                                      +  TBookName.getText()
                                       + "', Publications='" + TBookpubli.getText() + "', Generation='" + TBookGen.getText() + "', Writer='"
                                      + TBookWriter.getText() + "' WHERE Book_Id=" + TBookId.getText();
                              Statement statemen = con3.createStatement();
                              statemen.executeUpdate(quwer);
                            }
                          if(inputStream12!=null){
                            final String qR = "update Books_Infor set img = ? where Book_Id ="+TBookId.getText();
            PreparedStatement pst = con3.prepareStatement(qR);
            pst.setBinaryStream(1, inputStream12 ,inputStream12.available());
            pst.executeUpdate();
                          }


                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(panel, "Update Suceesfully", "Message!..",
                                    JOptionPane.INFORMATION_MESSAGE);

                                ClearFied();

                          // inputStream12=null;
                          // res1.setText(null);
                          // TBookId.setEditable(true);
                          // TBookId.setText(null);
                          // TBookName.setText(null);
                          // TBookpubli.setText(null);
                          // TBookGen.setText(null);
                          // TBookWriter.setText(null);
                          // bufImg=null;
                          // BookUpload.setIcon(null);
                          // BookUpload.setText("click Upload");       
                         
                            con3.close();
              
                       
                  
                  } catch (SQLException e1) {
                            try {
                                pei.t.stop();
                                pei.pro.setIndeterminate(false);
                                pei.pro.setVisible(false);
                            } catch (Exception e) {

                            }
                            JOptionPane.showMessageDialog(panel, e1, "Message!..", JOptionPane.ERROR_MESSAGE);

                        } catch (IOException e1) {
                          // TODO Auto-generated catch block
                          e1.printStackTrace();
                        }
                    }
    
                }).start();

                  }else{
                    
                   JOptionPane.showMessageDialog(panel, "Not Change Any Attributes");

                
                }


              }
                }
                
              });
              


            }

            

            
        });


        }

    });

    



















  }
public void ClearFied(){

            BookIdMatch=null;
           inputStream12=null;

          TBookGenCheck.setEnabled(true);
          TBookGenCheck.setSelected(false);
          TBookGen.setEditable(false);

          TBookNameCheck.setEnabled(true);
          TBookNameCheck.setSelected(false);
          TBookName.setEditable(false);

          TBookWriterCheck.setEnabled(true);
          TBookWriterCheck.setSelected(false);
          TBookWriter.setEditable(false);

          TBookpubliCheck.setEnabled(true);
          TBookpubliCheck.setSelected(false);
          TBookpubli.setEditable(false);

                          TBookNameCheck.setVisible(false);
                          TBookpubliCheck.setVisible(false);
                          TBookGenCheck.setVisible(false);
                          TBookWriterCheck.setVisible(false);
          
                          res1.setText(null);
                          TBookId.setEditable(true);
                          TBookId.setText(null);
                          TBookName.setText(null);
                          TBookpubli.setText(null);
                          TBookGen.setText(null);
                          TBookWriter.setText(null);
                          bufImg=null;
                                      BookUpload.setIcon(null);
                                      BookUpload.setText("click Upload");

  
}




  public void paint(Graphics g) {
    super.paint(g); /// fixes the immediate problem.
    Graphics2D g2 = (Graphics2D) g;
    Line2D lin = new Line2D.Float(10, 170, 1920, 170);
    Line2D lin1 = new Line2D.Float(300, 50, 300, 170);
    Line2D lin2 = new Line2D.Float(10, 50, 1920, 50);
    Line2D lin6 = new Line2D.Float(10, 965, 1920, 965);
    // Line2D lin3 = new Line2D.Float(950, 50, 950, 170);
    g2.draw(lin);
    g2.draw(lin1);
    g2.draw(lin2);
    
    g2.draw(lin6);
  }

  // public void re(Graphics sh){
  //   super.add
  // }
}