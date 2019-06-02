package com.artemsirosh.mfb.charper15;

import java.applet.Applet;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by cresh on 23.04.16.
 */
public class Watch extends Applet implements Runnable {
    String timeMessage, errorMessage = null;
    Thread thread;
    Calendar calendar;
    boolean stopFlag;

    @Override
    public void init() {
        calendar = new GregorianCalendar();
        timeMessage = String.format("%1$d:%2$d:%3$d",
                calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),calendar.get(Calendar.MILLISECOND));
    }

    @Override
    public void start() {
        thread = new Thread(this);
        stopFlag = false;
        thread.start();
    }

    @Override
    public void stop() {
        thread = null;
        stopFlag = true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                calendar = Calendar.getInstance();
                timeMessage = String.format("%1$d:%2$d:%3$d",
                        calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
            } catch (InterruptedException exc) {
                errorMessage = exc.getMessage();
            }
            if (stopFlag) break;
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(timeMessage,20,20);
        if (errorMessage!=null) showStatus(errorMessage);
    }
}
