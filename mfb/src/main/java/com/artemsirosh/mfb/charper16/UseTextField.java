package com.artemsirosh.mfb.charper16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cresh on 28.04.16.
 */
public class UseTextField {
    private JTextField textField;
    private JButton button;
    private JLabel prompt, content;
    private String text;

    private UseTextField() {

        JFrame frame = new JFrame("Use a Text Field.");
        frame.setLayout(new FlowLayout());
        frame.setSize(240,120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        textField = new JTextField(10);
        button = new JButton("Reverse");
        prompt = new JLabel("Enter text: ");
        content = new JLabel("");

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text = textField.getText();
                content.setText("You type: "+text);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (text==null) text = textField.getText();
                String result = "";
                for (int i = text.length()-1; i>=0; i--) {
                    result+=text.charAt(i);
                }
                content.setText(result);
            }
        });

        frame.add(prompt);
        frame.add(textField);
        frame.add(button);
        frame.add(content);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UseTextField();
            }
        });
    }
}
