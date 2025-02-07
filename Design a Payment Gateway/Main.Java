public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Program execution has started");
        
        PaymentGateway pg = new PaymentGateway();
        pg.addClient("Flipkart");
        pg.addClient("Amazon");
        pg.showClients();
        
        pg.addSupportedPayModesClient("Flipkart", "UPI");
        pg.addSupportedPayModesClient("Amazon", "Netbanking");
        pg.addSupportedPayModesClient("Amazon", "UPI");
        pg.listSupportedPayModesClient("Flipkart");
        pg.listSupportedPayModesClient("Amazon");
        
        Map<String, Object> details = new HashMap<>();
        details.put("type", "UPI");
        details.put("client_name", "Flipkart");
        details.put("amount", 120);
        details.put("upi_pin", 1231);
        pg.makePayment(details);
        
        pg.viewTransactions();
        
        System.out.println("==========================================================");
        System.out.println("Program Execution took: " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("==========================================================");
    }
}
