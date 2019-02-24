package ru.job4j.magnit;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Class ConvertXSQT.
 *
 * @author dshustrov
 * @version 1
 * @since 24.02.2019
 */
public class ConvertXSQT {
    public void convert(File source, File dest, File schema) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(schema);
        Transformer transformer = factory.newTransformer(xslt);
        Source xml = new StreamSource(source);
        transformer.transform(xml, new StreamResult(dest));
    }
}
