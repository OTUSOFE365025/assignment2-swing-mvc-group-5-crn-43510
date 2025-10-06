# Assignment 2: Architectural MVC Pattern

**Group Number:** 5

---

## 👥 Group Members & Contributions

| Name | Student ID | Contribution |
| :--- | :--- | :--- |
| **Eesha Razia** | 100930229 | Deliverables 1 and 2 |
| **Sayyeda Faruqui** | 100912573 | Deliverables 3 and 4 |

---

## Deliverable 1: Investigation into the Swing Framework

### Description of the Purpose of the Swing Framework

The Java Swing framework is part of Java’s Foundation Classes (JFC) and is used to create graphical user interfaces (GUIs) for desktop applications. It’s a lightweight, platform-independent toolkit that builds on the older Abstract Window Toolkit (AWT), offering more flexibility and features.With Swing, developers can create interactive interfaces using components like buttons, text fields, labels, tables, and panels. It’s based on the Model-View-Controller (MVC) architecture.The model manages data, the view displays the interface, and the controller responds to user actions.Swing applications are event-driven, meaning they react to user inputs such as clicks and key presses. The framework also supports layout managers to organize components easily and includes a “pluggable look-and-feel” system, allowing the same program to adapt its visual style across different operating systems. In short, Swing provides developers with the tools to build rich, responsive, and customizable desktop applications in Java.


### Components of Swing Class Diagram

<img width="1295" height="873" alt="image" src="https://github.com/user-attachments/assets/f18bc16c-b7c8-45f8-b2d1-986681c8f269" />


**Figure 1.1: Components of Class Diagram (created using PlantUML)**

#### Explanation of Class Diagram:

In Swing, JComponent is the parent class for all user interface elements; things like buttons, labels, and text fields are all built on top of it.
Swing uses what’s called a delegation event model, which means that whenever something happens (like a button being clicked or text being entered), special objects called event listeners handle those actions.
Behind the scenes, Swing also follows the Model-View-Controller (MVC) pattern to keep everything organized:
The Model stores and manages the data (for example, ButtonModel or TextModel).
The View takes care of how things look on the screen.
The Controller handles what happens when users interact with the interface.
This setup helps separate the logic, appearance, and behavior of components, making Swing applications easier to build and maintain.

---

## Deliverable 2: MVC Implementation in Example Code

### How the Example Implements MVC

Model.java stores a single piece of data, a person’s name  and provides simple methods like setName() and getName() to update or retrieve it.
View.java handles everything the user sees. It includes GUI elements such as a text field for input and a label to display messages, along with methods to update what’s shown on the screen.
Controller.java acts as the middleman. It listens for user actions  for example, when the user clicks a “Display” button  then takes the input from the view, updates the model with model.setName(), and refreshes the view using view.displayName(model.getName()).
In short, when the user types a name and clicks “Display,” the controller processes that action, updates the stored name in the model, and tells the view to show the updated name — creating a smooth flow between data, logic, and interface.


### Difference from Conventional MVC Taught in Lectures

In traditional MVC architecture, the controller acts as a strict middleman between the model and the view meaning the model and view never directly interact with each other. However, in Swing’s version of MVC, things work a little differently:
The view often takes on some controller-like responsibilities by registering its own event listeners (for example, reacting to button clicks or text input).


The controller can even be built directly into the view or combined with the event-handling logic.
Because of this, Swing’s approach is sometimes called the Model–Delegate pattern, a more flexible variation of MVC where the line between the view and controller is not as rigid. This design makes it easier to handle user interactions directly within the components themselves.


---

## Deliverable 3: Modified Scanner & Cash Register MVC Design

The provided GitHub files were refactored to implement a Cash Register application.

### Design Changes

Model.java was completely refactored to handle cash register logic, including loading the product catalog (in a text file called products.txt), maintaining a list of scanned items, and calculating the running subtotal via the addItemByUPC() method.

View.java was refactored to create the Display GUI, featuring a scrollable list of items (JTextArea in JScrollPane) and a JLabel for the running subtotal, along with a crucial update() method to reflect the Model's state.

Controller.java was stripped of old name-handling methods and updated to contain the core scanReceived(upc) logic, which is responsible for telling the Model to process the scan and instructing the View to update the display.

Scanner.java was modified to randomly select UPCs from the loaded product data and uses a new setController() method to send the generated UPC code directly to the Controller's scanReceived() method upon a button press.

MySwingMVCApp.java was updated to correctly instantiate and link the Model, View, Controller, and Scanner components, ensuring the application starts, connects the event source (Scanner), and initializes the display.


### Screenshots of the Output
<img width="951" height="633" alt="image" src="https://github.com/user-attachments/assets/ca3c3ec5-45a6-4bf2-a52f-2c805c520f93" />

**Figure 1.2: Initial Output After Implementing Deliverable 3**

Each click of the “Scan” button adds items to the scrollable list and automatically updates the subtotal.

<img width="1313" height="825" alt="image" src="https://github.com/user-attachments/assets/712e2e3c-3880-4b3d-9dff-ae0d9d4d920b" />

**Figure 1.3: Snapshot of the output of calculated subtotals and purchased items when the user clicks “Scan” 12 times**

---

## Deliverable 4: Sequence Diagram for One Scan Event

This diagram illustrates the sequence of interactions between components for a single press of the "Scan" button.

<img width="1235" height="893" alt="image" src="https://github.com/user-attachments/assets/2468ae45-0921-495e-989b-0227a9ccfbee" />

**Figure 1.4: Single Scan Button Press Sequence Diagram (created using PlantUML)**

1. The user presses the button on the Scanner GUI.
2. The Scanner internally calls generateUPC(), which selects a random UPC code from the product list.
3. The Scanner calls the Controller's scanReceived(upcCode) method (the connection established via scanner.setController().
4. The Controller instructs the Model to update its state by calling addItemByUPC().
5. The Model handles the product lookup, adds the item to the transaction, and updates the subtotal.
6. The Controller retrieves the updated state data (the list of items and the new subtotal) from the Model.
7. The Controller calls the View's update() method, passing the new state data.
8.  The **View** refreshes the GUI, showing the newly added item and the updated subtotal.

