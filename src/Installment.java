import java.util.Scanner;

public class Installment implements PaymentStrategy {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void pay(double amount) {
        // Prompt customer to choose a valid installment plan
        int months = getValidMonths();

        // Calculate installments
        double rate = getInterestRate(months);
        double totalWithInterest = amount + (amount * rate);
        double monthlyPayment = totalWithInterest / months;

        // Display result
        System.out.println("\n--- Installment Plan ---");
        System.out.println("Duration: " + months + " months");
        System.out.printf("Interest Rate: %.0f%%%n", rate * 100);
        System.out.printf("Grand Total: %s%.2f%n", Order.CURRENCY, totalWithInterest);
        System.out.printf("Monthly Payment: %s%.2f%n", Order.CURRENCY, monthlyPayment);
        System.out.println("Order confirmed!");
        System.out.println("------------------------");
    }

    private int getValidMonths() {
        System.out.println("Available plans: ");
        System.out.println("1. 3 months (2%)");
        System.out.println("2. 6 months (5%)");
        System.out.println("3. 12 months (10%)");

        while (true) {
            System.out.print("Please select a plan (1-3): ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the leftover '\n'
                switch (choice) {
                    case 1: return 3;
                    case 2: return 6;
                    case 3: return 12;
                    default: System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
            }
        }
    }

    private double getInterestRate(int months) {
        switch (months) {
            case 3: return 0.02;
            case 6: return 0.05;
            case 12: return 0.10;
            default: return 0; // should never happen
        }
    }
}
