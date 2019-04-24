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
        this.checkAnswer = new JButton("ОК");
        this.checkAnswer.addActionListener(e -> {
           this.checkAnswer();
        });

        this.help = new JButton("Справка");
        this.help.addActionListener(e -> {
            this.openRules();
        });

        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        centerPanel.add(numberField);
        centerPanel.add(resultField);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(answerField);
        bottomPanel.add(checkAnswer);
        bottomPanel.add(help);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.number = NumberGenerator.generateNumber();
    }

    private void checkAnswer() {
        String answer = answerField.getText();
        boolean isRight = true;
        if(answer.equals("    ")) {
            this.resultField.setText("Введите 4-х значное число");
            this.answerField.setValue(null);
            this.answerField.requestFocusInWindow();
            return;
        }
        this.playersTry++;
        StringBuilder checkResult = new StringBuilder();
        for(int answerDigit = 0; answerDigit < 4; answerDigit++) {
            if(this.number.charAt(answerDigit) == answer.charAt(answerDigit)) {
                checkResult.append('R');
                continue;
            }
            for(int numberDigit = 0; numberDigit < 4; numberDigit++) {
                if(this.number.charAt(numberDigit) == answer.charAt(answerDigit) &&
                        numberDigit != answerDigit ) {
                    checkResult.append('W');
                    break;
                }
            }
            isRight = false;
        }
        if(isRight) {
            this.showResult("Правильный ответ - " + answer + "\nПопыток - " + this.playersTry);
        }
        this.answerField.requestFocusInWindow();
        this.answerField.setValue(null);
        this.resultField.setText(Integer.toString(this.playersTry) + ": " + answer + "-" + checkResult.toString());
    }

    private void showResult(String text) {
        Main.frame.remove(this);
        Main.frame.add(new EndPanel(text));
        Main.frame.validate();
        Main.frame.repaint();
    }

    private void openRules() {
        JFrame frame = new HelpFrame();
        frame.setSize(300,200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Справка");
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
