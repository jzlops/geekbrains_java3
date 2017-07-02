package ru.tikhonov.term6.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Edit by Tikhonov Sergey
 * Класс доступа к базе данных
 */
public class DBHelper {
    private static String dbPath = null;
    private static String dbName = null;
    private static final String JDBC_CLASS_DRIVER = "org.sqlite.JDBC";
    private Connection connection;
    private static DBHelper dbHelper;

    static {
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

    public Connection getConnection(String dbPath, String dbName) {
        DBHelper.dbPath = dbPath;
        DBHelper.dbName = dbName;
        File dir = new File(DBHelper.dbPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        if ((DBHelper.dbName != null) && (DBHelper.dbPath != null)) {
            String jdbcURI = "jdbc:sqlite:" + DBHelper.dbPath + "//" + DBHelper.dbName;
            try {
                this.connection = DriverManager.getConnection(jdbcURI);
                return this.connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
