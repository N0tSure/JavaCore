package shield.book.charper15;

import java.applet.*;
import java.awt.*;
/**
 * Created by cresh on 21.04.16.
 */
public class Banner extends Applet implements Runnable {
    String msg, errorMsg = null;
    Thread thread;
    boolean stopFlag;
    char ch;
    int delay = 250;


    public void init() {
        thread = null;
    }

    @Override
    public void start() {
        msg = getParameter("message");
        if (msg==null) msg = " not available ";
        try {
            delay = Integer.parseInt(getParameter("delay"));
        } catch (NumberFormatException exc) {
            errorMsg = String.format("Invalid parameter \"delay\": %s.",exc.getMessage());
        }
        thread = new Thread(this);
        stopFlag = false;
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                repaint();
                Thread.sleep(delay);
                if (stopFlag) break;
            } catch (InterruptedException exc) {}

        }
    }

    @Override
    public void stop() {
        stopFlag = true;
        thread = null;
    }

    @Override
    public void paint(Graphics g) {
        ch = msg.charAt(0);
        msg = msg.substring(1,msg.length());
        msg+=ch;
        g.drawString(msg, 50, 30);
        if (errorMsg!=null) showStatus(errorMsg);
    }
}
