package ekkel.book.io;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created on 21 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class Animal implements Serializable {

    private String name;
    private House preferredHouse;

    public Animal(String name, House preferredHouse) {
        this.name = name;
        this.preferredHouse = preferredHouse;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("identity", Integer.toHexString(System.identityHashCode(this)))
                .add("preferred house", preferredHouse)
                .toString();
    }
}
