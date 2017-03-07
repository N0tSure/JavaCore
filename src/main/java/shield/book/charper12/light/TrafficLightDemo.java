package shield.book.charper12.light;

/**
 * Created by cresh on 31.03.16.
 */
class TrafficLightDemo {
    public static void main(String[] args) {
        TrafficLightSimulator lightSimulator
                = new TrafficLightSimulator(TrafficLightColour.GREEN);

        for (int i = 0; i < 9; i++) {
            System.out.println(lightSimulator);
            //System.out.println("waitForChanges() " + i);
            lightSimulator.waitForChanges();
        }
        lightSimulator.cancel();
    }
}
