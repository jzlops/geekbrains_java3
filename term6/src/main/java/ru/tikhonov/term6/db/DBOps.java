package ru.tikhonov.term6.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Edit by Tikhonov Sergey
 * Класс с набором методов для негерации базы и таблицы в ней (по ДЗ)
 */
class DBOps {
    private static final String CLEAR_PRODUCT_TABLE = "DELETE FROM Students";
    private static final String FILL_PRODUCT_TABLE = "INSERT INTO Students (Surname, Ball) VALUES (?,?)";
    private static final String INIT_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS Students (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "Surname TEXT NOT NULL, " +
            "Ball Integer NOT NULL)";

    private Connection connection;


    DBOps(Connection connection) {
        this.connection = connection;
    }

    void initTable() {
        try (PreparedStatement ps = this.connection.prepareStatement(INIT_PRODUCT_TABLE)) {
            if (ps.executeUpdate() == 0) {
                clearTable();
            }
            fillTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTable() {
        try (PreparedStatement ps = this.connection.prepareStatement(CLEAR_PRODUCT_TABLE)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTable() {
        try (PreparedStatement ps = this.connection.prepareStatement(FILL_PRODUCT_TABLE)) {
            this.connection.setAutoCommit(false);
            for (int i = 1; i <= 100; i++) {
                ps.setString(1, "Surname " + i);
                ps.setInt(2, i);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
