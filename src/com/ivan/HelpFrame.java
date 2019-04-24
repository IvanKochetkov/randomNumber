package com.ivan;

import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {
    private static String info =
            "Вам нужно угадать четырёхзначное число.\n" +
            "W - Такая цифра есть, но стоит не на своём месте\n" +
            "R - Такая цифра есть и стоит на правильном месте \n" +
            "Если есть несколько W или  R, то таких цифр несколько.";

    public HelpFrame() {
        JTextArea rules = new JTextArea(info);
        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);
        rules.setEditable(false);
        rules.setBackground(Color.LIGHT_GRAY);
        rules.setDisabledTextColor(Color.BLACK);
        this.add(rules, BorderLayout.CENTER);
    }
}
