package ekkel.book.io;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static com.google.common.base.Objects.equal;

/**
 * Created on 18 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
class Worm implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Worm.class);
    private static final Random random = new Random(42);

    private Worm next;
    private char character;
    private Data[] data = {
        new Data(random.nextInt(10)),
        new Data(random.nextInt(10)),
        new Data(random.nextInt(10))
    };

    Worm(int count, char character) {
        LOGGER.info("Worm constructor: {}", count);

        this.character = character;
        if (--count > 0) {
            this.next = new Worm(count, (char) (character + 1));
        }
    }

    Worm() {
        LOGGER.info("Default constructor");
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(next, character, Arrays.hashCode(data));
    }

    @Override
    public boolean equals(Object that) {
        return !equal(null, that) &&
                that instanceof Worm &&
                equal(this.character, ((Worm) that).character) &&
                equal(this.next, ((Worm) that).next) &&
                Arrays.equals(this.data, ((Worm) that).data);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder(":");
        result
                .append(character)
                .append('(');

        for (Data d : data)
            result.append(d);

        result.append(')');

        if (this.next != null)
            result.append(this.next);

        return result.toString();
    }
}
