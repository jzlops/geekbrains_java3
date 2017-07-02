package ru.tikhonov.term6.db;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Edit by Tikhonov Sergey
 */
public class DBHelperTest {

    private static DBHelper dbHelper;
    private static Connection connection;

    @BeforeClass
    public static void init() throws SQLException {
        dbHelper = DBHelper.getInstance();
        dbHelper.setDBURI("C://!Study//#GeekBrains//Java3//Junk", "littleDB.db");
        connection = dbHelper.getConnection();
        connection.setAutoCommit(false);
    }

    @AfterClass
    public static void close() {
        dbHelper.disconnect();
    }

    @Test
    public void dbReadBall() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Students WHERE Ball > 12 AND Ball < 30");
        ResultSet rs = ps.executeQuery();
        for (int i = 13; i < 29; i++) {
            rs.next();
            Assert.assertEquals(i, rs.getInt("Ball"));
        }
    }

    @Test
    public void dbReadSurname() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Students LIMIT 10");
        ResultSet rs = ps.executeQuery();
        for (int i = 1; i < 11; i++) {
            rs.next();
            Assert.assertEquals("Surname " + i, rs.getString("Surname"));
        }
    }

    @Test
    public void dbInsert() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Students (Surname, Ball) VALUES (?,?)");
        for (int i = 0; i < 10; i++) {
            ps.setString(1, "John " + "i");
            ps.setInt(2, i);
            int result = ps.executeUpdate();
            Assert.assertEquals(1, result);
        }

    }

    @Test
    public void dbUpdate() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE Students SET Ball=? WHERE  Ball > 10 AND Ball < 20");
        ps.setInt(1, 1);
        Assert.assertEquals(9, ps.executeUpdate());
    }

}




