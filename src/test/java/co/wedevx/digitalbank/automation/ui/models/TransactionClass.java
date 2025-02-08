package co.wedevx.digitalbank.automation.ui.models;


public class TransactionClass {
    private String date;
    private String category;
    private String description;
    private Double amount;
    private  Double balance;

    public TransactionClass(String date, String category, String description, Double amount, Double balance) {

        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

