package ru.tikhonov.term2.main;


import ru.tikhonov.term2.UI.UI;
import ru.tikhonov.term2.db.DBHelper;
import ru.tikhonov.term2.db.ProductTableOps;
import java.sql.Connection;

/**
 * Edit by Tikhonov Sergey
 * Основной класс
 */
public class Main {
    public static void main(String[] args) {
        // Получаем коннект к базе
        DBHelper dbHelper = DBHelper.getInstance();
        Connection connection = dbHelper.getConnection();

        // Создаем класс для операций над таблицей Product
        ProductTableOps ops = new ProductTableOps(connection);

        // Создаем таблицу Product и инициализируем ее данными по умолчанию (из задания)
        ops.initTable();

        // Создаем пользовательский интерфейс и передаем в него класс для работы с таблицей Product
        UI ui = new UI(ops);

        // Вызываем диалог пользовательского интерфейса
        ui.dialog();

        //Завершаем работу с базой
        dbHelper.disconnect();
    }
}
