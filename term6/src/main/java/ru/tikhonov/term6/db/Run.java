package ru.tikhonov.term6.db;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Edit by Tikhonov Sergey
 * Основной класс, вызываем ряд методов для создания базы и таблицы (по ДЗ) чтоб не создавать вручную
 */
public class Run {
    public static void main(String[] args) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();
        Connection connection = dbHelper.getConnection(System.getProperty("user.dir")+"//Junk", "littleDB.db");

        DBOps dbOps = new DBOps(connection);
        dbOps.initTable();

        dbHelper.disconnect();

    }
}
