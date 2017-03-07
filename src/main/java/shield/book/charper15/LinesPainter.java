package shield.book.charper15;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by cresh on 23.04.16.
 */
public class LinesPainter extends Applet implements MouseMotionListener, MouseListener {
    String msg;
    Graphics graphics;
    int x,y, locMsgX, locMsgY;

    @Override
    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
        msg = "";
        graphics = getGraphics();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        msg = String.format("Mouse location: %d:%d",
                mouseEvent.getX(),mouseEvent.getY());
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        msg = String.format("Mouse location: %d:%d",
                mouseEvent.getX(),mouseEvent.getY());
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        graphics.drawLine(x,y,mouseEvent.getX(),mouseEvent.getY());
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg,20,20);
    }
}
