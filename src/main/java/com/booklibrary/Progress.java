// package com.booklibrary;

// import java.awt.Color;
// import java.awt.Font;
// import java.awt.Toolkit;

// import javax.swing.*;

// public class Progress implements Runnable {
//     Thread t;
//     /**
//      * 
//      */
//     JFrame frame;
//     JProgressBar pro;

//     Progress() {
//         int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2;// 1080
//         int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2; // 1920

//         int x1 = 350;
//         int y1 = 90;

//         frame = new JFrame();
//         frame.setBounds((x - (x1 / 2)), (y - (y1 / 2)), x1, y1);
//         frame.setResizable(true);
//         frame.setUndecorated(true);
//         java.awt.Container c = frame.getContentPane();
//         frame.setBackground(java.awt.Color.BLACK);
//         ImageIcon imageIcon = new ImageIcon("img/book_lib.png");
//         frame.setIconImage(imageIcon.getImage());
//         frame.setTitle("Books Libarary");
//         c.setLayout(null);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         pro = new JProgressBar();
//         pro.setStringPainted(true);
//         // pro.setMaximum(100);
//         // pro.setMinimum(0);
//         pro.setString("Loading...");
//         pro.setFont(new Font("arial", Font.ITALIC, 25));
//         pro.setBackground(Color.BLUE);
//         pro.setBounds(10, 30, 330, 35);
//         frame.add(pro);
//         frame.setVisible(true);
//         t = new Thread(this);
//         t.start();
//     }

//     private int getHeight() {
//         return 0;
//     }

//     private int getWidth() {
//         return 0;
//     }

//     @Override
//     public void run() {
//         {

//             // int k = 1;
//             // do{
//             // try {
//             // Thread.sleep(100);
//             // } catch (InterruptedException e) {
//             // // TODO Auto-generated catch block
//             // e.printStackTrace();
//             // }
//             // pro.setValue(k);
//             // k++;
//             // }while(k!=0);

//             pro.setIndeterminate(true);

//         }
//     }
// }


// it improve --------------------
package com.booklibrary;

import java.awt.Font;

import javax.swing.*;

public class Progress implements Runnable {
    Thread t;
    /**
     * 
     */

    JProgressBar pro;

    public Progress() {



        
        pro = new JProgressBar();
        pro.setStringPainted(true);

        pro.setString("Loading...");
        pro.setFont(new Font("arial", Font.ITALIC, 15));
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
    //     {
    //         int k = 1;
    //         do{
    //        System.out.println(k);
    //         k++;
    //     }while(k!=0);
    // }
    pro.setIndeterminate(true);
    }
}
