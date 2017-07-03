package ru.interview.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.interview.db.JobDirEnt;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * Edit by Tikhonov Sergey
 */
public class XMLOps {
    private Connection connection;

    public void toDataBase(File xmlFile) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            HashSet<JobDirEnt> jdeSet = new HashSet<>();
            NodeList records = extractListNodes(document, "//Record");
            for (int i = 0; i < records.getLength(); i++) {
                Node nodeDepCode = extractSingleNode(records.item(i), "DepCode");
                Node nodeDepJob = extractSingleNode(records.item(i), "DepJob");
                Node nodeDescription = extractSingleNode(records.item(i), "Description");
                JobDirEnt jde = new JobDirEnt();
                jde.setDepCode(nodeDepCode.getTextContent());
                jde.setDepJob(nodeDepJob.getTextContent());
                jde.setDescription(nodeDescription.getTextContent());
                if (!jdeSet.add(jde)) {
                    System.out.println("Error xml file");
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public XMLOps(Connection connection) {
        this.connection = connection;
    }

    public void fromDataBase(File xmlFile) {
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT DepCode, DepJob, Description FROM JobDir");
             ResultSet rs = ps.executeQuery()) {

            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlOut = xmlOutputFactory.createXMLStreamWriter(new BufferedWriter(new FileWriter(xmlFile)));

            xmlOut.writeStartDocument();
            xmlOut.writeCharacters(System.getProperty("line.separator"));
            xmlOut.writeStartElement("JobDir");
            xmlOut.writeCharacters(System.getProperty("line.separator"));
            while (rs.next()) {
                xmlOut.writeStartElement("Record");
                xmlOut.writeCharacters(System.getProperty("line.separator"));
                xmlOut.writeStartElement("DepCode");
                xmlOut.writeCharacters(rs.getString("DepCode"));
                xmlOut.writeEndElement();
                xmlOut.writeCharacters(System.getProperty("line.separator"));
                xmlOut.writeStartElement("DepJob");
                xmlOut.writeCharacters(rs.getString("DepJob"));
                xmlOut.writeEndElement();
                xmlOut.writeCharacters(System.getProperty("line.separator"));
                xmlOut.writeStartElement("Description");
                xmlOut.writeCharacters(rs.getString("Description"));
                xmlOut.writeEndElement();
                xmlOut.writeCharacters(System.getProperty("line.separator"));
                xmlOut.writeEndElement();
                xmlOut.writeCharacters(System.getProperty("line.separator"));
            }
            xmlOut.writeEndElement();
            xmlOut.writeEndDocument();

            xmlOut.close();
        } catch (SQLException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    static private NodeList extractListNodes(Node nodeRoot, String subNodeName) throws XPathExpressionException {
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = xpath.compile(subNodeName);
        return (NodeList) expr.evaluate(nodeRoot, XPathConstants.NODESET);
    }

    static private Node extractSingleNode(Node nodeRoot, String subNodeName) throws XPathExpressionException {
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = xpath.compile(subNodeName);
        return (Node) expr.evaluate(nodeRoot, XPathConstants.NODE);
    }
}

