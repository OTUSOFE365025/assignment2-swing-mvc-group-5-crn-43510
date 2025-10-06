import javax.swing.SwingUtilities;

public class MySwingMVCApp {

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety for GUI components
        SwingUtilities.invokeLater(() -> {
            // 1. Create the Model (The CashRegister logic)
            // The default constructor is used as the Model now initializes itself
            // by loading products.txt.
            Model model = new Model();

            // 2. Create the View (The Display)
            // The View constructor initializes the display window.
            View view = new View("Cash Register Display");

            // 3. Create the Controller
            Controller controller = new Controller(model, view);

            // 4. Create the Scanner (The Event Source)
            Scanner scanner = new Scanner();

            // 5. Connect the Scanner (Source) to the Controller
            // This tells the Scanner where to send the UPC code when the button is pressed.
            scanner.setController(controller);

            // 6. Initialize the View with the starting state (empty transaction)
            // This ensures the display is correctly set to $0.00 and an empty list.
            view.update(model.getScannedItems(), model.getRunningSubtotal());

            // The c.initController() call from the original file is no longer needed
            // as the Cash Register Controller handles events directly from the Scanner.
        });
    }
}
