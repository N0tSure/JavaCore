package com.artemsirosh.tij.enumerated.machine;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class ItemDeserializationTest {

    @Test
    void shouldDeserializeItemProperly() throws IOException {
        Path path = Paths.get("./src/main/resources/items.xml");
        assumeTrue(Files.exists(path));

        XmlMapper objectMapper = new XmlMapper();
        SelectionItems wrapper = objectMapper.readValue(Files.newInputStream(path), SelectionItems.class);
        Item chips = wrapper.getItems().get(0);

        assertEquals("Chips", chips.getName());
        assertEquals(100, chips.amount());
    }
}
