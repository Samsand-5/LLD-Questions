import java.util.*;

class Bank {
    private String name;
    private List<String> support;
    
    public Bank(String bankName, String support) {
        this.name = bankName;
        this.support = Arrays.asList(support.split(" "));
    }
    
    public int runTransaction() {
        return new Random().nextInt(2);
    }
    
    public String getName() {
        return name;
    }
}
