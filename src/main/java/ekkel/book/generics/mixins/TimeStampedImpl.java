package ekkel.book.generics.mixins;

/**
 * Created by cresh on 17.12.16.
 */
class TimeStampedImpl implements TimeStamped {
    private final long timeStamp;

    public TimeStampedImpl() {
        this.timeStamp = System.currentTimeMillis();
    }

    @Override
    public long getStamp() {
        return this.timeStamp;
    }
}
