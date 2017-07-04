package ru.interview.db;

import ru.interview.Init;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Edit by Tikhonov Sergey
 * Класс доступа к базе данных
 */
public class DBHelper {
    private static final String JDBC_CLASS_DRIVER = Init.getJDBCClassDriverName();
    private static final String DATA_BASE_URL = Init.getDataBaseURL();
    private Connection connection;
    private static DBHelper dbHelper;

    static {
        try {
            Class.forName(DBHelper.JDBC_CLASS_DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод возвращает объет DBHelper в ед. экземпляре
     *
     * @return - объект DBHelper
     */
    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
            return dbHelper;
        } else {
            return dbHelper;
        }
    }

    /**
     * Приватный конструктор
     */
    private DBHelper() {
    }

    /**
     * Метод возвращает объект соединения к БД
     *
     * @return объект Connection
     */
    public Connection getConnection() {
        try {
            this.connection = DriverManager.getConnection(DATA_BASE_URL);
            return this.connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Закрыает соединение к БД
     */
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
