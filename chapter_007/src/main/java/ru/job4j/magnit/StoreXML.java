package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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

    public static void main(String[] args) throws Exception {
        Config con = new Config();
        StoreSQL sq = new StoreSQL(con);
        sq.generate(2);
        sq.load();
        sq.generate(6);
        sq.load();
        String fileName = "chapter_007\\src\\main\\java\\ru\\job4j\\magnit\\test.xml";
        StoreXML st = new StoreXML(new File(fileName));
        st.save(sq.getEntryes());
        ConvertXSQT convert = new ConvertXSQT();
        convert.convert(new File("chapter_007\\src\\main\\java\\ru\\job4j\\magnit\\test.xml"),
                new File("chapter_007\\src\\main\\java\\ru\\job4j\\magnit\\output.xml"),
                new File("chapter_007\\src\\main\\java\\ru\\job4j\\magnit\\schema.xsl")
        );
    }
}

