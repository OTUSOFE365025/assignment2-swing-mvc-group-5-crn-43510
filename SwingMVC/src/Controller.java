// The Controller component for the Cash Register application.
// It receives scan events (UPC codes) and coordinates the Model and View updates.

public class Controller {
    private Model model; // Functionally the CashRegister
    private View view;   // Functionally the Display

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;

        // No initView/initController methods are strictly needed here as the Scanner handles the event source connection externally.
    }

    // This method is the core of the Controller. It is called by the Scanner whenever the "Scan" button is pressed and a UPC code is generated.
    public void scanReceived(String upc) {
        // 1. Tell the Model to add the item, which updates the internal list and subtotal.
        model.addItemByUPC(upc);

        // 2. Tell the View to update its display based on the Model's new state.
        // This ensures the scrollable list and subtotal are refreshed.
        view.update(model.getScannedItems(), model.getRunningSubtotal());
    }

    // All previous methods (initView, initController, saveFirstname, saveLastname,
    // sayHello, sayBye) have been removed as they are not relevant to the Cash Register system.
}
