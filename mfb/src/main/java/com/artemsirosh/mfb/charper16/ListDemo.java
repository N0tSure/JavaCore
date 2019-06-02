package com.artemsirosh.mfb.charper16;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by cresh on 28.04.16.
 */
class ListDemo {
    private ListDemo() {
        JFrame frame = new JFrame("JList Demo");
        frame.setSize(200,160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String[] names = {
                "Mercury", "Venus", "Earth",
                "Mars", "Jupiter", "Saturn",
                "Neptune", "Pluto"
        };
        final JList<String> list = new JList<>(names);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(120,90));

        final JLabel label = new JLabel("Please choose planet.");
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = list.getSelectedIndex();
                if (index!=-1) label.setText("Current planet: " + names[index]);
                else label.setText("Please choose planet.");
            }
        });

        frame.add(pane,BorderLayout.CENTER);
        frame.add(label,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListDemo();
            }
        });
    }
}
