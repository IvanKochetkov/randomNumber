package com.ivan;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame frame;
    public static Font textFont = new Font("Arial", Font.BOLD, 20);

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);
        frame.setTitle("Угадай число");
        frame.setResizable(false);
        frame.add(new GamePanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
