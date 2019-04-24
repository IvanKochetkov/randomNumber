package com.ivan;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

import static com.ivan.Main.textFont;

public class GamePanel extends JPanel {

    private String number;

    private JLabel numberField;
    private JLabel resultField;

    private JFormattedTextField answerField;
    private JButton checkAnswer;

    private int playersTry = 0;

    private JButton help;

    public GamePanel() {
        this.setLayout(new BorderLayout());


        this.numberField = new JLabel("XXXX");
        this.numberField.setFont(textFont);
        this.numberField.setVerticalAlignment(SwingConstants.CENTER);
        this.numberField.setHorizontalAlignment(SwingConstants.CENTER);

        this.resultField = new JLabel();
        this.resultField.setFont(textFont);
        this.resultField.setVerticalAlignment(SwingConstants.CENTER);
        this.resultField.setHorizontalAlignment(SwingConstants.CENTER);

        MaskFormatter answerMask = null;
        try {
            answerMask = new MaskFormatter("####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.answerField = new JFormattedTextField(answerMask);
        this.answerField.setPreferredSize(new Dimension(40,30));
        this.checkAnswer = new JButton("Проверить");
        this.checkAnswer.addActionListener(e -> {
           this.checkAnswer();
        });

        this.help = new JButton("Справка");
        this.help.addActionListener(e -> {
            this.openRules();
        });

        JPanel centerPanel = new JPanel(new GridLayout(2,1)); // создаем панель с сеточным выводом и пихаем туда два лейбла
        centerPanel.add(numberField);
        centerPanel.add(resultField);

        JPanel bottomPanel = new JPanel(new FlowLayout()); // панель с кнопками
        bottomPanel.add(answerField);
        bottomPanel.add(checkAnswer);
        bottomPanel.add(help);

        this.add(centerPanel, BorderLayout.CENTER); //добавляем обе панели
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.number = NumberGenerator.generateNumber(); // генерируем число
    }

    private void checkAnswer() { // метод проверки
        String answer = answerField.getText(); // получаем введенный текст
        boolean isRight = true; // флаг для проверки
        if(answer.equals("    ")) { // проверяем введены ли 4 цифры
            this.resultField.setText("Вы должны ввести 4 цифры"); // выводим что фейл
            this.answerField.setValue(null); // чистим поле
            this.answerField.requestFocusInWindow(); // ставим курсор на поле
            return;
        }
        this.playersTry++; // прошли проверку увеличили счетчик попыток
        StringBuilder checkResult = new StringBuilder(); //  создаем стринг билдер для результата
        for(int answerDigit = 0; answerDigit < 4; answerDigit++) { // перебираем цифры ответа
            if(this.number.charAt(answerDigit) == answer.charAt(answerDigit)) { // если цифры ответа совпадает. доабвляем R и идем к следующей
                checkResult.append('R');
                continue;
            }
            for(int numberDigit = 0; numberDigit < 4; numberDigit++) { // перебираем все правильные цифры, чтобы найти если похожие цифры
                if(this.number.charAt(numberDigit) == answer.charAt(answerDigit) &&
                        numberDigit != answerDigit ) {
                    checkResult.append('W'); // если есть и при этом это не цифры одного разряда добавляем W
                    break;
                }
            }
            isRight = false; // флаг что есть ошибка
        }
        if(isRight) { // если ошибок нет вызываем метод
            this.showResult("Ответ - " + answer + "\n Попыток - " + this.playersTry);
        }
        this.answerField.requestFocusInWindow(); // перевод курсора на поле
        this.answerField.setValue(null); // чистка поля
        this.resultField.setText(Integer.toString(this.playersTry) + ": " + answer + "-" + checkResult.toString()); // отображаем результат
    }

    private void showResult(String text) {
        Main.frame.remove(this);// из фрейма удаляем эту панель
        Main.frame.add(new EndPanel(text)); // создаем панель с результатами
        Main.frame.validate(); // 2 метода для правильной отрисовки
        Main.frame.repaint();
    }

    private void openRules() {
        JFrame frame = new HelpFrame(); // создаем окно
        frame.setLocationRelativeTo(null); // спавн по центру
        frame.setBackground(Color.BLACK); // цвет бека задаем
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // крест отключает только окно правил, а не всю прогу
        frame.setSize(300,200); // задаем размеры окна
        frame.setTitle("Справка "); // задаем название окна
        frame.setResizable(false); // отключаем возможность изменения окна
        frame.setVisible(true); // делаем окно видимым
    }
}
