package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import java.io.File;

/**
 * Class StoreXML.
 *
 * @author dshustrov
 * @version 1
 * @since 24.02.2019
 */
public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(Entryes entryes) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entryes.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(entryes, target);
    }

    public static void main(String[] args) throws JAXBException, TransformerException {
        Config con = new Config();
        StoreSQL sq = new StoreSQL(con);
        sq.generate(2);
        sq.load();
        sq.generate(3);
        sq.load();
        String fileName = "C:\\sqlite\\db\\test.xml";
        StoreXML st = new StoreXML(new File(fileName));
        st.save(sq.getEntryes());
        ConvertXSQT convert = new ConvertXSQT();
        convert.convert(new File("C:\\sqlite\\db\\test.xml"),
                new File("C:\\sqlite\\db\\output.xml"),
                new File("C:\\sqlite\\db\\schema.xsl")
        );
    }
}

