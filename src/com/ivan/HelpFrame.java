package com.ivan;

import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {
    private static String info =
            "Вы должны ввести 4-х значное число .\n" +
            "W - В вашем числе есть цифра загаданного числа, но она стоит не на своём месте\n" +
            "R - В вашем числе есть цифра загаданного числа и стоит на своём месте\n" +
            "Если таких W или R несколько , значит в вашем числе несколько таких цифр .";

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
