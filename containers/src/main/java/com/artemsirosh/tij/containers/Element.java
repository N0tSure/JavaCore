package com.artemsirosh.tij.containers;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cresh on 02.04.17.
 */
class Element {
    private String id;

    public Element(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object that) {
        return that instanceof Element && Objects.equal(this.id, ((Element) that).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("ID", id).toString();
    }

    @Override
    protected void finalize() {
        System.out.printf("Finalizing %s\n", toString());
    }
}
