package ekkel.book.io;

import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>
 *     Created on 21.06.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
class SerialControl implements Serializable {

    private String one;
    private transient String another;

    public SerialControl(String one, String another) {
        this.one = "Not transient " + one;
        this.another = "Transient " + another;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(another);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        another = (String) stream.readObject();
    }

    @Override
    public String toString() {
        return one + "\n" + another;
    }
}
