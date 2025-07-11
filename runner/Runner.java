
package runner;

import service.BillServicePostgres;

public class Runner {
    public static void main(String[] args) {
        BillServicePostgres app = new BillServicePostgres();
        app.start();
    }
}
