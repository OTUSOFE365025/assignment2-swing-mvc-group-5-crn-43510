// This window emulates the scanning of an item. Every time the button is pressed
// it will send a notification of a UPC code

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scanner {
    // Scanner uses Swing framework to create a UPC code
    private JFrame frame;
    private JPanel scannerPanel;
    private JButton scanButton;

    // MVC CONNECTION: New field to hold a reference to the Controller
    private Controller controller;

    // Data structures for product information
    private List<Product> products = new ArrayList<>();
    private Random random = new Random();
    private final String PRODUCT_FILE = "products.txt"; // Name of the product data file

    public Scanner() {
        // Load product data from file when the scanner is initialized
        loadProductData();

        frame = new JFrame("Scanner");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.setLocation(300,50);
        frame.setVisible(true);

        // Create UI elements
        scanButton = new JButton("Scan");
        scannerPanel = new JPanel();

        // Add UI element to frame
        scannerPanel.add(scanButton);
        frame.getContentPane().add(scannerPanel);
        scanButton.addActionListener(e -> {
            String upc = generateUPC();

            if (controller != null) {
                // Send the UPC to the Controller for processing
                controller.scanReceived(upc);
            } else {
                // Fallback
                System.out.println("Scanner not connected to Controller. UPC: " + upc);
            }
        });
    }

    // Setter to connect the Controller from the main application file
    public void setController(Controller controller) {
        this.controller = controller;
    }

    // Method to read the product data file
    private void loadProductData() {
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String upc = parts[0].trim();
                        String name = parts[1].trim();
                        // Parse the price, assuming the format is always correct
                        double price = Double.parseDouble(parts[2].trim());
                        products.add(new Product(upc, name, price));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping line due to invalid price format: " + line);
                    }
                } else {
                    System.err.println("Skipping line due to incorrect format: " + line);
                }
            }
            if (products.isEmpty()) {
                System.err.println("WARNING: No products loaded from " + PRODUCT_FILE + ". Check file path and format.");
                // Fallback for testing if file is empty/missing
                products.add(new Product("00000", "Default Product", 0.00));
            }
        } catch (IOException e) {
            System.err.println("ERROR: Could not read product file: " + PRODUCT_FILE + ". Ensure the file exists.");
            e.printStackTrace();
            // Fallback for testing if file is missing
            products.add(new Product("00000", "Default Product", 0.00));
        }
    }

    // Modified method to randomly select and return a UPC code (returns String)
    private String generateUPC() {
        if (products.isEmpty()) {
            return "ERROR";
        }
        // Randomly select an index
        int randomIndex = random.nextInt(products.size());

        // Get the Product object and return its UPC code
        Product selectedProduct = products.get(randomIndex);
        return selectedProduct.getUpcCode();
    }

    // Existing getters and setters
    public JFrame getFrame() { return frame; }
    public void setFrame(JFrame frame) { this.frame = frame; }
    public JPanel getScannerPanel() { return scannerPanel; }
    public void setScannerPanel(JPanel scannerPanel) { this.scannerPanel = scannerPanel; }
    public JButton getScanButton() { return scanButton; }
    public void setScanButton(JButton scanButton) { this.scanButton = scanButton; }
}
