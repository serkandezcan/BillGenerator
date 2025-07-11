
package dao;

import db.DbUtil;
import model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM foods ORDER BY category, code";

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String category = rs.getString("category");

                Food food = new Food(code, name, price, category);
                foodList.add(food);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodList;
    }

    public void addFood(Food food) {
        String sql = "INSERT INTO foods (code, name, price, category) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, food.getCode());
            pstmt.setString(2, food.getName());
            pstmt.setDouble(3, food.getPrice());
            pstmt.setString(4, food.getCategory());

            pstmt.executeUpdate();
            System.out.println("Ürün eklendi: " + food.getName());

        } catch (SQLException e) {
            System.out.println("Ürün eklenemedi: " + e.getMessage());
        }
    }
}
