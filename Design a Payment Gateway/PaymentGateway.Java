class PaymentGateway {
    private Map<String, Client> clients;
    private List<Map<String, Object>> transactions;
    
    public PaymentGateway() {
        clients = new HashMap<>();
        transactions = new ArrayList<>();
    }
    
    public void addClient(String clientName) {
        clients.put(clientName, new Client(clientName));
    }
    
    public void showClients() {
        System.out.println("==============================================");
        System.out.println("Clients of the Payment Gateway");
        System.out.println("==============================================");
        System.out.println(clients.keySet());
        System.out.println("==============================================");
    }
    
    public void removeClient(String clientName) {
        clients.remove(clientName);
    }
    
    public void listSupportedPayModesClient(String clientName) {
        clients.get(clientName).listSupportedPayModes();
    }
    
    public void addSupportedPayModesClient(String clientName, String pyMode) {
        clients.get(clientName).addSupportForPayModes(pyMode);
    }
    
    public void removePayModeForClient(String clientName, String pyMode) {
        clients.get(clientName).removePayModeForClient(pyMode);
    }
    
    public void makePayment(Map<String, Object> details) {
        String pType = (String) details.get("type");
        String clientName = (String) details.get("client_name");
        int amount = (int) details.get("amount");
        
        if (!clients.containsKey(clientName) || !clients.get(clientName).supportsPaymentMode(pType)) {
            System.out.println("Transaction could not be completed because client name or payment mode was invalid");
            return;
        }
        
        String bankName = pType.equals("UPI") ? "SBI" : "HDFC";
        Bank bank = new Bank(bankName, pType);
        int status = bank.runTransaction();
        
        if (status == 1) {
            if (pType.equals("UPI") && details.containsKey("upi_pin")) {
                processTransaction(details, bank.getName());
            } else if (pType.equals("Netbanking") && details.containsKey("user_id") && details.containsKey("password")) {
                processTransaction(details, bank.getName());
            } else {
                System.out.println("Transaction failed due to incorrect details supplied");
            }
        } else {
            System.out.println("Transaction failed due to bank issue");
        }
    }
    
    private void processTransaction(Map<String, Object> details, String bankName) {
        String clientName = (String) details.get("client_name");
        int amount = (int) details.get("amount");
        
        clients.get(clientName).addAmount(amount);
        
        System.out.println("---------------------------------------");
        System.out.println(details.get("type") + " Transaction of amount " + amount + " performed by " + bankName + " for client " + clientName);
        System.out.println("---------------------------------------");
        System.out.println("===========================================");
        System.out.println("Updated balance of client " + clientName + " is: " + clients.get(clientName).balanceAmount());
        System.out.println("===========================================");
        transactions.add(details);
    }
    
    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions performed on the gateway");
        } else {
            for (Map<String, Object> transaction : transactions) {
                System.out.println("--------------------------------------------");
                System.out.println(transaction);
                System.out.println("--------------------------------------------");
            }
        }
    }
}
