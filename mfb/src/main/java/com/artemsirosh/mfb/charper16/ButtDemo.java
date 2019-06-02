package com.artemsirosh.mfb.charper16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cresh on 27.04.16.
 */
public class ButtDemo {
    JLabel label;
    public ButtDemo() {
        JFrame frame = new JFrame("A Button Example.");
        frame.setSize(220,90);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel nord = new JPanel();
        JPanel south = new JPanel();
        frame.add(nord, BorderLayout.NORTH);
        frame.add(south,BorderLayout.SOUTH);

        JButton up = new JButton("Up");
        JButton down = new JButton("Down");
        label = new JLabel("Press a button.");

        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                label.setText("You press Up.");
            }
        });

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                label.setText("You press Down.");
            }
        });

        nord.add(up,BorderLayout.WEST);
        nord.add(down,BorderLayout.EAST);
        south.add(label);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ButtDemo();
            }
        });
    }
}
