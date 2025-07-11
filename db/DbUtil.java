
package db;

import java.sql.*;

public class DbUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurant_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String foodTable = "CREATE TABLE IF NOT EXISTS foods (code TEXT PRIMARY KEY, name TEXT NOT NULL, price DOUBLE PRECISION NOT NULL, category TEXT NOT NULL);";
            String orderTable = "CREATE TABLE IF NOT EXISTS orders (order_code SERIAL PRIMARY KEY, food_code TEXT REFERENCES foods(code), food_name TEXT NOT NULL, quantity INT NOT NULL, total DOUBLE PRECISION NOT NULL);";
            stmt.execute(foodTable);
            stmt.execute(orderTable);
            System.out.println("Veritabanı başarıyla hazırlandı.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
