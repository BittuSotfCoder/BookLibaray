package com.booklibrary;

import java.awt.*;
import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App {
    /**
    *
    */
    public static void main(String[] args) {
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2;// 1080
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2; // 1920

        int x1 = 750;
        int y1 = 450;

        JFrame frame = new JFrame();
        frame.setBounds((x - (x1 / 2)), (y - (y1 / 2)), x1, y1);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.awt.Container c = frame.getContentPane();
        ImageIcon imageIcon = new ImageIcon("img/book_lib.png");
        frame.setIconImage(imageIcon.getImage());
        frame.setTitle("Books Libarary");
        c.setLayout(null);

        Login_Signup l = new Login_Signup();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception hujd) {
        }
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(50, 350, 650, 40);
        progressBar.setStringPainted(true);
        progressBar.setMaximum(100);
        progressBar.setMinimum(0);

        c.add(progressBar);

        JLabel wel = new JLabel("Welcome,");
        wel.setBounds(50, 50, 200, 100);
        frame.add(wel);
        wel.setForeground(Color.decode("#851947"));
        wel.setFont(new Font("Algerian", Font.PLAIN, 40));

        JLabel lib = new JLabel("To Books Libaray!");
        lib.setBounds(180, 110, 400, 100);
        frame.add(lib);
        lib.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        lib.setForeground(Color.decode("#579145"));

        JLabel li = new JLabel("L.S College,Muzzaffarpur");
        li.setBounds(80, 200, 800, 100);
        frame.add(li);
        li.setFont(new Font("Algerian", Font.PLAIN, 45));
        li.setForeground(Color.decode("#978544"));

        frame.setVisible(true);

        for (int i = 1; i <= 100; i++) {

            try {

                Thread.sleep(30);
                progressBar.setValue(i);
                if (i % 2 == 0) {
                    progressBar.setValue(i);

                } else {
                    progressBar.setValue(i);
                    ;
                }
                if (i == 100) {
                    frame.setVisible(false);
                    l.setVisible(true);
                }

            } catch (Exception hbh) {
            }

        }

    }

    private static int getWidth() {
        return 0;
    }

    private static int getHeight() {
        return 0;
    }

}
