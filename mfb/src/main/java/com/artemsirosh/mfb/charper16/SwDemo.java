package com.artemsirosh.mfb.charper16;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cresh on 24.04.16.
 */
class SwDemo {
    SwDemo() {
        JFrame frame = new JFrame("A Simple Swing App.");
        GridLayout layout = new GridLayout(0,2);
        frame.setLayout(layout);
        frame.setSize(550,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Swing defines the modern Java GUI.");
        JLabel label1 = new JLabel("Swing defines the modern Java GUI.");
        JLabel label2 = new JLabel("Swing defines the modern Java GUI.");
        JLabel label3 = new JLabel("Swing defines the modern Java GUI.");
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwDemo();
            }
        });
    }
}
