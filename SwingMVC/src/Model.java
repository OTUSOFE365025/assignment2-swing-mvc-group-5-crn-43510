import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// The Model component for the Cash Register application.
// Manages the product catalog and the current transaction state.
// (Functionally acts as the CashRegister class).

public class Model {
    private Map<String, Product> catalog; // Stores all products for fast UPC lookup
    private List<Product> scannedItems;    // Stores the list of products in the current transaction
    private double subtotal;

    private final String PRODUCT_FILE = "products.txt";

    // Constructor now initializes the cash register state
    public Model() {
        catalog = new HashMap<>();
        scannedItems = new ArrayList<>();
        subtotal = 0.0;
        loadProductCatalog();
    }

    // Default constructor is now empty as Model initializes itself
    public Model(String firstname, String lastname) {
        // NOTE: This constructor is retained to match the original file structure
        // but is functionally useless for the Cash Register.
        this(); // Call the proper initialization
    }

    private void loadProductCatalog() {
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String upc = parts[0].trim();
                        String name = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        catalog.put(upc, new Product(upc, name, price));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing price: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR: Could not read product file: " + PRODUCT_FILE + ". Check file location.");
        }
    }

    public boolean addItemByUPC(String upc) {
        Product product = catalog.get(upc);
        if (product != null) {
            scannedItems.add(product);
            subtotal += product.getPrice();
            return true;
        } else {
            System.out.println("Product not found for UPC: " + upc);
            return false;
        }
    }

    // The old firstname/lastname getters are removed, replaced by transaction state getters

    public List<Product> getScannedItems() {
        return scannedItems;
    }

    public double getRunningSubtotal() {
        return subtotal;
    }

    // All old set/get methods for firstname/lastname are removed as they are irrelevant.
}
