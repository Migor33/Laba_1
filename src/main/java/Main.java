import com.person.MyRepository;
import com.person.Person;
import com.person.tools.MyRepositoryReader;
import com.person.tools.PersonFactory;
import com.person.tools.XmlTool;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException, TransformerException, ParserConfigurationException, SAXException {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        MyRepositoryReader myRepositoryReader = new MyRepositoryReader();
        File file = new File("src\\main\\resources\\persons.csv");
        MyRepository<Person> repository = (MyRepository<Person>) myRepositoryReader.readRepository(new Scanner(file),new PersonFactory());
        XmlTool.writeRepositoryJAXB(repository);
        MyRepository<Person> newRepJAXB = XmlTool.readRepositoryJAXB();
        XmlTool.writeRepositoryDOM(repository);
        MyRepository<Person> newRepDOM = XmlTool.readRepositoryDOM();
        return;
    }
}
