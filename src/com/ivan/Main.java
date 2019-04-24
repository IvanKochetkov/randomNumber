package com.ivan;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame frame;
    public static Font textFont = new Font("Arial", Font.BOLD, 20);

    public static void main(String[] args) {
        frame = new JFrame(); // создаем окно
        frame.setLocationRelativeTo(null); // открытие окна по центру
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливаем стандартный тип закрытия
        frame.setSize(300,150); // задаем размеры окна
        frame.setTitle("Угадай число"); // задаем название окна
        frame.setResizable(false); // отключаем возможность изменения окна
        frame.add(new GamePanel(), BorderLayout.CENTER); // добавляем основную панель с игрой
        frame.setVisible(true); // делаем окно видимым
    }
}
