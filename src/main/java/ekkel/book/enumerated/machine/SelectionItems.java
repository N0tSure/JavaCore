package ekkel.book.enumerated.machine;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
@JacksonXmlRootElement(localName = "selectionItems")
final class SelectionItems {

    @JacksonXmlElementWrapper
    private final List<Item> items;

    public SelectionItems(@JacksonXmlProperty(localName = "items") List<Item> items) {
        this.items = items;
    }

    List<Item> getItems() {
        return items;
    }
}
