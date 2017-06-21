package ekkel.book.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * <p>
 * Created on 21.06.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
class Another implements Externalizable {

    private int number;
    private String string;

    public Another() {

        System.out.println("Another()");
    }

    public Another(int number, String string) {

        System.out.println("Another(number: int, string:String)");
        this.number = number;
        this.string = string;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        System.out.printf("Another.writeExternal: number=%d, string=%s\n", number, string);
        out.writeObject(string);
        out.writeInt(number);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        string = (String) in.readObject();
        number = in.readInt();
        System.out.printf("Another.readExternal: number=%d, string=%s\n", number, string);
    }

    @Override
    public String toString() {
        return string + " " + String.valueOf(number);
    }
}
