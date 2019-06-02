package com.artemsirosh.mfb.charper16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by cresh on 28.04.16.
 */
class CheckBoxDemo implements ItemListener {
    private JLabel selected, changed;
    private JCheckBox alpha, beta, gamma;

    private CheckBoxDemo() {
        JFrame frame = new JFrame("Demonstrate check boxes");
        frame.setSize(320,120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel north = new JPanel();
        JPanel south = new JPanel();
        frame.add(north,BorderLayout.NORTH);
        frame.add(south,BorderLayout.CENTER);

        selected = new JLabel("");
        changed = new JLabel("");

        alpha = new JCheckBox("Alpha");
        beta = new JCheckBox("Beta");
        gamma = new JCheckBox("Gamma");

        alpha.addItemListener(this);
        beta.addItemListener(this);
        gamma.addItemListener(this);

        north.add(alpha,BorderLayout.WEST);
        north.add(beta,BorderLayout.CENTER);
        north.add(gamma,BorderLayout.EAST);
        south.add(changed,BorderLayout.NORTH);
        south.add(selected,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        String s = "";

        JCheckBox tmp = (JCheckBox) itemEvent.getItem();

        if (tmp.isSelected()) changed.setText(tmp.getText() + " was just selected.");
        else changed.setText(tmp.getText() + " was just cleared.");

        if (alpha.isSelected()) s+=alpha.getText();
        if (beta.isSelected()) s+=beta.getText();
        if (gamma.isSelected()) s+=gamma.getText();

        selected.setText("Selected check boxes: " + s);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CheckBoxDemo();
            }
        });
    }
}
