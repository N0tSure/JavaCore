package shield.book.charper15;

import java.applet.*;
import java.awt.*;

/**
 * Created by cresh on 21.04.16.
 */
public class SimpleApplet extends Applet {
    public void paint(Graphics g) {
        g.drawString("Java makes applets",20,20);
        showStatus("This will shown in status window.");
    }
}
