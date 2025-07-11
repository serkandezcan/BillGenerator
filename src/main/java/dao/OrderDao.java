
package dao;

import db.DbUtil;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void addOrder(Order order) {
        String sql = "INSERT INTO orders (food_code, food_name, quantity, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, order.getFoodCode());
            pstmt.setString(2, order.getFoodName());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setDouble(4, order.getTotal());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteOrder(int orderCode) {
        String sql = "DELETE FROM orders WHERE order_code = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderCode);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_code";

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int orderCode = rs.getInt("order_code");
                String foodCode = rs.getString("food_code");
                String foodName = rs.getString("food_name");
                int quantity = rs.getInt("quantity");
                double total = rs.getDouble("total");

                orders.add(new Order(orderCode, foodCode, foodName, quantity, total));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public double getTotalAmount() {
        String sql = "SELECT SUM(total) AS total_amount FROM orders";

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total_amount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}
