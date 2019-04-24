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
        JTextArea rules = new JTextArea(info); //создаем поле и в него вставляем инструкцию
        rules.setLineWrap(true); // разрешаем перенос текста
        rules.setWrapStyleWord(true); // разрешаем перенос текста целыми словами
        rules.setEditable(false); // отключаем возможность изменения текста
        rules.setBackground(Color.LIGHT_GRAY); // меня цвет на серый
        rules.setDisabledTextColor(Color.BLACK); // вовзращаем тексту черный цвет
        this.add(rules, BorderLayout.CENTER); // добавляем в центр
    }
}
