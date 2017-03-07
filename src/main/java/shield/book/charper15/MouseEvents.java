package shield.book.charper15;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by cresh on 22.04.16.
 */
public class MouseEvents extends Applet implements MouseListener, MouseMotionListener, MouseWheelListener {
    String msg = "";
    int x = 0, y = 0;

    @Override
    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        x = 0;
        y = 10;
        msg = "Клик!";
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        msg = "ЛКМ нажата.";
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        msg = "ЛКМ отжата.";
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        x = 0;
        y = 10;
        msg = "Мышь в окне.";
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        x = 0;
        y = 10;
        msg = "Мышь вне окна.";
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        x = mouseEvent.getXOnScreen();
        y = mouseEvent.getYOnScreen();
        msg = String.format("Мышь перетаскивается: %1$d, %2$d.",mouseEvent.getX(),mouseEvent.getY());
        x = 0;
        y = 10;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        msg = String.format("Мышь перемещается в: %1$d, %2$d.",mouseEvent.getX(),mouseEvent.getY());
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        x = 0;
        y = 10;
        msg = String.format("Wheel rotate at: %.4f \nRotate count: %d",
                mouseWheelEvent.getPreciseWheelRotation(),mouseWheelEvent.getUnitsToScroll());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg,x,y);
    }
}
