package com.booklibrary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Line2D;
import java.sql.Connection;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

public class Students_information extends JPanel {

  public Students_information() {
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight());
    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth());
    setBounds(550, 0, x, y);
    setBackground(Color.decode("#C8BFE7"));
    setLayout(null);
    initFrame(new Fayat());
    add(new Fayat());

  }

  private void initFrame(JInternalFrame jInternalFrame) {
  }

}

class Fayat extends JInternalFrame {
  private JDateChooser startcal, endcal;
  private JButton GetDetails, Details;
  private JLabel semlabel, stlabel, endlabel, fillabel, bookisues, BookLabelId, BookLabelName, DoMore, doLess, OR,
      restrictBookName, rsetBookId;
  private JComboBox<String> SEM, libray;
  private JTextField TextBookId, TextBookName;
  private JRadioButton OrderDate, OrderBook;
  private JPanel TablejPanel;
  JTable j;
  Font lfont;
  final String url = "jdbc:mysql://bly1cdzloeqy4ve6m8ni-mysql.services.clever-cloud.com:3306/bly1cdzloeqy4ve6m8ni";
  final String DBuser = "usxqljshik8op7dd";
  final String DBpass = "ViDjCizs55WoYAMXOmeW";
  Progress pei;

  public Fayat() {
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

    
    lfont = new Font("Georgia", Font.ROMAN_BASELINE, 20);

    TablejPanel = new JPanel();
    TablejPanel.setBounds(5, 140, 1350, 775);
    add(TablejPanel);
    TablejPanel.setLayout(new BorderLayout());

    fillabel = new JLabel("Filters:");
    fillabel.setBounds(50, 15, 200, 40);
    fillabel.setFont(lfont);
    add(fillabel);

    OrderDate = new JRadioButton("ByDate");
    OrderDate.setBounds(40, 50, 100, 40);
    OrderDate.setFont(lfont);
    add(OrderDate);

    OrderBook = new JRadioButton("ByBooks");
    OrderBook.setBounds(140, 50, 120, 40);
    OrderBook.setFont(lfont);
    add(OrderBook);

    ButtonGroup gender = new ButtonGroup();
    gender.add(OrderBook);
    gender.add(OrderDate);
    OrderDate.setSelected(true);

    semlabel = new JLabel("Semster:");
    semlabel.setBounds(300, 15, 200, 40);
    semlabel.setFont(lfont);
    add(semlabel);
    String Sem[] = { "All", "SEM_1", "SEM_2", "SEM_3", "SEM_4", "SEM_5", "SEM_6" };
    SEM = new JComboBox<>(Sem);
    SEM.setBounds(300, 50, 110, 40);
    SEM.setFont(new Font("italic", Font.PLAIN, 18));
    add(SEM);

    stlabel = new JLabel("Start Date:");
    stlabel.setBounds(450, 15, 150, 40);
    stlabel.setFont(lfont);
    add(stlabel);
    startcal = new JDateChooser();
    startcal.setBounds(450, 50, 150, 40);
    add(startcal);

    endlabel = new JLabel("End Date:");
    endlabel.setBounds(640, 15, 200, 40);
    endlabel.setFont(lfont);
    add(endlabel);
    endcal = new JDateChooser();
    endcal.setBounds(640, 50, 150, 40);
    add(endcal);

    bookisues = new JLabel("Books:");
    bookisues.setBounds(830, 15, 150, 40);
    bookisues.setFont(lfont);
    add(bookisues);
    String lib[] = { "Issues", "Deposite", "Both" };
    libray = new JComboBox<>(lib);
    libray.setBounds(830, 50, 150, 40);
    libray.setFont(new Font("italic", Font.PLAIN, 18));
    add(libray);

    GetDetails = new JButton("GetDetails");
    GetDetails.setBounds(1050, 50, 120, 40);
    add(GetDetails);

    startcal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    endcal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    OrderDate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    OrderBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    SEM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    libray.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    GetDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    OrderDate.setEnabled(false);



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
          TablejPanel.removeAll();
          String Rol;
          String S_nmae;
          String Semm;
          String isue;
          String BookNAME;
          String BookId;
          String Issuer;
          String[] t;
          String IssuDate;
          DefaultTableModel Model = new DefaultTableModel();
          String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
              "Issuer Name" };
          Model.setColumnIdentifiers(columnNames);
          j = new JTable();
          j.setModel(Model);

          DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
          cellRenderer.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
          }
          JTableHeader head = j.getTableHeader();
          // head.setDefaultRenderer(cellRenderer);
          head.setFont(new Font("Segoe UI", Font.BOLD, 22));
          head.setOpaque(false);
          head.setBorder(BorderFactory.createBevelBorder(10));
          head.setForeground(Color.decode("#004766"));
          j.setRowHeight(40);
          j.setRowMargin(5);
          j.setFont(new Font("Segoe UI", Font.BOLD, 20));
          j.setForeground(Color.BLACK);
          j.updateUI();

          try {
            // Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
            Statement statement = conn.createStatement();
            final String Quer = "select * from BOOKS where checked=1 order by issues desc";
            ResultSet rs = statement.executeQuery(Quer);
            // int i=0;
            while (rs.next()) {
              Rol = Integer.toString(rs.getInt("ROLL"));
              S_nmae = rs.getString("STUDENT_NAME");
              Semm = rs.getString("SEM");
              isue = rs.getString("ISSUES");
              BookNAME = rs.getString("BOOK_NAME");
              BookId = rs.getString("Book_NO");
              Issuer = rs.getString("ISSUER_NAME");
              t = isue.split(" ");
              IssuDate = t[0];
              Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });

            }
            TablejPanel.add(new JScrollPane(j));
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

    setVisible(true);

    OrderDate.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
       
        remove(BookLabelId);
        remove(BookLabelName);
        remove(TextBookId);
        remove(TextBookName);
        remove(OR);
        remove(DoMore);
        remove(doLess);
        remove(Details);
        remove(restrictBookName);
        remove(rsetBookId);

        semlabel = new JLabel("Semster:");
        semlabel.setBounds(300, 15, 200, 40);
        semlabel.setFont(lfont);
        add(semlabel);
        String Sem[] = { "All", "SEM_1", "SEM_2", "SEM_3", "SEM_4", "SEM_5", "SEM_6" };
        SEM = new JComboBox<>(Sem);
        SEM.setBounds(300, 50, 110, 40);
        SEM.setFont(new Font("italic", Font.PLAIN, 18));
        add(SEM);

        stlabel = new JLabel("Start Date:");
        stlabel.setBounds(450, 15, 150, 40);
        stlabel.setFont(lfont);
        add(stlabel);
        startcal = new JDateChooser();
        startcal.setBounds(450, 50, 150, 40);
        add(startcal);

        endlabel = new JLabel("End Date:");
        endlabel.setBounds(640, 15, 200, 40);
        endlabel.setFont(lfont);
        add(endlabel);
        endcal = new JDateChooser();
        endcal.setBounds(640, 50, 150, 40);
        add(endcal);

        bookisues = new JLabel("Books:");
        bookisues.setBounds(830, 15, 150, 40);
        bookisues.setFont(lfont);
        add(bookisues);
        String lib[] = { "Issues", "Deposite", "Both" };
        libray = new JComboBox<>(lib);
        libray.setBounds(830, 50, 150, 40);
        libray.setFont(new Font("italic", Font.PLAIN, 18));
        add(libray);

        GetDetails = new JButton("GetDetails");
        GetDetails.setBounds(1050, 50, 120, 40);
        add(GetDetails);

        revalidate();
        repaint();
        OrderDate.setEnabled(false);
        OrderBook.setEnabled(true);

        startcal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        endcal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SEM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        libray.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GetDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GetDetails.addActionListener(new ActionListener() {
          // ----------------------------
          @Override
          public void actionPerformed(ActionEvent e) {

            pei = new Progress();
            pei.pro.setBounds(590, 100, 330, 25);
            add(pei.pro);
            repaint();
            validate();
            new Thread(new Runnable() {

              @Override
              public void run() {
                if ((SEM.getSelectedItem().equals("All")) && (libray.getSelectedItem().equals("Issues"))) {

                  TablejPanel.removeAll();
                  String Rol;
                  String S_nmae;
                  String Semm;
                  String isue;
                  String BookNAME;
                  String BookId;
                  String Issuer;
                  String[] t;
                  String IssuDate;
                  DefaultTableModel Model = new DefaultTableModel();
                  String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                      "Issuer Name" };
                  Model.setColumnIdentifiers(columnNames);
                  j = new JTable();
                  j.setModel(Model);

                  DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                  cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                  for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                    j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
                  }
                  JTableHeader head = j.getTableHeader();
                  // head.setDefaultRenderer(cellRenderer);
                  head.setFont(new Font("Segoe UI", Font.BOLD, 22));
                  head.setOpaque(false);
                  head.setBorder(BorderFactory.createBevelBorder(10));
                  head.setForeground(Color.decode("#004766"));
                  j.setRowHeight(40);
                  j.setRowMargin(5);
                  j.setFont(new Font("Segoe UI", Font.BOLD, 20));
                  j.setForeground(Color.BLACK);
                  j.updateUI();

                  try {
                    // Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                    Statement statement = conn.createStatement();
                    final String Quer = "select * from BOOKS where checked=1";
                    ResultSet rs = statement.executeQuery(Quer);
                    // int i=0;
                    while (rs.next()) {
                      Rol = Integer.toString(rs.getInt("ROLL"));
                      S_nmae = rs.getString("STUDENT_NAME");
                      Semm = rs.getString("SEM");
                      isue = rs.getString("ISSUES");
                      BookNAME = rs.getString("BOOK_NAME");
                      BookId = rs.getString("Book_NO");
                      Issuer = rs.getString("ISSUER_NAME");
                      t = isue.split(" ");
                      IssuDate = t[0];
                      Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });

                    }
                    TablejPanel.add(new JScrollPane(j));
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

                else if ((SEM.getSelectedItem().equals("All")) && (libray.getSelectedItem().equals("Deposite"))) {
                  TablejPanel.removeAll();
                  String Rol;
                  String S_nmae;
                  String Semm;
                  String isue;
                  String BookNAME;
                  String BookId;
                  String Issuer;
                  String[] t;
                  String IssuDate;
                  DefaultTableModel Model = new DefaultTableModel();
                  String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id",
                      "Deposite Date",
                      "Depositer Name" };
                  Model.setColumnIdentifiers(columnNames);
                  j = new JTable();
                  j.setModel(Model);
                  DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                  cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                  for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                    j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
                  }
                  JTableHeader head = j.getTableHeader();
                  // head.setDefaultRenderer(cellRenderer);
                  head.setFont(new Font("Segoe UI", Font.BOLD, 22));
                  head.setOpaque(false);
                  head.setBorder(BorderFactory.createBevelBorder(10));
                  head.setForeground(Color.decode("#004766"));
                  j.setRowHeight(40);
                  j.setRowMargin(5);
                  j.setFont(new Font("Segoe UI", Font.BOLD, 20));
                  j.setForeground(Color.BLACK);
                  j.updateUI();

                  try {
                    // Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                    Statement statement = conn.createStatement();
                    final String Quer = "select * from BOOKS where checked=0";
                    ResultSet rs = statement.executeQuery(Quer);
                    // int i=0;
                    while (rs.next()) {
                      Rol = Integer.toString(rs.getInt("ROLL"));
                      S_nmae = rs.getString("STUDENT_NAME");
                      Semm = rs.getString("SEM");
                      isue = rs.getString("DEPOSITE");
                      BookNAME = rs.getString("BOOK_NAME");
                      BookId = rs.getString("Book_NO");
                      Issuer = rs.getString("DEPOSITE_NAME");
                      t = isue.split(" ");
                      IssuDate = t[0];
                      Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });
                    }
                    TablejPanel.add(new JScrollPane(j));
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
                } else if ((SEM.getSelectedItem().equals("All")) && (libray.getSelectedItem().equals("Both"))) {

                  TablejPanel.removeAll();
                  String Rol;
                  String S_nmae;
                  String Semm;
                  String isue;
                  String DepoDate;
                  String BookNAME;
                  String BookId;
                  String Issuer;
                  String[] t;
                  // String[] u;
                  String IssuDate;
                  // String DeposDate;
                  String dEpo;
                  DefaultTableModel Model = new DefaultTableModel();
                  String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                      "Deposite Date", "Issuer Name", "Depositer Name" };
                  Model.setColumnIdentifiers(columnNames);
                  j = new JTable();
                  j.setModel(Model);
                  DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                  cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                  for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                    j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
                  }
                  JTableHeader head = j.getTableHeader();
                  // head.setDefaultRenderer(cellRenderer);
                  head.setFont(new Font("Segoe UI", Font.BOLD, 22));
                  head.setOpaque(false);
                  head.setBorder(BorderFactory.createBevelBorder(10));
                  head.setForeground(Color.decode("#004766"));
                  j.setRowHeight(40);
                  j.setRowMargin(5);
                  j.setFont(new Font("Segoe UI", Font.BOLD, 20));
                  j.setForeground(Color.BLACK);
                  j.updateUI();

                  try {
                    // Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                    Statement statement = conn.createStatement();
                    final String Quer = "select * from BOOKS";
                    ResultSet rs = statement.executeQuery(Quer);
                    // int i=0;
                    while (rs.next()) {
                      Rol = Integer.toString(rs.getInt("ROLL"));
                      S_nmae = rs.getString("STUDENT_NAME");
                      Semm = rs.getString("SEM");
                      isue = rs.getString("ISSUES");
                      DepoDate = rs.getString("DEPOSITE");
                      BookNAME = rs.getString("BOOK_NAME");
                      BookId = rs.getString("Book_NO");
                      Issuer = rs.getString("ISSUER_NAME");
                      dEpo = rs.getString("Deposite_NAME");

                      t = isue.split(" ");
                      IssuDate = t[0];
                      // System.out.println(DepoDate);
                      // if(DepoDate.equals("")){
                      // DeposDate="";
                      // }else{
                      // u=DepoDate.split(" ");
                      // DeposDate=u[0];

                      // }
                      Model.addRow(
                          new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, DepoDate, Issuer, dEpo });

                    }

                    TablejPanel.add(new JScrollPane(j));
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

                // ------------------------------------------------------------------------------------------------------------
                else if ((SEM.getSelectedItem().equals("SEM_1") || SEM.getSelectedItem().equals("SEM_2")
                    || SEM.getSelectedItem().equals("SEM_3") ||
                    SEM.getSelectedItem().equals("SEM_4") || SEM.getSelectedItem().equals("SEM_5")
                    || SEM.getSelectedItem().equals("SEM_6")) && libray.getSelectedItem().equals("Issues")) {

                  TablejPanel.removeAll();
                  String Rol;
                  String S_nmae;
                  String Semm;
                  String isue;
                  String BookNAME;
                  String BookId;
                  String Issuer;
                  String[] t;
                  String IssuDate;
                  DefaultTableModel Model = new DefaultTableModel();
                  String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                      "Issuer Name" };
                  Model.setColumnIdentifiers(columnNames);
                  j = new JTable();
                  j.setModel(Model);
                  DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                  cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                  for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                    j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
                  }
                  JTableHeader head = j.getTableHeader();
                  // head.setDefaultRenderer(cellRenderer);
                  head.setFont(new Font("Segoe UI", Font.BOLD, 22));
                  head.setOpaque(false);
                  head.setBorder(BorderFactory.createBevelBorder(10));
                  head.setForeground(Color.decode("#004766"));
                  j.setRowHeight(40);
                  j.setRowMargin(5);
                  j.setFont(new Font("Segoe UI", Font.BOLD, 20));
                  j.setForeground(Color.BLACK);
                  j.updateUI();

                  try {
                    // Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                    Statement statement = conn.createStatement();
                    final String Quer = "select * from BOOKS where checked=1 and SEM='" + SEM.getSelectedItem() + "'";
                    ResultSet rs = statement.executeQuery(Quer);
                    // int i=0;
                    while (rs.next()) {
                      Rol = Integer.toString(rs.getInt("ROLL"));
                      S_nmae = rs.getString("STUDENT_NAME");
                      Semm = rs.getString("SEM");
                      isue = rs.getString("ISSUES");
                      BookNAME = rs.getString("BOOK_NAME");
                      BookId = rs.getString("Book_NO");
                      Issuer = rs.getString("ISSUER_NAME");
                      t = isue.split(" ");
                      IssuDate = t[0];
                      Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });

                    }
                    TablejPanel.add(new JScrollPane(j));
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

                } else if ((SEM.getSelectedItem().equals("SEM_1") || SEM.getSelectedItem().equals("SEM_2")
                    || SEM.getSelectedItem().equals("SEM_3") ||
                    SEM.getSelectedItem().equals("SEM_4") || SEM.getSelectedItem().equals("SEM_5")
                    || SEM.getSelectedItem().equals("SEM_6")) && libray.getSelectedItem().equals("Deposite")) {

                  TablejPanel.removeAll();
                  String Rol;
                  String S_nmae;
                  String Semm;
                  String isue;
                  String BookNAME;
                  String BookId;
                  String Issuer;
                  String[] t;
                  String IssuDate;
                  DefaultTableModel Model = new DefaultTableModel();
                  String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id",
                      "Deposite Date",
                      "Dpositer Name" };
                  Model.setColumnIdentifiers(columnNames);
                  j = new JTable();
                  j.setModel(Model);
                  DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                  cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                  for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                    j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
                  }
                  JTableHeader head = j.getTableHeader();
                  // head.setDefaultRenderer(cellRenderer);
                  head.setFont(new Font("Segoe UI", Font.BOLD, 22));
                  head.setOpaque(false);
                  head.setBorder(BorderFactory.createBevelBorder(10));
                  head.setForeground(Color.decode("#004766"));
                  j.setRowHeight(40);
                  j.setRowMargin(5);
                  j.setFont(new Font("Segoe UI", Font.BOLD, 20));
                  j.setForeground(Color.BLACK);
                  j.updateUI();

                  try {
                    // Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                    Statement statement = conn.createStatement();
                    final String Quer = "select * from BOOKS where checked=0 and SEM='" + SEM.getSelectedItem() + "'";
                    ResultSet rs = statement.executeQuery(Quer);
                    // int i=0;
                    while (rs.next()) {
                      Rol = Integer.toString(rs.getInt("ROLL"));
                      S_nmae = rs.getString("STUDENT_NAME");
                      Semm = rs.getString("SEM");
                      isue = rs.getString("DEPOSITE");
                      BookNAME = rs.getString("BOOK_NAME");
                      BookId = rs.getString("Book_NO");
                      Issuer = rs.getString("DEPOSITE_NAME");
                      t = isue.split(" ");
                      IssuDate = t[0];
                      Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });
                    }
                    TablejPanel.add(new JScrollPane(j));
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

                } else if ((SEM.getSelectedItem().equals("SEM_1") || SEM.getSelectedItem().equals("SEM_2")
                    || SEM.getSelectedItem().equals("SEM_3") ||
                    SEM.getSelectedItem().equals("SEM_4") || SEM.getSelectedItem().equals("SEM_5")
                    || SEM.getSelectedItem().equals("SEM_6")) && libray.getSelectedItem().equals("Both")) {

                  TablejPanel.removeAll();
                  String Rol;
                  String S_nmae;
                  String Semm;
                  String isue;
                  String DepoDate;
                  String BookNAME;
                  String BookId;
                  String Issuer;
                  String[] t;
                  // String[] u;
                  String IssuDate;
                  // String DeposDate;
                  String dEpo;
                  DefaultTableModel Model = new DefaultTableModel();
                  String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                      "Deposite Date", "Issuer Name", "Depositer Name" };
                  Model.setColumnIdentifiers(columnNames);
                  j = new JTable();
                  j.setModel(Model);
                  DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                  cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                  for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                    j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
                  }
                  JTableHeader head = j.getTableHeader();
                  // head.setDefaultRenderer(cellRenderer);
                  head.setFont(new Font("Segoe UI", Font.BOLD, 22));
                  head.setOpaque(false);
                  head.setBorder(BorderFactory.createBevelBorder(10));
                  head.setForeground(Color.decode("#004766"));
                  j.setRowHeight(40);
                  j.setRowMargin(5);
                  j.setFont(new Font("Segoe UI", Font.BOLD, 20));
                  j.setForeground(Color.BLACK);
                  j.updateUI();

                  try {
                    // Class.forName("oracle.jdbc.OracleDriver");
                    Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                    Statement statement = conn.createStatement();
                    final String Quer = "select * from BOOKS where SEM='" + SEM.getSelectedItem() + "'";
                    ResultSet rs = statement.executeQuery(Quer);
                    // int i=0;
                    while (rs.next()) {
                      Rol = Integer.toString(rs.getInt("ROLL"));
                      S_nmae = rs.getString("STUDENT_NAME");
                      Semm = rs.getString("SEM");
                      isue = rs.getString("ISSUES");
                      DepoDate = rs.getString("DEPOSITE");
                      BookNAME = rs.getString("BOOK_NAME");
                      BookId = rs.getString("Book_NO");
                      Issuer = rs.getString("ISSUER_NAME");
                      dEpo = rs.getString("Deposite_NAME");

                      t = isue.split(" ");
                      IssuDate = t[0];
                      // System.out.println(DepoDate);
                      // if(DepoDate.equals("")){
                      // DeposDate="";
                      // }else{
                      // u=DepoDate.split(" ");
                      // DeposDate=u[0];

                      // }
                      Model.addRow(
                          new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, DepoDate, Issuer, dEpo });

                    }
                    TablejPanel.add(new JScrollPane(j));
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
                // \\\\\---------
              }

            }).start();
            // ----

          }
        });

      }

    });

    OrderBook.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        remove(SEM);
        remove(semlabel);
        remove(stlabel);
        remove(endlabel);
        remove(startcal);
        remove(endcal);
        remove(bookisues);
        remove(libray);
        remove(GetDetails);
        // if(OrderBook.isSelected()==OrderBook.isSelected()){
        // System.out.println("hsusdjsk");
        // }
        BookLabelId = new JLabel("Book Id : ");
        BookLabelId.setBounds(350, 15, 200, 40);
        BookLabelId.setFont(lfont);
        add(BookLabelId);
        TextBookId = new JTextField();
        TextBookId.setBounds(350, 50, 200, 40);
        add(TextBookId);

        DoMore = new JLabel("(Do More Use)");
        DoMore.setBounds(450, 18, 200, 40);
        DoMore.setFont(new Font("Arial", Font.ITALIC, 15));
        DoMore.setForeground(Color.decode("#2A3BFA"));
        add(DoMore);

        OR = new JLabel("(OR)");
        OR.setBounds(590, 50, 50, 40);
        add(OR);

        doLess = new JLabel("(Do Less Use)");
        doLess.setBounds(880, 18, 200, 40);
        doLess.setFont(new Font("Arial", Font.ITALIC, 15));
        doLess.setForeground(Color.decode("#2A3BFA"));
        add(doLess);

        BookLabelName = new JLabel("Book Name:");
        BookLabelName.setBounds(650, 15, 200, 40);
        BookLabelName.setFont(lfont);
        add(BookLabelName);
        TextBookName = new JTextField();
        TextBookName.setBounds(650, 50, 330, 40);
        add(TextBookName);

        Details = new JButton("GetDetails");
        Details.setBounds(1050, 50, 120, 40);
        add(Details);
        Details.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rsetBookId = new JLabel();
        rsetBookId.setFont(new Font("arial", Font.BOLD, 15));
        rsetBookId.setForeground(Color.RED);
        add(rsetBookId);

        restrictBookName = new JLabel();
        restrictBookName.setFont(new Font("arial", Font.BOLD, 15));
        restrictBookName.setForeground(Color.RED);
        add(restrictBookName);

        revalidate();
        repaint();
        OrderBook.setEnabled(false);
        OrderDate.setEnabled(true);

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
            // TODO Auto-generated method stub
          }

        });
        // -----------------------------------------------------------------------------------------------------------------------------
        Details.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
           
            if (TextBookId.getText().equals("") && TextBookName.getText().equals("")) {
              rsetBookId.setText("Book Id Required*");
              rsetBookId.setBounds(350, 90, 300, 20);
              rsetBookId.setVisible(true);

              restrictBookName.setText("Book Name Required*");
              restrictBookName.setBounds(650, 90, 300, 20);
              restrictBookName.setVisible(true);
            } else {

              TablejPanel.removeAll();
              String[][] data = {
                  { "Kundan Kumar books", "4031", "CSE", "" },

              };

              // Column Names
              String[] columnNames = { "Name", "Roll Number", "Department", "hjnjkkx" };

              DefaultTableModel fDefaultTableModel = new DefaultTableModel(data, columnNames);
              j = new JTable(fDefaultTableModel);
              TablejPanel.add(new JScrollPane(j));
              revalidate();
              repaint();
            }

          }
        });
      }

    });
    // ---------------------------------------------------------
    GetDetails.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        pei = new Progress();
        pei.pro.setBounds(590, 100, 330, 25);
        add(pei.pro);
        repaint();
        validate();
        new Thread(new Runnable() {

          @Override
          public void run() {
            if ((SEM.getSelectedItem().equals("All")) && (libray.getSelectedItem().equals("Issues"))) {

              TablejPanel.removeAll();
              String Rol;
              String S_nmae;
              String Semm;
              String isue;
              String BookNAME;
              String BookId;
              String Issuer;
              String[] t;
              String IssuDate;
              DefaultTableModel Model = new DefaultTableModel();
              String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                  "Issuer Name" };
              Model.setColumnIdentifiers(columnNames);
              j = new JTable();
              j.setModel(Model);

              DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
              cellRenderer.setHorizontalAlignment(JLabel.CENTER);
              for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
              }
              JTableHeader head = j.getTableHeader();
              // head.setDefaultRenderer(cellRenderer);
              head.setFont(new Font("Segoe UI", Font.BOLD, 22));
              head.setOpaque(false);
              head.setBorder(BorderFactory.createBevelBorder(10));
              head.setForeground(Color.decode("#004766"));
              j.setRowHeight(40);
              j.setRowMargin(5);
              j.setFont(new Font("Segoe UI", Font.BOLD, 20));
              j.setForeground(Color.BLACK);
              j.updateUI();

              try {
                // Class.forName("oracle.jdbc.OracleDriver");
                Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                Statement statement = conn.createStatement();
                final String Quer = "select * from BOOKS where checked=1";
                ResultSet rs = statement.executeQuery(Quer);
                // int i=0;
                while (rs.next()) {
                  Rol = Integer.toString(rs.getInt("ROLL"));
                  S_nmae = rs.getString("STUDENT_NAME");
                  Semm = rs.getString("SEM");
                  isue = rs.getString("ISSUES");
                  BookNAME = rs.getString("BOOK_NAME");
                  BookId = rs.getString("Book_NO");
                  Issuer = rs.getString("ISSUER_NAME");
                  t = isue.split(" ");
                  IssuDate = t[0];
                  Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });

                }
                TablejPanel.add(new JScrollPane(j));
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

            else if ((SEM.getSelectedItem().equals("All")) && (libray.getSelectedItem().equals("Deposite"))) {
              TablejPanel.removeAll();
              String Rol;
              String S_nmae;
              String Semm;
              String isue;
              String BookNAME;
              String BookId;
              String Issuer;
              String[] t;
              String IssuDate;
              DefaultTableModel Model = new DefaultTableModel();
              String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Deposite Date",
                  "Depositer Name" };
              Model.setColumnIdentifiers(columnNames);
              j = new JTable();
              j.setModel(Model);
              DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
              cellRenderer.setHorizontalAlignment(JLabel.CENTER);
              for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
              }
              JTableHeader head = j.getTableHeader();
              // head.setDefaultRenderer(cellRenderer);
              head.setFont(new Font("Segoe UI", Font.BOLD, 22));
              head.setOpaque(false);
              head.setBorder(BorderFactory.createBevelBorder(10));
              head.setForeground(Color.decode("#004766"));
              j.setRowHeight(40);
              j.setRowMargin(5);
              j.setFont(new Font("Segoe UI", Font.BOLD, 20));
              j.setForeground(Color.BLACK);
              j.updateUI();

              try {
                // Class.forName("oracle.jdbc.OracleDriver");
                Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                Statement statement = conn.createStatement();
                final String Quer = "select * from BOOKS where checked=0";
                ResultSet rs = statement.executeQuery(Quer);
                // int i=0;
                while (rs.next()) {
                  Rol = Integer.toString(rs.getInt("ROLL"));
                  S_nmae = rs.getString("STUDENT_NAME");
                  Semm = rs.getString("SEM");
                  isue = rs.getString("DEPOSITE");
                  BookNAME = rs.getString("BOOK_NAME");
                  BookId = rs.getString("Book_NO");
                  Issuer = rs.getString("DEPOSITE_NAME");
                  t = isue.split(" ");
                  IssuDate = t[0];
                  Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });
                }
                TablejPanel.add(new JScrollPane(j));
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
            } else if ((SEM.getSelectedItem().equals("All")) && (libray.getSelectedItem().equals("Both"))) {

              TablejPanel.removeAll();
              String Rol;
              String S_nmae;
              String Semm;
              String isue;
              String DepoDate;
              String BookNAME;
              String BookId;
              String Issuer;
              String[] t;
              // String[] u;
              String IssuDate;
              // String DeposDate;
              String dEpo;
              DefaultTableModel Model = new DefaultTableModel();
              String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                  "Deposite Date", "Issuer Name", "Depositer Name" };
              Model.setColumnIdentifiers(columnNames);
              j = new JTable();
              j.setModel(Model);
              DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
              cellRenderer.setHorizontalAlignment(JLabel.CENTER);
              for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
              }
              JTableHeader head = j.getTableHeader();
              // head.setDefaultRenderer(cellRenderer);
              head.setFont(new Font("Segoe UI", Font.BOLD, 22));
              head.setOpaque(false);
              head.setBorder(BorderFactory.createBevelBorder(10));
              head.setForeground(Color.decode("#004766"));
              j.setRowHeight(40);
              j.setRowMargin(5);
              j.setFont(new Font("Segoe UI", Font.BOLD, 20));
              j.setForeground(Color.BLACK);
              j.updateUI();

              try {
                // Class.forName("oracle.jdbc.OracleDriver");
                Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                Statement statement = conn.createStatement();
                final String Quer = "select * from BOOKS";
                ResultSet rs = statement.executeQuery(Quer);
                // int i=0;
                while (rs.next()) {
                  Rol = Integer.toString(rs.getInt("ROLL"));
                  S_nmae = rs.getString("STUDENT_NAME");
                  Semm = rs.getString("SEM");
                  isue = rs.getString("ISSUES");
                  DepoDate = rs.getString("DEPOSITE");
                  BookNAME = rs.getString("BOOK_NAME");
                  BookId = rs.getString("Book_NO");
                  Issuer = rs.getString("ISSUER_NAME");
                  dEpo = rs.getString("Deposite_NAME");

                  t = isue.split(" ");
                  IssuDate = t[0];
                  // System.out.println(DepoDate);
                  // if(DepoDate.equals("")){
                  // DeposDate="";
                  // }else{
                  // u=DepoDate.split(" ");
                  // DeposDate=u[0];

                  // }
                  Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, DepoDate, Issuer, dEpo });

                }

                TablejPanel.add(new JScrollPane(j));
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

            // ------------------------------------------------------------------------------------------------------------
            else if ((SEM.getSelectedItem().equals("SEM_1") || SEM.getSelectedItem().equals("SEM_2")
                || SEM.getSelectedItem().equals("SEM_3") ||
                SEM.getSelectedItem().equals("SEM_4") || SEM.getSelectedItem().equals("SEM_5")
                || SEM.getSelectedItem().equals("SEM_6")) && libray.getSelectedItem().equals("Issues")) {

              TablejPanel.removeAll();
              String Rol;
              String S_nmae;
              String Semm;
              String isue;
              String BookNAME;
              String BookId;
              String Issuer;
              String[] t;
              String IssuDate;
              DefaultTableModel Model = new DefaultTableModel();
              String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                  "Issuer Name" };
              Model.setColumnIdentifiers(columnNames);
              j = new JTable();
              j.setModel(Model);
              DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
              cellRenderer.setHorizontalAlignment(JLabel.CENTER);
              for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
              }
              JTableHeader head = j.getTableHeader();
              // head.setDefaultRenderer(cellRenderer);
              head.setFont(new Font("Segoe UI", Font.BOLD, 22));
              head.setOpaque(false);
              head.setBorder(BorderFactory.createBevelBorder(10));
              head.setForeground(Color.decode("#004766"));
              j.setRowHeight(40);
              j.setRowMargin(5);
              j.setFont(new Font("Segoe UI", Font.BOLD, 20));
              j.setForeground(Color.BLACK);
              j.updateUI();

              try {
                // Class.forName("oracle.jdbc.OracleDriver");
                Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                Statement statement = conn.createStatement();
                final String Quer = "select * from BOOKS where checked=1 and SEM='" + SEM.getSelectedItem() + "'";
                ResultSet rs = statement.executeQuery(Quer);
                // int i=0;
                while (rs.next()) {
                  Rol = Integer.toString(rs.getInt("ROLL"));
                  S_nmae = rs.getString("STUDENT_NAME");
                  Semm = rs.getString("SEM");
                  isue = rs.getString("ISSUES");
                  BookNAME = rs.getString("BOOK_NAME");
                  BookId = rs.getString("Book_NO");
                  Issuer = rs.getString("ISSUER_NAME");
                  t = isue.split(" ");
                  IssuDate = t[0];
                  Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });

                }
                TablejPanel.add(new JScrollPane(j));
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

            } else if ((SEM.getSelectedItem().equals("SEM_1") || SEM.getSelectedItem().equals("SEM_2")
                || SEM.getSelectedItem().equals("SEM_3") ||
                SEM.getSelectedItem().equals("SEM_4") || SEM.getSelectedItem().equals("SEM_5")
                || SEM.getSelectedItem().equals("SEM_6")) && libray.getSelectedItem().equals("Deposite")) {

              TablejPanel.removeAll();
              String Rol;
              String S_nmae;
              String Semm;
              String isue;
              String BookNAME;
              String BookId;
              String Issuer;
              String[] t;
              String IssuDate;
              DefaultTableModel Model = new DefaultTableModel();
              String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Deposite Date",
                  "Dpositer Name" };
              Model.setColumnIdentifiers(columnNames);
              j = new JTable();
              j.setModel(Model);
              DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
              cellRenderer.setHorizontalAlignment(JLabel.CENTER);
              for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
              }
              JTableHeader head = j.getTableHeader();
              // head.setDefaultRenderer(cellRenderer);
              head.setFont(new Font("Segoe UI", Font.BOLD, 22));
              head.setOpaque(false);
              head.setBorder(BorderFactory.createBevelBorder(10));
              head.setForeground(Color.decode("#004766"));
              j.setRowHeight(40);
              j.setRowMargin(5);
              j.setFont(new Font("Segoe UI", Font.BOLD, 20));
              j.setForeground(Color.BLACK);
              j.updateUI();

              try {
                // Class.forName("oracle.jdbc.OracleDriver");
                Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                Statement statement = conn.createStatement();
                final String Quer = "select * from BOOKS where checked=0 and SEM='" + SEM.getSelectedItem() + "'";
                ResultSet rs = statement.executeQuery(Quer);
                // int i=0;
                while (rs.next()) {
                  Rol = Integer.toString(rs.getInt("ROLL"));
                  S_nmae = rs.getString("STUDENT_NAME");
                  Semm = rs.getString("SEM");
                  isue = rs.getString("DEPOSITE");
                  BookNAME = rs.getString("BOOK_NAME");
                  BookId = rs.getString("Book_NO");
                  Issuer = rs.getString("DEPOSITE_NAME");
                  t = isue.split(" ");
                  IssuDate = t[0];
                  Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, Issuer });
                }
                TablejPanel.add(new JScrollPane(j));
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

            } else if ((SEM.getSelectedItem().equals("SEM_1") || SEM.getSelectedItem().equals("SEM_2")
                || SEM.getSelectedItem().equals("SEM_3") ||
                SEM.getSelectedItem().equals("SEM_4") || SEM.getSelectedItem().equals("SEM_5")
                || SEM.getSelectedItem().equals("SEM_6")) && libray.getSelectedItem().equals("Both")) {

              TablejPanel.removeAll();
              String Rol;
              String S_nmae;
              String Semm;
              String isue;
              String DepoDate;
              String BookNAME;
              String BookId;
              String Issuer;
              String[] t;
              // String[] u;
              String IssuDate;
              // String DeposDate;
              String dEpo;
              DefaultTableModel Model = new DefaultTableModel();
              String[] columnNames = { "Roll No", "Student's Name", "Sem", "Books Name", "Books Id", "Issues Date",
                  "Deposite Date", "Issuer Name", "Depositer Name" };
              Model.setColumnIdentifiers(columnNames);
              j = new JTable();
              j.setModel(Model);
              DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
              cellRenderer.setHorizontalAlignment(JLabel.CENTER);
              for (int i = 0; i < j.getColumnModel().getColumnCount(); i++) {
                j.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);// onley one
              }
              JTableHeader head = j.getTableHeader();
              // head.setDefaultRenderer(cellRenderer);
              head.setFont(new Font("Segoe UI", Font.BOLD, 22));
              head.setOpaque(false);
              head.setBorder(BorderFactory.createBevelBorder(10));
              head.setForeground(Color.decode("#004766"));
              j.setRowHeight(40);
              j.setRowMargin(5);
              j.setFont(new Font("Segoe UI", Font.BOLD, 20));
              j.setForeground(Color.BLACK);
              j.updateUI();

              try {
                // Class.forName("oracle.jdbc.OracleDriver");
                Connection conn = DriverManager.getConnection(url, DBuser, DBpass);
                Statement statement = conn.createStatement();
                final String Quer = "select * from BOOKS where SEM='" + SEM.getSelectedItem() + "'";
                ResultSet rs = statement.executeQuery(Quer);
                // int i=0;
                while (rs.next()) {
                  Rol = Integer.toString(rs.getInt("ROLL"));
                  S_nmae = rs.getString("STUDENT_NAME");
                  Semm = rs.getString("SEM");
                  isue = rs.getString("ISSUES");
                  DepoDate = rs.getString("DEPOSITE");
                  BookNAME = rs.getString("BOOK_NAME");
                  BookId = rs.getString("Book_NO");
                  Issuer = rs.getString("ISSUER_NAME");
                  dEpo = rs.getString("Deposite_NAME");

                  t = isue.split(" ");
                  IssuDate = t[0];
                  // System.out.println(DepoDate);
                  // if(DepoDate.equals("")){
                  // DeposDate="";
                  // }else{
                  // u=DepoDate.split(" ");
                  // DeposDate=u[0];

                  // }
                  Model.addRow(new Object[] { Rol, S_nmae, Semm, BookNAME, BookId, IssuDate, DepoDate, Issuer, dEpo });

                }
                TablejPanel.add(new JScrollPane(j));
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
            // \\\\\---------
          }

        }).start();
        // ----

      }
    });
  }

  public void paint(Graphics g) {
    super.paint(g); // fixes the immediate problem.
    Graphics2D g2 = (Graphics2D) g;
    Line2D lin = new Line2D.Float(10, 170, 1920, 170);
    Line2D lin1 = new Line2D.Float(280, 50, 280, 170);
    Line2D lin2 = new Line2D.Float(10, 50, 1920, 50);
    g2.draw(lin);
    g2.draw(lin1);
    g2.draw(lin2);
  }
}