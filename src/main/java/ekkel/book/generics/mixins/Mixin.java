package ekkel.book.generics.mixins;

import java.awt.*;

/**
 * Created by cresh on 17.12.16.
 */
class Mixin extends BasicImpl implements TimeStamped, Serial, Colored {
    private TimeStamped timeStamped;
    private Serial serial;
    private Colored colored;

    Mixin() {
        timeStamped = new TimeStampedImpl();
        serial = new SerialNumbered();
        colored = new Green();
    }

    @Override
    public long getSerialNumber() {
        return serial.getSerialNumber();
    }

    @Override
    public long getStamp() {
        return timeStamped.getStamp();
    }

    @Override
    public Color getColor() {
        return colored.getColor();
    }
}
