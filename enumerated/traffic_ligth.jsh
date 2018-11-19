import java.util.stream.Stream;

enum Signal {
    GREEN, YELLOW, RED
}

class TrafficLight {

    private Signal color = Signal.RED;

    void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return color.name();
    }
}

Stream.iterate(new TrafficLight(), trafficLight -> {trafficLight.change(); return trafficLight;}).limit(7).forEach(System.out::println);

