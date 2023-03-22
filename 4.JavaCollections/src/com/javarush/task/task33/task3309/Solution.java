package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, stringWriter);

        String xml = stringWriter.toString();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));

        NodeList nodeList = document.getElementsByTagName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node item = nodeList.item(i);
            Comment documentComment = document.createComment(comment);
            documentComment.normalize();
            item.getParentNode().insertBefore(documentComment, item);

        }
        StringWriter stringWriter2 = new StringWriter();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter2));

        return stringWriter2.toString();
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException, JAXBException {
        System.out.println(toXmlWithComment(new First(), "second", "it's a comment"));
    }

    //object for marshalling to XML
    @XmlType(name = "First")
    @XmlRootElement
    public static class First {
        @XmlElement
        public static List<String> second = new ArrayList<>();

        public First() {
            second.add("Data-1");
            second.add("Data-2");
            second.add("Data-3");
            second.add("");
        }
    }
}
