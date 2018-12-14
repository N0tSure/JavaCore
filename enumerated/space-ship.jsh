import java.util.Arrays;

enum SpaceShip {
    SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name());
        final int sz = builder.length();
        return builder.replace(1, sz, builder.substring(1, sz).toLowerCase()).toString();
    }
}

Arrays.stream(SpaceShip.values()).forEach(System.out::println);
