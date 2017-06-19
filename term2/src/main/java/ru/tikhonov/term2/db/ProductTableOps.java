package ru.tikhonov.term2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Edit by Tikhonov Sergey
 * Класс для работы с таблицей Product
 */
public class ProductTableOps {
    private Connection connection;
    private static final String CLEAR_PRODUCT_TABLE = "DELETE FROM Product";
    private static final String FILL_PRODUCT_TABLE = "INSERT INTO Product (ProdID, Title, Cost) VALUES (?,?,?)";
    private static final String INIT_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS Product (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "ProdID TEXT UNIQUE NOT NULL, " +
            "Title TEXT NOT NULL, " +
            "Cost REAL NOT NULL)";

    public ProductTableOps(Connection connection) {
        this.connection = connection;
    }

    public void initTable() {
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
            for (int i = 1; i <= 10000; i++) {
                ps.setString(1, "Id_Product " + i);
                ps.setString(2, "Product" + i);
                ps.setFloat(3, (i * 10));
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCostByTitle(String title) {
        String result = "Product with this Title is not exists";
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT Cost FROM product where Title = ?")) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = Float.valueOf(rs.getFloat("Cost")).toString();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String setCostByTitle(String title, float newCost) {
        String result = "Product with this Title is not exists";
        try (PreparedStatement ps = this.connection.prepareStatement("UPDATE product SET Cost = ? WHERE Title = ?")) {
            int changeCount;
            ps.setFloat(1, newCost);
            ps.setString(2, title);
            if ((changeCount = ps.executeUpdate()) > 0) {
                result = changeCount + " product was changed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

   public String printProductByCostRange(float beginCost, float endCost) {
        StringBuilder result = new StringBuilder();
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT ProdID, Title, Cost FROM product where Cost > ? AND Cost < ?")) {
            ps.setFloat(1, beginCost);
            ps.setFloat(2, endCost);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.append(rs.getString("ProdID")).append(" ");
                    result.append(rs.getString("Title")).append(" ");
                    result.append(rs.getString("Cost")).append(" ");
                    result.append(System.getProperty("line.separator"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
