// Data entity for a single product. Used by the Scanner and the CashRegister Model.
public class Product {
    private String upcCode;
    private String name;
    private double price;

    public Product(String upcCode, String name, double price) {
        this.upcCode = upcCode;
        this.name = name;
        this.price = price;
    }

    public String getUpcCode() { return upcCode; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Override toString for easy display in the View
    @Override
    public String toString() {
        return String.format("%-25s $%.2f", name, price);
    }
}