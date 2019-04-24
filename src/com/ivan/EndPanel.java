package com.ivan;

import javax.swing.*;

import java.awt.*;

import static com.ivan.Main.textFont;

public class EndPanel extends JPanel {

    public EndPanel(String text) { // передаем выводимый текст
        JTextArea result = new JTextArea(text); // создаем поле для вывода
        result.setFont(textFont); // используем шрифт созданный в Main
        result.setEnabled(false); // отключаем возможность ручного редактирования
        result.setDisabledTextColor(Color.BLACK); // т.к. поле выключено, меняем цвет отключенного текста на черный
        result.setSelectionColor(Color.CYAN); // меняем цвет бэка
        JButton tryAgain = new JButton("Ещё Раз?"); // добавляем кнопку перезапуска
        tryAgain.setHorizontalAlignment(SwingConstants.CENTER); // ставим кнопку по центру
        tryAgain.addActionListener(e -> { // добавляем на кнопку обработчик
            Main.frame.remove(this); // очищает основной фрейм от этого компонента
            Main.frame.add(new GamePanel()); // добавляем компонент реализующий игру
            Main.frame.validate(); // 2 метода для корректной отрисовки фрейма
            Main.frame.repaint(); // 2 метода для корректной отрисовки фрейма
        });

        this.add(result); // выводим на панель поле текстовое
        this.add(tryAgain, BorderLayout.SOUTH); // снизу доббавляем кнопку
    }
}
