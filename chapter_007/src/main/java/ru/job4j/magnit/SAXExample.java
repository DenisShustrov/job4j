package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Class SAXExample.
 *
 * @author dshustrov
 * @version 1
 * @since 24.02.2019
 */
public class SAXExample {

    private static int count = 0;

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("C:\\sqlite\\db\\output.xml"), handler);
        System.out.println(count);

    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                String field = attributes.getValue("field");
                count = count + Integer.parseInt(field);
            }
        }
    }
}
