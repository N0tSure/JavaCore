package com.artemsirosh.tij.io.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.artemsirosh.tij.util.TextFile;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created on 24 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class PersonSerializationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonSerializationTest.class);

    @Test
    public void equalsTest() throws Exception {

        Person bunsen = new Person("Dr. Bunsen", "Honeydew");
        LOGGER.info("Original: {}", bunsen);

        assertEquals("Same instance", bunsen, bunsen);
        assertNotEquals("With null value", null, bunsen);
        assertNotEquals("With an object", new Object(), bunsen);

        Person gonzo = new Person("Gonzo", "The Great");
        LOGGER.info("Another: {}", gonzo);

        assertNotEquals("With another", gonzo, bunsen);

        Person same = new Person(bunsen.getFirstName(), bunsen.getLastName());
        LOGGER.info("Same: {}", same);

        assertEquals("Same and one", bunsen, same);
        assertEquals("One and same", same, bunsen);

        assertEquals("Hash codes", bunsen.hashCode(), same.hashCode());
    }

    @Test
    public void serializationTest() throws Exception {
        ObjectMapper xmlMapper = new XmlMapper();

        Person bunsen = new Person("Dr. Bunsen", "Honeydew");
        LOGGER.info("Person: {}", bunsen);

        String personXml = xmlMapper.writeValueAsString(bunsen);

        LOGGER.info("Serialized: {}", personXml);

        Person restored = xmlMapper.readValue(personXml, Person.class);
        LOGGER.info("Restored: {}", restored);
        assertEquals(bunsen, restored);
    }

    @Test
    public void peoplesList() throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter streamWriter = outputFactory.createXMLStreamWriter(stringWriter);

        XmlMapper xmlMapper = new XmlMapper(inputFactory);
        streamWriter.writeStartDocument("UTF-8", "1.0");
        streamWriter.writeStartElement("peoples");

        List<Person> people = Arrays.asList(
                new Person("Dr. Bunsen", "Honeydew"),
                new Person("Gonzo", "The Great"),
                new Person("Phillip J.", "Fry")
        );

        LOGGER.info("People: {}", people);

        for (Person person : people) {
            xmlMapper.writeValue(streamWriter, person);
        }

        streamWriter.writeEndElement();
        streamWriter.writeEndDocument();


        String peopleXml = stringWriter.toString();
        LOGGER.info("Serialized: {}", peopleXml);
        TextFile.write("peoples.xml", peopleXml);
    }
}
