
package service;

import dao.FoodDao;
import dao.OrderDao;
import db.DbUtil;
import model.Food;
import model.Order;

import java.util.List;
import java.util.Scanner;

public class BillServicePostgres {

    private final FoodDao foodDao = new FoodDao();
    private final OrderDao orderDao = new OrderDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        DbUtil.initializeDatabase();

        while (true) {
            System.out.println("""
                \n📋 Lütfen bir seçim yapın:
                1 - Menü Göster
                2 - Sipariş Oluştur
                3 - Sipariş İptal Et
                4 - Hesap Fişi Yazdır
                5 - Çıkış
                """);
            String secim = scanner.nextLine();

            switch (secim) {
                case "1" -> showMenu();
                case "2" -> createOrder();
                case "3" -> cancelOrder();
                case "4" -> printBill();
                case "5" -> {
                    System.out.println("Uygulama sonlandırıldı.");
                    return;
                }
                default -> System.out.println("Geçersiz giriş!");
            }
        }
    }

    private void showMenu() {
        List<Food> foodList = foodDao.getAllFoods();
        if (foodList.isEmpty()) {
            System.out.println("📭 Menüde ürün bulunamadı.");
            return;
        }

        System.out.println("📋 Menü:");
        foodList.stream()
                .collect(java.util.stream.Collectors.groupingBy(Food::getCategory))
                .forEach((cat, foods) -> {
                    System.out.println("\n📂 " + cat.toUpperCase());
                    foods.forEach(System.out::println);
                });
    }

    private void createOrder() {
        System.out.print("Ürün kodunu girin: ");
        String code = scanner.nextLine();

        List<Food> foodList = foodDao.getAllFoods();
        Food selected = foodList.stream()
                .filter(f -> f.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (selected == null) {
            System.out.println("❌ Ürün bulunamadı.");
            return;
        }

        System.out.print("Adet: ");
        int adet;
        try {
            adet = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Geçersiz sayı girdiniz.");
            return;
        }

        double total = selected.getPrice() * adet;
        Order order = new Order(0, selected.getCode(), selected.getName(), adet, total);
        orderDao.addOrder(order);
    }

    private void cancelOrder() {
        System.out.print("İptal edilecek sipariş kodunu girin: ");
        int kod = Integer.parseInt(scanner.nextLine());

        boolean result = orderDao.deleteOrder(kod);
        if (result) {
            System.out.println("✅ Sipariş iptal edildi.");
        } else {
            System.out.println("❌ Sipariş bulunamadı.");
        }
    }

    private void printBill() {
        List<Order> orders = orderDao.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("📭 Sipariş bulunamadı.");
            return;
        }

        System.out.println("\n🧾 Hesap Fişi:");
        orders.forEach(order -> {
            System.out.printf("- %s x%d → %.2f₺\n",
                    order.getFoodName(), order.getQuantity(), order.getTotal());
        });

        System.out.printf("💰 Toplam Tutar: %.2f₺\n", orderDao.getTotalAmount());
    }
}
