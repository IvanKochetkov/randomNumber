package com.ivan;

import javax.swing.*;

import java.awt.*;

import static com.ivan.Main.textFont;

public class EndPanel extends JPanel {

    public EndPanel(String text) {
        JTextArea result = new JTextArea(text);
        result.setFont(textFont);
        result.setEnabled(false);
        result.setDisabledTextColor(Color.BLACK);
        result.setSelectionColor(Color.CYAN);
        JButton tryAgain = new JButton("Play again");
        tryAgain.setHorizontalAlignment(SwingConstants.CENTER);
        tryAgain.addActionListener(e -> {
            Main.frame.remove(this);
            Main.frame.add(new GamePanel());
            Main.frame.validate();
            Main.frame.repaint();
        });

        this.add(result);
        this.add(tryAgain, BorderLayout.SOUTH);
    }
}
