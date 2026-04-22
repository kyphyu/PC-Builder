# PC-Builder 🖥️
ADV COMP PROG (2190103.i) FINAL PROJECT

* **A command-line interface (CLI) application built in Java that simulates a custom PC purchasing system.**

by Group Pursuit of Grade

### Features
* **Hardware Customization:** Users can upgrade RAM and Storage on a base PC.
* **Payment Methods:** Supports Cash, Credit Card (with validation masking), Installments (with interest calculation), and PromptPay (generates a live QR code).
* **Order Tracking:** Simulates the lifecycle of an order from Pending to Delivered.
* **Input Validation:** Prevents application crashes from invalid user inputs.

### Design Patterns Used
1. **Decorator Pattern:** Used to dynamically wrap the `BasePC` with `RAMUpgrade` and `StorageUpgrade` at runtime, automatically calculating the new description and total price.
2. **Strategy Pattern:** Used to handle the `PaymentStrategy`. The `Order` class does not need to know *how* a payment is processed, only that it can call `.pay()`.
3. **State Pattern:** Used to manage the `OrderState` (Pending -> Building -> Ready -> Delivered) without relying on complex if/else chains.
4. **Factory Pattern:** Utilized in `PCBuilder` to handle the step-by-step construction of the decorated PC object.

### Project Documentation
* [Class Diagram](docs/ClassDiagram.md)
* [Use Case Scenario](docs/UseCaseScenario.md)

### 🛠️ How to Run
**External Libraries:** This project uses Google's ZXing library to generate the PromptPay QR code. You must download the following `.jar` files:
  * [ZXing Core 3.5.4](https://mvnrepository.com/artifact/com.google.zxing/core/3.5.4) (Click "jar" under the Files section)
  * [ZXing JavaSE 3.5.4](https://mvnrepository.com/artifact/com.google.zxing/javase/3.5.4) (Click "jar" under the Files section)
1. Clone this repository.
2. Navigate to the `src` directory.
3. Compile the code: `javac Main.java`
4. Run the application: `java Main`
