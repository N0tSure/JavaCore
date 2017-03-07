package ekkel.book.generics.latency;

/**
 * Created by cresh on 19.12.16.
 */
class LatentReflection {
    public static void main(String[] args) {
        Communicate.perform(new SmartDog());
        Communicate.perform(new Mime());
    }
}
