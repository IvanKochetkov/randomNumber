package com.ivan;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

import static com.ivan.Main.textFont;

public class GamePanel extends JPanel {

    private String number; // строка правильного ответа

    private JLabel numberField; // лейбл где отображаем XXXX
    private JLabel resultField; // лейбл отоббражающий R W и ошибку

    private JFormattedTextField answerField; // поле для ввода текста. взят формат, т.к. нужно вводить  только цифры
    private JButton checkAnswer; // кнопка проверки ответа

    private int playersTry = 0; // количество ошибок

    private JButton help; // кнопка показывающая правила

    public GamePanel() {
        this.setLayout(new BorderLayout()); // задаем тип сетки


        this.numberField = new JLabel("XXXX"); // создаем лейбл, задаем ему шрифт и ставим по центру
        this.numberField.setFont(textFont);
        this.numberField.setVerticalAlignment(SwingConstants.CENTER);
        this.numberField.setHorizontalAlignment(SwingConstants.CENTER);

        this.resultField = new JLabel(); // создаем лейбл, задаем ему шрифт и ставим по центру
        this.resultField.setFont(textFont);
        this.resultField.setVerticalAlignment(SwingConstants.CENTER);
        this.resultField.setHorizontalAlignment(SwingConstants.CENTER);

        MaskFormatter answerMask = null; // формируем маску для ввода цифр
        try {
            answerMask = new MaskFormatter("####"); // # - позволяет вводить только цифры => #### - 4 цифры
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.answerField = new JFormattedTextField(answerMask); // создаем поле с вводом ответа с помощью маски
        this.answerField.setPreferredSize(new Dimension(40,30)); // определяем размеры
        this.checkAnswer = new JButton("Check"); // создаем кнопку и добавляем обработчик событий
        this.checkAnswer.addActionListener(e -> {
           this.checkAnswer();
        });

        /*this.help = new JButton("Rules");// создаем кнопку и добавляем обработчик событий
        this.help.addActionListener(e -> {
            this.openRules();
        });*/

        JPanel bottomPanel = new JPanel(new FlowLayout()); // панель с кнопками
        bottomPanel.add(answerField);
        bottomPanel.add(checkAnswer);
        //bottomPanel.add(help);

        JTextArea rules = new JTextArea(HelpFrame.info); //создаем поле и в него вставляем инструкцию
        rules.setLineWrap(true); // разрешаем перенос текста
        rules.setWrapStyleWord(true); // разрешаем перенос текста целыми словами
        rules.setEditable(false); // отключаем возможность изменения текста
        rules.setDisabledTextColor(Color.BLACK); // вовзращаем тексту черный цвет

        JPanel centerPanel = new JPanel(new GridLayout(3,1)); // создаем панель с сеточным выводом и пихаем туда два лейбла
        centerPanel.add(numberField);
        centerPanel.add(rules);
        centerPanel.add(resultField);

        this.add(centerPanel, BorderLayout.CENTER); //добавляем обе панели
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.number = NumberGenerator.generateNumber(); // генерируем число
        System.out.println(number);
    }

    private void checkAnswer() { // метод проверки
        String answer = answerField.getText(); // получаем введенный текст
        boolean isRight = true; // флаг для проверки
        if(answer.equals("    ")) { // проверяем введены ли 4 цифры
            this.resultField.setText("You should input 4 digits"); // выводим что фейл
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
            this.showResult("Right answer - " + answer + "\n Count of tries - " + this.playersTry);
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
        frame.setSize(300,200); // задаем размеры окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // получаем данные экрана чтобы ...
        frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2); // ..
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // крест отключает только окно правил, а не всю прогу
        frame.setTitle("Numbinator rules"); // задаем название окна
        frame.setResizable(false); // отключаем возможность изменения окна
        frame.setVisible(true); // делаем окно видимым
    }
}
