import java.util.Scanner;

public class CreditCard implements PaymentStrategy {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void pay(double grandTotal){
        // add scanner.nextLine(); to clear '\n' if nextDouble() is called before this

        // Prompt customer to enter valid credentials
        String name;
        while (true) {
            System.out.print("Enter card holder name: ");
            name = scanner.nextLine().trim();

            if (isValidName(name)) {
                break;
            } else {
                System.out.println("Invalid name. Please try again");
            }
        }
        String cardNumber;
        while (true) {
            System.out.print("Enter card number: ");
            cardNumber = scanner.nextLine().trim();

            if (isValidCardNumber(cardNumber)) {
                break;
            } else {
                System.out.println("Invalid card number. Must be 12-16 digits.");
            }
        }

        // Mask card number
        String maskedCardNumber = maskCardNumber(cardNumber);

        // Loading effect
        loadingEffect();

        // Display result
        System.out.println("\n--- Payment Summary ---");
        System.out.println("Payment Method: Credit Card");
        System.out.println("Card Holder: " + name.toUpperCase());
        System.out.println("Card Number: " + maskedCardNumber);
        System.out.printf("Amount Paid: %s%.2f%n", Order.CURRENCY, grandTotal);
        System.out.println("Order confirmed!");
        System.out.println("-----------------------");
    }

    // Name validation
    private boolean isValidName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    // Card number validation
    private boolean isValidCardNumber(String cardNumber) {
        if (cardNumber.length() < 12 || cardNumber.length() > 16) {
            return false;
        }
        for (char c : cardNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Mask card number
    private String maskCardNumber(String cardNumber) {
        String lastFour = cardNumber.substring(cardNumber.length() - 4);
        return "**** **** **** " + lastFour;
    }

    // Loading animation
    private void loadingEffect() {
        System.out.print("Processing payment");

        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(700);
                System.out.print(".");
            }
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println("\nProcessing interrupted.");
        }

        System.out.println();
    }
}
