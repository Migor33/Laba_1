package com.person.tools;

import com.person.MyRepository;
import com.person.Person;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.vsu.lab.entities.enums.Gender;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class XmlTool {

    /**
     * write repository in xml by JAXB
     * @param myRepository
     * @throws JAXBException
     */
    public static void writeRepositoryJAXB(MyRepository<Person> myRepository) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MyRepository.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        marshaller.marshal(myRepository,new File("src\\main\\resources\\repoJAXB.xml"));
    }

    /**
     * read repository from xml by JAXB
     * @return
     * @throws JAXBException
     */
    public static MyRepository<Person> readRepositoryJAXB() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MyRepository.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (MyRepository<Person>) unmarshaller.unmarshal(new File("src\\main\\resources\\repoJAXB.xml"));
    }

    /**
     * write repository in xml by DOM
     * @param myRepository
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public static void writeRepositoryDOM(MyRepository<Person> myRepository) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("repository");
        doc.appendChild(rootElement);
        for (int i = 0; i < myRepository.length(); i++) {
            Element person = doc.createElement("person");
            person.setAttribute("firstName",myRepository.get(i).getFirstName());
            person.setAttribute("lastName",myRepository.get(i).getLastName());
            person.setAttribute("middleName",myRepository.get(i).getMiddleName());
            person.setAttribute("birthdate",myRepository.get(i).getBirthdate().toString());
            person.setAttribute("division",myRepository.get(i).getDivision().getName());
            person.setAttribute("gender",myRepository.get(i).getGender().toString());
            person.setAttribute("id",myRepository.get(i).getId().toString());
            person.setAttribute("salary",myRepository.get(i).getSalary().toString());
            rootElement.appendChild(person);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src\\main\\resources\\repoDOM.xml"));
        transformer.transform(source, result);
    }

    /**
     * read repository from xml by DOM
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static MyRepository<Person> readRepositoryDOM() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new File("src\\main\\resources\\repoDOM.xml"));
        NodeList personElements = document.getDocumentElement().getElementsByTagName("person");
        MyRepository<Person> myRepository = new MyRepository<>();
        for (int i = 0; i < personElements.getLength(); i++) {
            Person person = new Person();
            Node p = personElements.item(i);
            NamedNodeMap attributes = p.getAttributes();
            person.setBirthdate(LocalDate.parse(attributes.getNamedItem("birthdate").getNodeValue()));
            person.setDivision(MyRepositoryReader.readDivision(attributes.getNamedItem("division").getNodeValue(),new PersonFactory()));
            person.setFirstName(attributes.getNamedItem("firstName").getNodeValue());
            person.setLastName(attributes.getNamedItem("lastName").getNodeValue());
            person.setMiddleName(attributes.getNamedItem("middleName").getNodeValue());
            person.setGender(Gender.valueOf(attributes.getNamedItem("gender").getNodeValue()));
            person.setId(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()));
            person.setSalary(new BigDecimal(attributes.getNamedItem("salary").getNodeValue()));
            myRepository.add(person);
        }
        return myRepository;
    }
}
