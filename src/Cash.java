import java.util.Scanner;

public class Cash implements PaymentStrategy {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void pay(double grandTotal) {
        double inputAmount = 0;
        // Prompt customer to enter valid amount
        while (true) {
            System.out.printf("Enter amount: %s", Order.CURRENCY);

            if (scanner.hasNextDouble()) {
                inputAmount = scanner.nextDouble();

                if (inputAmount >= grandTotal) {
                    break;
                } else {
                    System.out.println("Insufficient funds.");
                }

            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
            }
        }

        // Handle change
        double change = inputAmount - grandTotal;

        // Display result
        System.out.println("\n--- Payment Summary ---");
        System.out.println("Payment Method: Cash");
        System.out.printf("Amount Paid: %s%.2f%n", Order.CURRENCY, inputAmount);

        if (change > 0) {
            System.out.printf("Change: %s%.2f%n", Order.CURRENCY, change);
        }

        System.out.println("Order confirmed!");
        System.out.println("-----------------------");
    }
}
