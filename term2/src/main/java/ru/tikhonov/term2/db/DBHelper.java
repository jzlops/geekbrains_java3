package ru.tikhonov.term2.db;

import java.io.File;
import java.sql.*;

/**
 * Edit by Tikhonov Sergey
 * Класс доступа к базе данных
 */
public class DBHelper {
    private static final String PREFIX = System.getProperty("user.dir") + "/Junk/";
    private static final String JDBC_CLASS_DRIVER = "org.sqlite.JDBC";
    private static final String JDBC_URL = "jdbc:sqlite:"+PREFIX+"shop.db";
    private Connection connection;
    private static DBHelper dbHelper;

    static {
        File dir = new File(DBHelper.PREFIX);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            Class.forName(DBHelper.JDBC_CLASS_DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
            return dbHelper;
        } else {
            return dbHelper;
        }
    }

    private DBHelper() {
    }

    public Connection getConnection() {
        try {
            this.connection = DriverManager.getConnection(DBHelper.JDBC_URL);
            return this.connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
