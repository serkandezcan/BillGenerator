BillGenerator (Restaurant Fiş Üretme Uygulaması)
Proje Amacı
• Restoran sipariş ve fiş yönetimi uygulaması
• PostgreSQL ile kalıcı veri saklama
• Modüler Java yapısı
Proje Mimarisi
• model → Veri sınıfları (Food, Order)
• dao → Veritabanı işlemleri
• db → Bağlantı ve tablo oluşturma
• service → İş mantığı ve kullanıcı akışı
• runner → Uygulama başlangıcı
Model Sınıfları
• Food.java → Ürün bilgisi
• Order.java → Sipariş bilgisi
DAO Sınıfları
• FoodDao → Menü sorguları
• OrderDao → Sipariş işlemleri
DbUtil ve Service Sınıfı
• DbUtil → Bağlantı ve tablo kurulum
• BillServicePostgres → Kullanıcı menüsü, sipariş yönetimi
Runner.java
• Ana sınıf
• start() metoduyla uygulama başlar
Ana Metotlar
• showMenu() → Menü göster
• createOrder() → Sipariş al
• cancelOrder() → Siparişi iptal et
• printBill() → Fiş yazdır
Geliştirme Fırsatları
• Web veya GUI arayüz
• Kullanıcı girişi ve yetki
• Raporlama ve istatistik
• Testler ve CI/CD
