package com.artemsirosh.mfb.charper16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.io.*;

/**
 * Created by cresh on 02.05.16.
 */
class SwingFC {
    private SwingFC() {
        JFrame frame = new JFrame("Сравнение файлов");
        frame.setSize(650,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JPanel full = new JPanel();
        Border emptyBorder = BorderFactory.createEmptyBorder(5,5,0,0);
        full.setBorder(emptyBorder);
        Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        panel.setBorder(border);
        panel.setLayout(new GridLayout(0,2,5,5));

        final JTextField firstTextField = new JTextField(14);
        final JTextField secondTextField = new JTextField(14);

        JButton button = new JButton("Ебашь!");

        JLabel firstLabel = new JLabel("Первый файл");
        JLabel secondLabel = new JLabel("Второй файл");
        final JLabel resultLabel = new JLabel("");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i=0, j=0;
                FileInputStream firstStream=null, secondStream=null;

                if (firstTextField.getText().equals(""))
                    resultLabel.setText("Имя первого файла не задано!");

                if (secondTextField.getText().equals(""))
                    resultLabel.setText("Имя второго файла не задано!");

                File firstFile = new File(firstTextField.getText());
                File secondFile = new File(secondTextField.getText());

                if (!firstFile.exists()) resultLabel.setText("Первый файл: Нет такого файла");
                if (!secondFile.exists()) resultLabel.setText("Второй файл: Нет такого файла");

                try {
                    firstStream = new FileInputStream(firstFile);
                    secondStream = new FileInputStream(secondFile);

                    do {
                        i = firstStream.read();
                        j = secondStream.read();
                        if (i!=j) break;
                    } while (i!=-1 && j!=-1);

                    if (i!=j) resultLabel.setText("Файлы разные!");
                    else resultLabel.setText("Файлы одинаковые!");

                } catch (IOException exc) {
                    resultLabel.setText(exc.getMessage());
                }

                try {
                    if (firstStream!=null) firstStream.close();
                    if (secondStream!=null) secondStream.close();
                } catch (IOException exc) {
                    resultLabel.setText(exc.getMessage());
                }

            }
        });


        frame.add(full);
        full.add(panel);
        panel.add(firstLabel);
        panel.add(secondLabel);
        panel.add(firstTextField);
        panel.add(secondTextField);
        panel.add(button);
        panel.add(resultLabel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingFC();
            }
        });
    }
}
