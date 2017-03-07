package shield.book.charper15;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by cresh on 22.04.16.
 */
public class Parameters extends Applet {
    String author;
    String purpose;
    int version;
    String errorMsg;

    @Override
    public void start() {
        author = getParameter("author");
        if (author==null) author="n/a";
        purpose = getParameter("purpose");
        if (purpose==null) purpose="n/a";
        try {
            version = Integer.parseInt(getParameter("version"));
        } catch (NumberFormatException exc) {
            errorMsg=exc.getMessage();
            version=-1;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Purpose: " + purpose,10,20);
        g.drawString("Author: " + author,10,40);
        g.drawString("Version: " + version,10,60);
        if (version<0) showStatus(errorMsg);
    }
}
