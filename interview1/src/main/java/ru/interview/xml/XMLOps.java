package ru.interview.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.interview.db.JobDirEntity;

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
 * Класс имеет 2 публичных метода для работы с БД. И ряд приватных статических метода для работы с XML
 */
public class XMLOps {
    private Connection connection;
    private static XPathFactory pathFactory = XPathFactory.newInstance();
    private static XPath xpath = pathFactory.newXPath();

    /**
     * Конструктор принимает в качетсве параметра объект соединения к БД
     *
     * @param connection - соединение с БД
     */
    public XMLOps(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод извлекает данные из xml файла, создает HashSet объектов для вставки в БД
     *
     * @param xmlFile - XML файл в данными для импорта в БД
     */
    public void fromXMLToDatabase(File xmlFile) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            //Создаем множество представляющих совокупность значений строк и столбцов таблицы JobDir в БД
            HashSet<JobDirEntity> jdeSet = new HashSet<>();
            //получаем лист нод из XML файла с записями Record
            NodeList records = extractListNodes(document, "//Record");
            for (int i = 0; i < records.getLength(); i++) {
                //пробегаем по каждому узлу Record и извлекаем из него ноды представляющие значения полей для  БД
                Node nodeDepCode = extractSingleNode(records.item(i), "DepCode");
                Node nodeDepJob = extractSingleNode(records.item(i), "DepJob");
                Node nodeDescription = extractSingleNode(records.item(i), "Description");

                //создаем и заполняем значениями объект представляющий запись (строка) в таблице JobDit
                JobDirEntity jde = new JobDirEntity();
                jde.setDepCode(nodeDepCode.getTextContent());
                jde.setDepJob(nodeDepJob.getTextContent());
                jde.setDescription(nodeDescription.getTextContent());

                //если в XML файле есть дубликат по полям DepJob и DepCode то выводим в консоль предупреждение
                if (!jdeSet.add(jde)) {
                    System.out.println("XML file has duplicate record on fields: DepCode and DepJob");
                }
            }
            connection.setAutoCommit(false);
            //создаем временну таблицу в базе на основе данных из XML файла (а конкретно на основании нашего HashSet)
            createTempDBFromXML(jdeSet);
            //удаляем лишние данные из таблицы JobDir
            syncDelete();
            //Вставляем строки в таблицу JobDir
            syncInsert();
            //Удаляем временную таблицу
            dropTempDB();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод вставляет дополнительные строки временной таблицы
     */
    private void syncInsert() {
        String insS = "INSERT INTO JobDir (DepCode, DepJob, Description) SELECT * FROM JobDirTemper " +
                "WHERE EXISTS (SELECT * FROM JobDir WHERE " +
                "(JobDir.DepCode = JobDirTemper.DepCode AND JobDir.DepJob = JobDirTemper.DepJob AND JobDir.Description<>JobDirTemper.Description)" +
                "OR (JobDir.DepCode<>JobDirTemper.DepCode) OR (JobDir.DepJob<>JobDirTemper.DepJob));";
        try (PreparedStatement ps = connection.prepareStatement(insS);) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод удаляет из базы JobDir данные отсувтующие во временной таблице
     */
    private void syncDelete() {
        String delS = "DELETE FROM JobDir WHERE NOT EXISTS (SELECT * FROM JobDirTemper WHERE DepCode=JobDir.DepCode OR DepJob=JobDir.DepJob )";
        try (PreparedStatement ps = connection.prepareStatement(delS);) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод удаляет временную таблицу
     */
    private void dropTempDB() {
        String dropS = "DROP TABLE IF EXISTS JobDirTemper";
        try (PreparedStatement ps = connection.prepareStatement(dropS)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает временую таблицу в базе JobDescription
     *
     * @param jdeSet - множество сгенерированное на основании XML файла
     */
    private void createTempDBFromXML(HashSet<JobDirEntity> jdeSet) {
        PreparedStatement ps;
        String sDrop = "DROP TABLE IF EXISTS JobDirTemper";
        String sCreate = "CREATE TABLE JobDirTemper (" +
                "  DepCode     TEXT NOT NULL," +
                "  DepJob      TEXT NOT NULL," +
                "  Description TEXT NOT NULL)";
        String sFill = "INSERT INTO JobDirTemper (DepCode, DepJob, Description) VALUES (?,?,?)";
        try {

            ps = connection.prepareStatement(sDrop);
            ps.executeUpdate();
            ps = connection.prepareStatement(sCreate);
            ps.executeUpdate();
            ps = connection.prepareStatement(sFill);
            for (JobDirEntity entity : jdeSet) {
                ps.setString(1, entity.getDepCode());
                ps.setString(2, entity.getDepJob());
                ps.setString(3, entity.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод импортирует все данные из БД в файл XML
     *
     * @param xmlFile - xml файл для импорта данных из БД
     */

    public void fromDataBaseToXML(File xmlFile) {
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT DepCode, DepJob, Description FROM JobDir");
             ResultSet rs = ps.executeQuery()) {

            //создаем выходной XML поток
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlOut = xmlOutputFactory.createXMLStreamWriter(new BufferedWriter(new FileWriter(xmlFile)));

            //закидываем в него данные (line.separator всталвен для удобвства чтения выходного xml файла)
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

    /**
     * Метод  извлекает лист заданных нод из родительской ноды по передаваемому имени
     *
     * @param nodeRoot    родительская нода
     * @param subNodeName имя искомых нод
     * @return лист искомых нод
     * @throws XPathExpressionException при ошибке парсинга бросаем соотвествующее исключение
     */
    static private NodeList extractListNodes(Node nodeRoot, String subNodeName) throws XPathExpressionException {
        XPathExpression expr = xpath.compile(subNodeName);
        return (NodeList) expr.evaluate(nodeRoot, XPathConstants.NODESET);
    }

    /**
     * Метод  извлекает заданную ноду из родительской ноды по передаваемому имени
     *
     * @param nodeRoot    родительская нода
     * @param subNodeName имя искомой ноды
     * @return искомая нода
     * @throws XPathExpressionException при ошибке парсинга бросаем соотвествующее исключение
     */
    static private Node extractSingleNode(Node nodeRoot, String subNodeName) throws XPathExpressionException {
        XPathExpression expr = xpath.compile(subNodeName);
        return (Node) expr.evaluate(nodeRoot, XPathConstants.NODE);
    }
}

