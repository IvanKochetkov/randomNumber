package com.ivan;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame frame;
    public static Font textFont = new Font("Arial", Font.BOLD, 20);

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(500,400);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2); // ..
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Numbinator");
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
