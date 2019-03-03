package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOG = LogManager.getLogger(SAXExample.class);

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("chapter_007\\src\\main\\java\\ru\\job4j\\magnit\\output.xml"), handler);
        LOG.error("trace message {}", handler.getCount());
    }

    private static class XMLHandler extends DefaultHandler {
        private int count = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                String field = attributes.getValue("field");
                count = count + Integer.parseInt(field);
            }
        }

        public int getCount() {
            return count;
        }
    }
}
