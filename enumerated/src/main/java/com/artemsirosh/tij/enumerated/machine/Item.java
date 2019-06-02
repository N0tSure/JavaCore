package com.artemsirosh.tij.enumerated.machine;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
@JacksonXmlRootElement(localName = "item")
final class Item implements Input {

    private final String name;
    private final int amount;

    public Item(
            @JacksonXmlProperty(localName = "name") String name,
            @JacksonXmlProperty(localName = "amount") int amount
    ) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    @JacksonXmlProperty(localName = "amount")
    public int amount() {
        return amount;
    }

    @Override
    public Category category() {
        return Category.ITEM_SELECTION;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
