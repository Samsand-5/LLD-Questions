class Client {
    private String clientName;
    private List<String> paymentModes;
    private int balance;
    
    public Client(String clientName) {
        this.clientName = clientName;
        this.paymentModes = new ArrayList<>();
        this.balance = 0;
    }
    
    public void listSupportedPayModes() {
        System.out.println("Payment modes supported by " + clientName + " are: " + paymentModes);
    }
    
    public void addSupportForPayModes(String mode) {
        paymentModes.add(mode);
        System.out.println("Payment mode " + mode + " added to client: " + clientName);
    }
    
    public void removePayModeForClient(String mode) {
        paymentModes.remove(mode);
        System.out.println("Payment mode " + mode + " removed from client: " + clientName);
    }
    
    public void addAmount(int amount) {
        balance += amount;
    }
    
    public int balanceAmount() {
        return balance;
    }
    
    public boolean supportsPaymentMode(String mode) {
        return paymentModes.contains(mode);
    }
    
    public String getClientName() {
        return clientName;
    }
}
