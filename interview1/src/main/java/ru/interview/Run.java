package ru.interview;

import ru.interview.db.DBHelper;
import ru.interview.xml.XMLOps;

import java.io.File;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) {
        DBHelper dbHelper = DBHelper.getInstance();
        XMLOps xmlOps = new XMLOps(dbHelper.getConnection());
        xmlOps.fromDataBaseToXML(new File("c://!Study//#GeekBrains//Java3//interview1//interviewXML/fromDB.xml"));
        xmlOps.fromXMLToDatabase(new File("c://!Study//#GeekBrains//Java3//interview1//interviewXML/toDB.xml"));
        dbHelper.disconnect();
    }
}
