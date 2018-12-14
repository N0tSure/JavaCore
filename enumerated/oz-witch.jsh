import java.util.Arrays;
import java.util.stream.Collectors;

enum OzWitch {
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");

    private final String description;

    OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

String witches = Arrays.stream(OzWitch.values()).map(OzWitch::getDescription).collect(Collectors.joining("\n"));

System.out.println(witches);