## Custom PC Ordering System

### Overview
This project simulates a real-world online computer store where customers can build and order their own custom personal computers. In today’s market, many users do not want pre-built computers because they have different needs, such as gaming, schoolwork, programming, or content creation. 

This system allows users to start with a basic PC and then customize it by adding components like RAM and storage based on their preferences. For example, a gamer may choose higher RAM and faster storage, while a student may choose a more affordable setup. The system updates the price and description automatically as each component is added, making it clear and interactive for the user. This reflects how real websites allow customers to configure products step by step.

### Key Features
* **Payment Processing:** The project handles multiple payment methods, a key feature in real e-commerce systems. Users can choose between options such as cash, credit card, installment plans, or QR-based payments like PromptPay. Each payment method behaves differently:
  * **Cash:** Checks if the user has enough money and returns change if needed.
  * **Credit Card:** Validates input and masks sensitive information for security.
  * **Installments:** Allows users to spread payments over time with interest, common for expensive products.
  * **PromptPay:** Simulates a QR code payment, widely used in Thailand. 
* **Order Tracking:** After payment is completed, the order moves through realistic stages: *Pending*, *Building*, *Ready*, and *Delivered*. The system updates these stages step by step, helping users understand the progress of their order and improving the overall user experience.
* **Separation of Concerns:** The PC customization, payment processing, and order tracking are handled independently. If a new payment method is added in the future, it can be included without changing the rest of the system. This reflects how real software systems are built to handle future updates and changes efficiently.

Overall, this project demonstrates a practical and realistic application of programming concepts in an e-commerce environment. It is flexible, easy to extend, and closely resembles how modern online shopping platforms work.

---

## Use Case Specification: Customize, Purchase, and Track PC

**Primary Actor:** Customer  
**Secondary Actor:** Payment System (Simulated)  
**System:** Custom PC Builder CLI Application  

### 1. Brief Description
This use case describes the end-to-end process of a Customer using the system to select hardware upgrades for a base PC, review the order summary, process a payment using various strategies, and track the order status through a linear lifecycle.

### 2. Preconditions
* The application is successfully launched and initialized.
* The system is waiting for user input at the main menu.

### 3. Postconditions
* **Success:** A custom PC order is finalized, payment is processed, the order status reaches the "Delivered" state, and the application terminates safely.
* **Failure:** The system identifies invalid input or payment failure and either reprompts the user or terminates without processing.

### 4. Main Success Scenario

| Phase | Step | Action |
| :--- | :---: | :--- |
| **I: Hardware Selection** | 1 | System displays Base PC specs and base price. |
| | 2 | System prompts for RAM upgrade (8GB, 16GB, 32GB, 64GB). |
| | 3 | Customer inputs RAM choice. |
| | 4 | System prompts for Storage Drive type (HDD, SSD, NVMe). |
| | 5 | Customer selects Drive type. |
| | 6 | System prompts for Storage capacity (250GB, 500GB, 1TB, 2TB). |
| | 7 | Customer selects capacity. |
| **II: Order Summary** | 8 | System aggregates hardware components using the Decorator Pattern. |
| | 9 | System displays final specifications and calculated Total Price. |
| **III: Payment** | 10 | System presents payment methods: Cash, Credit Card, Installment, PromptPay. |
| | 11 | Customer selects Credit Card. |
| | 12 | System prompts for cardholder name and card number. |
| | 13 | Customer inputs valid name and card details (12-16 digits). |
| | 14 | System masks the card number, simulates processing, and displays success. |
| **IV: Tracking** | 15 | System initiates the State Pattern lifecycle. |
| | 16 | System transitions order to *Pending*. |
| | 17 | Customer presses `ENTER` to advance through *Building* and *Ready* states. |
| | 18 | System displays final *Delivered* status and exits. |

### 5. Alternative Flows & Extensions

* **5a. Global: Invalid Input Detection**
  * **Condition:** User enters non-numeric text or out-of-range choices.
  * **System Action:** `InputHelper` intercepts the error, displays an "Invalid choice" message, and reprompts the user without crashing the application.
* **5b. Payment: Cash**
  * **Condition:** Customer selects "Cash" at Step 11.
  * **Action:** System prompts for the cash amount. 
  * *Sub-flow (Insufficient Funds):* If the amount < total, system displays "Insufficient funds" and reprompts.
  * **Resolution:** System calculates and displays the change owed.
* **5c. Payment: Installment**
  * **Condition:** Customer selects "Installment" at Step 11.
  * **Action:** System presents plans (3, 6, 12 months) with interest rates (2%, 5%, 10%).
  * **Resolution:** System calculates compounded total and monthly breakdown for user confirmation.
* **5d. Payment: PromptPay (QR Code)**
  * **Condition:** Customer selects "PromptPay" at Step 11.
  * **Action:** System generates a dynamic QR Code in a GUI window.
  * **Resolution:** Execution pauses until the window is closed (simulating a scan), then proceeds to confirmation.
* **5e. Payment: Invalid Credit Card Details**
  * **Condition:** Name contains special characters or card number length is invalid.
  * **System Action:** System displays a specific validation error (e.g., "Must be 12-16 digits") and reprompts for the detail.
