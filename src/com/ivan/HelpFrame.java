package com.ivan;

import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {
    public static String info =
            "Введите 4-х значное число.\n" +
            "W - Означает, что в вашем числе есть цифра загаданного числа, но стоит не на своём месте\n" +
            "R - Означает, что в вашем числе есть цифра загаданного числа и стоит на своём месте\n" +
            "Если таких W или R несколько, то таких цифр несеколько.";

    public HelpFrame() {
        JTextArea rules = new JTextArea(info); //создаем поле и в него вставляем инструкцию
        rules.setLineWrap(true); // разрешаем перенос текста
        rules.setWrapStyleWord(true); // разрешаем перенос текста целыми словами
        rules.setEditable(false); // отключаем возможность изменения текста
        rules.setBackground(Color.LIGHT_GRAY); // меня цвет на серый
        rules.setDisabledTextColor(Color.BLACK); // вовзращаем тексту черный цвет
        this.add(rules, BorderLayout.CENTER); // добавляем в центр
    }
}
