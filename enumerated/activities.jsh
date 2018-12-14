import ekkel.book.enumerated.Enums;

import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Activity {
    SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING
}

String result = Stream.generate(() -> Enums.random(Activity.class)).limit(20).map(Activity::name).collect(Collectors.joining(" "));