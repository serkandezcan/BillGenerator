
package model;

public class Order {
    private int orderCode;
    private String foodCode;
    private String foodName;
    private int quantity;
    private double total;

    public Order(int orderCode, String foodCode, String foodName, int quantity, double total) {
        this.orderCode = orderCode;
        this.foodCode = foodCode;
        this.foodName = foodName;
        this.quantity = quantity;
        this.total = total;
    }

    public int getOrderCode() { return orderCode; }
    public String getFoodCode() { return foodCode; }
    public String getFoodName() { return foodName; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return total; }
}
