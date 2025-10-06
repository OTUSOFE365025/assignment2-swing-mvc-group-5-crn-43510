import javax.swing.*;
import java.awt.*;
import java.util.List;

// The View component for the Cash Register (Functionally acts as the Display class).
// Shows a scrollable list of scanned items and the running subtotal.
public class View {
    private JFrame frame;
    private JTextArea itemsArea;
    private JLabel subtotalLabel;

    public View(String title) {
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocation(450, 50);
        frame.setVisible(true);

        // --- CENTER: Scrollable list for items ---
        itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        itemsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(itemsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Scanned Items"));
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- SOUTH: Subtotal display ---
        subtotalLabel = new JLabel("Subtotal: $0.00", SwingConstants.RIGHT);
        subtotalLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        subtotalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(subtotalLabel, BorderLayout.SOUTH);

        // Ensure the frame layout is set
        frame.pack(); // Adjusts window size based on component sizes
    }

    // Updates the View based on the Model's current state.
    public void update(List<Product> items, double subtotal) {
        // 1. Update the scrollable items list
        StringBuilder sb = new StringBuilder();
        // Add a header for clarity
        sb.append(String.format("%-25s %s\n", "PRODUCT", "PRICE"));
        sb.append("-------------------------------------------\n");

        for (Product item : items) {
            // Relies on Product's toString() or manual formatting
            sb.append(item.toString()).append("\n");
        }
        itemsArea.setText(sb.toString());

        // 2. Update the subtotal label
        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", subtotal));
    }

    // --- Accessors for the frame (rest of old accessors are removed) ---

    public JFrame getFrame() {
        return frame;
    }
}
