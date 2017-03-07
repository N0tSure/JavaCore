package shield.book.charper12;

/**
 * Created by cresh on 30.03.16.
 */

enum Transport {
    CAR(120), TRUCK(80), AIRPLANE(1100), TRAIN(60), BOAT(20);

    private int speed;

    Transport(int speed) {
        this.speed=speed;
    }

    int getSpeed() {
        return speed;
    }
}
